package com.emulators.cubicCMS.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="Response")
public class Cards {
	private List<SmartCard> Cards;
	private String responseCode;
	private String responseMessage;
	
	public Cards(){
		
	}
	@XmlElementWrapper(name = "Cards")
	@XmlElement(name = "Card")
	public List<SmartCard> getCards() {
		return Cards;
	}

	public void setCards(List<SmartCard> cards) {
		Cards = cards;
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
