package controller;

/**
 * Visitor class that visits the hittables and triggers the aproppiate {@link Bonus}es.
 *
 * @author Felipe Canales
 */
public class BonusTriggerer {
    private Game game;

    /**
     * Constructor
     *
     * @param game Instance of Game, over which the Bonuses will be triggered.
     */
    public BonusTriggerer(Game game) {
        this.game = game;
    }

    /**
     * Triggers {@link logic.bonus.ExtraBallBonus} in the given game.
     */
    public void triggerExtraBallBonus() {
        game.getExtraBallBonus().trigger(game);
    }

    /**
     * Triggers {@link logic.bonus.JackPotBonus} in the given game.
     */
    public void triggerJackPotBonus() {
        game.getJackPotBonus().trigger(game);
    }

    /**
     * Triggers {@link logic.bonus.DropTargetBonus} in the given game.
     */
    public void triggerDropTargetBonus() {
        game.getDropTargetBonus().trigger(game);
    }

    /**
     * Visits the game table, and if appropiate, calls to triggerDropTargetBonus.
     */
    public void checkDropTargetBonus() {
        game.getTable().acceptTriggerer(this);
    }
}
