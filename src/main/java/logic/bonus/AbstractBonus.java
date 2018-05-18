package logic.bonus;

public abstract class AbstractBonus implements Bonus {
    private int triggered;

    /**
     * Constructor
     */
    public AbstractBonus() {
        triggered = 0;
    }

    @Override
    public int timesTriggered() {
        return triggered;
    }

    protected void wasTriggered() {
        triggered++;
    }
}
