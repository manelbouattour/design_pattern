package dp_observer_selection_shape;


import dp_enumerations.ShapeType;

public interface ShapeSelectionSubject {
    void addObserver(ShapeObserver observer);
    void removeObserver(ShapeObserver observer);
    void notifyObservers(ShapeType selectedShape);
}