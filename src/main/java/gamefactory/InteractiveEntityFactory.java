package gamefactory;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import entitytype.EntityType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class InteractiveEntityFactory {
    public static Entity newLeftFlipper() {
        Entity flipper =  Entities.builder()
                .at(180, 410)
                .type(EntityType.LEFTFLIPPER)
                .viewFromNodeWithBBox(new Rectangle(100, 25, Color.BLACK))
                .with(new CollidableComponent(true))
                .build();

        flipper.setRotation(20);
        return flipper;
    }

    public static Entity newRightFlipper() {
        Entity flipper = Entities.builder()
                .at(320, 410)
                .type(EntityType.RIGHTFLIPPER)
                .viewFromNodeWithBBox(new Rectangle(100, 25, Color.BLACK))
                .with(new CollidableComponent(true))
                .build();

        flipper.setRotation(-20);
        return flipper;
    }

    public static Entity newBall() {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f));
        physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(0, -5 * 200));

        return Entities.builder()
                .at(550, 300)
                .type(EntityType.BALL)
                .bbox(new HitBox("Ball", BoundingShape.circle(10)))
                .viewFromNode(new Circle(10, Color.LIGHTCYAN))
                .with(physics, new CollidableComponent(true))
                .build();
    }
}
