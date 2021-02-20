package org.sussanacode.Jersey_Messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.sussanacode.Jersey_Messenger.model.*;


//a static class with static maps that maps to message and profile class
public class DatabaseClass {
	//creating a map for id so that any service class can access it
    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<String, Profile> profiles = new HashMap<>();


    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }

}
