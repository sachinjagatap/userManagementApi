package com.user_management_multidb_api.dto;

import java.sql.Date;
import com.user_management_multidb_api.mysql.entities.Role;

public class UserDto {

	private int id;
	private String userName;
	private String password;
	private Date createdDate;
	private int requestedRoleId;
	private Role role;
	
	public UserDto() {
		super();
	}

	public UserDto(int id, String userName, String password, Date createdDate, int requestedRoleId, Role role) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.createdDate = createdDate;
		this.requestedRoleId = requestedRoleId;
		this.role = role;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", userName=" + userName + ", password=" + password + ", createdDate="
				+ createdDate + ", requestedRoleId=" + requestedRoleId + ", role=" + role + "]";
	}
	
}
