package logic.gameelements.bumper;

import controller.BonusTriggerer;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public abstract class AbstractBumper extends Observable implements Bumper {

    private boolean upgraded;
    private boolean triggerBonus;
    private Random rng;
    private int maxHits;
    private int hitsToUpgrade;
    private int score;

    public AbstractBumper(int hits, int score, Random rng) {
        this.upgraded = false;
        this.triggerBonus = false;
        this.rng = rng;
        this.maxHits = hits;
        this.hitsToUpgrade = hits;
        this.score = score;
    }

    private void decreaseHitsToUpgrade() {
        if (isUpgraded())
            return;
        hitsToUpgrade--;
        if (remainingHitsToUpgrade() == 0)
            upgrade();
            if (rng.nextDouble() < 0.1)
                triggerBonus = true;
    }

    @Override
    public int hit() {
        decreaseHitsToUpgrade();
        setChanged();
        notifyObservers();
        return getScore();
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int remainingHitsToUpgrade() {
        return hitsToUpgrade;
    }

    @Override
    public boolean isUpgraded() {
        return upgraded;
    }

    @Override
    public void upgrade() {
        hitsToUpgrade = 0;
        upgraded = true;
    }

    @Override
    public void downgrade() {
        upgraded = false;
        hitsToUpgrade = maxHits;
    }

    @Override
    public void acceptTriggerer(BonusTriggerer bonusTriggerer) {
        if (triggerBonus) {
            bonusTriggerer.triggerExtraBallBonus();
            triggerBonus = false;
        }
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
    }
}
