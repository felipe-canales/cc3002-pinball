package Component;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.Target;
import component.TargetComponent;
import org.junit.*;
import visitor.NormalShapePicker;

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
        Entity e = Entities.builder().viewFromNodeWithBBox(new NormalShapePicker(t).getShape()).with(tc).build();
        t.hit();
        tc.resetState();
        assertTrue(t.isActive());
    }*/
}
