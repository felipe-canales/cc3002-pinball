package controller;

import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.table.NullTable;
import logic.table.Table;

import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game implements Observer {
    private Table table;
    private ExtraBallBonus extraBall;
    private JackPotBonus jackPot;
    private DropTargetBonus dropBonus;
    private int balls;
    private int score;

    public Game() {
        table = new NullTable();
        extraBall = new ExtraBallBonus();
        jackPot = new JackPotBonus();
        dropBonus = new DropTargetBonus();
        balls = 3;
        score = 0;
    }

    private void setObservables() {

    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void setTable(Table newTable) {
        table = newTable;
        setObservables();
    }

    public Table getTable() {
        return table;
    }

    public int getNumberOfBalls() {
        return balls;
    }

    public void dropBall() {
        if (balls > 0)
            balls--;
    }

    public void addBall() {
        balls++;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int scoreObtained) {
        score += scoreObtained;
    }
}
