package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompoundShape extends BaseShape {
	private List<Shape> children = new ArrayList<>();

	public CompoundShape(Shape... components) {
		super(0, 0, Color.BLACK);
		add(components);
	}

	public void add(Shape component) {
		children.add(component);
	}

	public void add(Shape... components) {
		children.addAll(Arrays.asList(components));
	}

	public void remove(Shape component) {
		children.remove(component);
	}

	public void remove(Shape... components) {
		children.removeAll(Arrays.asList(components));
	}

	public void clear() {
		children.clear();
	}

	@Override
	public int getX() {
		if (children.size() == 0) {
			return 0;
		}

		int x = children.get(0).getX();
		for (Shape child : children) {
			if (x < child.getX()) {
				x = child.getX();
			}
		}

		return x;
	}

	@Override
	public int getY() {
		if (children.size() == 0) {
			return 0;
		}

		int y = children.get(0).getY();
		for (Shape child : children) {
			if (y < child.getY()) {
				y = child.getX();
			}
		}

		return y;
	}

	@Override
	public int getWidth() {
		int maxWidth = 0;
		int x = getX();
		for (Shape child : children) {
			int childrenRelatives = child.getX() - x;
			int childWidth = childrenRelatives + child.getWidth();
			if (maxWidth < childWidth) {
				maxWidth = childWidth;
			}
		}
		return maxWidth;
	}

	@Override
	public int getHeight() {
		int maxHeight = 0;
		int y = getY();
		for (Shape child : children) {
			int childrenRelatives = child.getY() - y;
			int childHeight = childrenRelatives + child.getWidth();
			if (maxHeight < childHeight) {
				maxHeight = childHeight;
			}
		}
		return maxHeight;
	}

	@Override
	public void drag() {
		for (Shape child : children) {
			child.drag();
		}
	}

	@Override
	public void drop() {
		for (Shape child : children) {
			child.drop();
		}
	}

	@Override
	public void moveTo(int x, int y) {
		for (Shape child : children) {
			child.moveTo(x, y);
		}
	}

	@Override
	public void moveBy(int x, int y) {
		for (Shape child : children) {
			child.moveBy(x, y);
		}
	}

	@Override
	public boolean isInsideBounds(int x, int y) {
		for (Shape child : children) {
			if (child.isInsideBounds(x, y)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void setColor(Color color) {
		super.setColor(color);
		for (Shape child : children) {
			child.setColor(color);
		}
	}

	@Override
	public void unSelect() {
		super.unSelect();

		for (Shape child : children) {
			child.unSelect();
		}
	}

	public Shape getChildAt(int x, int y) {
		for (Shape child : children) {
			if (child.isInsideBounds(x, y)) {
				return child;
			}
		}

		return null;
	}

	public boolean selectChildAt(int x, int y) {
		Shape child = getChildAt(x, y);
		if (child != null) {
			child.select();
			return true;
		}

		return false;
	}

	public List<Shape> getSelected() {
		List<Shape> selected = new ArrayList<>();
		for (Shape child : children) {
			if (child.isSelected()) {
				selected.add(child);
			}
		}

		return selected;
	}

	@Override
	public void paint(Graphics graphics) {
		if (isSelected()) {
			enableSelectionStyle(graphics);
			graphics.drawRect(getX() - 1, getY() - 1, getWidth() - 1, getHeight() - 1);
			disableSelectionStyle(graphics);
		}

		for (Shape child : children) {
			child.paint(graphics);
		}
	}
}
