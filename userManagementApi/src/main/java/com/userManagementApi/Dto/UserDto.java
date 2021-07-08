package com.userManagementApi.Dto;

public class UserDto {
	private int userId;
	private String userName;
	private String password;
	private int currentRoleId;
	private int requestedRoleId;
	
	public UserDto() {
		super();
	}

	public UserDto(int userId, String userName, String password, int currentRoleId, int requestedRoleId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.currentRoleId = currentRoleId;
		this.requestedRoleId = requestedRoleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public int getCurrentRoleId() {
		return currentRoleId;
	}

	public void setCurrentRoleId(int currentRoleId) {
		this.currentRoleId = currentRoleId;
	}

	public int getRequestedRoleId() {
		return requestedRoleId;
	}

	public void setRequestedRoleId(int requestedRoleId) {
		this.requestedRoleId = requestedRoleId;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userName=" + userName + ", password=" + password + ", currentRoleId="
				+ currentRoleId + ", requestedRoleId=" + requestedRoleId + "]";
	}

	
	
}
