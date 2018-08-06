package visitor;

import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;

/**
 * Interface of a Visitor class that visits {@link logic.gameelements.Hittable}s.
 *
 * @author Felipe Canales
 */
public interface HittableVisitor {
    /**
     * Receives a {@link KickerBumper} and acts accordingly.
     *
     * @param k Instance of KickerBumper.
     */
    void visitKickerBumper(KickerBumper k);

    /**
     * Receives a {@link PopBumper} and acts accordingly.
     *
     * @param p Instance of PopBumper.
     */
    void visitPopBumper(PopBumper p);

    /**
     * Receives a {@link SpotTarget} and acts accordingly.
     *
     * @param s Instance of SpotTarget.
     */
    void visitSpotTarget(SpotTarget s);

    /**
     * Receives a {@link DropTarget} and acts accordingly.
     *
     * @param d Instance of DropTarget.
     */
    void visitDropTarget(DropTarget d);
}
