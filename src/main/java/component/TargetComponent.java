package component;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.TimerAction;
import javafx.util.Duration;
import logic.gameelements.target.Target;
import visitor.AlternateShapePicker;
import visitor.NormalShapePicker;
import visitor.Visitor;

public class TargetComponent extends Component implements HittableComponent {
    private Target target;
    private TimerAction action;

    public TargetComponent(Target t) {
        target = t;
        action = new TimerAction(Duration.UNKNOWN, () -> {}, 0);
    }

    @Override
    public void hit() {
        target.hit();
    }

    @Override
    public boolean isAlternateState() {
        return !target.isActive();
    }

    @Override
    public void resetState() {
        target.reset();
        resetView();
    }

    @Override
    public void resetView() {
        super.entity.getViewComponent().setView(new NormalShapePicker(target).getShape());
    }

    @Override
    public void changeView() {
        super.entity.getViewComponent().setView(new AlternateShapePicker(target).getShape());
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
        target.acceptVisitor(v);
    }
}
