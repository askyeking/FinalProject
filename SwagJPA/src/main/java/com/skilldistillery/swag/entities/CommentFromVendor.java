package com.skilldistillery.swag.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * CommentFromVendor entity
 * Mapped fields to the table in the DB using JPA and Hibernate.
 * Methods are standard getters and setters, hashcode and equals (using unique ID) and toString
 */
@Entity
@Table(name="comment_from_vendor")
public class CommentFromVendor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="vendor_id")
	private Vendor poster;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="item_rental_id")
	private ItemRental itemRental;
	
	private String comment;
	
	@Column(name="post_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date postDate;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vendor getPoster() {
		return poster;
	}

	public void setPoster(Vendor poster) {
		this.poster = poster;
	}

	public ItemRental getItemRental() {
		return itemRental;
	}

	public void setItemRental(ItemRental itemRental) {
		this.itemRental = itemRental;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
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
		CommentFromVendor other = (CommentFromVendor) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommentFromVendor [id=" + id + ", comment=" + comment + ", postDate=" + postDate + "]";
	}

	public CommentFromVendor() {
		super();
	}

	public CommentFromVendor(int id, Vendor poster, ItemRental itemRental, String comment, Date postDate) {
		super();
		this.id = id;
		this.poster = poster;
		this.itemRental = itemRental;
		this.comment = comment;
		this.postDate = postDate;
	}
	
	
	
	
	
}
