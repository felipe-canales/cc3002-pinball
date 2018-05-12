package logic.gameelements.target;

import java.util.Observable;

public abstract class AbstractTarget extends Observable implements Target {

    private boolean active;
    private int score;

    public AbstractTarget(int score) {
        this.score = score;
        this.active = true;
    }

    @Override
    public int hit() {
        if (!isActive())
            return 0;
        deactivate();
        setChanged();
        notifyObservers();
        return getScore();
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
        active = true;
    }

    protected void deactivate() {
        active = false;
    }
}
