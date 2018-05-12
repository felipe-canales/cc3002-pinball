package logic.gameelements.bumper;

public class KickerBumper extends AbstractBumper {
    public KickerBumper() {
        super(5, 500);
    }

    @Override
    public int hit() {
        int score = getScore();
        decreaseHitsToUpgrade();
        return score;
    }

    @Override
    public int getScore() {
        return super.getScore() * (isUpgraded()? 2: 1);
    }
}
