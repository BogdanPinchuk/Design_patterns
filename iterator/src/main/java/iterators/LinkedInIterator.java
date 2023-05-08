package iterators;

import java.util.ArrayList;
import java.util.List;
import profile.Profile;
import social_networks.LinkedIn;

public class LinkedInIterator implements ProfileIterator {

	private final LinkedIn linkedIn;
	private final String type;
	private final String email;

	private int currentPosition = 0;
	private final List<String> emails = new ArrayList<>();
	private final List<Profile> contacts = new ArrayList<>();

	public LinkedInIterator(LinkedIn linkedIn, String type, String email) {
		this.linkedIn = linkedIn;
		this.type = type;
		this.email = email;
	}

	private void lazyLoad() {
		if (emails.size() == 0) {
			List<String> profiles =
					linkedIn.requestRelatedContactsFromLinkedInAPI(this.email, this.type);
			for (String profile : profiles) {
				this.emails.add(profile);
				this.contacts.add(null);
			}
		}
	}

	@Override
	public boolean hasNext() {
		lazyLoad();
		return currentPosition < emails.size();
	}

	@Override
	public Profile getNext() {
		if (!hasNext()) {
			return null;
		}

		String friendEmail = emails.get(currentPosition);
		Profile friendProfile = contacts.get(currentPosition);

		if (friendProfile == null) {
			friendProfile = linkedIn.requestContactInfoFromLinkedInAPI(friendEmail);
			contacts.set(currentPosition, friendProfile);
		}
		currentPosition++;
		return friendProfile;
	}

	@Override
	public void reset() {
		currentPosition = 0;
	}
}
