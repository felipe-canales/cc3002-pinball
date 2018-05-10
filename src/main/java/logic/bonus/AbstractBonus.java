package logic.bonus;

public abstract class AbstractBonus implements Bonus {

    private int triggered;

    public AbstractBonus() {
        triggered = 0;
    }

    @Override
    public int timesTriggered() {
        return triggered;
    }
}
