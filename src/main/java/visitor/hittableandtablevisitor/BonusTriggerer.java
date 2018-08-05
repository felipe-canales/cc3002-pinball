package visitor.hittableandtablevisitor;

import controller.Game;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.table.NullTable;
import logic.table.PlayableTable;
import visitor.hittableandtablevisitor.HittableAndTableVisitor;

/**
 * Visitor class that visits the hittables and triggers the aproppiate {@link Bonus}es.
 *
 * @author Felipe Canales
 */
public class BonusTriggerer implements HittableAndTableVisitor {
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
    @Override
    public void visitKickerBumper(KickerBumper k) {
        if (k.shouldTriggerbonus())
            triggerExtraBallBonus();
    }

    /**
     * Triggers {@link logic.bonus.ExtraBallBonus} in the given game.
     */
    @Override
    public void visitPopBumper(PopBumper p) {
        if (p.shouldTriggerbonus())
            triggerExtraBallBonus();
    }

    /**
     * Triggers {@link logic.bonus.JackPotBonus} in the given game.
     */
    @Override
    public void visitSpotTarget(SpotTarget s) {
        triggerJackPotBonus();
    }

    /**
     * Visits the game table, and if appropiate, calls to triggerDropTargetBonus.
     */
    @Override
    public void visitDropTarget(DropTarget d) {
        if (d.getRNG().nextDouble() < 0.3)
            triggerExtraBallBonus();
        game.getTable().acceptVisitor(this);
    }

    @Override
    public void visitNullTable(NullTable n) {

    }

    @Override
    public void visitPlayableTable(PlayableTable t) {
        if (t.getCurrentlyDroppedDropTargets() == t.getNumberOfDropTargets())
            triggerDropTargetBonus();
    }

    /**
     * Triggers {@link logic.bonus.DropTargetBonus} in the given game.
     */
    private void triggerDropTargetBonus() {
        game.getDropTargetBonus().trigger(game);
        game.getTable().resetDropTargets();
    }

    private void triggerExtraBallBonus() {
        game.getExtraBallBonus().trigger(game);
    }

    private void triggerJackPotBonus() {
        game.getJackPotBonus().trigger(game);
    }
}
