package com.skilldistillery.concerts.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "concert_act")
public class ConcertAct {

	@EmbeddedId
	private ConcertActId id;

	@Column(name = "lineup_position")
	private int lineupPosition;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "concert_id")
	@MapsId(value = "concertId")
	private Concert concert;

	@ManyToOne
	@JoinColumn(name = "band_id")
	@MapsId(value = "bandId")
	private Band band;

	public ConcertAct() {
		super();
	}

	public ConcertActId getId() {
		return id;
	}

	public void setId(ConcertActId id) {
		this.id = id;
	}

	public int getLineupPosition() {
		return lineupPosition;
	}

	public void setLineupPosition(int lineupPosition) {
		this.lineupPosition = lineupPosition;
	}

	public Concert getConcert() {
		return concert;
	}

	public void setConcert(Concert concert) {
		this.concert = concert;
	}

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
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
		ConcertAct other = (ConcertAct) obj;
		return id.equals(other.getId());
	}

	@Override
	public String toString() {
		return "ConcertAct [id=" + id + ", lineupPosition=" + lineupPosition + "]";
	}

}
