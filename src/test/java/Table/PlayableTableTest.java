package Table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import logic.table.PlayableTable;
import org.junit.*;

import static org.junit.Assert.*;

public class PlayableTableTest {
    private PlayableTable p;

    @Before
    public void reset() {
        p = new PlayableTable("test", 10, 0.5, 3, 2);
    }

    @Test
    public void name() {
        assertEquals("test", p.getTableName());
    }

    @Test
    public void numberOfDropTargets() {
        assertEquals(2, p.getNumberOfDropTargets());
    }

    @Test
    public void droppedTargetsBeforeHitting() {
        assertEquals(0, p.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void droppedTargetsAfterHitting() {
        for (Target t: p.getTargets())
            t.hit();
        assertEquals(p.getNumberOfDropTargets(), p.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void hittingOneDropTarget() {
        p.getTargets().get(3).hit();
        assertEquals(1, p.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void resettingOneDroppedTargets() {
        for (Target t: p.getTargets())
            t.hit();
        p.getTargets().get(3).reset();
        assertEquals(1, p.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void gettingBumpers() {
        assertEquals(10, p.getBumpers().size());
    }

    @Test
    public void gettingTargets() {
        assertEquals(5, p.getTargets().size());
    }

    @Test
    public void resettingDropTargets() {
        for (Target t: p.getTargets())
            t.hit();
        p.resetDropTargets();
        assertEquals(0, p.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void resettingSpotTargets() {
        for (Target t: p.getTargets())
            t.hit();
        p.resetDropTargets();
        for (int i = 0; i < p.getTargets().size() - p.getNumberOfDropTargets(); i++)
            assertFalse(p.getTargets().get(i).isActive());
    }

    @Test
    public void upgradingBumpers() {
        p.upgradeAllBumpers();
        for (Bumper b : p.getBumpers()) {
            assertTrue(b.isUpgraded());
        }
    }

    @Test
    public void isPlayable() {
        assertTrue(p.isPlayableTable());
    }
}
