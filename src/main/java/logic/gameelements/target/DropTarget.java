package logic.gameelements.target;

public class DropTarget extends AbstractTarget {
    public DropTarget() {
        super(100);
    }

    @Override
    public int hit() {
        if (!isActive())
            return 0;
        deactivate();
        return getScore();
    }
}
