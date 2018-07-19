package component;

import com.almasb.fxgl.entity.component.Component;
import logic.gameelements.target.Target;
import visitor.AlternateShapePicker;
import visitor.NormalShapePicker;

public class TargetComponent extends Component implements HittableComponent {
    Target target;

    public TargetComponent(Target t) {
        target = t;
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
}
