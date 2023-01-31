package refactoring_guru;

import facade.VideoConversionFacade;
import java.io.File;

public class Main {
	public static void main(String[] args) {
		VideoConversionFacade converter = new VideoConversionFacade();
		File mp4Video = converter.convertVideo("youtubevideo.ogg", "mp4");

		System.out.println("\nFinished!");
	}
}
