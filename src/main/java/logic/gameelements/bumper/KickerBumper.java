package logic.gameelements.bumper;

import visitor.Visitor;

import java.util.Random;

/**
 * Bumper of the Kicker kind.
 *
 * @author Felipe Canales
 */
public class KickerBumper extends AbstractBumper {
    /**
     * Constructor
     *
     * @param rng Instance of Random used to randomize the triggering of {@link logic.bonus.ExtraBallBonus}
     */
    public KickerBumper(Random rng) {
        super(5, 500, rng);
    }

    /**
     * Constructor
     */
    public KickerBumper() {
        this(new Random());
    }

    @Override
    public int getScore() {
        return super.getScore() * (isUpgraded()? 2: 1);
    }

    @Override
    public void acceptVisitor(Visitor v) {
        v.visitKickerBumper(this);
    }
}
