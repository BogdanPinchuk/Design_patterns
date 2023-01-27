package shapes;

import java.util.Objects;

public abstract class Shape {
	public int x;
	public int y;
	public String color;

	public Shape() {
	}

	public Shape(Shape shape) {
		if (shape != null) {
			this.x = shape.x;
			this.y = shape.y;
			this.color = shape.color;
		}
	}

	public abstract Shape clone();

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Shape)) {
			return false;
		}
		Shape shape = (Shape) obj;
		return shape.x == x && shape.y == y && Objects.equals(shape.color, color);
	}
}
