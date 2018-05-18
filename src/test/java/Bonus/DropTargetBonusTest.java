package Bonus;

import controller.Game;
import logic.bonus.DropTargetBonus;
import logic.gameelements.bumper.Bumper;
import logic.table.PlayableTable;
import org.junit.*;
import static org.junit.Assert.*;

public class DropTargetBonusTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void timesTriggeredBeforeTriggering() {
        DropTargetBonus dt = game.getDropTargetBonus();
        assertEquals(0, dt.timesTriggered());
    }

    @Test
    public void timesTriggeredAfterTriggering() {
        DropTargetBonus dt = game.getDropTargetBonus();
        dt.trigger(game);
        assertEquals(1, dt.timesTriggered());
    }


    @Test
    public void bonusTriggering() {
        game.getDropTargetBonus().trigger(game);
        assertEquals(1000000, game.getScore());
    }

    @Test
    public void upgradingBumpers() {
        PlayableTable t = new PlayableTable("test", 10,0.5, 0,0);
        game.setTable(t);
        game.getDropTargetBonus().trigger(game);
        for (Bumper b : t.getBumpers()) {
            assertTrue(b.isUpgraded());
        }
    }
}
