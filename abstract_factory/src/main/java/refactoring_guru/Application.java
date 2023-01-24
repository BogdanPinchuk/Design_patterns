package refactoring_guru;

import buttons.Button;
import checkboxes.Checkbox;
import factories.GUIFactory;

public class Application {
	private final Button button;
	public final Checkbox checkbox;

	public Application(GUIFactory factory){
		button = factory.createButton();
		checkbox = factory.createCheckbox();
	}

	public void paint(){
		button.paint();
		checkbox.paint();
	}
}
