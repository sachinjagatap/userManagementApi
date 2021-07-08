package com.user_management_multidb_api.mysql.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.PersistenceConstructor;

@Entity(name ="permissions")
public class Permission {

	@Id
	@Column(name = "permission_id")
	private int id;
	
	@NotEmpty
	@Column(name = "permission_name")
	private String name;
	
	@NotEmpty
	@Column(name = "permission_description")
	private String description;
    
	public Permission() {
		super();
	}

	@PersistenceConstructor
	public Permission(int id, @NotEmpty String name, @NotEmpty String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permission other = (Permission) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", description=" + description +  "]";
	}



}
