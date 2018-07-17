package pinball;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;

import entitytype.EntityType;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

import static gamefactory.StaticElementFactory.*;
import static gamefactory.InteractiveEntityFactory.*;

public class Pinball extends GameApplication {

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(600);
        gameSettings.setHeight(600);
        gameSettings.setTitle("Game Window");
        gameSettings.setVersion("0.2");
    }

    @Override
    protected void initGame() {
        Entity bg = newBackground();
        Entity lFlipper = newFlipper();
        Entity ball = newBall();
        Entity walls = newBorderWalls();
        lFlipper.setRotation(20);

        getGameWorld().addEntities(bg, lFlipper, ball, walls);
    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("Flipper activated") {
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(EntityType.LEFTFLIPPER)
                        .forEach(e -> {
                            if (e.getRotation() > - 40)
                                e.rotateBy(-20);
                        });
            }

            @Override
            protected void onActionEnd() {
                getGameWorld().getEntitiesByType(EntityType.LEFTFLIPPER)
                        .forEach(e -> e.setRotation(20));
            }
        }, KeyCode.A);
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 200);

        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(EntityType.BALL, EntityType.WALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity wall, HitBox boxBall, HitBox boxWall) {
                        if (boxWall.getName().equals("BOT"))
                            ball.removeFromWorld();
                    }
                }
        );

        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(EntityType.BALL, EntityType.LEFTFLIPPER) {
                    @Override
                    protected void onCollisionBegin(Entity ball, Entity flipper) {
                        ball.getComponent(PhysicsComponent.class).applyForceToCenter(new Point2D(0, -500));
                    }
                }
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
