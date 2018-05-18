import facade.HomeworkTwoFacade;
import logic.table.Table;
import org.junit.*;
import static org.junit.Assert.*;

public class FacadeTest {
    private HomeworkTwoFacade facade;

    @Before
    public void setUp() {
        facade = new HomeworkTwoFacade();
    }

    @Test
    public void defaultTableIsPlayableTable() {
        assertFalse(facade.isPlayableTable());
    }

    @Test
    public void playableTableWithNoTargetsIsPlayableTable() {
        facade.setGameTable(facade.newPlayableTableWithNoTargets("test", 1, 1));
        assertTrue(facade.isPlayableTable());
    }

    @Test
    public void fullPlayableTableIsPlayableTable() {
        facade.setGameTable(facade.newFullPlayableTable("test", 1, 1, 1, 1));
        assertTrue(facade.isPlayableTable());
    }

    @Test
    public void availableBalls() {
        assertEquals(3, facade.getAvailableBalls());
    }

    @Test
    public void dropBall() {
        facade.dropBall();
        assertEquals(2, facade.getAvailableBalls());
    }

    @Test
    public void tableName() {
        assertEquals("", facade.getTableName());
    }

    @Test
    public void gettingTable() {
        Table t = facade.newPlayableTableWithNoTargets("test", 1, 1);
        facade.setGameTable(t);
        assertEquals(t, facade.getCurrentTable());
    }

    @Test
    public void gettingTargets() {
        facade.setGameTable(facade.newPlayableTableWithNoTargets("test", 1, 1));
        assertEquals(0, facade.getCurrentTable().getTargets().size());
        Table t = facade.newFullPlayableTable("test", 1, 1, 1, 1);
        facade.setGameTable(t);
        assertEquals(t.getTargets(), facade.getTargets());
    }

    @Test
    public void gettingBumpers() {
        Table t = facade.newPlayableTableWithNoTargets("test", 1, 1);
        facade.setGameTable(t);
        assertEquals(t.getBumpers(), facade.getBumpers());
    }

    @Test
    public void score() {
        assertEquals(0, facade.getCurrentScore());
        Table t = facade.newPlayableTableWithNoTargets("test", 1, 1);
        facade.setGameTable(t);
        t.getBumpers().get(0).hit();
        assertTrue(facade.getCurrentScore() > 0);
    }

    @Test
    public void bonuses() {
        assertEquals(0, facade.getExtraBallBonus().timesTriggered());
        assertEquals(0, facade.getJackPotBonus().timesTriggered());
        assertEquals(0, facade.getDropTargetBonus().timesTriggered());
    }

    @Test
    public void gameOver() {
        assertFalse(facade.gameOver());
        for (int i = 0; i < 5; i++) {
            facade.dropBall();
        }
        assertEquals(0, facade.getAvailableBalls());
        assertTrue(facade.gameOver());
    }
}
