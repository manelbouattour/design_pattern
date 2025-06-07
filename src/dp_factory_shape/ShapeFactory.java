package dp_factory_shape;

import javafx.scene.shape.Shape;

//Factory pour la création d'un form(shape)
public interface ShapeFactory {
	Shape create(double startX, double startY, double endX, double endY);
}
//pour la procédure de creation nous avons utilise factory methode et voici le diagramme de classe 
