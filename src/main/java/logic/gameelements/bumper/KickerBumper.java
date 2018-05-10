package logic.gameelements.bumper;

public class KickerBumper extends AbstractBumper {

    public KickerBumper() {
        super(5, 500);
    }

    @Override
    public int hit() {
        return 0;
    }
}
