package shapes;

public class Circle extends Shape {

	public int radius;

	public Circle() {
	}

	public Circle(Circle circle) {
		super(circle);
		if (circle != null) {
			this.radius = circle.radius;
		}
	}

	@Override
	public Shape clone() {
		return new Circle(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Circle) || !super.equals(obj)) {
			return false;
		}
		Circle circle = (Circle) obj;
		return circle.radius == radius;
	}
}
