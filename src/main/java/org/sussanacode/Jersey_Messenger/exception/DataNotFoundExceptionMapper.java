package org.sussanacode.Jersey_Messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.sussanacode.Jersey_Messenger.model.ErrorMessage;


@Provider //this register this class into jax-rs so that the class can mapper to the exceptionMapper
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 404, "http://sussanacode.org");
		
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	}

}
