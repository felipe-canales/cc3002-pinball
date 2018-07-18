package component;

import com.almasb.fxgl.entity.component.Component;
import logic.gameelements.Hittable;

public abstract class HittableComponent extends Component {
    Hittable gameElement;

    public HittableComponent(Hittable h) {
        gameElement = h;
    }

    public void hit() {
        gameElement.hit();
    }

    public Hittable getGameElement() {
        return gameElement;
    }
}
