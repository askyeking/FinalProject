package com.skilldistillery.swag.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Vendor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="image_url")
	private String imgUrl;
	
	private String about;
	
	@Column(name="display_name")
	private String displayName;
	
	@Column(name="active")
	private boolean isActive;
	
	@JsonIgnore
	@OneToMany(mappedBy="poster")
	private List<CommentFromVendor> comments;
	
	@OneToMany(mappedBy="vendor")
	private List<InventoryItem> listedItems;
	
	
	
	
	

	public List<CommentFromVendor> getComments() {
		return comments;
	}

	public void setComments(List<CommentFromVendor> comments) {
		this.comments = comments;
	}

	public List<InventoryItem> getListedItems() {
		return listedItems;
	}

	public void setListedItems(List<InventoryItem> listedItems) {
		this.listedItems = listedItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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
