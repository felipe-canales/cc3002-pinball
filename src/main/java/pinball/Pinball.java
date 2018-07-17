package pinball;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;

import entitytype.EntityType;
import javafx.scene.input.KeyCode;

import static gamefactory.GameFactory.*;

public class Pinball extends GameApplication {

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(600);
        gameSettings.setHeight(600);
        gameSettings.setTitle("Game Window");
    }

    @Override
    protected void initGame() {
        Entity bg = newBackground();
        Entity lFlipper = newFlipper();
        lFlipper.setRotation(20);

        getGameWorld().addEntities(bg, lFlipper);
    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("Flipper") {
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

    public static void main(String[] args) {
        launch(args);
    }
}
