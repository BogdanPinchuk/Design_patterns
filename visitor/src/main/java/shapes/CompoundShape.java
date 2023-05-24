package shapes;

import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class CompoundShape implements Shape {
    private final int id;
    public List<Shape> shapes = new ArrayList<>();

    public CompoundShape(int id) {
        this.id = id;
    }

    @Override
    public void move(int x, int y) {
        System.out.println("Move shape");
    }

    @Override
    public void draw() {
        System.out.println("Draw shape");
    }

    public int getId() {
        return id;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitCompoundGraphics(this);
    }

    public void add(Shape shape) {
        shapes.add(shape);
    }
}
