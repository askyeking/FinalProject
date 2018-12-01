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

@Entity
@Table(name="payment_method")
public class PaymentMethod {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(name="account_number")
	private String acctNo;
	
	private String cvc;
	
	@Column(name="expiration_date")
	private String expirationDate;
	
	@Column(name="account_holder_name")
	private String cardHolderName;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="payment_type_id")
	private PaymentType paymentType;
	
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public String getCvc() {
		return cvc;
	}
	public void setCvc(String cvc) {
		this.cvc = cvc;
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
	public int getId() {
		return id;
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
		PaymentMethod other = (PaymentMethod) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PaymentMethod [id=" + id + ", name=" + name + ", acctNo=" + acctNo + ", cvc=" + cvc
				+ ", expirationDate=" + expirationDate + ", cardHolderName=" + cardHolderName + ", customer=" + customer
				+ ", paymentType=" + paymentType + "]";
	}
	
	
	public PaymentMethod(int id, String name, String acctNo, String cvc, String expirationDate, String cardHolderName,
			Customer customer, PaymentType paymentType) {
		super();
		this.id = id;
		this.name = name;
		this.acctNo = acctNo;
		this.cvc = cvc;
		this.expirationDate = expirationDate;
		this.cardHolderName = cardHolderName;
		this.customer = customer;
		this.paymentType = paymentType;
	}
	public PaymentMethod() {
		super();
	}
	
	
	
	
	
	
}
