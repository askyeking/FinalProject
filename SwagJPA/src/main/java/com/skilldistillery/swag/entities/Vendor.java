package com.skilldistillery.swag.entities;

public class Vendor {
	
	private int id;
	
//	private int userID;
	
	private String imgUrl;
	
	private String about;
	
	private String displayName;
	
	private boolean isActive;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

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
		Vendor other = (Vendor) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vendor [id=" + id + ", imgUrl=" + imgUrl + ", about=" + about + ", displayName=" + displayName
				+ ", isActive=" + isActive + "]";
	}
	
	public Vendor() {
		
	}

	public Vendor(int id, String imgUrl, String about, String displayName, boolean isActive) {
		super();
		this.id = id;
		this.imgUrl = imgUrl;
		this.about = about;
		this.displayName = displayName;
		this.isActive = isActive;
	}

	
}
