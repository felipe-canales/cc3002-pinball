package logic.gameelements.bumper;

import java.util.Random;

/**
 * Bumper of the Pop kind.
 *
 * @author Felipe Canales
 */
public class PopBumper extends AbstractBumper {
    /**
     * Constructor
     *
     * @param rng Instance of Random used to randomize the triggering of {@link logic.bonus.ExtraBallBonus}
     */
    public PopBumper(Random rng) {
        super(3, 100, rng);
    }

    /**
     * Constructor
     */
    public PopBumper() {
        this(new Random());
    }

    @Override
    public int getScore() {
        return super.getScore() * (isUpgraded()? 3: 1);
    }
}
