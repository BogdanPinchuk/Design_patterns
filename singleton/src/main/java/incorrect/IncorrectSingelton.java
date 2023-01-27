package incorrect;

public class IncorrectSingelton {
	private static IncorrectSingelton instance;
	public String value;

	private IncorrectSingelton(String value) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		this.value = value;
	}

	public static IncorrectSingelton getInstance(String value) {
		if (instance == null){
			instance = new IncorrectSingelton(value);
		}
		return instance;
	}
}
