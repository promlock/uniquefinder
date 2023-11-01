package com.example.uniquefinder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class RequestDetails {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String folder;
	
	private String userName;
	
	private LocalDateTime requestDate;

	
	public RequestDetails() {
	}

	public RequestDetails(String folder, String userName) {
		super();
		this.folder = folder;
		this.userName = userName;
		this.requestDate = LocalDateTime.now();
	}

	public Long getId() {
		return id;
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

	public LocalDateTime getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}

	@Override
	public String toString() {
		return "RequestDetails [id=" + id + ", folder=" + folder + ", userName=" + userName + ", requestDate="
				+ requestDate + "]";
	}
	
}
