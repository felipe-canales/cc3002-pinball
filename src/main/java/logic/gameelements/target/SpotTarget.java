package logic.gameelements.target;

import controller.BonusTriggerer;

/**
 * Target of the Spot kind.
 *
 * @author Felipe Canales
 */
public class SpotTarget extends AbstractTarget {
    /**
     * Consructor
     */
    public SpotTarget() {
        super(0);
    }

    @Override
    public void acceptTriggerer(BonusTriggerer bonusTriggerer) {
        bonusTriggerer.triggerJackPotBonus();
    }
}
