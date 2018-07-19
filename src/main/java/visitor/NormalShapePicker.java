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
import logic.table.NullTable;
import logic.table.PlayableTable;

public class NormalShapePicker implements Visitor{
    Shape shape;

    public NormalShapePicker(Hittable h) {
        h.acceptVisitor(this);
    }

    @Override
    public void visitKickerBumper(KickerBumper k) {
        shape = new Circle(20, Color.DARKRED);
    }

    @Override
    public void visitPopBumper(PopBumper p) {
        shape = new Circle(18, Color.TEAL);
    }

    @Override
    public void visitSpotTarget(SpotTarget s) {
        shape = new Rectangle(30, 30, Color.PURPLE);
    }

    @Override
    public void visitDropTarget(DropTarget d) {
        shape = new Rectangle(25, 25, Color.DARKOLIVEGREEN);
    }

    @Override
    public void visitNullTable(NullTable n) {

    }

    @Override
    public void visitPlayableTable(PlayableTable p) {

    }

    public Shape getShape() {
        return shape;
    }
}
