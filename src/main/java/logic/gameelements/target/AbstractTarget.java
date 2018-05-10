package logic.gameelements.target;

public abstract class AbstractTarget implements Target {

    private boolean active;
    private int score;

    public AbstractTarget(int score) {
        this.score = score;
        this.active = true;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void reset() {
        this.active = true;
    }
}
