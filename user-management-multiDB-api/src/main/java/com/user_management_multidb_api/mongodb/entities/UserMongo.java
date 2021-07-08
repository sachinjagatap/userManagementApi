package com.user_management_multidb_api.mongodb.entities;

import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(value = "users")
public class UserMongo {

	@Id
	@Field(name = "userId")
	private int id;
	
	@NotEmpty
	@Size(min = 8)
	private String userName;
	@NotEmpty
	private String password;
	private Date createdDate;
	private int requestedRoleId;


	public UserMongo() {
		super();
	}


	@PersistenceConstructor
	public UserMongo(int id,  @NotEmpty @Size(min = 8) String userName, @NotEmpty String password,
			Date createdDate, int requestedRoleId) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.createdDate = createdDate;
		this.requestedRoleId = requestedRoleId;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public int getRequestedRoleId() {
		return requestedRoleId;
	}


	public void setRequestedRoleId(int requestedRoleId) {
		this.requestedRoleId = requestedRoleId;
	}


	@Override
	public String toString() {
		return "UserMongo [id=" + id + ", userName=" + userName + ", password=" + password
				+ ", createdDate=" + createdDate + ", requestedRoleId=" + requestedRoleId + "]";
	}

	
}
