package dp_observer_selection_shape;

import java.util.ArrayList;
import java.util.List;
import dp_enumerations.ShapeType;

public class ShapeSelectionSubjectImpl implements ShapeSelectionSubject {
    private List<ShapeObserver> observers = new ArrayList<>();
    
    @Override
    public void addObserver(ShapeObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }
    
    @Override
    public void removeObserver(ShapeObserver observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers(ShapeType selectedShape) {
        for (ShapeObserver observer : new ArrayList<>(observers)) {
            observer.update(selectedShape);
        }
    }
}