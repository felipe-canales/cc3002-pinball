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
 * Visitor class that visits hittables a tables and triggers the aproppiate {@link Bonus}es if appropiate.
 *
 * @author Felipe Canales
 * @see logic.gameelements.Hittable
 * @see logic.table.Table
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

    @Override
    public void visitKickerBumper(KickerBumper k) {
        if (k.shouldTriggerbonus())
            triggerExtraBallBonus();
    }

    @Override
    public void visitPopBumper(PopBumper p) {
        if (p.shouldTriggerbonus())
            triggerExtraBallBonus();
    }

    @Override
    public void visitSpotTarget(SpotTarget s) {
        triggerJackPotBonus();
    }

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
