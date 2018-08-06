package Visitor;

import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import org.junit.*;
import visitor.HittableSoundPicker;

import static  org.junit.Assert.*;

public class HittableSoundPickerTest {

    @Test
    public void visitNormalKickerBumper() {
        KickerBumper k = new KickerBumper();
        assertEquals("bumper1.wav", new HittableSoundPicker(k).getSound());
    }

    @Test
    public void visitUpgradedKickerBumper() {
        KickerBumper k = new KickerBumper();
        k.upgrade();
        assertEquals("bumper1up.wav", new HittableSoundPicker(k).getSound());
    }

    @Test
    public void visitNormalPopBumper() {
        PopBumper p = new PopBumper();
        assertEquals("bumper2.wav", new HittableSoundPicker(p).getSound());
    }

    @Test
    public void visitUpgradedPopBumper() {
        PopBumper p = new PopBumper();
        p.upgrade();
        assertEquals("bumper2up.wav", new HittableSoundPicker(p).getSound());
    }

    @Test
    public void visitNormalSpotTarget() {
        SpotTarget s = new SpotTarget();
        assertEquals("target1active.wav", new HittableSoundPicker(s).getSound());
    }

    @Test
    public void visitDisabledSpotTarget() {
        SpotTarget s = new SpotTarget();
        s.hit();
        assertEquals("target1inactive.wav", new HittableSoundPicker(s).getSound());
    }

    @Test
    public void visitNormalDropTarget() {
        DropTarget d = new DropTarget();
        assertEquals("target2active.wav", new HittableSoundPicker(d).getSound());
    }

    @Test
    public void visitDisabledDropTarget() {
        DropTarget d = new DropTarget();
        d.hit();
        assertEquals("target2inactive.wav", new HittableSoundPicker(d).getSound());
    }
}
