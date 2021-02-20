package org.sussanacode.Jersey_Messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.sussanacode.Jersey_Messenger.database.DatabaseClass;
import org.sussanacode.Jersey_Messenger.exception.DataNotFoundException;
import org.sussanacode.Jersey_Messenger.model.Message;

public class MessageService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService(){
        messages.put(1l, new Message(1, "Ammy Smith", "Hello World"));
        messages.put(2l, new Message(2, "Joe Adams", "Hi There!"));
        messages.put(3l, new Message(3, "Zoe Hui", "Post on point"));
        messages.put(4l, new Message(4, "John Doe", "I've been there "));
        messages.put(5l, new Message(5, "Adams White", "Superrrrr great!!!"));
        messages.put(6l, new Message(6, "Suzie Denn", "This is amazzzzzzzzn..."));
        messages.put(7l, new Message(7, "Amber Gembson", "Now that was on point.."));
        messages.put(8l, new Message(8, "Peter Pan", "Amazing"));

    }


    //a method that return a list of all messages
    public List<Message> getAllMessages() {
        return new ArrayList<Message>(messages.values());
    }
    
    
    //filter the message resource to get message by the year they were posted
    public List<Message> getAllMessagesForYear(int year){
    	List<Message> messagesForYear = new ArrayList<>();
    	Calendar calender = Calendar.getInstance();
    	for(Message message : messages.values()) {
    		calender.setTime(message.getCreated());
    		if(calender.get(Calendar.YEAR) == year) {
    			messagesForYear.add(message);
    		}
    		
    	}
    	
    	return messagesForYear;
    	
    }
    
    
    public List<Message> getAllMessagesPaginated(int start, int size){
    	
    	//convert the values of the messages to a list 
    	ArrayList<Message> listMessage = new ArrayList<Message>(messages.values());
    	
    	//so if the start + size is greater than the message list then am empty list is return
    	if(start + size > listMessage.size()) return new ArrayList<Message>();
    	
    	//return the list as a sublist with the start and the start + size of the page 
    	return listMessage.subList(start, start + size);
    	
    }
    
    
    //get message by id
    public Message getMessages(long id) {
    	Message message = messages.get(id);
    	
    	if(message == null) {
    		throw new DataNotFoundException("Message with id " + id + " not found.");
    	}
    	
        return message;

    }

    public Message addMessage(Message message) {
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);
        return message;

    }

    public Message updateMessage(Message message) {
        if(message.getId() <= 0){
            return null;
        }
        messages.put(message.getId(), message);
        return message;

    }

    public Message removeMessage(long id) {
        return messages.remove(id);

    }



















//    //a method that return a list of all messages
//    public List<Message> getAllMessages() {
//        Message msg1 = new Message(1l, "Ammy Smith", "Hello World");
//        Message msg2 = new Message(2l, "Joe Adams", "Hi There!");
//        Message msg3 = new Message(3l, "Zoe Hui", "Post on point");
//        Message msg4 = new Message(4l, "John Doe", "I've been there ");
//
//        //Add to the List
//        List<Message> msgList = new ArrayList<>();
//        msgList.add(msg1);
//        msgList.add(msg2);
//        msgList.add(msg3);
//        msgList.add(msg4);
//
//        return msgList;
//
//    }
}
