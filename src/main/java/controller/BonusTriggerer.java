package controller;

public class BonusTriggerer {
    private Game game;

    public BonusTriggerer(Game game) {
        this.game = game;
    }

    public void triggerExtraBallBonus() {
        game.getExtraBallBonus().trigger(game);
    }

    public void triggerJackPotBonus() {
        game.getJackPotBonus().trigger(game);
    }

    public void triggerDropTargetBonus() {
        game.getDropTargetBonus().trigger(game);
    }

    public void checkDropTargetBonus() {
        game.getTable().acceptTriggerer(this);
    }
}
