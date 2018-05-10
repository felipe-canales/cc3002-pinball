import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import org.junit.*;
import static org.junit.Assert.*;

public class TargetTest {
    private static DropTarget drop;
    private static SpotTarget spot;

    @BeforeClass
    public static void setUp() {
        drop = new DropTarget();
        spot = new SpotTarget();
    }

    @Before
    public void reset() {
        drop.reset();
        spot.reset();
    }

    @Test
    public void activeBeforeHitting() {
        assertTrue(drop.isActive());
        assertTrue(spot.isActive());
    }

    @Test
    public void activeAfterHitting() {
        drop.hit();
        spot.hit();
        assertFalse(drop.isActive());
        assertFalse(spot.isActive());
    }

    @Test
    public void scoreWhenActive() {
        assertEquals(100, drop.hit());
        assertEquals(0,spot.hit());
    }

    @Test
    public void scoreWhenNotActive() {
        drop.hit();
        spot.hit();
        assertEquals(0, drop.hit());
        assertEquals(0,spot.hit());
    }
}
