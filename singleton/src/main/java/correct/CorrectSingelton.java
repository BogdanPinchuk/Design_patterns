package correct;

public class CorrectSingelton {
	private static volatile CorrectSingelton instance;
	public String value;

	private CorrectSingelton(String value) {
		this.value = value;
	}

	public static CorrectSingelton getInstance(String value) {
		CorrectSingelton singleton = instance;
		if (singleton != null) {
			return singleton;
		}
		synchronized (CorrectSingelton.class) {
			if (instance == null) {
				instance = new CorrectSingelton(value);
			}
			return instance;
		}
	}

}
