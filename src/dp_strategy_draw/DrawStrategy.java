package dp_strategy_draw;
import javafx.scene.shape.Shape;

public interface DrawStrategy {
    Shape draw(double startX, double startY, double endX, double endY);
}
