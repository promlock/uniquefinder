package com.example.uniquefinder.model;

public class RequestDetailsDTO {

	
	private String folder;
	
	private String userName;
	
	private String requestDate;


	public RequestDetailsDTO(String folder, String userName, String dateTime) {
		super();
		this.folder = folder;
		this.userName = userName;
		this.requestDate = dateTime;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	@Override
	public String toString() {
		return "RequestDetails [folder=" + folder + ", userName=" + userName + ", requestDate="
				+ requestDate + "]";
	}
	
}
