package com.emulators.cubicCMS.services;

import java.io.StringWriter;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.emulators.cubicCMS.utilities.DBOps;
import com.emulators.cubicCMS.entities.CustomerInformation;
import com.emulators.cubicCMS.utilities.CMSLogger;

public class CustomerInfoService {
	public static CustomerInformation customerRes=null;
	@Produces(MediaType.APPLICATION_XML)
	@XmlElement(name="CustomerInfo")
	public CustomerInformation getCustomerInformation(String reqID,String custmerRefNum){
		customerRes=DBOps.getData(custmerRefNum);

		// Create a JaxBContext
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(CustomerInformation.class);


			// Create the Marshaller Object using the JaxB Context
			Marshaller marshaller = jc.createMarshaller();

			// Set it to true if you need the XML output to formatted
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			CMSLogger.logger.info("e-cebs Card Management System Responses: Below is the Recevied the response for the Customer Information Get Request :::::::: ");
			StringWriter stringWriter = new StringWriter();
			// Marshal the employee object to JSON and print the output to console
			marshaller.marshal(customerRes, stringWriter);
			CMSLogger.logger.info("e-cebs Card Management System Responses: Response received"+stringWriter.toString());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		CMSLogger.logger.info("e-cebs Card Management System Responses: Customer Information each field level : " + ToStringBuilder.reflectionToString(customerRes));
		return customerRes;
	}

}
