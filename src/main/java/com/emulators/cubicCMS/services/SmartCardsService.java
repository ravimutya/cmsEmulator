package com.emulators.cubicCMS.services;

import java.io.StringWriter;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.emulators.cubicCMS.utilities.DBOps;
import com.emulators.cubicCMS.entities.Cards;
import com.emulators.cubicCMS.utilities.CMSLogger;
public class SmartCardsService {
	@Produces(MediaType.APPLICATION_XML)
	
	
	public Cards getSmartCards(String reqID,String custmerRefNum){
		
		Cards cards = new Cards();	
		cards=DBOps.getCards(custmerRefNum);
		// Create a JaxBContext
				JAXBContext jc;
				try {
					jc = JAXBContext.newInstance(Cards.class);

					// Create the Marshaller Object using the JaxB Context
					Marshaller marshaller = jc.createMarshaller();

					// Set it to true if you need the XML output to formatted
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

					CMSLogger.logger.info("e-cebs Card Management System Responses: Below is the Recevied the response for the Smart Cards Get Request :::::::: ");
					StringWriter stringWriter = new StringWriter();
					// Marshal the employee object to JSON and print the output to console
					marshaller.marshal(cards, stringWriter);
					CMSLogger.logger.info("e-cebs Card Management System Responses: Response received"+stringWriter.toString());
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


				CMSLogger.logger.info("e-cebs Card Management System Responses: Smart Card Information each field level : " + ToStringBuilder.reflectionToString(cards));
		
			return cards;
	}
	
}
