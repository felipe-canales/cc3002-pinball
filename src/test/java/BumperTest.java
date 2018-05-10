import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import org.junit.*;

import static org.junit.Assert.*;

public class BumperTest {
    static private PopBumper pop;
    static private KickerBumper kick;

    @BeforeClass
    public static void init() {
        pop = new PopBumper();
        kick = new KickerBumper();
    }

    @Before
    public void setUp() {
        pop.downgrade();
        kick.downgrade();
    }

    @Test
    public void normalScore() {
        assertEquals("PopBumper's score is incorrect", 100, pop.getScore());
        assertEquals("KickerBumper's score is incorrect", 500, kick.getScore());
    }

    @Test
    public void hitsToUpgrade() {
        assertEquals(3, pop.remainingHitsToUpgrade());
        assertEquals(5, kick.remainingHitsToUpgrade());
    }

    @Test
    public void hitting() {
        pop.hit();
        kick.hit();
        assertEquals(2, pop.remainingHitsToUpgrade());
        assertEquals(4, kick.remainingHitsToUpgrade());
    }

    @Test
    public void upgrading() {
        for (int i = 0; i < 3; i++)
            pop.hit();
        for (int i = 0; i < 5; i++)
            kick.hit();
        assertTrue(pop.isUpgraded());
        assertTrue(kick.isUpgraded());
    }

    @Test
    public void upgradedScore() {
        for (int i = 0; i < 3; i++)
            pop.hit();
        for (int i = 0; i < 5; i++)
            kick.hit();
        assertEquals("PopBumper's upgraded score is incorrect", 300, pop.getScore());
        assertEquals("KickerBumper's upgraded score is incorrect",1000, kick.getScore());
    }
}
