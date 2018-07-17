package gamefactory;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.RenderLayer;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import entitytype.EntityType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class StaticElementFactory {
    public static Entity newBackground() {
        return Entities.builder()
                .viewFromNode(new Rectangle(600, 600, Color.ORANGE))
                .renderLayer(RenderLayer.BACKGROUND)
                .build();
    }

    public static Entity newBorderWalls() {
        Entity walls = Entities.makeScreenBounds(100);
        walls.setType(EntityType.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }

    public static Entity newWall(int x, int y, int width, int height, double angle) {
        Entity wall = Entities.builder()
                .at(x, y)
                .type(EntityType.WALL)
                .viewFromNodeWithBBox(new Rectangle(width, height, Color.MIDNIGHTBLUE))
                .with(new PhysicsComponent())
                .build();
        wall.rotateBy(angle);

        return wall;
    }
}
