package logic.gameelements.target;

import visitor.HittableVisitor;

import java.util.Random;

/**
 * Target of the Spot kind.
 *
 * @author Felipe Canales
 */
public class SpotTarget extends AbstractTarget {
    /**
     * Consructor
     */
    public SpotTarget() {
        super(0);
    }

    @Override
    public void acceptVisitor(HittableVisitor v) {
        v.visitSpotTarget(this);
    }


    @Override
    public Random getRNG() {
        return null;
    }
}
