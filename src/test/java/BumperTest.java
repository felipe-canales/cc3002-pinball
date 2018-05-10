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
    public void scoreTest() {
        assertEquals("PopBumper's score is incorrect", 100, pop.getScore());
        assertEquals("KickerBumper's score is incorrect", 500, kick.getScore());
    }
}
