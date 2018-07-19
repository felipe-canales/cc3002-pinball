package visitor;

import component.HittableComponent;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.table.NullTable;
import logic.table.PlayableTable;

public class ResetTime implements Visitor {
    private int time;

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

    @Override
    public void visitNullTable(NullTable n) {

    }

    @Override
    public void visitPlayableTable(PlayableTable p) {

    }

    public int getTime() {
        return time;
    }
}
