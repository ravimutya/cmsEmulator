package com.emulators.cubicCMS.entities;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"requestEvent","customer","smartCard","product"})
public class Event {

	private String requestEvent;
	
	private CustomerInformation customer;
	private SmartCard smartCard;
	private Product product;
	
	public String getRequestEvent() {
		return requestEvent;
	}

	public void setRequestEvent(String requestEvent) {
		this.requestEvent = requestEvent;
	}
	public CustomerInformation getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerInformation customer) {
		this.customer = customer;
	}
	public SmartCard getSmartCard() {
		return smartCard;
	}

	public void setSmartCard(SmartCard smartCard) {
		this.smartCard = smartCard;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}	
		
}
