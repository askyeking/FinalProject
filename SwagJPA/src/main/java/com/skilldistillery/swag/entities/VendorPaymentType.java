package com.skilldistillery.swag.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * VendorPaymentType entity
 * ** Not currently used by the front-end, but tested via Postman **
 * Mapped fields to the table in the DB using JPA and Hibernate.
 * Methods are standard getters and setters, hashcode and equals (using unique ID) and toString
 */
@Entity
@Table(name="vendor_payment_type")
public class VendorPaymentType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="vendor_id")
	private Vendor vendor;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="payment_type_id")
	private PaymentType paymentType;
	
	@Column(name="account_number")
	private String acctNo;
	
	@Column(name="expiration_date")
	private String expirationDate;
	
	@Column(name="Account_holder_name")
	private String cardHolderName;

	
	
	public int getId() {
		return id;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
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
		VendorPaymentType other = (VendorPaymentType) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VendorPaymentType [id=" + id + ", vendor=" + vendor + ", paymentType=" + paymentType + ", acctNo="
				+ acctNo + ", expirationDate=" + expirationDate + ", cardHolderName=" + cardHolderName + "]";
	}
	
	public VendorPaymentType() {
		
	}

	public VendorPaymentType(int id, Vendor vendor, PaymentType paymentType, String acctNo, String expirationDate,
			String cardHolderName) {
		super();
		this.id = id;
		this.vendor = vendor;
		this.paymentType = paymentType;
		this.acctNo = acctNo;
		this.expirationDate = expirationDate;
		this.cardHolderName = cardHolderName;
	}

	
	
	
	
}
