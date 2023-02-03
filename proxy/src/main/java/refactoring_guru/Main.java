package refactoring_guru;

import downloader.YouTubeDownloader;
import proxy.YouTubeCacheProxy;
import some_cool_media_library.ThirdPartyYouTubeClass;

public class Main {
	public static void main(String[] args) {
		YouTubeDownloader naiveDownloader = new YouTubeDownloader(new ThirdPartyYouTubeClass());
		YouTubeDownloader smartDownloader = new YouTubeDownloader(new YouTubeCacheProxy());

		long naive = test(naiveDownloader);
		long smart = test(smartDownloader);

		System.out.print("Time saved by caching proxy: " + (naive - smart) + "ms");

		System.out.println("\nFinished!");
	}

	private static long test(YouTubeDownloader downloader) {
		long startTime = System.currentTimeMillis();

		// User behavior in our app:
		downloader.renderPopularVideos();
		downloader.renderVideoPage("catzzzzzzzzz");
		downloader.renderPopularVideos();
		downloader.renderVideoPage("dancesvideoo");
		// Users might visit the same page quite often.
		downloader.renderVideoPage("catzzzzzzzzz");
		downloader.renderVideoPage("someothervid");

		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Time elapsed: " + estimatedTime + "ms");
		return estimatedTime;
	}
}
