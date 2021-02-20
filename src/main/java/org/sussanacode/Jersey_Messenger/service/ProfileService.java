package org.sussanacode.Jersey_Messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sussanacode.Jersey_Messenger.database.DatabaseClass;
import org.sussanacode.Jersey_Messenger.model.Profile;

public class ProfileService {
	
	
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public ProfileService(){
    	
    	profiles.put("ammysmith", new Profile(1,"ammysmith",  "Ammy", "Smith"));
    	profiles.put("joeadams", new Profile(2, "joeadams",  "Joe", "Adams"));
        profiles.put("zoehui", new Profile(3, "zoehui", "Zoe", "Hui"));
        profiles.put("johndoe", new Profile(4, "johndoe", "John", "Doe"));
        profiles.put("adamswhite", new Profile(5, "adamswhite", "Adams", "White"));
        profiles.put("suziedenn", new Profile(6, "suziedenn", "Suzie", "Denn"));
        profiles.put("ambergembson", new Profile(7, "ambergembson", "Amber", "Gembson"));
        profiles.put("peterpan", new Profile(8, "peterpan", "Peter", "Pan"));

    }


    //a method that return a list of all messages
    public List<Profile> getAllProfiles() {
        return new ArrayList<Profile>(profiles.values());
    }

    public Profile getProfile(String profileName) {
        return profiles.get(profileName);

    }

    public Profile addProfile(Profile profile) {
    	profile.setId(profiles.size() + 1);
        profiles.put(profile.getProfileName(), profile);
        return profile;

    }

    public Profile updateProfile(Profile profile) {
        if(profile.getProfileName().isEmpty()){
            return null;
        }
        profiles.put(profile.getProfileName(), profile);
        return profile;

    }

    public Profile removeProfile(String profileName) {
        return profiles.remove(profileName);

    }
	
	

}
