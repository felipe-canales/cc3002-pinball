package component;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.TimerAction;
import javafx.util.Duration;
import logic.gameelements.bumper.Bumper;
import visitor.AlternateShapePicker;
import visitor.NormalShapePicker;
import visitor.Visitor;


public class BumperComponent extends Component implements HittableComponent {
    private Bumper bumper;
    private TimerAction action;

    public BumperComponent(Bumper b) {
        bumper = b;
        action = new TimerAction(Duration.UNKNOWN, () -> {}, 0);
    }

    @Override
    public void hit() {
        bumper.hit();
    }

    @Override
    public boolean isAlternateState() {
        return bumper.isUpgraded();
    }

    @Override
    public void resetState() {
        bumper.downgrade();
        resetView();
    }

    @Override
    public void resetView() {
        super.entity.getViewComponent().setView(new NormalShapePicker(bumper).getShape());
    }

    @Override
    public void changeView() {
        super.entity.getViewComponent().setView(new AlternateShapePicker(bumper).getShape());
    }

    @Override
    public void setTimerAction(TimerAction timerAction) {
        action.expire();
        action = timerAction;
    }

    @Override
    public void expireTimerAction() {
        action.expire();
    }

    @Override
    public void accept(Visitor v) {
        bumper.acceptVisitor(v);
    }
}
