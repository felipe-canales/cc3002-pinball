package Component;

import component.FlipperComponent;
import gamefactory.InteractiveEntityFactory;
import javafx.application.Platform;
import org.junit.*;

import static org.junit.Assert.*;

import com.almasb.fxgl.entity.Entity;

public class FlipperComponentTest {
    private Entity lFlipper;
    private Entity rFlipper;

    @Before
    public void setUp() {
        lFlipper = InteractiveEntityFactory.newLeftFlipper();
        rFlipper = InteractiveEntityFactory.newRightFlipper();
    }

    @Test
    public void startRotation() {
        double initAngle = lFlipper.getRotation();
        lFlipper.getComponent(FlipperComponent.class).startRotation();
        //assertNotEquals(initAngle, lFlipper.getRotation(),1);
    }

    @AfterClass
    public static void stopTest() {
        Platform.exit();
    }
}
