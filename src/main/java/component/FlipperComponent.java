package component;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import entitytype.FlipperType;

public class FlipperComponent extends Component {
    private FlipperType flipperType;

    public FlipperComponent(FlipperType type) {
        flipperType = type;
    }

    public void startRotation() {
        int velocity = 0;
        if (flipperType == FlipperType.RIGHTFLIPPER)
            velocity = 10;
        else if (flipperType == FlipperType.LEFTFLIPPER)
            velocity = -10;
        super.entity.getComponent(PhysicsComponent.class).setAngularVelocity(velocity);
    }

    public void stopRotation() {
        super.entity.getComponent(PhysicsComponent.class).setAngularVelocity(0);
    }

    public void resetRotation() {
        PhysicsComponent p = super.entity.getComponent(PhysicsComponent.class);
        p.setBodyType(BodyType.STATIC);
        super.entity.setRotation(20);
        p.setBodyType(BodyType.KINEMATIC);
    }
}
