package dp_factory_shape;

import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleFactory implements ShapeFactory {
    private Color strokeColor = Color.BLACK;

    public void setStrokeColor(Color color) {
        this.strokeColor = color;
    }

    @Override
    public Shape create(double startX, double startY, double endX, double endY) {
        double width = Math.abs(endX - startX);
        double height = Math.abs(endY - startY);
        
        Rectangle rectangle = new Rectangle(Math.min(startX, endX), Math.min(startY, endY), width, height);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(strokeColor);
        rectangle.setStrokeWidth(1.5);
        
        return rectangle;
    }
}
//design pattern : kol haja lchnou 
//chnoma logger kifeh 
//
