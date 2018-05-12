package logic.gameelements.bumper;

import java.util.Random;

public class KickerBumper extends AbstractBumper {
    public KickerBumper(Random rng) {
        super(6, 500, rng);
    }

    public KickerBumper() {
        this(new Random());
    }

    @Override
    public int getScore() {
        return super.getScore() * (isUpgraded()? 2: 1);
    }
}
