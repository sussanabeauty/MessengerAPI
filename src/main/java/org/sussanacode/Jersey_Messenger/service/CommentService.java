package org.sussanacode.Jersey_Messenger.service;

import java.util.*;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.sussanacode.Jersey_Messenger.database.DatabaseClass;
import org.sussanacode.Jersey_Messenger.model.*;

public class CommentService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	
	public List<Comment> getAllComments(long messageId){	
		//get the comments for the message instance
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.get(commentId);
	}
	
	
//	public Comment getComment(long messageId, long commentId){
//		
//		ErrorMessage errorMessage = new ErrorMessage("Not_Found", 404, "http://sussanacode.org");
//		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();
//		Message message = messages.get(messageId);
//		if(message == null) {
//			throw new WebApplicationException(response);
//		}
//		
//		Map<Long, Comment> comments = messages.get(messageId).getComments();
//		Comment comment = comments.get(commentId);
//		if(comment == null) {
//			throw new WebApplicationException(response);
//		}
//		return comment;
//	}
	

		
	public Comment addComment(long messageId, Comment comment){	
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment){	
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if(comment.getId() <= 0){
			return null;
		}
		
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId){	
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
	
	
	
	
	
	
	

}
