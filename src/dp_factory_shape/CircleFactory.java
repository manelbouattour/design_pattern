package dp_factory_shape;

import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleFactory implements ShapeFactory {
    @Override
    public Shape create(double startX, double startY, double endX, double endY) {
        double radius = 40;
        Circle circle = new Circle(startX, startY, radius);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1.5); // c'est l'épaisseur du contour
        return circle;
    }
}
