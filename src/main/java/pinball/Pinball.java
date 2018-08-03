package pinball;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import component.hittablecomponent.BumperComponent;
import component.FlipperComponent;
import component.hittablecomponent.HittableComponent;
import component.hittablecomponent.TargetComponent;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import entitytype.EntityType;
import facade.HomeworkTwoFacade;
import logic.table.Table;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import visitor.HittableSoundPlayer;
import visitor.ResetTime;

import java.util.Map;
import java.util.Random;

import static gamefactory.StaticElementFactory.*;
import static gamefactory.InteractiveEntityFactory.*;

public class Pinball extends GameApplication {
    private HomeworkTwoFacade game;

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
        setNewTable();

        getGameWorld().addEntities(bg, lFlipper, rFlipper,
                walls, upperCorner, leftFlipperWall, rightFlipperWall);
    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("Left flipper activated") {
            @Override
            protected void onActionBegin() {
                getGameWorld().getEntitiesByType(EntityType.FLIPPER)
                        .forEach(e -> e.getComponent(FlipperComponent.class).startRotation());
            }

            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(EntityType.FLIPPER)
                        .forEach(e -> {
                            if (e.getRotation() < -50 || e.getRotation() > 50)
                                e.getComponent(FlipperComponent.class).stopRotation();
                        });
            }

            @Override
            protected void onActionEnd() {
                getGameWorld().getEntitiesByType(EntityType.FLIPPER)
                        .forEach(e -> {
                            FlipperComponent flipper = e.getComponent(FlipperComponent.class);
                            flipper.stopRotation();
                            flipper.resetRotation();
                        });
            }
        }, KeyCode.A);
        /*input.addAction(new UserAction("Right flipper activated") {
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
        }, KeyCode.S);*/
        input.addAction(new UserAction("New ball") {
            @Override
            protected void onActionBegin() {
                if (getGameWorld().getEntitiesByType(EntityType.BALL).size() == 0
                        && !game.gameOver())
                    getGameWorld().addEntity(newBall());
            }
        }, KeyCode.SPACE);
        input.addAction(new UserAction("New table") {
            @Override
            protected void onActionBegin() {
                getGameWorld().getEntitiesByType(EntityType.BUMPER).forEach(e -> {
                    e.getComponent(BumperComponent.class).expireTimerAction();
                    e.removeFromWorld();
                });
                getGameWorld().getEntitiesByType(EntityType.TARGET).forEach(e -> {
                    e.getComponent(TargetComponent.class).expireTimerAction();
                    e.removeFromWorld();
                });
                getGameWorld().getEntitiesByType(EntityType.BALL).forEach(e -> e.removeFromWorld());
                setNewTable();
            }
        }, KeyCode.N);
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 300);

        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(EntityType.BALL, EntityType.WALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity wall, HitBox boxBall, HitBox boxWall) {
                        if (boxWall.getName().equals("BOT")) {
                            ball.removeComponent(PhysicsComponent.class);
                            ball.removeFromWorld();
                            game.dropBall();
                            updateUI();
                        }
                    }
                }
        );
        /*getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(EntityType.BALL, EntityType.LEFTFLIPPER) {
                    @Override
                    protected void onCollision(Entity ball, Entity flipper) {
                        ball.getComponent(PhysicsComponent.class).applyForceToCenter(new Point2D(10, -200));
                    }
                }
        );
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(EntityType.BALL, EntityType.RIGHTFLIPPER) {
                    @Override
                    protected void onCollision(Entity ball, Entity flipper) {
                        ball.getComponent(PhysicsComponent.class).applyForceToCenter(new Point2D(-10, -200));
                    }
                }
        );*/
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(EntityType.BALL, EntityType.BUMPER) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity bumper, HitBox boxBall, HitBox boxBumper) {
                        BumperComponent bumperState = bumper.getComponent(BumperComponent.class);
                        boolean state = bumperState.isAlternateState();

                        bumperState.hit();
                        getAudioPlayer().playSound(new HittableSoundPlayer(bumperState).getSound());

                        if (state != bumperState.isAlternateState()) {
                            bumperState.changeView();
                            resetTimer(bumperState);
                        }
                        updateUI();
                    }
                }
        );
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(EntityType.BALL, EntityType.TARGET) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity target, HitBox boxBall, HitBox boxTarget) {
                        TargetComponent targetState = target.getComponent(TargetComponent.class);
                        int timesTriggered = game.getDropTargetBonus().timesTriggered();

                        getAudioPlayer().playSound(new HittableSoundPlayer(targetState).getSound());
                        targetState.hit();
                        targetState.changeView();

                        if (game.getDropTargetBonus().timesTriggered() > timesTriggered)
                            updateAllElements();
                        else resetTimer(targetState);
                        updateUI();
                    }
                }
        );
    }

    private void updateUI() {
        getGameState().intProperty("balls").setValue(game.getAvailableBalls());
        getGameState().intProperty("score").setValue(game.getCurrentScore());
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("balls", 0);
        vars.put("score", 0);
    }

    @Override
    protected void initUI() {
        Font font = new Font(30);
        Text textBalls = new Text();
        Text textScore = new Text();
        textBalls.setFont(font);
        textScore.setFont(font);
        textBalls.setTranslateX(100);
        textBalls.setTranslateY(550);
        textScore.setTranslateX(500);
        textScore.setTranslateY(550);

        textBalls.textProperty().bind(getGameState().intProperty("balls").asString());
        textScore.textProperty().bind(getGameState().intProperty("score").asString());

        getGameScene().addUINode(textBalls);
        getGameScene().addUINode(textScore);

        updateUI();
    }

    private void setNewTable() {
        Table table = game.newFullPlayableTable(
                "Table", 5, 0.35, 2, 2);
        Random rng = new Random();
        for (Bumper b : table.getBumpers())
            getGameWorld().addEntity(newBumper(rng.nextInt(550), rng.nextInt(400), b));
        for (Target t : table.getTargets())
            getGameWorld().addEntity(newTarget(rng.nextInt(550), rng.nextInt(400), t));
        game.setGameTable(table);
        getGameWorld().addEntity(newBall());
    }

    private void updateAllElements() {
        for (Entity e : getGameWorld().getEntitiesByType(EntityType.BUMPER)) {
            BumperComponent bumper = e.getComponent(BumperComponent.class);
            if (bumper.isAlternateState()) {
                bumper.changeView();
                resetTimer(bumper);
            }
        }
        for (Entity e : getGameWorld().getEntitiesByType(EntityType.TARGET)) {
            TargetComponent target = e.getComponent(TargetComponent.class);
            if (!target.isAlternateState()) {
                target.changeView();
                target.expireTimerAction();
            }
        }
    }

    private void resetTimer(HittableComponent hittable) {
        hittable.setTimerAction(
                getMasterTimer().runOnceAfter(() -> hittable.resetState(),
                        Duration.seconds(new ResetTime(hittable).getTime())));
    }

    public static void main(String[] args) {
        launch(args);
    }
}