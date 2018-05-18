package logic.table;

import controller.BonusTriggerer;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A table that can be played on.
 *
 * @author Felipe Canales
 */
public class PlayableTable implements Table {
    private String name;
    private ArrayList<Bumper> bumpers;
    private ArrayList<Target> targets;
    private int dropTargetIndex;

    /**
     * Constructor
     *
     * @param name                  Name of the table.
     * @param numberOfBumpers       Number of {@link Bumper}s the table has.
     * @param prob                  The probability that a Bumper is a {@link PopBumper} or a {@link KickerBumper}.
     * @param numberOfTargets       Number of {@link SpotTarget}s the table has.
     * @param numberOfDropTargets   Number of {@link DropTarget}s the table has.
     * @param rng                   Instance of the class Random used to randomize the bumpers.
     */
    public PlayableTable(String name, int numberOfBumpers, double prob, int numberOfTargets, int numberOfDropTargets, Random rng) {
        this.name = name;
        this.dropTargetIndex = numberOfTargets;
        bumpers = new ArrayList<>();
        targets = new ArrayList<>();

        for (int i = 0; i < numberOfBumpers; i++) {
            if (rng.nextDouble() < prob)
                bumpers.add(new PopBumper(rng));
            else
                bumpers.add(new KickerBumper(rng));
        }
        for (int i = 0; i < numberOfTargets; i++) {
            targets.add(new SpotTarget());
        }
        for (int i = 0; i < numberOfDropTargets; i++) {
            targets.add(new DropTarget(rng));
        }
    }

    /**
     * Constructor
     *
     * @param name                  Name of the table.
     * @param numberOfBumpers       Number of {@link Bumper}s the table has.
     * @param prob                  The probability that a Bumper is a {@link PopBumper} or a {@link KickerBumper}.
     * @param numberOfTargets       Number of {@link SpotTarget}s the table has.
     * @param numberOfDropTargets   Number of {@link DropTarget}s the table has.
     */
    public PlayableTable(String name, int numberOfBumpers, double prob, int numberOfTargets, int numberOfDropTargets) {
        this(name, numberOfBumpers, prob, numberOfTargets, numberOfDropTargets, new Random());
    }

    @Override
    public String getTableName() {
        return name;
    }

    @Override
    public int getNumberOfDropTargets() {
        return targets.size() - dropTargetIndex;
    }

    @Override
    public int getCurrentlyDroppedDropTargets() {
        int droppedTargets = 0;
        for (int i = dropTargetIndex; i < targets.size(); i++) {
            if (!targets.get(i).isActive())
                droppedTargets++;
        }
        return droppedTargets;
    }

    @Override
    public List<Bumper> getBumpers() {
        return bumpers;
    }

    @Override
    public List<Target> getTargets() {
        return targets;
    }

    @Override
    public void resetDropTargets() {
        for (int i = dropTargetIndex; i < targets.size(); i++) {
            targets.get(i).reset();
        }
    }

    @Override
    public void upgradeAllBumpers() {
        for (Bumper b : bumpers) {
            b.upgrade();
        }
    }

    @Override
    public boolean isPlayableTable() {
        return true;
    }

    @Override
    public void acceptTriggerer(BonusTriggerer bonusTriggerer) {
        if (getNumberOfDropTargets() == getCurrentlyDroppedDropTargets())
            bonusTriggerer.triggerDropTargetBonus();
    }
}
