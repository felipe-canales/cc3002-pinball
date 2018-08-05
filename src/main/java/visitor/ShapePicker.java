package visitor;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;
import logic.gameelements.Hittable;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;

public class ShapePicker implements HittableVisitor{
    private Shape shape;

    public ShapePicker(Hittable h) {
        h.acceptVisitor(this);
    }

    @Override
    public void visitKickerBumper(KickerBumper k) {
        if (k.isUpgraded())
            shape = new Circle(20, Color.RED);
        else
            shape = new Circle(20, Color.DARKRED);
    }

    @Override
    public void visitPopBumper(PopBumper p) {
        if (p.isUpgraded())
            shape = new Circle(18, Color.AQUAMARINE);
        else
            shape = new Circle(18, Color.TEAL);
    }

    @Override
    public void visitSpotTarget(SpotTarget s) {
        if (s.isActive())
            shape = new Rectangle(30, 30, Color.PURPLE);
        else
            shape = new Rectangle(30, 30, Color.GRAY);
    }

    @Override
    public void visitDropTarget(DropTarget d) {
        if (d.isActive())
            shape = new Rectangle(25, 25, Color.DARKOLIVEGREEN);
        else
            shape = new Rectangle(25, 25, Color.DARKGRAY);
    }

    public Shape getShape() {
        return shape;
    }
}
