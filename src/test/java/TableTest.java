import logic.gameelements.bumper.Bumper;
import logic.table.NullTable;
import logic.table.PlayableTable;
import org.junit.*;

import static org.junit.Assert.*;

public class TableTest {
    private NullTable n;
    private PlayableTable p;

    @Before
    public void reset() {
        n = new NullTable();
        p = new PlayableTable("test", 10, 0.5, 3, 2);
    }

    @Test
    public void name() {
        assertEquals("Null Table", n.getTableName());
        assertEquals("test", p.getTableName());
    }

    @Test
    public void numberOfDropTargets() {
        assertEquals(0, n.getNumberOfDropTargets());
        assertEquals(2, p.getNumberOfDropTargets());
    }

    @Test
    public void droppedTargetsBeforeHitting() {
        assertEquals(0, n.getCurrentlyDroppedDropTargets());
        assertEquals(0, p.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void droppedTargetsAfterHitting() {
        p.getTargets().get(3).hit();
        assertEquals(1, p.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void countingSpotTargetsAsDropped() {
        p.getTargets().get(0).hit();
        assertEquals(0, p.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void gettingBumpers() {
        assertEquals(0, n.getBumpers().size());
        assertEquals(10, p.getBumpers().size());
    }

    @Test
    public void gettingTargets() {
        assertEquals(0, n.getTargets().size());
        assertEquals(5, p.getTargets().size());
    }

    @Test
    public void resettingDropTargets() {
        n.resetDropTargets();
        p.getTargets().get(3).hit();
        p.resetDropTargets();
        assertEquals(0, p.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void resettingSpotTargets() {
        p.getTargets().get(1).hit();
        p.resetDropTargets();
        assertFalse(p.getTargets().get(1).isActive());
    }

    @Test
    public void upgradingBumpers() {
        n.upgradeAllBumpers();
        p.upgradeAllBumpers();
        for (Bumper b : p.getBumpers()) {
            assertTrue(b.isUpgraded());
        }
    }

    @Test
    public void isPlayable() {
        assertTrue(p.isPlayableTable());
        assertFalse(n.isPlayableTable());
    }
}
