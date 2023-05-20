package social_networks;

import iterators.LinkedInIterator;
import iterators.ProfileIterator;
import java.util.ArrayList;
import java.util.List;
import profile.Profile;

public class LinkedIn implements SocialNetwork {
	private final List<Profile> contacts;

	public LinkedIn(List<Profile> cache) {
		if (cache != null) {
			this.contacts = cache;
		} else {
			this.contacts = new ArrayList<>();
		}
	}

	public Profile requestContactInfoFromLinkedInAPI(String profileEmail) {
		simulateNetworkLatency();
		System.out.println("LinkedIn: Loading profile '" + profileEmail + "' over the network");

		return findContact(profileEmail);
	}

	public List<String> requestRelatedContactsFromLinkedInAPI(String profileEmail,
															  String contactType) {
		simulateNetworkLatency();
		System.out.println("LinkedIn: Loading '" + contactType + "' list of '" + profileEmail +
				"' over the network...");

		Profile profile = findContact(profileEmail);
		if (profile != null) {
			return profile.getContacts(contactType);
		}

		return null;
	}

	private Profile findContact(String profileEmail) {
		for (Profile profile : contacts) {
			if (profile.getEmail().equals(profileEmail)) {
				return profile;
			}
		}

		return null;
	}

	private void simulateNetworkLatency() {
		try {
			Thread.sleep(250);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public ProfileIterator createFriendsIterator(String profileEmail) {
		return new LinkedInIterator(this, "friends", profileEmail);
	}

	@Override
	public ProfileIterator createCoworkersIterator(String profileEmail) {
		return new LinkedInIterator(this, "coworkers", profileEmail);
	}
}
