package logic.gameelements.target;

public class DropTarget extends AbstractTarget {
    public DropTarget() {
        super(100);
    }

    @Override
    public int hit() {
        return 0;
    }
}
