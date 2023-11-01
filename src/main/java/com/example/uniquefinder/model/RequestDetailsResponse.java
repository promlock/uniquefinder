package com.example.uniquefinder.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Set;


@JsonInclude(Include.NON_NULL)
public class RequestDetailsResponse {

    private String requiredPath;
    private String message;
    private Set<String> uniqueFileList;


    public RequestDetailsResponse() {
    }

    public RequestDetailsResponse(String message) {
        super();
        this.message = message;
    }

    public RequestDetailsResponse(String requiredPath, Set<String> uniqueFileList) {
        super();
        this.requiredPath = requiredPath;
        this.uniqueFileList = uniqueFileList;
    }

    public String getRequiredPath() {
        return requiredPath;
    }

    public void setRequiredPath(String requiredPath) {
        this.requiredPath = requiredPath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<String> getUniqueFileList() {
        return uniqueFileList;
    }

    public void setUniqueFileList(Set<String> uniqueFileList) {
        this.uniqueFileList = uniqueFileList;
    }

    @Override
    public String toString() {
        return "RequestDetailsResponse [requiredPath=" + requiredPath + ", message=" + message + ", uniqueFileList="
                + uniqueFileList + "]";
    }

}
