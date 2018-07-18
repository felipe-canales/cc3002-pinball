package component;

import logic.gameelements.target.Target;

public class TargetComponent extends HittableComponent {

    public TargetComponent(Target t) {
        super(t);
    }

    public boolean isActive() {
        return ((Target)getGameElement()).isActive();
    }

    public void reset() {
        ((Target)getGameElement()).reset();
    }
}
