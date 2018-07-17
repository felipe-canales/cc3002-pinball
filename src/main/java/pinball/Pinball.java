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
        Entity walls = newBorderWalls();
        Entity upperCorner = newWall(550, -50, 100, 100, 45.0);
        Entity leftFlipperWall = newWall(-120, 330, 300, 300, 30);
        Entity rightFlipperWall = newWall(420, 330, 300, 300, -30);
        Entity lFlipper = newLeftFlipper();
        Entity rFlipper = newRightFlipper();
        Entity ball = newBall();
        lFlipper.setRotation(20);

        getGameWorld().addEntities(bg, lFlipper, rFlipper, ball, walls, upperCorner,
                leftFlipperWall, rightFlipperWall);
    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("Left flipper activated") {
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

        input.addAction(new UserAction("Right flipper activated") {
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(EntityType.RIGHTFLIPPER)
                        .forEach(e -> {
                            if (e.getRotation() < 40)
                                e.rotateBy(20);
                        });
            }

            @Override
            protected void onActionEnd() {
                getGameWorld().getEntitiesByType(EntityType.RIGHTFLIPPER)
                        .forEach(e -> e.setRotation(-20));
            }
        }, KeyCode.S);
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 300);

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
                        ball.getComponent(PhysicsComponent.class).setVelocityY(
                                -ball.getComponent(PhysicsComponent.class).getVelocityY());
                    }
                }
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}