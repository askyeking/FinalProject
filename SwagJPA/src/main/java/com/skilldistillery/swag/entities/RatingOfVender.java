package com.skilldistillery.swag.entities;

public class RatingOfVender {
	
	private int rating;
	
	private String comment;

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "RatingOfVender [rating=" + rating + ", comment=" + comment + "]";
	}

	public RatingOfVender(int rating, String comment) {
		super();
		this.rating = rating;
		this.comment = comment;
	}

	public RatingOfVender() {
		super();
	}
	
	

}
