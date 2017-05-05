package com.emulators.cubicCMS.entities;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name="Card")
@XmlType(propOrder={"custRefNum","designation","autoApprove","cardNickName", "identifier", "state", "expiry","product","photo","responseCode","responseMessage"})

public class SmartCard {
	private String custRefNum;
	private String designation;
	private Boolean autoApprove;
	private String cardNickName;
	private String identifier;
	private Product product;
	private String photo;
	private String state;
	private String expiry;
	private String responseCode;
	private String responseMessage;
	
	public SmartCard() {
		// TODO Auto-generated constructor stub
	}
	@XmlElement(name = "Designation")
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String Designation) {
		designation = Designation;
	}
	public Boolean getAutoApprove() {
		return autoApprove;
	}
	public void setAutoApprove(Boolean AutoApproveState) {
		autoApprove = AutoApproveState;
	}
	
	@XmlElement(name = "Expiry")
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String Expiry) {
		expiry = Expiry;
	}
	
	@XmlElement(name = "CustomerReferenceNumber")
	public String getCustRefNum() {
		return custRefNum;
	}
	
	public void setCustRefNum(String custRefNum) {
		this.custRefNum = custRefNum;
	}
	@XmlElement(name = "Product")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@XmlElement(name = "Photo")
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@XmlElement(name = "CardNickName")
	public String getCardNickName() {
		return cardNickName;
	}
	public void setCardNickName(String cardNickName) {
		this.cardNickName = cardNickName;
	}
	@XmlElement(name = "Identifier")
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	@XmlElement(name = "State")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@XmlElement(name = "ResponseCode")
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	@XmlElement(name = "ResponseMessage")
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	
	
}
