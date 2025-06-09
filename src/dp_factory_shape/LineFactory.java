package dp_factory_shape;

import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineFactory implements ShapeFactory {
    private Color strokeColor = Color.BLACK;

    public void setStrokeColor(Color color) {
        this.strokeColor = color;
    }

    @Override
    public Shape create(double startX, double startY, double endX, double endY) {
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(strokeColor);
        line.setStrokeWidth(2);
        return line;
    }
}
