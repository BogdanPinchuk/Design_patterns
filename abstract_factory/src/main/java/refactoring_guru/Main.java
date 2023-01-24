package refactoring_guru;

import factories.GUIFactory;
import factories.MacOSFactory;
import factories.WindowsFactory;

public class Main {
	public static void main(String[] args) {
		Application app = configureApplication();
		app.paint();

		System.out.println("Finished!");
	}

	private static Application configureApplication(){
		Application app;
		GUIFactory factory;

		String osName = System.getProperty("os.name").toLowerCase();

		if (osName.contains("mac")){
			factory = new MacOSFactory();
		} else {
			factory = new WindowsFactory();
		}

		app = new Application(factory);
		return app;
	}
}
