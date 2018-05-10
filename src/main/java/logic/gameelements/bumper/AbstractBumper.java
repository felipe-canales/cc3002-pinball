package logic.gameelements.bumper;

public abstract class AbstractBumper implements Bumper {

    private boolean upgraded;
    private int maxHits;
    private int hitsToUpgrade;
    private int score;

    public AbstractBumper(int hits, int score) {
        this.upgraded = false;
        this.maxHits = hits;
        this.hitsToUpgrade = hits;
        this.score = score;
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
        upgraded = true;
    }

    @Override
    public void downgrade() {
        upgraded = false;
        hitsToUpgrade = maxHits;
    }
}
