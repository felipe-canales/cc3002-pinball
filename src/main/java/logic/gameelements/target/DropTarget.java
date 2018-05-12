package logic.gameelements.target;

import controller.BonusTriggerer;

import java.util.Random;

public class DropTarget extends AbstractTarget {
    private boolean triggerBonus = true;
    private Random rng;

    public DropTarget(Random rng) {
        super(100);
        this.rng = rng;
    }

    public DropTarget() {
        this(new Random());
    }

    @Override
    public void acceptTriggerer(BonusTriggerer bonusTriggerer) {
        if (rng.nextDouble() < 0.3) {
            bonusTriggerer.triggerExtraBallBonus();
        }
        bonusTriggerer.checkDropTargetBonus();
    }
}
