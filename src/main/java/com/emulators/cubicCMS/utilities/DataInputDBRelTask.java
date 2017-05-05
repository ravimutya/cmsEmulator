package com.emulators.cubicCMS.utilities;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.TimerTask;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.emulators.cubicCMS.constants.PathConstants;



public class DataInputDBRelTask extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String query="";
		InputStream input = null;
		JSONObject obj = new JSONObject();
		JSONObject obj2 = new JSONObject();
		JSONArray list = new JSONArray();
		JSONArray updatedList = new JSONArray();
		JSONParser parser = new JSONParser();
    	try {
    		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    		try {
				obj=(JSONObject) parser.parse(new FileReader(PathConstants.generateResourceFilePath("scheduleFileList.json")));
				list=(JSONArray) obj.get("scheduleTimes");
//				System.out.println("Length of the Files List:::::"+list.size());
				Iterator<String> iterator = list.iterator();
	            while (iterator.hasNext()) {
	            	Long val=Long.parseLong(iterator.next());
	                if(val<= timestamp.getTime()){
						query=FileUtils.readFile(PathConstants.PROJECT_RESOURCES_PATH+val.toString()+".txt", null);
						DBOps d = new DBOps();			
						d.setDataOnSchedule(query);
					}else{
						updatedList.add(val.toString());
						
					}
	                try (FileWriter file = new FileWriter(PathConstants.generateResourceFilePath("scheduleFileList.json"))) {
					 	obj2.put("scheduleTimes",updatedList);
			            file.write(obj2.toJSONString());
			            file.flush();

			        } catch (IOException e) {
			            e.printStackTrace();
			        }
//	                System.out.println(iterator.next());
	            }
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    		
  		
    	}catch (IOException ex) {
    		ex.printStackTrace();
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
	}
		
		
	}

