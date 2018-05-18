package Table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import logic.table.NullTable;
import org.junit.*;

import static org.junit.Assert.*;

public class NullTableTest {
    private NullTable n;

    @Before
    public void reset() {
        n = new NullTable();
    }

    @Test
    public void name() {
        assertEquals("", n.getTableName());
    }

    @Test
    public void numberOfDropTargets() {
        assertEquals(0, n.getNumberOfDropTargets());
    }

    @Test
    public void droppedTargetsBeforeHitting() {
        assertEquals(0, n.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void droppedTargetsAfterHitting() {
        for (Target t: n.getTargets())
            t.hit();
        assertEquals(n.getNumberOfDropTargets(), n.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void gettingBumpers() {
        assertEquals(0, n.getBumpers().size());
    }

    @Test
    public void gettingTargets() {
        assertEquals(0, n.getTargets().size());
    }

    @Test
    public void resettingDropTargets() {
        for (Target t: n.getTargets())
            t.hit();
        n.resetDropTargets();
        assertEquals(0, n.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void resettingSpotTargets() {
        for (Target t: n.getTargets())
            t.hit();
        n.resetDropTargets();
        for (int i = 0; i < n.getTargets().size() - n.getNumberOfDropTargets(); i++)
            assertFalse(n.getTargets().get(i).isActive());
    }

    @Test
    public void upgradingBumpers() {
        n.upgradeAllBumpers();
        for (Bumper b : n.getBumpers()) {
            assertTrue(b.isUpgraded());
        }
    }

    @Test
    public void isPlayable() {
        assertFalse(n.isPlayableTable());
    }
}
