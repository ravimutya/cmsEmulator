package com.emulators.cubicCMS.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="Response")
@XmlType(propOrder={"responseCode","responseMessage"})
public class CustomResponse {
	private String responseCode;
	private String responseMessage;
	
	public CustomResponse(){
		
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
