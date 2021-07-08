package com.user_management_multidb_api.mysql.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;

	@NotEmpty
	@Size(min = 8)
	@Column(name = "user_name")
	private String userName;
	@NotEmpty
	private String password;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "requested_role_id")
	private int requestedRoleId;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;

	public User() {
		super();
	}

	public User(int id, @NotEmpty @Size(min = 8) String userName, @NotEmpty String password, Date createdDate,
			@NotEmpty int requestedRoleId, Role role) {
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
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", createdDate=" + createdDate
				+ ", requestedRoleId=" + requestedRoleId + ", role=" + role + "]";
	}

}
