package component;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import entitytype.FlipperType;

public class FlipperComponent extends Component {
    private FlipperType flipperType;
    private float lowerLimit;
    private float upperLimit;
    private int rotationSign;
    private Rotation currentRotation;

    private enum Rotation {
        UP,
        DOWN,
        NONE
    }

    public FlipperComponent(FlipperType type) {
        flipperType = type;
        if (type == FlipperType.LEFTFLIPPER) {
            rotationSign = -1;
            lowerLimit = -rotationSign * 10;
            upperLimit = rotationSign * 50;
        }
        else if (type == FlipperType.RIGHTFLIPPER) {
            rotationSign = 1;
            lowerLimit = -rotationSign * 10;
            upperLimit = rotationSign * 50;
        }
        currentRotation = Rotation.NONE;
    }

    public void startRotation() {
        super.getEntity().getComponent(PhysicsComponent.class).setAngularVelocity(rotationSign * 20);
        currentRotation = Rotation.UP;
    }

    public void stopRotation() {
        super.getEntity().getComponent(PhysicsComponent.class).setAngularVelocity(0);
        currentRotation = Rotation.NONE;
    }

    public void resetRotation() {
        super.getEntity().getComponent(PhysicsComponent.class).setAngularVelocity(-rotationSign * 10);
        currentRotation = Rotation.DOWN;
    }

    public FlipperType getType() {
        return flipperType;
    }

    @Override
    public void onUpdate(double tpf) {
        Entity flipper = super.getEntity();
        if ((rotationSign * lowerLimit >= rotationSign * flipper.getRotation() && currentRotation == Rotation.DOWN)
                || (rotationSign * upperLimit <= rotationSign * flipper.getRotation() && currentRotation == Rotation.UP))
            stopRotation();
    }
}
