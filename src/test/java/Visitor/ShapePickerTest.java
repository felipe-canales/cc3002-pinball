package Visitor;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import org.junit.*;
import static org.junit.Assert.*;

import visitor.ShapePicker;

public class ShapePickerTest {
    private ShapePicker picker;

    @Before
    public void setUp() {

    }

    @Test
    public void visitNormalKickerBumper() {
        Shape shape = new ShapePicker(new KickerBumper()).getShape();
        assertTrue(shape instanceof  Circle);
        assertEquals(20, ((Circle)shape).getRadius(), 0.5);
        assertEquals(Color.DARKRED, shape.getFill());
    }

    @Test
    public void visitUpgradedKickerBumper() {
        KickerBumper k = new KickerBumper();
        k.upgrade();
        Shape shape = new ShapePicker(k).getShape();
        assertTrue(shape instanceof  Circle);
        assertEquals(20, ((Circle)shape).getRadius(), 0.5);
        assertEquals(Color.RED, shape.getFill());
    }

    @Test
    public void visitNormalPopBumper() {
        Shape shape = new ShapePicker(new PopBumper()).getShape();
        assertTrue(shape instanceof  Circle);
        assertEquals(18, ((Circle)shape).getRadius(), 0.5);
        assertEquals(Color.TEAL, shape.getFill());
    }

    @Test
    public void visitUpgradedPopBumper() {
        PopBumper p = new PopBumper();
        p.upgrade();
        Shape shape = new ShapePicker(p).getShape();
        assertTrue(shape instanceof  Circle);
        assertEquals(18, ((Circle)shape).getRadius(), 0.5);
        assertEquals(Color.AQUAMARINE, shape.getFill());
    }

    @Test
    public void visitNormalDropTarget() {
        Shape shape = new ShapePicker(new DropTarget()).getShape();
        assertTrue(shape instanceof  Rectangle);
        assertEquals(25, ((Rectangle)shape).getHeight(), 0.5);
        assertEquals(25, ((Rectangle)shape).getWidth(), 0.5);
        assertEquals(Color.DARKOLIVEGREEN, shape.getFill());
    }

    @Test
    public void visitDisabledDropTarget() {
        DropTarget d = new DropTarget();
        d.hit();
        Shape shape = new ShapePicker(d).getShape();
        assertTrue(shape instanceof  Rectangle);
        assertEquals(25, ((Rectangle)shape).getHeight(), 0.5);
        assertEquals(25, ((Rectangle)shape).getWidth(), 0.5);
        assertEquals(Color.DARKGRAY, shape.getFill());
    }

    @Test
    public void visitNormalSpotTarget() {
        Shape shape = new ShapePicker(new SpotTarget()).getShape();
        assertTrue(shape instanceof  Rectangle);
        assertEquals(30, ((Rectangle)shape).getHeight(), 0.5);
        assertEquals(30, ((Rectangle)shape).getWidth(), 0.5);
        assertEquals(Color.PURPLE, shape.getFill());
    }

    @Test
    public void visitDisabledSpotTarget() {
        SpotTarget s = new SpotTarget();
        s.hit();
        Shape shape = new ShapePicker(s).getShape();
        assertTrue(shape instanceof  Rectangle);
        assertEquals(30, ((Rectangle)shape).getHeight(), 0.5);
        assertEquals(30, ((Rectangle)shape).getWidth(), 0.5);
        assertEquals(Color.GRAY, shape.getFill());
    }
}
