import controller.Game;
import logic.rng.RNG;
import logic.table.NullTable;
import logic.table.PlayableTable;
import org.junit.*;
import static org.junit.Assert.*;

public class GameTest {
    private Game game;
    private PlayableTable table;

    @Before
    public void reset() {
        game = new Game();
        table = new PlayableTable("test table", 10, 0.5, 3, 2, new RNG());
        game.setTable(table);
    }

    @Test
    public void defaultTableIsNullTable() {
        Game g =  new Game();
        assertEquals(new NullTable(), g.getTable());
    }

    @Test
    public void numberOfBalls() {
        assertEquals(3, game.getNumberOfBalls());
    }

    @Test
    public void addingBalls() {
        game.addBall();
        assertEquals(4, game.getNumberOfBalls());
    }

    @Test
    public void droppingBalls() {
        game.dropBall();
        assertEquals(2, game.getNumberOfBalls());
    }

    @Test
    public void gettingTable() {
        assertEquals(table, game.getTable());
    }

    @Test
    public void gettingScore() {
        assertEquals(0, game.getScore());
    }
}