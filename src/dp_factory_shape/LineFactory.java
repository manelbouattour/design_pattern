package dp_factory_shape;

import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineFactory implements ShapeFactory {
    @Override
    public Shape create(double startX, double startY, double endX, double endY) {
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2); //  c 'est l'épaisseur du trait
        return line;
    }
}
