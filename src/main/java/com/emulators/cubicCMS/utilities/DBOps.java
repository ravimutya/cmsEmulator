package com.emulators.cubicCMS.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.emulators.cubicCMS.constants.DBConstants;
import com.emulators.cubicCMS.entities.Cards;
import com.emulators.cubicCMS.entities.CustomResponse;
import com.emulators.cubicCMS.entities.CustomerInformation;
import com.emulators.cubicCMS.entities.SmartCard;

public class DBOps {
	public static Connection connection = null;
	public static Statement statement = null;
	public static ResultSet customerRes=null;
	public static ResultSet customerRef=null;
	public static ResultSet cardRes=null;
	public static ResultSet cardRef=null;
	public static String DriverName=DBConstants.DriverName;
	public static String DBURL=DBConstants.DBURL;
	public static String DBUserName=DBConstants.DBUserName;
	public static String DBPassword=DBConstants.DBPassword;
	
	public int result;

	public static Connection getDBConnection(){
		try{
			Class.forName(DriverName);
			connection=DriverManager.getConnection(DBURL
					,DBUserName
					,DBPassword);
			CMSLogger.logger.info("Database connection estalished to :::::"+DBURL);
			statement = connection.createStatement();
		}catch(Exception e){
			e.printStackTrace();
			CMSLogger.logger.info("Exception on establishing DB connection :::::"+e);
			CMSLogger.logger.debug(e.getMessage(),e);
		}
		return connection;
	}	
	
