package visitor;

import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;

public interface HittableVisitor {
    void visitKickerBumper(KickerBumper k);
    void visitPopBumper(PopBumper p);
    void visitSpotTarget(SpotTarget s);
    void visitDropTarget(DropTarget d);
}
