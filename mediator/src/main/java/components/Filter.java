package components;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import mediator.Mediator;
import mediator.Note;

public class Filter extends JTextField implements Component {
	private Mediator mediator;
	private ListModel<?> listModel;

	public Filter() {
	}

	@Override
	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}

	@Override
	protected void processComponentKeyEvent(KeyEvent keyEvent) {
		String start = getText();
		searchElements(start);
	}

	public void setList(ListModel<?> listModel) {
		this.listModel = listModel;
	}

	private void searchElements(String s) {
		if (this.listModel == null){
			return;
		}

		if (s.isEmpty()){
			mediator.setElementList(this.listModel);
			return;
		}

		ArrayList<Note> notes = new ArrayList<>();
		for (int i = 0; i < this.listModel.getSize(); i++){
			notes.add((Note) this.listModel.getElementAt(i));
		}

		DefaultListModel<Note> listModel = new DefaultListModel<>();
		for (Note note : notes){
			if (note.getName().contains(s)){
				listModel.addElement(note);
			}
		}

		mediator.setElementList(listModel);
	}

	@Override
	public String getName() {
		return "Filter";
	}
}
