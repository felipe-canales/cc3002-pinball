package gamefactory;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.RenderLayer;
import entitytype.EntityType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameFactory {
    public static Entity newBackground() {
        return Entities.builder().
                viewFromNode(new Rectangle(600, 600, Color.ORANGE))
                .renderLayer(RenderLayer.BACKGROUND)
                .build();
    }

    public static Entity newFlipper() {
        return  Entities.builder()
                .at(200, 400)
                .type(EntityType.LEFTFLIPPER)
                .viewFromNode(new Rectangle(50, 10, Color.BLACK))
                .build();
    }
}
