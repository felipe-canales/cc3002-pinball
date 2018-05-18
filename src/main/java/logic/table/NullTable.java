package logic.table;

import controller.BonusTriggerer;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;

import java.util.ArrayList;
import java.util.List;

public class NullTable implements Table{

    @Override
    public String getTableName() {
        return "";
    }

    @Override
    public int getNumberOfDropTargets() {
        return 0;
    }

    @Override
    public int getCurrentlyDroppedDropTargets() {
        return 0;
    }

    @Override
    public List<Bumper> getBumpers() {
        return new ArrayList<>();
    }

    @Override
    public List<Target> getTargets() {
        return new ArrayList<>();
    }

    @Override
    public void resetDropTargets() {

    }

    @Override
    public void upgradeAllBumpers() {

    }

    @Override
    public boolean isPlayableTable() {
        return false;
    }

    @Override
    public void acceptTriggerer(BonusTriggerer bonusTriggerer) {

    }
}
