package dp_factory_shape;

import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleFactory implements ShapeFactory {
    @Override
    public Shape create(double startX, double startY, double endX, double endY) {
        // Calcul de la largeur et hauteur
        double width = Math.abs(endX - startX);
        double height = Math.abs(endY - startY);
        
        // Calcul du point de départ (coin supérieur gauche)
        double x = Math.min(startX, endX);
        double y = Math.min(startY, endY);
        
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        return rectangle;
    }
}