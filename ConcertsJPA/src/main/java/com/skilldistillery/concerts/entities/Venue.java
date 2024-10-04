package com.skilldistillery.concerts.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Venue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String city;
	
	private String state;
	
	private String zip;
	
	private String street;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="website_url")
	private String websiteUrl;
	
	@JsonIgnore
	@OneToMany(mappedBy="venue")
	private List<Concert> concerts;
	
	public Venue() {
		super();
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	
	public List<Concert> getConcerts() {
		return concerts;
	}

	public void setConcerts(List<Concert> concerts) {
		this.concerts = concerts;
	}

	@Override
	public String toString() {
		return "Venue [id=" + id + ", name=" + name + ", city=" + city + ", state=" + state + ", zip=" + zip
				+ ", street=" + street + ", imageUrl=" + imageUrl + ", websiteUrl=" + websiteUrl + "]";
	}
	
	
}
