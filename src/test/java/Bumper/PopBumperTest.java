package Bumper;

import logic.gameelements.bumper.PopBumper;
import org.junit.*;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PopBumperTest {
    static private PopBumper pop;

    @BeforeClass
    public static void setUp() {
        pop = new PopBumper();
    }

    @Before
    public void reset() {
        pop.downgrade();
    }

    @Test
    public void normalScore() {
        assertEquals("PopBumper's score is incorrect", 100, pop.hit());
    }

    @Test
    public void hitsToUpgrade() {
        assertEquals(3, pop.remainingHitsToUpgrade());
    }

    @Test
    public void hitting() {
        pop.hit();
        assertEquals(2, pop.remainingHitsToUpgrade());
    }

    @Test
    public void upgrading() {
        for (int i = 0; i < 3; i++)
            pop.hit();
        assertTrue(pop.isUpgraded());
    }

    @Test
    public void upgradedScore() {
        for (int i = 0; i < 2; i++)
            pop.hit();
        assertEquals("PopBumper's upgraded score is incorrect", 300, pop.hit());
    }

    @Test
    public void remainingHitsBelowZero () {
        for (int i = 0; i < 4; i++)
            pop.hit();
        assertEquals("PopBumper's remaining hits can't be negative", 0, pop.remainingHitsToUpgrade());
    }

    @Test
    public void getRNG() {
        Random rng = new Random();
        PopBumper p = new PopBumper(rng);
        assertEquals(p.getRNG(), rng);
    }
}
