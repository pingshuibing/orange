package com.qut.spc.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.Responses;

public class InvalidArgumentException extends WebApplicationException {

	private static final long serialVersionUID = 4617727113561585926L;

	public InvalidArgumentException() {
		super(Responses.notAcceptable().build());
	}

	public InvalidArgumentException(String message) {
		super(Response.status(400).entity(message).type("text/plain").build());
	}

}
