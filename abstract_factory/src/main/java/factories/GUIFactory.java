package factories;

import buttons.Button;
import checkboxes.Checkbox;

public interface GUIFactory {
	Button createButton();
	Checkbox createCheckbox();
}
