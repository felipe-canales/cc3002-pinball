package gamefactory;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import component.hittablecomponent.BumperComponent;
import component.FlipperComponent;
import component.hittablecomponent.HittableComponent;
import component.hittablecomponent.TargetComponent;
import entitytype.EntityType;
import entitytype.FlipperType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.gameelements.Hittable;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import visitor.ShapePicker;

public class InteractiveEntityFactory {
    public static Entity newLeftFlipper() {
        return newFlipper(180, 450, new FlipperComponent(FlipperType.LEFTFLIPPER), 20);
        /*PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        Entity flipper =  Entities.builder()
                .at(180, 450)
                .type(EntityType.FLIPPER)
                .viewFromNodeWithBBox(new Rectangle(100, 25, Color.BLACK))
                .with(new CollidableComponent(true), new FlipperComponent(FlipperType.LEFTFLIPPER))
                .build();

        flipper.setRotation(20);
        flipper.addComponent(physics);
        return flipper;*/
    }

    public static Entity newRightFlipper() {
        return newFlipper(320, 450, new FlipperComponent(FlipperType.RIGHTFLIPPER), -20);
        /*Entity flipper = Entities.builder()
                .at(320, 450)
                .type(EntityType.FLIPPER)
                .viewFromNodeWithBBox(new Rectangle(100, 25, Color.BLACK))
                .with(new CollidableComponent(true))
                .build();

        flipper.setRotation(-20);
        return flipper;*/
    }

    private static Entity newFlipper(int x, int y, FlipperComponent fc, double angle) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        Entity flipper =  Entities.builder()
                .at(x, y)
                .type(EntityType.FLIPPER)
                .viewFromNodeWithBBox(new Rectangle(100, 25, Color.BLACK))
                .with(new CollidableComponent(true), fc)
                .build();

        flipper.setRotation(angle);
        flipper.addComponent(physics);
        return flipper;
    }

    public static Entity newBall() {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(0.9f).density(0.1f));
        physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(0, -5 * 200));

        return Entities.builder()
                .at(550, 300)
                .type(EntityType.BALL)
                .bbox(new HitBox("Ball", BoundingShape.circle(10)))
                .viewFromNode(new Circle(10, Color.LIGHTCYAN))
                .with(physics, new CollidableComponent(true))
                .build();
    }

    public static Entity newBumper(int x, int y, Bumper b) {
        return newHittable(x, y, b, new BumperComponent(b), EntityType.BUMPER);
    }

    public static Entity newTarget(int x, int y, Target t) {
        return newHittable(x, y, t, new TargetComponent(t), EntityType.TARGET);
    }

    private static Entity newHittable(int x, int y, Hittable h, HittableComponent hc, EntityType type) {
        return Entities.builder()
                .at(x, y)
                .type(type)
                .viewFromNodeWithBBox(new ShapePicker(h).getShape())
                .with(new PhysicsComponent(), new CollidableComponent(true), (Component) hc)
                .build();
    }
}
