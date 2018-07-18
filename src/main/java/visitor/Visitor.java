package visitor;

import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.table.NullTable;
import logic.table.PlayableTable;

public interface Visitor {
    void visitKickerBumper(KickerBumper k);
    void visitPopBumper(PopBumper p);
    void visitSpotTarget(SpotTarget s);
    void visitDropTarget(DropTarget d);
    void visitNullTable(NullTable n);
    void visitPlayableTable(PlayableTable p);
}
