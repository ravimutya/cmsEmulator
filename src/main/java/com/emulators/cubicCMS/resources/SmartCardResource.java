package com.emulators.cubicCMS.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.emulators.cubicCMS.entities.Cards;
import com.emulators.cubicCMS.services.SmartCardsService;
import com.emulators.cubicCMS.utilities.CMSLogger;

@Path("getSmartCards")
public class SmartCardResource {
	SmartCardsService service=new SmartCardsService();
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{requestID}/{customerReferenceNumber}")
	public Cards getSmartCards(@PathParam("requestID") String reqID,
			@PathParam("customerReferenceNumber") String custmerRefNum){
		
		CMSLogger.logger.info("e-cebs Card Management System Requests: This is invocation of Get Smart Cards Information using CustomerReferenceNumber");
		CMSLogger.logger.info("e-cebs Card Management System Requests: Request ID for the Customer Information Get Request : " + reqID);
		CMSLogger.logger.info("e-cebs Card Management System Requests: CustomerReference Number for the Customer Information Get Request : " + custmerRefNum);

		return service.getSmartCards(reqID,custmerRefNum);
	}
	
}
