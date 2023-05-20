package social_networks;

import iterators.FacebookIterator;
import iterators.ProfileIterator;
import java.util.ArrayList;
import java.util.List;
import profile.Profile;

public class Facebook implements SocialNetwork {

	private List<Profile> profiles;

	public Facebook(List<Profile> cache) {
		if (cache != null) {
			this.profiles = cache;
		} else {
			this.profiles = new ArrayList<>();
		}
	}

	public Profile requestProfileFromFacebook(String profileEmail) {
		simulateNetworkLatency();
		System.out.println("Facebook: Loading profile '" + profileEmail + "' over the network");

		return findProfile(profileEmail);
	}

	public List<String> requestProfileFriendsFromFacebook(String profileEmail, String contactType) {
		simulateNetworkLatency();
		System.out.println("Facebook: Loading '" + contactType + "' list of '" + profileEmail +
				"' over the network...");

		Profile profile = findProfile(profileEmail);
		if (profile != null) {
			return profile.getContacts(contactType);
		}

		return null;
	}

	private Profile findProfile(String profileEmail) {
		for (Profile profile : profiles) {
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
		return new FacebookIterator(this, "friends", profileEmail);
	}

	@Override
	public ProfileIterator createCoworkersIterator(String profileEmail) {
		return new FacebookIterator(this, "coworkers", profileEmail);
	}
}
