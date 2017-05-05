package com.emulators.cubicCMS.services;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Timer;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.emulators.cubicCMS.utilities.DataInputDBOps;
import com.emulators.cubicCMS.utilities.DataInputDBRelTask;
import com.emulators.cubicCMS.utilities.FileUtils;
import com.emulators.cubicCMS.constants.PathConstants;
import com.emulators.cubicCMS.entities.CustomResponse;
import com.emulators.cubicCMS.entities.Script;
import com.emulators.cubicCMS.utilities.CMSLogger;


public class DataInputSchedulerService {
	@Produces(MediaType.APPLICATION_XML)
	@XmlElement(name="Response")
	public CustomResponse getCustomerResponse(Script inputXML){
		DataInputDBOps diDBOps=new DataInputDBOps();		
		CustomResponse cResponse=new CustomResponse();
		String batchQuery = "";
		int relativeTime;
		JSONObject obj = new JSONObject();
		JSONObject obj2 = new JSONObject();
		JSONArray list = new JSONArray();
		JSONParser parser = new JSONParser();
		if(inputXML.getTimestamp().equals("0")){
			cResponse = diDBOps.storeDataIntoDB(inputXML);
		}
		else{
			cResponse.setResponseCode("010");
			cResponse.setResponseMessage("Your request reached the server, it will be processed after "+inputXML.getTimestamp()+" Minutes");
			Timer time = new Timer(); // Instantiate Timer Object
			DataInputDBRelTask st = new DataInputDBRelTask(); // Instantiate SheduledTask class
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			Long currentTime=timestamp.getTime();
			Long scheduleTime= currentTime+Integer.parseInt(inputXML.getTimestamp())*60*1000;
			relativeTime=Integer.parseInt(inputXML.getTimestamp())*60*1000;
			CMSLogger.logger.info("Log Data Input: Started preparing Batch Insert query to insert the request data in Database :::::::: ");
			batchQuery = diDBOps.prepareBatchInsertSQLQuery(inputXML)+"commit;";
			try {
				try {
					obj=(JSONObject) parser.parse(new FileReader(PathConstants.generateResourceFilePath("scheduleFileList.json")));
					list=(JSONArray) obj.get("scheduleTimes");
					list.add(scheduleTime.toString());
					 try (FileWriter file = new FileWriter(PathConstants.generateResourceFilePath("scheduleFileList.json"))) {
						 	obj2.put("scheduleTimes",list);
				            file.write(obj2.toJSONString());
				            file.flush();

				        } catch (IOException e) {
				            e.printStackTrace();
				        }
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FileUtils.writeToFile(PathConstants.PROJECT_RESOURCES_PATH+scheduleTime.toString()+".txt",batchQuery);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			time.schedule(st, relativeTime);
		}
		return cResponse;
	}	


}
