package Visitor;

import component.hittablecomponent.BumperComponent;
import component.hittablecomponent.TargetComponent;
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
        BumperComponent bc = new BumperComponent(new KickerBumper());
        assertEquals("bumper1.wav", new HittableSoundPicker(bc).getSound());
    }

    @Test
    public void visitUpgradedKickerBumper() {
        KickerBumper k = new KickerBumper();
        k.upgrade();
        BumperComponent bc = new BumperComponent(k);
        assertEquals("bumper1up.wav", new HittableSoundPicker(bc).getSound());
    }

    @Test
    public void visitNormalPopBumper() {
        BumperComponent bc = new BumperComponent(new PopBumper());
        assertEquals("bumper2.wav", new HittableSoundPicker(bc).getSound());
    }

    @Test
    public void visitUpgradedPopBumper() {
        PopBumper p = new PopBumper();
        p.upgrade();
        BumperComponent bc = new BumperComponent(p);
        assertEquals("bumper2up.wav", new HittableSoundPicker(bc).getSound());
    }

    @Test
    public void visitNormalSpotTarget() {
        TargetComponent tc = new TargetComponent(new SpotTarget());
        assertEquals("target1active.wav", new HittableSoundPicker(tc).getSound());
    }

    @Test
    public void visitDisabledSpotTarget() {
        TargetComponent tc = new TargetComponent(new SpotTarget());
        tc.hit();
        assertEquals("target1inactive.wav", new HittableSoundPicker(tc).getSound());
    }

    @Test
    public void visitNormalDropTarget() {
        TargetComponent tc = new TargetComponent(new DropTarget());
        assertEquals("target2active.wav", new HittableSoundPicker(tc).getSound());
    }

    @Test
    public void visitDisabledDropTarget() {
        TargetComponent tc = new TargetComponent(new DropTarget());
        tc.hit();
        assertEquals("target2inactive.wav", new HittableSoundPicker(tc).getSound());
    }
}
