package dp_factory_shape;

import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleFactory implements ShapeFactory {
    private Color strokeColor = Color.BLACK;

    public void setStrokeColor(Color color) {
        this.strokeColor = color;
    }

    @Override
    public Shape create(double startX, double startY, double endX, double endY) {
        double radius = 40;
        Circle circle = new Circle(startX, startY, radius);
        circle.setFill(Color.BLACK);
        circle.setStroke(strokeColor);
        circle.setStrokeWidth(1.5);
        return circle;
    }
}
