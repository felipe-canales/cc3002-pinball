package Visitor;

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
        KickerBumper k = new KickerBumper();
        assertEquals(10, new ResetTime(k).getTime());
    }

    @Test
    public void visitPopBumper() {
        PopBumper p = new PopBumper();
        assertEquals(10, new ResetTime(p).getTime());
    }

    @Test
    public void visitDropTarget() {
        DropTarget d = new DropTarget();
        assertEquals(120, new ResetTime(d).getTime());
    }

    @Test
    public void visitSpotTarget() {
        SpotTarget s = new SpotTarget();
        assertEquals(20, new ResetTime(s).getTime());
    }
}
