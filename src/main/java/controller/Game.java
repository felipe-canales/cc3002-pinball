package controller;

import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.gameelements.Hittable;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import logic.table.NullTable;
import logic.table.Table;
import visitor.BonusTriggerer;

import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author Felipe Canales
 */
public class Game implements Observer {
    private Table table;
    private ExtraBallBonus extraBall;
    private JackPotBonus jackPot;
    private DropTargetBonus dropBonus;
    private int balls;
    private int score;

    /**
     * Constructor
     */
    public Game() {
        table = new NullTable();
        extraBall = new ExtraBallBonus();
        jackPot = new JackPotBonus();
        dropBonus = new DropTargetBonus();
        balls = 3;
        score = 0;
    }

    private void setObservables() {
        for (Bumper b : table.getBumpers()) {
            b.addObserver(this);
        }
        for (Target t : table.getTargets()) {
            t.addObserver(this);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Hittable) {
            Hittable hittable = (Hittable) o;
            addScore(hittable.getScore());
            hittable.acceptVisitor(new BonusTriggerer(this));
        }
    }

    /**
     * Sets the game table.
     *
     * @param newTable The {@link Table} that will be part of the game.
     */
    public void setTable(Table newTable) {
        table = newTable;
        setObservables();
    }

    /**
     * Gets the game table.
     *
     * @return the {@link Table} that is currently part of the game.
     */
    public Table getTable() {
        return table;
    }

    /**
     * Gets the number of balls the game currently has.
     *
     * @return the number of balls.
     */
    public int getNumberOfBalls() {
        return balls;
    }

    /**
     * Decreases the number of balls by one.
     */
    public void dropBall() {
        if (balls > 0)
            balls--;
    }

    /**
     * Increases the number of balls by one.
     */
    public void addBall() {
        balls++;
    }

    /**
     * Gets the score accumulated.
     *
     * @return current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Adds score obtained.
     * @param scoreObtained Score obtained.
     */
    public void addScore(int scoreObtained) {
        score += scoreObtained;
    }

    /**
     * Gets the {@link ExtraBallBonus} that is part of the game.
     *
     * @return instance of ExtraBallBonus.
     */
    public ExtraBallBonus getExtraBallBonus() {
        return extraBall;
    }

    /**
     * Gets the {@link JackPotBonus} that is part of the game.
     *
     * @return instance of JackPotBonus.
     */
    public JackPotBonus getJackPotBonus() {
        return jackPot;
    }

    /**
     * Gets the {@link DropTargetBonus} that is part of the game.
     *
     * @return instance of DropTargetBonus.
     */
    public DropTargetBonus getDropTargetBonus() {
        return dropBonus;
    }
}
