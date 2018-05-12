package logic.bonus;

import controller.Game;
import logic.gameelements.bumper.Bumper;

public class DropTargetBonus extends AbstractBonus {
    @Override
    public void trigger(Game game) {
        game.addScore(1000000);
        for(Bumper b : game.getTable().getBumpers()) {
            b.upgrade();
        }
        wasTriggered();
    }
}
