package Component;

import logic.gameelements.target.DropTarget;
import logic.gameelements.target.Target;
import component.hittablecomponent.TargetComponent;
import org.junit.*;

import static org.junit.Assert.*;

public class TargetComponentTest {
    private TargetComponent tc;
    private Target t;

    @Before
    public void setUp() {
        t = new DropTarget();
        tc = new TargetComponent(t);
    }

    @Test
    public void hit() {
        tc.hit();
        assertFalse(t.isActive());
    }

    @Test
    public void isAlternateStateTrue() {
        tc.hit();
        assertTrue(tc.isAlternateState());
    }

    @Test
    public void isAlternateStateFalse() {
        assertFalse(tc.isAlternateState());
    }

    /*@Test
    public void resetState() {
        Entity e = Entities.builder().viewFromNodeWithBBox(new ShapePicker(t).getShape()).with(tc).build();
        t.hit();
        tc.resetState();
        assertTrue(t.isActive());
    }*/
}
