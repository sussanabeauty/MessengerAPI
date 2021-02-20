package org.sussanacode.Jersey_Messenger.resources;


import org.sussanacode.Jersey_Messenger.model.Message;
import org.sussanacode.Jersey_Messenger.service.MessageService;

import java.net.*;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;



@Path("messages")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class MessageResource {

   
	//implementing the CRUD Methods 
	
    //making a call to message service class
    MessageService messageService = new MessageService();

    //get all messages
    @GET
    public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
    	
    	//check if getMessage is by year or by page or return all messages
    	if(filterBean.getYear() > 0) {
    		return messageService.getAllMessagesForYear(filterBean.getYear());
    	}
    	//do >= instead of > because this a zero base result
    	if(filterBean.getStart() >= 0 && filterBean.getSize() >= 0) { 
    		return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
    	}
        return messageService.getAllMessages();
    }


    //get message by id
    @GET
    @Path("/{messageId}")
    public Message getMessageById(@PathParam("messageId") long messageId, @Context UriInfo uriInfo){
    	Message message = messageService.getMessages(messageId);	
    	message.addLink(getUriForSelf(uriInfo, message), "self");
    	message.addLink(getUriForProfile(uriInfo, message), "profile");
    	message.addLink(getUriForComments(uriInfo, message), "comments");
    	
    	
    	return message;
    	
        //return messageService.getMessages(messageId);
    }


	private String getUriForComments(UriInfo uriInfo, Message message) {
		
		URI uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
		    	.build();
				
		return uri.toString();	
		
	}


	private String getUriForProfile(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
		    	.path(message.getAuthor())
		    	.build();
				
		return uri.toString();
	}


	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
	    	.path(MessageResource.class)
	    	.path(Long.toString(message.getId()))
	    	.build().toString();
		return uri;
	}
     
    
    //adding a new message resource to the Messages
    @POST
	public Response addMessage(Message message, @Context UriInfo uriInfo){
    	
    	Message newMessage = messageService.addMessage(message);
    	String newId = String.valueOf(newMessage.getId());
    	URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
    	
    	return Response.created(uri).entity(newMessage).build();
    	
	 }
//    
//    @POST
//	public Message addMessage(Message message){	
//    	return messageService.addMessage(message);
//	 }
    
    
    //updating a new message resource to the Messages
    @PUT
    @Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long Id, Message message){
    	message.setId(Id);
    	return messageService.updateMessage(message);
	
	 }
    
    
    //deleting a new message resource
    @DELETE
    @Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long Id){
    	messageService.removeMessage(Id);
	 }
    
    
    //mapping to the comment resource
    //@GET 
    @Path("{messageId}/comments")
    public CommentResource getCommentResource() {
    	return new CommentResource(); 
    }
    
  


}

