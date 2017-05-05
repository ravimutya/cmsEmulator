package com.emulators.cubicCMS.resources;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.emulators.cubicCMS.entities.CustomerInformation;
import com.emulators.cubicCMS.services.CustomerInfoService;
import com.emulators.cubicCMS.utilities.CMSLogger;

@Path("getCustomerInformation")
public class CustomerInfoResource {
	
	CustomerInfoService service=new CustomerInfoService();
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{requestID}/{customerReferenceNumber}")
	public CustomerInformation getCustmerInformation(@PathParam("requestID") String reqID,
			@PathParam("customerReferenceNumber") String custmerRefNum){
		CMSLogger.logger.info("e-cebs Card Management System Requests: This is invocation of Get Customer Information using CustomerReferenceNumber");
		CMSLogger.logger.info("e-cebs Card Management System Requests: Request ID for the Customer Information Get Request : " + reqID);
		CMSLogger.logger.info("e-cebs Card Management System Requests: CustomerReference Number for the Customer Information Get Request : " + custmerRefNum);

		return service.getCustomerInformation(reqID,custmerRefNum);
	}
}

