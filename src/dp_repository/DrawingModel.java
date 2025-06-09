// === Correction : DrawingModel.java ===
package dp_repository;

import db_singleton_DB.DatabaseConnection;
import javafx.scene.shape.Shape;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DrawingModel {
    private final List<Shape> shapes = new ArrayList<>();

    public void addShape(Shape shape) {
        shapes.add(shape);
        saveShapeToDatabase(shape);
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    private void saveShapeToDatabase(Shape shape) {
        try (Connection conn = DatabaseConnection.getInstance().getConnection()) {
            String sql = "INSERT INTO shapes (type, x1, y1, x2, y2, color) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                String color = "black"; // Couleur par défaut
                
                if (shape instanceof javafx.scene.shape.Rectangle r) {
                    ps.setString(1, "RECTANGLE");
                    ps.setDouble(2, r.getX());
                    ps.setDouble(3, r.getY());
                    ps.setDouble(4, r.getX() + r.getWidth());
                    ps.setDouble(5, r.getY() + r.getHeight());
                    color = r.getStroke() != null ? r.getStroke().toString() : "black";
                } else if (shape instanceof javafx.scene.shape.Circle c) {
                    ps.setString(1, "CIRCLE");
                    ps.setDouble(2, c.getCenterX());
                    ps.setDouble(3, c.getCenterY());
                    ps.setDouble(4, c.getCenterX());
                    ps.setDouble(5, c.getCenterY());
                    color = c.getStroke() != null ? c.getStroke().toString() : "black";
                } else if (shape instanceof javafx.scene.shape.Line l) {
                    ps.setString(1, "LINE");
                    ps.setDouble(2, l.getStartX());
                    ps.setDouble(3, l.getStartY());
                    ps.setDouble(4, l.getEndX());
                    ps.setDouble(5, l.getEndY());
                    color = l.getStroke() != null ? l.getStroke().toString() : "black";
                } else {
                    return;
                }

                ps.setString(6, color);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'enregistrement dans la BDD : " + e.getMessage());
        }
    }
}


