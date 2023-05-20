package components;

import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import mediator.Mediator;

public class TextBox extends JTextArea implements Component {
	private Mediator mediator;

	@Override
	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}

	@Override
	protected void processComponentKeyEvent(KeyEvent e) {
		mediator.markNote();
	}

	@Override
	public String getName() {
		return "TextBox";
	}
}
