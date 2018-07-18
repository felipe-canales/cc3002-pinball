package component;

import logic.gameelements.bumper.Bumper;

public class BumperComponent extends HittableComponent {
    public BumperComponent(Bumper b) {
        super(b);
    }

    public void downgrade() {
        ((Bumper)getGameElement()).downgrade();
    }

    public boolean isUpgraded() {
        return ((Bumper)getGameElement()).isUpgraded();
    }
}
