package mediator;

import components.Component;
import javax.swing.ListModel;

public interface Mediator {
	void addNewNote(Note note);

	void deleteNote();

	void getInfoFromList(Note note);

	void saveChanges();

	void markNote();

	void clear();

	void sendToFilter(ListModel<?> listModel);

	void setElementList(ListModel<?> list);

	void registerComponent(Component component);

	void hideElements(boolean flag);

	void createGUI();
}
