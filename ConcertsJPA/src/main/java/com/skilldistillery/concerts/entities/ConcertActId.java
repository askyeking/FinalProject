package com.skilldistillery.concerts.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ConcertActId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="concert_id")
	private int concertId;
	
	@Column(name="band_id")
	private int bandId;

	public ConcertActId() {
		super();
	}

	public ConcertActId(int concertId, int bandId) {
		super();
		this.concertId = concertId;
		this.bandId = bandId;
	}

	public int getConcertId() {
		return concertId;
	}

	public void setConcertId(int concertId) {
		this.concertId = concertId;
	}

	public int getBandId() {
		return bandId;
	}

	public void setBandId(int bandId) {
		this.bandId = bandId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bandId, concertId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConcertActId other = (ConcertActId) obj;
		return bandId == other.bandId && concertId == other.concertId;
	}

	@Override
	public String toString() {
		return "ConcertActId [concertId=" + concertId + ", bandId=" + bandId + "]";
	}
	
	
	

}
