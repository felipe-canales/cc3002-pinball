package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;
import logic.rng.RNG;

import java.util.ArrayList;
import java.util.List;

public class PlayableTable implements Table {
    private String name;
    private ArrayList<Bumper> bumpers;
    private ArrayList<Target> targets;
    private int dropTargetIndex;

    public PlayableTable(String name, int numberOfBumpers, double prob, int numberOfTargets, int numberOfDropTargets, RNG rng) {
        this.name = name;
        this.dropTargetIndex = numberOfTargets;
        bumpers = new ArrayList<>();
        targets = new ArrayList<>();

        for (int i = 0; i < numberOfBumpers; i++) {
            if (rng.probability() < prob)
                bumpers.add(new PopBumper());
            else
                bumpers.add(new KickerBumper());
        }
        for (int i = 0; i < numberOfTargets; i++) {
            targets.add(new SpotTarget());
        }
        for (int i = 0; i < numberOfDropTargets; i++) {
            targets.add(new DropTarget());
        }
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
}
