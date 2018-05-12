import controller.Game;
import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.gameelements.bumper.Bumper;
import logic.table.PlayableTable;
import org.junit.*;
import static org.junit.Assert.*;

public class BonusTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void timesTriggeredBeforeTriggering() {
        ExtraBallBonus eb = game.getExtraBallBonus();
        JackPotBonus jp = game.getJackPotBonus();
        DropTargetBonus dt = game.getDropTargetBonus();
        assertEquals(0, eb.timesTriggered());
        assertEquals(0, jp.timesTriggered());
        assertEquals(0, dt.timesTriggered());
    }

    @Test
    public void timesTriggeredAfterTriggering() {
        ExtraBallBonus eb = game.getExtraBallBonus();
        JackPotBonus jp = game.getJackPotBonus();
        DropTargetBonus dt = game.getDropTargetBonus();
        eb.trigger(game);
        jp.trigger(game);
        dt.trigger(game);
        assertEquals(1, eb.timesTriggered());
        assertEquals(1, jp.timesTriggered());
        assertEquals(1, dt.timesTriggered());
    }

    @Test
    public void extraBallTriggering() {
        game.getExtraBallBonus().trigger(game);
        assertEquals(4, game.getNumberOfBalls());
    }

    @Test
    public void jackPotTriggering() {
        game.getJackPotBonus().trigger(game);
        assertEquals(100000, game.getScore());
    }

    @Test
    public void dropTargetTriggering() {
        game.getDropTargetBonus().trigger(game);
        assertEquals(1000000, game.getScore());
    }

    @Test
    public void dropTargetUpgradingBumpers() {
        PlayableTable t = new PlayableTable("test", 10,0.5, 0,0);
        game.setTable(t);
        game.getDropTargetBonus().trigger(game);
        for(Bumper b : t.getBumpers()) {
            assertTrue(b.isUpgraded());
        }
    }
}