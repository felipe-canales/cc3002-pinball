package visitor;

import component.HittableComponent;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.table.NullTable;
import logic.table.PlayableTable;

public class HittableSoundPlayer implements Visitor{
    private String soundName;

    public HittableSoundPlayer(HittableComponent hittable) {
        hittable.accept(this);
    }

    @Override
    public void visitKickerBumper(KickerBumper k) {
        if (k.isUpgraded())
            soundName = "bumper1up.wav";
        else
            soundName = "bumper1.wav";
    }

    @Override
    public void visitPopBumper(PopBumper p) {
        if (p.isUpgraded())
            soundName = "bumper2up.wav";
        else
            soundName = "bumper2.wav";
    }

    @Override
    public void visitSpotTarget(SpotTarget s) {
        if (s.isActive())
            soundName = "target1active.wav";
        else
        soundName = "target1inactive.wav";
    }

    @Override
    public void visitDropTarget(DropTarget d) {
        if (d.isActive())
            soundName = "target2active.wav";
        else
            soundName = "target2inactive.wav";
    }

    @Override
    public void visitNullTable(NullTable n) {

    }

    @Override
    public void visitPlayableTable(PlayableTable p) {

    }

    public String getSound() {
        return soundName;
    }
}
