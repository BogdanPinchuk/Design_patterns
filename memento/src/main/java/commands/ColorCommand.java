package commands;

import editor.Editor;
import java.awt.Color;
import shapes.Shape;

public class ColorCommand implements Command {
	private final Editor editor;
	private final Color color;

	public ColorCommand(Editor editor, Color color) {
		this.editor = editor;
		this.color = color;
	}

	@Override
	public String getName() {
		return "Colorize: " + color.toString();
	}

	@Override
	public void execute() {
		for (Shape child : editor.getShapes().getSelected()) {
			child.setColor(color);
		}
	}
}
