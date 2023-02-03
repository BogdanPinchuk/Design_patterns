package some_cool_media_library;

import java.util.HashMap;

public interface ThirdPartyYouTobeLib {
	HashMap<String, Video> popularVideos();

	Video getVideo(String videoId);
}
