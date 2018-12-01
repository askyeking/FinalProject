package com.skilldistillery.swag.entities;

public class VendorPaymentType {
	
//	private int vendorId;
//	private int paymentTypeId;
	
	private String acctNo;
	
	private String expirationDate;
	
	private String cardHolderName;

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
	public String toString() {
		return "VendorPaymentType [acctNo=" + acctNo + ", expirationDate=" + expirationDate + ", cardHolderName="
				+ cardHolderName + "]";
	}
	
	public VendorPaymentType() {
		
	}

	public VendorPaymentType(String acctNo, String expirationDate, String cardHolderName) {
		super();
		this.acctNo = acctNo;
		this.expirationDate = expirationDate;
		this.cardHolderName = cardHolderName;
	}
	
	
}
