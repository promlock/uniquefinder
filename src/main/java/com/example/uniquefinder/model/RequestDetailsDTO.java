package com.example.uniquefinder.model;

/**
 * Represents a Data Transfer Object (DTO) for encapsulating request details, including the folder path, user name, and request date as strings.
 */
public class RequestDetailsDTO {

	/**
	 * The folder path where the unique file search was performed.
	 */
	private String folder;

	/**
	 * The user name associated with the request.
	 */
	private String userName;

	/**
	 * The request date as a string.
	 */
	private String requestDate;

	/**
	 * Constructor for creating a RequestDetailsDTO with folder path, user name, and request date as strings.
	 *
	 * @param folder     The folder path where the unique file search was performed.
	 * @param userName   The user name associated with the request.
	 * @param dateTime The request date as a string.
	 */
	public RequestDetailsDTO(String folder, String userName, String dateTime) {
		super();
		this.folder = folder;
		this.userName = userName;
		this.requestDate = dateTime;
	}

	/**
	 * Get the folder path.
	 *
	 * @return The folder path.
	 */
	public String getFolder() {
		return folder;
	}

	/**
	 * Set the folder path.
	 *
	 * @param folder The folder path to set.
	 */
	public void setFolder(String folder) {
		this.folder = folder;
	}

	/**
	 * Get the user name.
	 *
	 * @return The user name.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set the user name.
	 *
	 * @param userName The user name to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Get the request date as a string.
	 *
	 * @return The request date as a string.
	 */
	public String getRequestDate() {
		return requestDate;
	}

	/**
	 * Set the request date as a string.
	 *
	 * @param requestDate The request date as a string to set.
	 */
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * Generates a string representation of the RequestDetailsDTO object.
	 *
	 * @return A string containing the values of folder, userName, and requestDate.
	 */
	@Override
	public String toString() {
		return "RequestDetails [folder=" + folder + ", userName=" + userName + ", requestDate="
				+ requestDate + "]";
	}
	
}
