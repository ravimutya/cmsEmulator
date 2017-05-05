package com.emulators.cubicCMS.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="CustomerInformation")
@XmlType(propOrder={"custRefNum", "title", "firstName", "middleName", "surName",
		"gender", "dob", "telePhone", "email", "addr1", "addr2","addr3","addr4",
		"addr5","addr6","postCode","responseCode","responseMessage"})

public class CustomerInformation {

private String custRefNum;
private String title;
private String firstName;
private String middleName;
private String surName;
private String gender;
private String dob;
private String telePhone;
private String email;
private String addr1;
private String addr2;
private String addr3;
private String addr4;
private String addr5;
private String addr6;
private String postCode;
private String responseCode;
private String responseMessage;

public CustomerInformation(){
//	custRefNum="1244355660";
//	title="Mr";
//	firstName="John";
//	middleName="Patrick";
//	surName="Smith";
//	gender="Male";
//	dob="14/12/1945";
//	telePhone="014143255253";
//	email="john.smith@email1.com";
//	addr1="Flat 3/1";
//	addr2="318";
//	addr3="Main Street";
//	addr5="Glasgow";
//	addr6="City of Glasgow";
//	postCode="G75 0QF";
}
//@XmlElementWrapper(name = "CustomerInformation")
@XmlElement(name = "CustomerReferenceNumber")
public String getCustRefNum() {
	return custRefNum;
}
public void setCustRefNum(String custRefNum) {
	this.custRefNum = custRefNum;
}
@XmlElement(name = "Title")
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
@XmlElement(name = "FirstName")
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
@XmlElement(name = "MiddleName")
public String getMiddleName() {
	return middleName;
}
public void setMiddleName(String middleName) {
	this.middleName = middleName;
}
@XmlElement(name = "Surname")
public String getSurName() {
	return surName;
}
public void setSurName(String surName) {
	this.surName = surName;
}
@XmlElement(name = "Gender")
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
@XmlElement(name = "DateOfBirth")
public String getDob() {
	return dob;
}
public void setDob(String dob) {
	this.dob = dob;
}
@XmlElement(name = "TelephoneNumber")
public String getTelePhone() {
	return telePhone;
}
public void setTelePhone(String telePhone) {
	this.telePhone = telePhone;
}
@XmlElement(name = "Email")
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
@XmlElement(name = "Address1")
public String getAddr1() {
	return addr1;
}
public void setAddr1(String addr1) {
	this.addr1 = addr1;
}
@XmlElement(name = "Address2")
public String getAddr2() {
	return addr2;
}
public void setAddr2(String addr2) {
	this.addr2 = addr2;
}
@XmlElement(name = "Address3")
public String getAddr3() {
	return addr3;
}
public void setAddr3(String addr3) {
	this.addr3 = addr3;
}
@XmlElement(name = "Address4")
public String getAddr4() {
	return addr4;
}
public void setAddr4(String addr4) {
	this.addr4 = addr4;
}
@XmlElement(name = "Address5")
public String getAddr5() {
	return addr5;
}
public void setAddr5(String addr5) {
	this.addr5 = addr5;
}
@XmlElement(name = "Address6")
public String getAddr6() {
	return addr6;
}
public void setAddr6(String addr6) {
	this.addr6 = addr6;
}
@XmlElement(name = "PostCode")
public String getPostCode() {
	return postCode;
}
public void setPostCode(String postCode) {
	this.postCode = postCode;
}
public String getResponseMessage() {
	return responseMessage;
}
public void setResponseMessage(String responseMessage) {
	this.responseMessage = responseMessage;
}
public String getResponseCode() {
	return responseCode;
}
public void setResponseCode(String responseCode) {
	this.responseCode = responseCode;
}
}
