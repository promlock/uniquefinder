package com.example.uniquefinder.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Set;

/**
 * Represents a response object containing details of a unique file search request, including the required path,
 * a message, and a set of unique file names.
 */
@JsonInclude(Include.NON_NULL)
public class RequestDetailsResponse {

    /**
     * The required path for the file search request.
     */
    private String requiredPath;

    /**
     * A message associated with the response, typically used for error messages.
     */
    private String message;

    /**
     * A set of unique file names found in the specified directory.
     */
    private Set<String> uniqueFileList;

    /**
     * Default constructor for RequestDetailsResponse.
     */
    public RequestDetailsResponse() {
    }

    /**
     * Constructor for creating a RequestDetailsResponse with a message.
     *
     * @param message The message to include in the response.
     */
    public RequestDetailsResponse(String message) {
        super();
        this.message = message;
    }

    /**
     * Constructor for creating a RequestDetailsResponse with a required path and a list of unique file names.
     *
     * @param requiredPath   The required path for the file search request.
     * @param uniqueFileList A set of unique file names found in the specified directory.
     */
    public RequestDetailsResponse(String requiredPath, Set<String> uniqueFileList) {
        super();
        this.requiredPath = requiredPath;
        this.uniqueFileList = uniqueFileList;
    }

    /**
     * Get the required path for the file search request.
     *
     * @return The required path.
     */
    public String getRequiredPath() {
        return requiredPath;
    }

    /**
     * Set the required path for the file search request.
     *
     * @param requiredPath The required path to set.
     */
    public void setRequiredPath(String requiredPath) {
        this.requiredPath = requiredPath;
    }

    /**
     * Get the message associated with the response.
     *
     * @return The message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message associated with the response.
     *
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Get the set of unique file names found in the specified directory.
     *
     * @return A set of unique file names.
     */
    public Set<String> getUniqueFileList() {
        return uniqueFileList;
    }

    /**
     * Set the set of unique file names found in the specified directory.
     *
     * @param uniqueFileList A set of unique file names to set.
     */
    public void setUniqueFileList(Set<String> uniqueFileList) {
        this.uniqueFileList = uniqueFileList;
    }

    /**
     * Generates a string representation of the RequestDetailsResponse object.
     *
     * @return A string containing the values of requiredPath, message, and uniqueFileList.
     */
    @Override
    public String toString() {
        return "RequestDetailsResponse [requiredPath=" + requiredPath + ", message=" + message + ", uniqueFileList="
                + uniqueFileList + "]";
    }

}
