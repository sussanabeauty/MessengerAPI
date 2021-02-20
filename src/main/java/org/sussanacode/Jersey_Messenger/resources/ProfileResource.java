package org.sussanacode.Jersey_Messenger.resources;


import org.sussanacode.Jersey_Messenger.model.Profile;
import org.sussanacode.Jersey_Messenger.service.ProfileService;

import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;



@Path("profiles")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class ProfileResource {
	
	private ProfileService profileService = new ProfileService();
	
	
	//get all profile
	@GET 
	public List<Profile> getProfiles(){
		return profileService.getAllProfiles();
	}
	
	//add a profile
	@POST 
	public Profile addProfile(Profile profile) {
		return profileService.addProfile(profile);
	}
	
	//get profile from the profile resources by profileName
	@GET
	@Path("{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName) {
		return profileService.getProfile(profileName);
		
	}
	
	
	//update the profile resources
	@PUT
	@Path("{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
		profile.setFirstName(profileName);
		return profileService.updateProfile(profile);
		
	}
	
	//delete profile from the profile resources
	 @DELETE
	 @Path("{profileName}")
	 public void deleteProfile(@PathParam("profileName") String profileName){
	    	profileService.removeProfile(profileName);
	 }
	    
	

}
