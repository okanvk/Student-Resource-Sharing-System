package com.vukat.studentresource.exception;

public class ResourceNotFoundExceptionResponse {

    private String resource;

    public ResourceNotFoundExceptionResponse(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
