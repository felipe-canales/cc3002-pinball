package logic.gameelements.target;

import controller.BonusTriggerer;

public class SpotTarget extends AbstractTarget {
    public SpotTarget() {
        super(0);
    }

    @Override
    public void acceptTriggerer(BonusTriggerer bonusTriggerer) {
        bonusTriggerer.triggerJackPotBonus();
    }
}
