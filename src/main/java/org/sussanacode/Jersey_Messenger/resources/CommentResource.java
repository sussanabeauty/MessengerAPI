package org.sussanacode.Jersey_Messenger.resources;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.sussanacode.Jersey_Messenger.model.Comment;
import org.sussanacode.Jersey_Messenger.service.CommentService;

@Path("/")
@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})

public class CommentResource {
	
	private CommentService commentService = new CommentService();
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
		return commentService.getAllComments(messageId); 
	}
	
	
	@GET
	@Path("{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return commentService.getComment(messageId, commentId);
	}
	
	
	@POST
	public Comment addComment(@PathParam("messageId") long messageId, Comment comment) {
		return commentService.addComment(messageId, comment);
	}
		
	
	@PUT
	@Path("{commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentID, Comment comment) {
		comment.setId(commentID);
		return commentService.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("{commentId}")
	public Comment deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentID) {
		return commentService.getComment(messageId, commentID);
	}

	
	
}
