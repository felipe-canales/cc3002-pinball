package Bonus;

import controller.Game;
import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.gameelements.bumper.Bumper;
import logic.table.PlayableTable;
import org.junit.*;
import static org.junit.Assert.*;

public class ExtraBallBonusTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void timesTriggeredBeforeTriggering() {
        ExtraBallBonus eb = game.getExtraBallBonus();
        assertEquals(0, eb.timesTriggered());

    }

    @Test
    public void timesTriggeredAfterTriggering() {
        ExtraBallBonus eb = game.getExtraBallBonus();
        eb.trigger(game);
        assertEquals(1, eb.timesTriggered());
    }

    @Test
    public void bonusTriggering() {
        game.getExtraBallBonus().trigger(game);
        assertEquals(4, game.getNumberOfBalls());
    }
}