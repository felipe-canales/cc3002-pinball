import controller.Game;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import logic.table.PlayableTable;
import org.junit.*;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class IntegrationTest {
    private PlayableTable table;
    private Game game;

    @Before
    public void setUp() {
        table = new PlayableTable("t", 50, 0.5, 1, 10, new Random(0));
        game = new Game();
        game.setTable(table);
    }

    @Test
    public void bumperScore() {
        int currentScore = 0;
        for(Bumper b : game.getTable().getBumpers()) {
            currentScore += b.hit();
            assertEquals(currentScore, game.getScore());
        }
    }

    @Test
    public void dropTargetScore() {
        Target t = game.getTable().getTargets().get(1);
        t.hit();
        assertEquals(100, game.getScore());
    }

    @Test
    public void bumpersTriggerExtraBall() {
        for(Bumper b : game.getTable().getBumpers()) {
            for(int i = 0; i < 6; i++) {
                b.hit();
            }
        }
        assertTrue(game.getExtraBallBonus().timesTriggered() >= 1);
    }

    @Test
    public void spotTargetsTriggersJackPot() {
        game.getTable().getTargets().get(0).hit();
        assertEquals(1, game.getJackPotBonus().timesTriggered());
        assertEquals(100000, game.getScore());
    }

    @Test
    public void dropTargetsTriggerExtraBall() {
        List<Target> dropTargets = game.getTable().getTargets().subList(1,11);
        for(Target dt : dropTargets) {
            dt.hit();
        }
        assertTrue(game.getExtraBallBonus().timesTriggered() >= 1);
    }

    @Test
    public void dropTargetsTriggerDropTarget() {
        List<Target> dropTargets = game.getTable().getTargets().subList(1,11);
        for(Target dt : dropTargets) {
            dt.hit();
        }
        assertEquals(1, game.getDropTargetBonus().timesTriggered());
        assertTrue(game.getScore() > 1000000);
    }
}
