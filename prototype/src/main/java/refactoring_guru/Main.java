package refactoring_guru;

import cache.BundledShapeCache;
import java.util.ArrayList;
import java.util.List;
import shapes.Circle;
import shapes.Rectangle;
import shapes.Shape;

public class Main {
	public static void main(String[] args) {
		List<Shape> shapes = new ArrayList<>();
		List<Shape> shapesCopy = new ArrayList<>();

		Circle circle = new Circle();
		circle.x = 10;
		circle.y = 20;
		circle.radius = 15;
		circle.color = "Red";
		shapes.add(circle);

		Circle anotherCircle = (Circle) circle.clone();
		shapes.add(anotherCircle);

		Rectangle rectangle = new Rectangle();
		rectangle.width = 10;
		rectangle.height = 20;
		rectangle.color = "Blue";
		shapes.add(rectangle);

		cloneAndComplate(shapes, shapesCopy);

		System.out.println("\n next task:\n");

		BundledShapeCache cache = new BundledShapeCache();

		Shape shape1 = cache.get("Big green circle");
		Shape shape2 = cache.get("Medium blue rectangle");
		Shape shape3 = cache.get("Medium blue rectangle");

		if (shape1 != shape2 && !shape1.equals(shape2)) {
			System.out.println("Big green circle != Medium blue rectangle (yay!)");
		} else {
			System.out.println("Big green circle == Medium blue rectangle (booo!)");
		}

		if (shape2 != shape3) {
			System.out.println("Medium blue rectangles are two different objects (yay!)");
			if (shape2.equals(shape3)) {
				System.out.println("And they are identical (yay!)");
			} else {
				System.out.println("But they are not identical (booo!)");
			}
		} else {
			System.out.println("Rectangle objects are the same (booo!)");
		}

		System.out.println("\nFinished!");
	}

	private static void cloneAndComplate(List<Shape> shapes, List<Shape> shapesCopy) {
		for (Shape shape : shapes) {
			shapesCopy.add(shape.clone());
		}

		for (int i = 0; i < shapes.size(); i++) {
			if (shapes.get(i) != shapesCopy.get(i)) {
				System.out.println(i + ": Shapes are different objects (yay!)");
				if (shapes.get(i).equals(shapesCopy.get(i))) {
					System.out.println(i + ": And they are identical (yay!)");
				} else {
					System.out.println(i + ": But they are not identical (booo!)");
				}
			} else {
				System.out.println(i + ": Shape objects are the same (booo!)");
			}
		}
	}
}
