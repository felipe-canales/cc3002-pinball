package component;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.time.Timer;
import entitytype.FlipperType;
import javafx.util.Duration;

public class FlipperComponent extends Component {
    private FlipperType flipperType;

    public FlipperComponent(FlipperType type) {
        flipperType = type;
    }

    public void startRotation() {
        int velocity = 0;
        if (flipperType == FlipperType.RIGHTFLIPPER)
            velocity = 20;
        else if (flipperType == FlipperType.LEFTFLIPPER)
            velocity = -20;
        super.entity.getComponent(PhysicsComponent.class).setAngularVelocity(velocity);
    }

    public void stopRotation() {
        super.entity.getComponent(PhysicsComponent.class).setAngularVelocity(0);
    }

    public void resetRotation(Timer timer) {
        PhysicsComponent p = super.entity.getComponent(PhysicsComponent.class);
        double angleDif = 0;
        if (flipperType == FlipperType.LEFTFLIPPER)
            angleDif = (30 - super.entity.getRotation()) / 6;
        else if (flipperType == FlipperType.RIGHTFLIPPER)
            angleDif = (-30 - super.entity.getRotation()) / 6;
        p.setAngularVelocity(angleDif);
        timer.runOnceAfter(() -> stopRotation(), Duration.millis(1000/9));
    }
}
