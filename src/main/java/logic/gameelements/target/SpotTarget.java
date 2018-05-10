package logic.gameelements.target;

public class SpotTarget extends AbstractTarget {
    public SpotTarget() {
        super(0);
    }

    @Override
    public int hit() {
        if (!isActive())
            return 0;
        deactivate();
        return getScore();
    }
}
