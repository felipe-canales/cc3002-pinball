package Target;

import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import org.junit.*;

import java.util.Random;

import static org.junit.Assert.*;

public class SpotTargetTest {
    private static SpotTarget spot;

    @BeforeClass
    public static void setUp() {
        spot = new SpotTarget();
    }

    @Before
    public void reset() {
        spot.reset();
    }

    @Test
    public void activeBeforeHitting() {
        assertTrue(spot.isActive());
    }

    @Test
    public void activeAfterHitting() {
        spot.hit();
        assertFalse(spot.isActive());
    }

    @Test
    public void scoreWhenActive() {
        assertEquals(0,spot.hit());
    }

    @Test
    public void scoreWhenNotActive() {
        spot.hit();
        assertEquals(0,spot.hit());
    }

    @Test
    public void getRNG() {
        assertEquals(spot.getRNG(), null);
    }
}
