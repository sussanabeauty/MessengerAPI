package org.sussanacode.Jersey_Messenger.resources;

import javax.ws.rs.*;

@Path("secure")
public class SecureResource {
	
	@GET
	@Path("message")
	public String secureMethod() {
		return "login info...";
	}

}
