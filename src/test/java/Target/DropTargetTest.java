package Target;

import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import org.junit.*;
import static org.junit.Assert.*;

public class DropTargetTest {
    private static DropTarget drop;

    @BeforeClass
    public static void setUp() {
        drop = new DropTarget();
    }

    @Before
    public void reset() {
        drop.reset();
    }

    @Test
    public void activeBeforeHitting() {
        assertTrue(drop.isActive());
    }

    @Test
    public void activeAfterHitting() {
        drop.hit();
        assertFalse(drop.isActive());
    }

    @Test
    public void scoreWhenActive() {
        assertEquals(100, drop.hit());
    }

    @Test
    public void scoreWhenNotActive() {
        drop.hit();
        assertEquals(0, drop.hit());
    }
}
