package com.emulators.cubicCMS.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.emulators.cubicCMS.entities.CustomResponse;
import com.emulators.cubicCMS.entities.Script;
import com.emulators.cubicCMS.services.DataInputSchedulerService;

@Path("scheduleDataImportToCMS")
public class DataInputSchedulerResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public CustomResponse setCMSDBData(Script inputXML){
		DataInputSchedulerService dis=new DataInputSchedulerService();
		return dis.getCustomerResponse(inputXML);
	}

}
