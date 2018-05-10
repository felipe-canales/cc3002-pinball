package logic.gameelements.bumper;

public abstract class AbstractBumper implements Bumper {

    private boolean upgraded;
    private int hitsToUpgrade;
    private int score;

    public AbstractBumper(int hits, int score) {
        this.upgraded = false;
        this.hitsToUpgrade = hits;
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
