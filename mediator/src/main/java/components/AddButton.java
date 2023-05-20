package components;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import mediator.Mediator;
import mediator.Note;

public class AddButton extends JButton implements Component {

	private Mediator mediator;

	public AddButton() {
		super("Add");
	}

	@Override
	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}

	@Override
	protected void fireActionPerformed(ActionEvent event) {
		mediator.addNewNote(new Note());
	}

	@Override
	public String getName() {
		return "AddButton";
	}
}