	public static String getCustomerReferenceNumber(String custmerRefNum){
		String CustomerReferenceNumber=null;
		String query= "select \"ReferenceNumber\" from cms.customer where \"ReferenceNumber\"='"+custmerRefNum+"'";
		
		try {			
			getDBConnection();
			customerRef = statement.executeQuery(query);
			while (customerRef.next()) {
			CustomerReferenceNumber=customerRef.getString("ReferenceNumber");	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			CMSLogger.logger.info("e-cebs Card Management System Requests: Exception while retreiving Customer from DB  :::::"+e);
			CMSLogger.logger.debug(e.getMessage(),e);
		}finally{
			try{
				if(connection!=null){
					connection.close();
				}
				if(statement!=null){
					statement.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
				CMSLogger.logger.info("e-cebs Card Management System Requests: Exception on closing DB connection :::::"+e);
				CMSLogger.logger.debug(e.getMessage(),e);
			}
		}
		
		return CustomerReferenceNumber;
	}
	
	
	public static Cards getCards(String custmerRefNum){
		String sql="SELECT * FROM cms.card where \"CustomerId\" in (select \"CustomerId\" from cms.customer where \"ReferenceNumber\"='" + custmerRefNum+ "')";
		Cards cards=new Cards();
		List<SmartCard> Cards =  new ArrayList<>();
		SmartCard card=new SmartCard();
		Boolean flag=true;
		try {
			if(getCustomerReferenceNumber(custmerRefNum)==null){
				CMSLogger.logger.info("e-cebs Card Management System Response: Customer is not existing with the requested Customer Reference Number:::::"+custmerRefNum);
				flag=false;
				card=new SmartCard();
				card.setResponseCode("0007");
				card.setResponseMessage("Unkonwn Customer");
				Cards.add(card);
			}else{				

				getDBConnection();
				cardRes = statement.executeQuery(sql);
				
				while (cardRes.next()) {
					flag=cardRes.wasNull();
					card=new SmartCard();
					card.setDesignation(cardRes.getString("Designation"));
					card.setCardNickName(cardRes.getString("CardNickName"));
					card.setIdentifier(cardRes.getString("Identifier"));
					card.setState(cardRes.getString("State"));
					card.setExpiry(cardRes.getString("Expiry"));				
					Cards.add(card);					
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			CMSLogger.logger.info("e-cebs Card Management System Requests: Exception while fetching card details from DB :::::"+e);
			CMSLogger.logger.debug(e.getMessage(),e);
		}finally{
			try{
				if(connection!=null){
					connection.close();
				}
				if(statement!=null){
					statement.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
				CMSLogger.logger.info("e-cebs Card Management System Requests: Exception on closing DB connection :::::"+e);
				CMSLogger.logger.debug(e.getMessage(),e);
			}
		}
		if(flag){
			card=new SmartCard();
			Cards.add(null);
			cards.setCards(Cards);
			return cards;
		}else{
			if(Cards.get(0).getResponseCode()=="0007"){
				cards.setResponseCode(Cards.get(0).getResponseCode());
				cards.setResponseMessage(Cards.get(0).getResponseMessage());
			}else{				
				cards.setCards(Cards);
			}
			return cards;
		}
	}

	public static CustomerInformation getData(String custmerRefNum){
		Boolean flag=true;
		CMSLogger.logger.info("e-cebs Card Management System Requests: Constructing the SQL query to fetch customer Information:::::");
		String query="SELECT * FROM cms.customer where \"ReferenceNumber\"='"+ custmerRefNum+ "'";
		CMSLogger.logger.info("e-cebs Card Management System Requests: Constructed SQL query to fetch customer Information is :::::"+query);
		CustomerInformation custInfo=new CustomerInformation();
		try{

			CMSLogger.logger.info("e-cebs Card Management System Requests: Establishing DB connection :::::");
			
			getDBConnection();
			
			customerRes = statement.executeQuery(query);
			CMSLogger.logger.info("e-cebs Card Management System Requests: SQL query executed & results retrieved :::::");
			while (customerRes.next()) {
				flag=customerRes.wasNull();
				custInfo.setCustRefNum(customerRes.getString("ReferenceNumber"));
				custInfo.setTitle(customerRes.getString("Title"));
				custInfo.setFirstName(customerRes.getString("FirstName"));
				custInfo.setMiddleName(customerRes.getString("MiddleName"));
				custInfo.setSurName(customerRes.getString("Surname"));
				custInfo.setGender(customerRes.getString("Gender"));
				custInfo.setDob(customerRes.getString("DateOfBirth"));
				custInfo.setEmail(customerRes.getString("Email"));
				custInfo.setTelePhone(customerRes.getString("TelephoneNumber"));
				custInfo.setAddr1(customerRes.getString("AddressLine1"));
				custInfo.setAddr2(customerRes.getString("AddressLine2"));
				custInfo.setAddr3(customerRes.getString("AddressLine3"));
				custInfo.setAddr4(customerRes.getString("AddressLine4"));
				custInfo.setAddr5(customerRes.getString("AddressLine5"));
				custInfo.setAddr6(customerRes.getString("AddressLine6"));
				custInfo.setPostCode(customerRes.getString("PostCode"));
				CMSLogger.logger.info("e-cebs Card Management System Requests: Customer information constructed with the DB retrieved data :::::");
			}
		}catch(Exception e){
			e.printStackTrace();
			CMSLogger.logger.info("e-cebs Card Management System Requests: Exception on establishing DB connection :::::"+e);
			CMSLogger.logger.debug(e.getMessage(),e);
		}finally{
			try{
				if(connection!=null){
					CMSLogger.logger.info("e-cebs Card Management System Requests: Closing the DB connection :::::");
					connection.close();
					CMSLogger.logger.info("e-cebs Card Management System Requests: DB connection Successfully Closed:::::");
				}
				if(statement!=null){
					statement.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
				CMSLogger.logger.info("e-cebs Card Management System Requests: Exception on closing DB connection :::::"+e);
				CMSLogger.logger.debug(e.getMessage(),e);
			}
		}
		if(flag){
			CMSLogger.logger.info("e-cebs Card Management System Response: Customer is not existing with the requested Customer Reference Number:::::"+custmerRefNum);
			custInfo.setResponseCode("0007");
			custInfo.setResponseMessage("Unkonwn Customer");
			return custInfo;
		}else{    		
			CMSLogger.logger.info("e-cebs Card Management System Response: Customer is existing with the requested Customer Reference Number:::::"+custmerRefNum);
			return custInfo;
		}
	}


	public CustomResponse setData(String query){
		Boolean flag=true;
		CustomResponse cResp=new CustomResponse();
		try{
			getDBConnection();
			result = statement.executeUpdate(query);
			System.out.println("Result:::::"+result);
			if(result==1)
				flag=true;
			else 
				flag=false;


		}catch(Exception e){
			e.printStackTrace();
			CMSLogger.logger.info("Log Data Input: Exception while inserting data into DB :::::"+e);
			CMSLogger.logger.debug(e.getMessage(),e);
//			System.out.println("Error Message ::::"+e.getMessage());
			cResp.setResponseCode("0007");
			cResp.setResponseMessage(e.getMessage());
			return cResp;
		}finally{
			try{
				if(connection!=null){
					connection.close();
				}
				if(statement!=null){
					statement.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
				CMSLogger.logger.info("Log Data Input: Exception on closing DB connection :::::"+e);
				CMSLogger.logger.debug(e.getMessage(),e);
			}
		}
		if(flag){
			cResp.setResponseCode("0001");
			cResp.setResponseMessage("Request Successful");
			CMSLogger.logger.info("Log Data Input: Successfully inserted the data into DB :::::");
			return cResp;
		}else{
			cResp.setResponseCode("0007");
			cResp.setResponseMessage("Unkonwn Customer");
			CMSLogger.logger.info("Log Data Input: Card/Product data is trying to insert for non-existing customer :::::");
			return cResp;
		}
	}
	
	
	public void setDataOnSchedule(String query){
		try{
			getDBConnection();
			result = statement.executeUpdate(query);
			System.out.println("Result:::::"+result);

		}catch(Exception e){
			e.printStackTrace();
			CMSLogger.logger.info("Log Data Input: Exception while inserting data into DB :::::"+e);
			CMSLogger.logger.debug(e.getMessage(),e);
		}finally{
			try{
				if(connection!=null){
					connection.close();
				}
				if(statement!=null){
					statement.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
				CMSLogger.logger.info("Log Data Input: Exception on closing DB connection :::::"+e);
				CMSLogger.logger.debug(e.getMessage(),e);
			}
		}

	}
}
