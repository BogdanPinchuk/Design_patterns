package commands;

import editor.Editor;

public abstract class Command {
	public Editor editor;
	private String backup;

	Command(Editor editor) {
		this.editor = editor;
	}

	public void backup(){
		backup = editor.textField.getText();
	}

	public void undo(){
		editor.textField.setText(backup);
	}

	public abstract boolean execute();
}
