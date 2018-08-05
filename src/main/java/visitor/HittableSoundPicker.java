package visitor;

import component.hittablecomponent.HittableComponent;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;

public class HittableSoundPicker implements HittableVisitor{
    private String soundName;

    public HittableSoundPicker(HittableComponent hittable) {
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

    public String getSound() {
        return soundName;
    }
}
