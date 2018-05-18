package Bumper;

import logic.gameelements.bumper.KickerBumper;
import org.junit.*;

import static org.junit.Assert.*;

public class KickBumperTest {
    static private KickerBumper kick;

    @BeforeClass
    public static void setUp() {
        kick = new KickerBumper();
    }

    @Before
    public void reset() {
        kick.downgrade();
    }

    @Test
    public void normalScore() {
        assertEquals("KickerBumper's score is incorrect", 500, kick.hit());
    }

    @Test
    public void hitsToUpgrade() {
        assertEquals(5, kick.remainingHitsToUpgrade());
    }

    @Test
    public void hitting() {
        kick.hit();
        assertEquals(4, kick.remainingHitsToUpgrade());
    }

    @Test
    public void upgrading() {
        for (int i = 0; i < 5; i++)
            kick.hit();
        assertTrue(kick.isUpgraded());
    }

    @Test
    public void upgradedScore() {
        for (int i = 0; i < 4; i++)
            kick.hit();
        assertEquals("KickerBumper's upgraded score is incorrect",1000, kick.hit());
    }

    @Test
    public void remainingHitsBelowZero () {
        for (int i = 0; i < 6; i++)
            kick.hit();
        assertEquals("KickerBumper's remaining hits can't be negative", 0, kick.remainingHitsToUpgrade());
    }
}
