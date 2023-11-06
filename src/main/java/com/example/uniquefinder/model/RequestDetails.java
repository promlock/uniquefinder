package com.example.uniquefinder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

/**
 * Represents details of a unique file search request, including the requested folder, user name, and request date.
 */
@Entity
public class RequestDetails {

	/**
	 * Unique identifier for the request details.
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	/**
	 * The folder path where the unique file search was performed.
	 */
	private String folder;

	/**
	 * The user name associated with the request.
	 */
	private String userName;

	/**
	 * The date and time when the request was made.
	 */
	private LocalDateTime requestDate;

	/**
	 * Default constructor for RequestDetails.
	 */
	public RequestDetails() {
	}

	/**
	 * Constructor for creating RequestDetails with a folder path and a user name. The request date is automatically set to the current date and time.
	 *
	 * @param folder   The folder path where the unique file search was performed.
	 * @param userName The user name associated with the request.
	 */
	public RequestDetails(String folder, String userName) {
		super();
		this.folder = folder;
		this.userName = userName;
		this.requestDate = LocalDateTime.now();
	}

	/**
	 * Get the unique identifier for the request details.
	 *
	 * @return The unique identifier.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Get the folder path where the unique file search was performed.
	 *
	 * @return The folder path.
	 */
	public String getFolder() {
		return folder;
	}

	/**
	 * Set the folder path for the request details.
	 *
	 * @param folder The folder path to set.
	 */
	public void setFolder(String folder) {
		this.folder = folder;
	}

	/**
	 * Get the user name associated with the request.
	 *
	 * @return The user name.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set the user name for the request details.
	 *
	 * @param userName The user name to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Get the date and time when the request was made.
	 *
	 * @return The request date and time.
	 */
	public LocalDateTime getRequestDate() {
		return requestDate;
	}

	/**
	 * Set the date and time when the request was made.
	 *
	 * @param requestDate The request date and time to set.
	 */
	public void setRequestDate(LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * Generates a string representation of the RequestDetails object.
	 *
	 * @return A string containing the values of id, folder, userName, and requestDate.
	 */
	@Override
	public String toString() {
		return "RequestDetails [id=" + id + ", folder=" + folder + ", userName=" + userName + ", requestDate="
				+ requestDate + "]";
	}
	
}
