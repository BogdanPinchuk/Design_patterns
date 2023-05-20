package components;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import mediator.Mediator;

public class SaveButton extends JButton implements Component {
	private Mediator mediator;

	public SaveButton() {
		super("Save");
	}

	@Override
	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}

	@Override
	protected void fireActionPerformed(ActionEvent event) {
		mediator.saveChanges();
	}

	@Override
	public String getName() {
		return "SaveButton";
	}
}
