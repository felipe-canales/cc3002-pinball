package logic.gameelements.bumper;

public class PopBumper extends AbstractBumper {
    public PopBumper() {
        super(3, 100);
    }

    @Override
    public int hit() {
        int score = getScore() * (isUpgraded()? 3: 1);
        decreaseHitsToUpgrade();
        return score;
    }
}
