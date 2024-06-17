package com.codewithhrishi.exception;

public class ResourceNotFound extends RuntimeException {
	private String resource;
	private String fieldname;
	private String fieldvalue;
	public ResourceNotFound(String resource, String fieldname, String fieldvalue) {
		super(String.format("%s not found for %s with %s ",resource, fieldname,fieldvalue));
		this.resource = resource;
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;
	}

}
