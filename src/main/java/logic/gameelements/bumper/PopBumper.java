package logic.gameelements.bumper;

import java.util.Random;

public class PopBumper extends AbstractBumper {
    public PopBumper(Random rng) {
        super(3, 100, rng);
    }

    public PopBumper() {
        this(new Random());
    }

    @Override
    public int getScore() {
        return super.getScore() * (isUpgraded()? 3: 1);
    }
}
