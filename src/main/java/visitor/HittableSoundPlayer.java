package visitor;

import com.almasb.fxgl.audio.AudioPlayer;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.table.NullTable;
import logic.table.PlayableTable;

public class HittableSoundPlayer implements Visitor{
    AudioPlayer player;

    HittableSoundPlayer(AudioPlayer player) {
        this.player = player;
    }

    @Override
    public void visitKickerBumper(KickerBumper k) {
        if (k.isUpgraded())
            player.playSound("bumper1up.wav");
        else
            player.playSound("bumper1.wav");
    }

    @Override
    public void visitPopBumper(PopBumper p) {
        if (p.isUpgraded())
            player.playSound("bumper2up.wav");
        else
            player.playSound("bumper2.wav");
    }

    @Override
    public void visitSpotTarget(SpotTarget s) {
        if (s.isActive())
            player.playSound("target1active.wav");
        else
            player.playSound("target1inactive.wav");
    }

    @Override
    public void visitDropTarget(DropTarget d) {
        if (d.isActive())
            player.playSound("target2active.wav");
        else
            player.playSound("target2inactive.wav");
    }

    @Override
    public void visitNullTable(NullTable n) {

    }

    @Override
    public void visitPlayableTable(PlayableTable p) {

    }
}
