package refactoring_guru;

import factory.Dialog;
import factory.HtmlDialog;
import factory.WindowsDialog;

// https://refactoring.guru/uk/design-patterns/factory-method/java/example#example-0--Demo-java
public class Main {

	private static Dialog dialog;

	public static void main(String[] args) {

		configure();
		runBusinessLogic();

		System.out.println("Finish!");
	}

	static void configure() {
		String s = System.getProperty("os.name");

		if (System.getProperty("os.name").equals("Windows 10")){
			dialog = new WindowsDialog();
		} else {
			dialog = new HtmlDialog();
		}
	}

	static void runBusinessLogic(){
		dialog.renderWindow();
	}
}
