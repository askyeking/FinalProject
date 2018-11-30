package com.skilldistillery.swag.entities;

public class PaymentType {
	
	private int id;
	
	private String type;
	
	private String provider;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
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
		PaymentType other = (PaymentType) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PaymentType [id=" + id + ", type=" + type + ", provider=" + provider + "]";
	}

	public PaymentType() {
		
	}

	public PaymentType(int id, String type, String provider) {
		super();
		this.id = id;
		this.type = type;
		this.provider = provider;
	}
	
	
}
