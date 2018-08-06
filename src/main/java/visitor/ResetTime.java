package visitor;

import component.hittablecomponent.HittableComponent;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;

/**
 * Visitor class that given a {@link logic.gameelements.Hittable} contained in a {@link HittableComponent}, selects the
 * time that said Hittable should reset its state.
 *
 * @author Felipe Canales
 */
public class ResetTime implements HittableVisitor {
    private int time;

    /**
     * Constructor
     *
     * @param h Component that contains a Hittable.
     */
    public ResetTime(HittableComponent h) {
        h.accept(this);
    }

    @Override
    public void visitKickerBumper(KickerBumper k) {
        time = 10;
    }

    @Override
    public void visitPopBumper(PopBumper p) {
        time = 10;
    }

    @Override
    public void visitSpotTarget(SpotTarget s) {
        time = 20;
    }

    @Override
    public void visitDropTarget(DropTarget d) {
        time = 120;
    }

    /**
     * Reset time getter
     *
     * @return time to reset a hittable.
     */
    public int getTime() {
        return time;
    }
}
