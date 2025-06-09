package dp_observer_selection_shape;

import dp_enumerations.ShapeType;
public interface ShapeObserver {
    void update(ShapeType selectedShape);
}
