package logic.bonus;

import controller.Game;

public class JackPotBonus extends AbstractBonus {
    @Override
    public void trigger(Game game) {
        game.addScore(100000);
        wasTriggered();
    }
}
