package Component;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import entitytype.EntityType;
import gamefactory.InteractiveEntityFactory;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.Target;
import component.hittablecomponent.TargetComponent;
import org.junit.*;
import visitor.ShapePicker;

import static org.junit.Assert.*;

public class TargetComponentTest {
    private TargetComponent tc;
    private Target t;

    @Before
    public void setUp() {
        t = new DropTarget();
        tc = new TargetComponent(t);
    }

    @Test
    public void hit() {
        tc.hit();
        assertFalse(t.isActive());
    }

    @Test
    public void isAlternateStateTrue() {
        tc.hit();
        assertTrue(tc.isAlternateState());
    }

    @Test
    public void isAlternateStateFalse() {
        assertFalse(tc.isAlternateState());
    }

    @Test
    public void resetState() {
        Entity e = Entities.builder()
                .with(new PhysicsComponent(), new TargetComponent(t))
                .build();
        t.hit();
        e.getComponent(TargetComponent.class).resetState();
        assertTrue(t.isActive());
    }

    @Test
    public void changeView() {
        Entity e = InteractiveEntityFactory.newTarget(0, 0, t);
    }
}
