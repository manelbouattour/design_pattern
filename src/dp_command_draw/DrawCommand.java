package dp_command_draw;

import javafx.scene.shape.Shape;

public interface DrawCommand {
    void execute();
    void undo();
}