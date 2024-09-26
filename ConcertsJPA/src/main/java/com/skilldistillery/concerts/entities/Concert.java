package com.skilldistillery.concerts.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Concert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="doors_time")
	private LocalTime doorsTime;
	
	@Column(name="start_time")
	private LocalTime startTime;
	
	@Column(name="day_of_event")
	private LocalDate dayOfEvent;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="age_requirement")
	private String ageRequirement;
	
	@Column(name="ticket_purchase_link")
	private String ticketPurchaseLink;
	
	@Column(name="website_url")
	private String websiteUrl;
	
	@OneToMany(mappedBy="concert")
	private List<ConcertAct> bands;
	
	@ManyToOne
	@JoinColumn(name="venue_id")
	private Venue venue;

	public Concert() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalTime getDoorsTime() {
		return doorsTime;
	}

	public void setDoorsTime(LocalTime doorsTime) {
		this.doorsTime = doorsTime;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalDate getDayOfEvent() {
		return dayOfEvent;
	}

	public void setDayOfEvent(LocalDate dayOfEvent) {
		this.dayOfEvent = dayOfEvent;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAgeRequirement() {
		return ageRequirement;
	}

	public void setAgeRequirement(String ageRequirement) {
		this.ageRequirement = ageRequirement;
	}

	public String getTicketPurchaseLink() {
		return ticketPurchaseLink;
	}

	public void setTicketPurchaseLink(String ticketPurchaseLink) {
		this.ticketPurchaseLink = ticketPurchaseLink;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	
	

	public List<ConcertAct> getBands() {
		return bands;
	}

	public void setBands(List<ConcertAct> bands) {
		this.bands = bands;
	}
	

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Concert other = (Concert) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Concert [id=" + id + ", doorsTime=" + doorsTime + ", startTime=" + startTime + ", dayOfEvent="
				+ dayOfEvent + ", imageUrl=" + imageUrl + ", ageRequirement=" + ageRequirement + ", ticketPurchaseLink="
				+ ticketPurchaseLink + ", websiteUrl=" + websiteUrl + "]";
	}
	
	
	
}
