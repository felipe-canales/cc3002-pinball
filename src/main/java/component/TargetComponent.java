package component;

import com.almasb.fxgl.entity.component.Component;
import logic.gameelements.target.Target;

public class TargetComponent extends Component implements HittableComponent {
    Target target;

    public TargetComponent(Target t) {
        target = t;
    }

    @Override
    public void hit() {
        target.hit();
    }

    @Override
    public boolean isAlternateState() {
        return target.isActive();
    }

    @Override
    public void resetState() {
        target.reset();
    }
}
