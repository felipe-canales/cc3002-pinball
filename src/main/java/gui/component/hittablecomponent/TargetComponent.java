package gui.component.hittablecomponent;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.TimerAction;
import javafx.util.Duration;
import logic.gameelements.target.Target;
import visitor.ShapePicker;
import visitor.HittableVisitor;

/**
 * Component that allows interacions between an {@link com.almasb.fxgl.entity.Entity} and a {@link Target}.
 */
public class TargetComponent extends Component implements HittableComponent {
    private Target target;
    private TimerAction action;

    /**
     * Constructor
     *
     * @param t Instance of Target.
     */
    public TargetComponent(Target t) {
        target = t;
        action = new TimerAction(Duration.UNKNOWN, () -> {}, 0);
    }

    @Override
    public void hit() {
        target.hit();
    }

    /**
     * Returns true if the Target is deactivated, else returns false.
     *
     * @return if the Target is deactivated.
     */
    @Override
    public boolean isAlternateState() {
        return !target.isActive();
    }

    @Override
    public void resetState() {
        target.reset();
        changeView();
    }

    @Override
    public void changeView() {
        super.entity.getViewComponent().setView(new ShapePicker(target).getShape());
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
    public void accept(HittableVisitor v) {
        target.acceptVisitor(v);
    }
}
