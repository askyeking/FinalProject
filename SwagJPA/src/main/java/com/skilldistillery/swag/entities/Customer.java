package com.skilldistillery.swag.entities;

public class Customer {

	private int id;
	
	private String displayName;
	
	private boolean isActive;
	
	private String avatarURL;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", displayName=" + displayName + ", isActive=" + isActive + ", avatarURL="
				+ avatarURL + "]";
	}

	public Customer(int id, String displayName, boolean isActive, String avatarURL) {
		super();
		this.id = id;
		this.displayName = displayName;
		this.isActive = isActive;
		this.avatarURL = avatarURL;
	}

	public Customer() {
		super();
	}
	
	
	
	
	
	
}
