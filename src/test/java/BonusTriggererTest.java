import controller.BonusTriggerer;
import controller.Game;
import logic.table.PlayableTable;
import org.junit.*;

import static org.junit.Assert.*;

public class BonusTriggererTest {
    private BonusTriggerer bt;
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
        bt = new BonusTriggerer(game);
    }

    @Test
    public void triggerExtraBall(){
        bt.triggerExtraBallBonus();
        assertEquals(4, game.getNumberOfBalls());
    }

    @Test
    public void triggerJackPot() {
        bt.triggerJackPotBonus();
        assertEquals(100000, game.getScore());
    }

    @Test
    public void triggerDropTarget() {
        bt.triggerDropTargetBonus();
        assertEquals(1000000, game.getScore());
    }

    @Test
    public void checkDropTargetWithNullTable() {
        bt.checkDropTargetBonus();
        assertEquals(0, game.getScore());
    }

    @Test
    public void checkDropTargetBeforeDropping() {
        PlayableTable t = new PlayableTable("test", 0, 0, 0, 1);
        game.setTable(t);
        bt.checkDropTargetBonus();
        assertEquals(0, game.getScore());
    }

    @Test
    public void checkDropTargetAfterDropping() {
        PlayableTable t = new PlayableTable("test", 0, 0, 0, 1);
        t.getTargets().get(0).hit();
        game.setTable(t);
        bt.checkDropTargetBonus();
        assertEquals(1000000, game.getScore());
    }
}
