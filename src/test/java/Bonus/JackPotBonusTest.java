package Bonus;

import controller.Game;
import logic.bonus.JackPotBonus;
import org.junit.*;

import static org.junit.Assert.assertEquals;


public class JackPotBonusTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void timesTriggeredBeforeTriggering() {
        JackPotBonus jp = game.getJackPotBonus();
        assertEquals(0, jp.timesTriggered());
    }

    @Test
    public void timesTriggeredAfterTriggering() {
        JackPotBonus jp = game.getJackPotBonus();
        jp.trigger(game);
        assertEquals(1, jp.timesTriggered());
    }

    @Test
    public void bonusTriggering() {
        game.getJackPotBonus().trigger(game);
        assertEquals(100000, game.getScore());
    }
}
