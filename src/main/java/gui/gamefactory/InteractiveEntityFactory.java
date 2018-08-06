package gui.gamefactory;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import gui.component.hittablecomponent.BumperComponent;
import gui.component.FlipperComponent;
import gui.component.hittablecomponent.HittableComponent;
import gui.component.hittablecomponent.TargetComponent;
import gui.entitytype.EntityType;
import gui.entitytype.FlipperType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.gameelements.Hittable;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import visitor.ShapePicker;

/**
 * Class with the purpose of creating balls, and Entities that interact with the ball.
 *
 * @author Felipe Canales
 */
public class InteractiveEntityFactory {

    /**
     * Creates a new left flipper.
     *
     * @return an Entity representing a left flipper.
     */
    public static Entity newLeftFlipper() {
        return newFlipper(180, 450, new FlipperComponent(FlipperType.LEFTFLIPPER), 20);
    }

    /**
     * Creates a new right flipper.
     *
     * @return an Entity representing a right flipper.
     */
    public static Entity newRightFlipper() {
        return newFlipper(320, 450, new FlipperComponent(FlipperType.RIGHTFLIPPER), -20);
    }

    private static Entity newFlipper(int x, int y, FlipperComponent fc, double angle) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        physics.setOnPhysicsInitialized(fc::resetRotation);

        return Entities.builder()
                .at(x, y)
                .type(EntityType.FLIPPER)
                .viewFromNodeWithBBox(new Rectangle(100, 25, Color.BLACK))
                .with(fc, physics)
                .build();
    }

    /**
     * Creates a new ball.
     *
     * @return an Entity that behaves like a ball.
     */
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

    /**
     * Creates a Bumper Entity.
     *
     * @param x horizontal position.
     * @param y vertical position.
     * @param b Instance of Bumper.
     * @return an Entity representing a Bumper.
     */
    public static Entity newBumper(int x, int y, Bumper b) {
        return newHittable(x, y, b, new BumperComponent(b), EntityType.BUMPER);
    }

    /**
     * Creates a Target Entity.
     *
     * @param x horizontal position.
     * @param y vertical position.
     * @param t Instance of Target.
     * @return an Entity representing a Bumper.
     */
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
