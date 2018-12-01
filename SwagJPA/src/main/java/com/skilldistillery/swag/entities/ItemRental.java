package com.skilldistillery.swag.entities;

import java.util.Date;

public class ItemRental {
	
	private int id;
	
//	private int invItemId;
//	private int custId;
	
	private boolean isPaid;
	
	private Date startDate;
	
	private Date endDate;
	
	private double paidAmount;;
	
	private boolean isActive;
	
	private String transactionInfo;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getTransactionInfo() {
		return transactionInfo;
	}

	public void setTransactionInfo(String transactionInfo) {
		this.transactionInfo = transactionInfo;
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
		ItemRental other = (ItemRental) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemRental [id=" + id + ", isPaid=" + isPaid + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", paidAmount=" + paidAmount + ", isActive=" + isActive + ", transactionInfo=" + transactionInfo
				+ "]";
	}
	
	public ItemRental() {
		
	}

	public ItemRental(int id, boolean isPaid, Date startDate, Date endDate, double paidAmount, boolean isActive,
			String transactionInfo) {
		super();
		this.id = id;
		this.isPaid = isPaid;
		this.startDate = startDate;
		this.endDate = endDate;
		this.paidAmount = paidAmount;
		this.isActive = isActive;
		this.transactionInfo = transactionInfo;
	}
	
	

}
