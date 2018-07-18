import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.table.NullTable;
import visitor.BonusTriggerer;
import controller.Game;
import logic.table.PlayableTable;
import org.junit.*;

import java.util.Random;

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
    public void visitBumperDoesntTriggerBonus(){
        KickerBumper k = new KickerBumper(new Random(0));
        for (int i = 0; i < 5; i++)
            k.hit();
        bt.visitKickerBumper(k);
        assertEquals(3, game.getNumberOfBalls());
    }

    @Test
    public void visitBumperTriggersBonus(){
        KickerBumper k = new KickerBumper(new Random(4096));
        for (int i = 0; i < 5; i++)
            k.hit();
        bt.visitKickerBumper(k);
        assertEquals(4, game.getNumberOfBalls());
    }

    @Test
    public void visitSpotTarget() {
        bt.visitSpotTarget(new SpotTarget());
        assertEquals(100000, game.getScore());
    }

    @Test
    public void visitDropTargetDoesntTriggerBonus() {
        bt.visitDropTarget(new DropTarget(new Random(0)));
        assertEquals(3, game.getNumberOfBalls());
    }

    @Test
    public void visitDropTargetTriggersBonus() {
        bt.visitDropTarget(new DropTarget(new Random(4096)));
        assertEquals(4, game.getNumberOfBalls());
    }

    @Test
    public void visitNullTable() {
        bt.visitNullTable(new NullTable());
        assertEquals(0, game.getScore());
    }

    @Test
    public void visitPlayableTableBeforeDropping() {
        PlayableTable t = new PlayableTable("test", 0, 0, 0, 1);
        bt.visitPlayableTable(t);
        assertEquals(0, game.getScore());
    }

    @Test
    public void visitPlayableTableAfterDropping() {
        PlayableTable t = new PlayableTable("test", 0, 0, 0, 1);
        t.getTargets().get(0).hit();
        bt.visitPlayableTable(t);
        assertEquals(1000000, game.getScore());
    }
}
