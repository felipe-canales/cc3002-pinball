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
import facade.HomeworkTwoFacade;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

import static gamefactory.StaticElementFactory.*;
import static gamefactory.InteractiveEntityFactory.*;

public class Pinball extends GameApplication {
    HomeworkTwoFacade game;

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
        Entity leftFlipperWall = newWall(-120, 370, 300, 300, 30);
        Entity rightFlipperWall = newWall(420, 370, 300, 300, -30);
        Entity lFlipper = newLeftFlipper();
        Entity rFlipper = newRightFlipper();

        game = new HomeworkTwoFacade();

        getGameWorld().addEntities(bg, lFlipper, rFlipper,
                walls, upperCorner, leftFlipperWall, rightFlipperWall);
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

        input.addAction(new UserAction("New ball") {
            @Override
            protected void onActionBegin() {
                if (getGameWorld().getEntitiesByType(EntityType.BALL).size() == 0)
                    getGameWorld().addEntity(newBall());
            }
        }, KeyCode.SPACE);
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
                    protected void onCollision(Entity ball, Entity flipper) {
                        ball.getComponent(PhysicsComponent.class).applyForceToCenter(new Point2D(10,-200));
                    }
                }
        );

        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(EntityType.BALL, EntityType.RIGHTFLIPPER) {
                    @Override
                    protected void onCollision(Entity ball, Entity flipper) {
                        PhysicsComponent ballPhysics = ball.getComponent(PhysicsComponent.class);
                        //ballPhysics.setLinearVelocity(0, 0);
                        ballPhysics.applyForceToCenter(new Point2D(-10,-200));
                    }
                }
        );
    }

    private void setNewTable() {
        //game.newFullPlayableTable()
    }

    public static void main(String[] args) {
        launch(args);
    }
}