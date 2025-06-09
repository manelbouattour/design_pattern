package dp_strategy_draw;

import dp_factory_shape.ShapeFactory;
import javafx.scene.shape.Shape;

public class FactoryBasedStrategy implements DrawStrategy {
    private final ShapeFactory factory;

    public FactoryBasedStrategy(ShapeFactory factory) {
        this.factory = factory;
    }

    @Override
    public Shape draw(double startX, double startY, double endX, double endY) {
        return factory.create(startX, startY, endX, endY);
    }
}