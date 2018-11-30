package com.skilldistillery.swag.entities;

public class PaymentMethod {
	
	private int id;
	
	private String name;
	
	private String acctNo;
	private String cvc;
	private String expirationDate;
	private String cardHolderName;
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
				+ ", expirationDate=" + expirationDate + ", cardHolderName=" + cardHolderName + "]";
	}
	public PaymentMethod(int id, String name, String acctNo, String cvc, String expirationDate, String cardHolderName) {
		super();
		this.id = id;
		this.name = name;
		this.acctNo = acctNo;
		this.cvc = cvc;
		this.expirationDate = expirationDate;
		this.cardHolderName = cardHolderName;
	}
	public PaymentMethod() {
		super();
	}
	
	
	
	
	
	
}
