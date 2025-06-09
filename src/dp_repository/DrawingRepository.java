package dp_repository;


import java.util.List;


import javafx.scene.shape.Shape;

public interface DrawingRepository {
    void save(String name, List<Shape> shapes);
    List<Shape> load(String name);
}