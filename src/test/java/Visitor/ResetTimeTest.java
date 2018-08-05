package Visitor;

import component.hittablecomponent.BumperComponent;
import component.hittablecomponent.TargetComponent;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import org.junit.*;
import static org.junit.Assert.*;
import visitor.ResetTime;

public class ResetTimeTest {

    @Test
    public void visitKickerBumper() {
        BumperComponent bc = new BumperComponent(new KickerBumper());
        assertEquals(10, new ResetTime(bc).getTime());
    }

    @Test
    public void visitPopBumper() {
        BumperComponent bc = new BumperComponent(new PopBumper());
        assertEquals(10, new ResetTime(bc).getTime());
    }

    @Test
    public void visitDropTarget() {
        TargetComponent tc = new TargetComponent(new DropTarget());
        assertEquals(120, new ResetTime(tc).getTime());
    }

    @Test
    public void visitSpotTarget() {
        TargetComponent tc = new TargetComponent(new SpotTarget());
        assertEquals(20, new ResetTime(tc).getTime());
    }
}
