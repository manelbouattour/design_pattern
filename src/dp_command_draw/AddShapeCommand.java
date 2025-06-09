package dp_command_draw;

import dp_repository.DrawingModel;
import javafx.scene.shape.Shape;

public class AddShapeCommand implements DrawCommand {
    private final DrawingModel model;
    private final Shape shape;
    
    public AddShapeCommand(DrawingModel model, Shape shape) {
        if (model == null || shape == null) {
            throw new IllegalArgumentException("Model and shape cannot be null");
        }
        this.model = model;
        this.shape = shape;
    }
    
    @Override
    public void execute() {
        model.addShape(shape);
    }
    
    @Override
    public void undo() {
        model.removeShape(shape);
    }
}