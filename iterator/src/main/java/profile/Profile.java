package profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import refactoring_guru.Main;

public class Profile {

	private final String name;
	private final String email;


	private final Map<String, List<String>> contacts = new HashMap<>();


	public Profile(String name, String email, String... contacts) {
		this.name = name;
		this.email = email;

		for (String contact : contacts) {
			String[] parts = contact.split(":");
			String contactType = "friend",
					contactEmail;

			if (parts.length == 1) {
				contactEmail = parts[0];
			} else {
				contactType = parts[0];
				contactEmail = parts[1];
			}

			if (!this.contacts.containsKey(contactType)) {
				this.contacts.put(contactType, new ArrayList<>());
			}
			this.contacts.get(contactType).add(contactEmail);
		}
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public List<String> getContacts(String contactType) {
		if (!this.contacts.containsKey(contactType)) {
			this.contacts.put(contactType, new ArrayList<>());
		}

		return contacts.get(contactType);
	}


}
