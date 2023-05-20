package components;


import javax.swing.DefaultListModel;
import javax.swing.JList;
import mediator.Mediator;
import mediator.Note;

public class List extends JList implements Component {
	private Mediator mediator;
	private final DefaultListModel<Note> LIST_MODEL;

	public List(DefaultListModel<Note> listModel) {
		super(listModel);
		this.LIST_MODEL = listModel;
		setModel(listModel);
		this.setLayoutOrientation(VERTICAL);
		Thread thread = new Thread(new Hide(this));
		thread.start();
	}

	@Override
	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}

	public void addElement(Note note) {
		LIST_MODEL.addElement(note);
		int index = LIST_MODEL.size() - 1;
		setSelectedIndex(index);
		ensureIndexIsVisible(index);
		mediator.sendToFilter(LIST_MODEL);
	}

	public void deleteElement() {
		int index = this.getSelectedIndex();
		try {
			LIST_MODEL.remove(index);
			mediator.sendToFilter(LIST_MODEL);
		} catch (ArrayIndexOutOfBoundsException ignored) {
		}
	}

	public Note getCurrentElement() {
		return (Note) getSelectedValue();
	}

	@Override
	public String getName() {
		return "List";
	}

	private class Hide implements Runnable {
		private final List list;

		public Hide(List list) {
			this.list = list;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}

				if (list.isSelectionEmpty()) {
					mediator.hideElements(true);
				} else {
					mediator.hideElements(false);
				}
			}
		}
	}
}
