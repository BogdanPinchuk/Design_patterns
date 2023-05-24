package shapes;

import visitor.Visitor;

public class Rectangle extends Dot {
    private int width;
    private int height;

    public Rectangle(int id, int x, int y, int width, int height) {
        super(id, x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitRectangle(this);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public int getId() {
        return super.getId();
    }
}
