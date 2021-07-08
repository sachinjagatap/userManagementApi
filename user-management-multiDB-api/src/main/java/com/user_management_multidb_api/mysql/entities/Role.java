package com.user_management_multidb_api.mysql.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private int id;
	@NotEmpty
	@Column(name = "role_name")
	private String name;
	@NotEmpty
	@Column(name = "role_description")
	private String description;
	
	@OneToOne(mappedBy = "role")
	@JsonIgnore
	private User user;

	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "roles_permissions", joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "role_id"),
	          inverseJoinColumns = @JoinColumn(name = "permission_id",referencedColumnName = "permission_id"))
	private Set<Permission> permissions;

	public Role() {
		super();
	}

	public Role(int id, @NotEmpty String name, @NotEmpty String description, User user,
			 Set<Permission> permissions) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.user = user;
		this.permissions = permissions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", description=" + description + ", user=" + user
				+ ", permissions=" + permissions + "]";
	}

}

