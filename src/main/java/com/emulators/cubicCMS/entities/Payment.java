package com.emulators.cubicCMS.entities;

public class Payment {
	private String price;
	private String paymentReference;
	public Payment(){
		
	}
	Payment(String price,String paymentReference){
		this.price=price;
		this.paymentReference=paymentReference;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPaymentReference() {
		return paymentReference;
	}
	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}
	
}
