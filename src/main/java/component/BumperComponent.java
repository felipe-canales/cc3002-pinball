package component;

import com.almasb.fxgl.entity.component.Component;
import logic.gameelements.bumper.Bumper;
import visitor.AlternateShapePicker;
import visitor.NormalShapePicker;


public class BumperComponent extends Component implements HittableComponent {
    private Bumper bumper;

    public BumperComponent(Bumper b) {
        bumper = b;
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
}
