package factory;

import buttons.Button;
import buttons.HtmlButton;

/**
 * HTML Dialog will produce HTML buttons
 */
public class HtmlDialog extends Dialog {

	@Override
	public Button createButton() {
		return new HtmlButton();
	}
}
