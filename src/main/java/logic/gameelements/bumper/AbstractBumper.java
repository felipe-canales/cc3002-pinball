package logic.gameelements.bumper;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * Abstract class that is the base for bumpers.
 *
 * @author Felipe Canales
 */
public abstract class AbstractBumper extends Observable implements Bumper {

    private boolean upgraded;
    private boolean triggerBonus;
    private Random rng;
    private int maxHits;
    private int hitsToUpgrade;
    private int score;

    /**
     * Constructor
     *
     * @param hits  Hits required to upgrade the bumper.
     * @param score Score received for hitting the bumper.
     * @param rng   Instance of Random used to randomize the triggering of {@link logic.bonus.ExtraBallBonus}
     */
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
        if (remainingHitsToUpgrade() == 0) {
            upgrade();
            if (rng.nextDouble() < 0.1)
                triggerBonus = true;
        }
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
    public boolean shouldTriggerbonus() {
        if (triggerBonus) {
            triggerBonus = false;
            return true;
        }
        return false;
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public Random getRNG() {
        return rng;
    }
}
