package logic.gameelements.target;

import visitor.HittableVisitor;

import java.util.Random;

/**
 * Target of the Drop kind.
 *
 * @author Felipe Canales
 */
public class DropTarget extends AbstractTarget {
    private Random rng;

    /**
     * Constructor
     *
     * @param rng Instance of {@link Random} used to randomize the triggering of {@link logic.bonus.ExtraBallBonus}.
     */
    public DropTarget(Random rng) {
        super(100);
        this.rng = rng;
    }

    /**
     * Constructor
     */
    public DropTarget() {
        this(new Random());
    }

    @Override
    public void acceptVisitor(HittableVisitor v) {
        v.visitDropTarget(this);
    }

    @Override
    public Random getRNG() {
        return rng;
    }
}
