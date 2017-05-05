package com.emulators.cubicCMS.utilities;

import java.util.HashMap;
import java.util.Map;


import org.apache.commons.lang.builder.ToStringBuilder;

import com.emulators.cubicCMS.constants.DBConstants;
import com.emulators.cubicCMS.constants.SQLConstants;
import com.emulators.cubicCMS.constants.WSResponseConstants;
import com.emulators.cubicCMS.entities.CustomResponse;
import com.emulators.cubicCMS.entities.CustomerInformation;
import com.emulators.cubicCMS.entities.Event;
import com.emulators.cubicCMS.utilities.ImportDataInputValidation;
import com.emulators.cubicCMS.entities.Payment;
import com.emulators.cubicCMS.entities.Product;
import com.emulators.cubicCMS.entities.Script;
import com.emulators.cubicCMS.entities.SmartCard;
import com.emulators.cubicCMS.utilities.CMSLogger;

public class DataInputDBOps {
	
	public CustomResponse storeDataIntoDB(Script inputXML){
		CustomResponse customResponse = new CustomResponse();
		ImportDataInputValidation dataInputValidation = new ImportDataInputValidation();

		//Validate the request XML
		try{
			customResponse = dataInputValidation.validateXML(inputXML);	
			if(customResponse != null){
				return customResponse;
			}
		}catch (Exception e) {
			customResponse = new CustomResponse();
			customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
			customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST);
			return customResponse;
		}

		CMSLogger.logger.info("Log Data Input: Started Processing Data before inserting into DataBase :::::::: ");
		//Store data in DB		
		customResponse = executeSQLBatch(inputXML);
		return customResponse;
	}


	public CustomResponse executeSQLBatch(Script inputXML){
		synchronized(this){			
			String batchQuery = "";
			CMSLogger.logger.info("Log Data Input: Started preparing Batch Insert query to insert the request data in Database :::::::: ");
			batchQuery = prepareBatchInsertSQLQuery(inputXML)+"commit;";
			//			System.out.println("batchQuery :: " + batchQuery);
			DBOps d = new DBOps();			
			return d.setData(batchQuery);
		}
	}

	public String prepareBatchInsertSQLQuery(Script inputXML){
		synchronized(this){
			String batchQuery = "";
			CMSLogger.logger.info("Log Data Input: Started processing each event one by one to generate SQL query using the data :::::::: ");
			for (Event event : inputXML.getEvent()) {

/*				JAXBContext jc;
				try {
					jc = JAXBContext.newInstance(Event.class);
					Marshaller marshaller = jc.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					//				CMSLogger.logger.info("Log Data Input: Event received in the Data Import Post Request :::::::: ");
					StringWriter stringWriter = new StringWriter();
					marshaller.marshal(event, stringWriter);
					CMSLogger.logger.info("Log Data Input: Event received"+stringWriter.toString());
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
*/


				if(event.getRequestEvent().equalsIgnoreCase("Customer")){
					batchQuery=batchQuery+prepareCustomerInsertSQLQuery(event.getCustomer());
				}else if(event.getRequestEvent().equalsIgnoreCase("Card")){
					batchQuery=batchQuery+prepareCardInsertSQLQuery(event.getSmartCard());
				}else if(event.getRequestEvent().equalsIgnoreCase("Product")){
					batchQuery=batchQuery+prepareProductInsertSQLQuery(event.getProduct());
				}
			}
			CMSLogger.logger.info("Log Data Input: SQL insert query includes Customer, Card and Product data :::::::: "+batchQuery);
			return batchQuery;
		}
	}

	private String prepareCustomerInsertSQLQuery(CustomerInformation customer) {
		synchronized(this){
			CMSLogger.logger.info("Log Data Input: Started preparing Customer Insert query with mentioned data :::::::: "+ToStringBuilder.reflectionToString(customer));
			String customerQuery = "";
			Map<String, String> queryMap = new HashMap<String, String>();

			String custRefNum = customer.getCustRefNum();
			custRefNum = custRefNum!=null ? custRefNum : ""; 
			queryMap.put(DBConstants.PARAM_CUSTOMER_REFERENCE_NUMBER, "'"+custRefNum+"'");

			String title = customer.getTitle();
			title = title!=null ? title : "";
			queryMap.put(DBConstants.PARAM_CUSTOMER_TITLE, "'"+title+"'");

			String firstName = customer.getFirstName();
			firstName = firstName!=null ? firstName : "";
			queryMap.put(DBConstants.PARAM_CUSTOMER_FIRST_NAME, "'"+firstName+"'");

			String middleName = customer.getMiddleName();
			middleName = middleName!=null ? middleName : "";
			queryMap.put(DBConstants.PARAM_CUSTOMER_MIDDLE_NAME, "'"+middleName+"'");

			String surName = customer.getSurName();
			surName = surName!=null ? surName : "";
			queryMap.put(DBConstants.PARAM_CUSTOMER_SURNAME, "'"+surName+"'");

			String telePhone = customer.getTelePhone();
			telePhone = telePhone!=null ? telePhone : "";
			queryMap.put(DBConstants.PARAM_CUSTOMER_TELEPHONE_NUMBER, "'"+telePhone+"'");

			String email = customer.getEmail();
			email = email!=null ? email : "";
			queryMap.put(DBConstants.PARAM_CUSTOMER_EMAIL, "'"+email+"'");

			String dob = customer.getDob();
			dob = dob!=null ? dob : "";
			queryMap.put(DBConstants.PARAM_CUSTOMER_DOB, "'"+dob+"'");

			String gender = customer.getGender();
			gender = gender!=null ? gender : "";
			queryMap.put(DBConstants.PARAM_CUSTOMER_GENDER, "'"+gender+"'");

			String addr1 = customer.getAddr1();
			addr1 = addr1!=null ? addr1 : "";
			queryMap.put(DBConstants.PARAM_CUSTOMER_ADDRESS_LINE1, "'"+addr1+"'");

			String addr2 = customer.getAddr2();
			addr2 = addr2!=null ? addr2 : "";
			queryMap.put(DBConstants.PARAM_CUSTOMER_ADDRESS_LINE2, "'"+addr2+"'");

			String addr3 = customer.getAddr3();
			addr3 = addr3!=null ? addr3 : "";
			queryMap.put(DBConstants.PARAM_CUSTOMER_ADDRESS_LINE3, "'"+addr3+"'");

			String addr4 = customer.getAddr4();
			addr4 = addr4!=null ? addr4 : "";
			queryMap.put(DBConstants.PARAM_CUSTOMER_ADDRESS_LINE4, "'"+addr4+"'");

			String addr5 = customer.getAddr5();
			addr5 = addr5!=null ? addr5 : "";
			queryMap.put(DBConstants.PARAM_CUSTOMER_ADDRESS_LINE5, "'"+addr5+"'");

			String addr6 = customer.getAddr6();
			addr6 = addr6!=null ? addr6 : "";
			queryMap.put(DBConstants.PARAM_CUSTOMER_ADDRESS_LINE6, "'"+addr6+"'");

			String postCode = customer.getPostCode();
			postCode = postCode!=null ? postCode : "";
			queryMap.put(DBConstants.PARAM_CUSTOMER_POST_CODE, "'"+postCode+"'");

			customerQuery = customerQuery + FileUtils.getQuery(SQLConstants.CUSTOMER_INSERT_QUERY, queryMap);
			CMSLogger.logger.info("Log Data Input: Completed preparing Customer Insert query with mentioned data :::::::: "+customerQuery);
			return customerQuery;			
		}
	}


	private String prepareCardInsertSQLQuery(SmartCard card) {
		synchronized(this){
			CMSLogger.logger.info("Log Data Input: Started preparing Card Insert query with mentioned data :::::::: "+ToStringBuilder.reflectionToString(card));
			String cardQuery = "";
			Map<String, String> queryMap = new HashMap<String, String>();
			//				queryMap.put(DBConstants.PARAM_CARD_CUSTOMER_REFERENCE_NUMBER, card.getCustRefNum());

			String custRefNum = card.getCustRefNum(); 
			custRefNum = custRefNum!=null ? custRefNum : "";
			queryMap.put(DBConstants.PARAM_CARD_CUSTOMER_REFERENCE_NUMBER, custRefNum);

			String designation = card.getDesignation(); 
			designation = designation!=null ? designation : "";				
			queryMap.put(DBConstants.PARAM_CARD_DESIGNATION, "'"+designation+"'");

			String cardNickName = card.getCardNickName(); 
			cardNickName = cardNickName!=null ? cardNickName : "";				
			queryMap.put(DBConstants.PARAM_CARD_NICK_NAME, "'"+cardNickName+"'");

			String identifier = card.getIdentifier(); 
			identifier = identifier!=null ? identifier : "";
			queryMap.put(DBConstants.PARAM_CARD_IDENTIFIER, "'"+identifier+"'");

			String state = card.getState(); 
			state = state!=null ? state : "";				
			queryMap.put(DBConstants.PARAM_CARD_STATE, "'"+state+"'");

			String expiry = card.getExpiry(); 
			expiry = expiry!=null ? expiry : "";				
			queryMap.put(DBConstants.PARAM_CARD_EXPIRY, "'"+expiry+"'");

			String photo = card.getPhoto(); 
			photo = photo!=null ? photo : "";				
			queryMap.put(DBConstants.PARAM_CARD_PHOTO, "'"+photo+"'");

			cardQuery = cardQuery + FileUtils.getQuery(SQLConstants.CARD_INSERT_QUERY, queryMap);
			CMSLogger.logger.info("Log Data Input: Completed preparing Card Insert query with mentioned data :::::::: "+cardQuery);
			return cardQuery;			
		}
	}


	private String prepareProductInsertSQLQuery(Product product) {
		synchronized(this){
			CMSLogger.logger.info("Log Data Input: Started preparing Product Insert query with mentioned data :::::::: "+ToStringBuilder.reflectionToString(product));
			String productQuery = "";
			Map<String, String> queryMap = new HashMap<String, String>();

			String cardId = product.getCardId();
			cardId = cardId!=null ? cardId : "";				
			queryMap.put(DBConstants.PARAM_PRODUCT_CARD_ID, cardId);

			String name = product.getName();
			name = name!=null ? name : "";				
			queryMap.put(DBConstants.PARAM_PRODUCT_NAME, "'"+name+"'");

			Payment payment = product.getPayment();

			String price = payment!=null ? payment.getPrice() : "";
			price = price!=null ? price : "";				
			queryMap.put(DBConstants.PARAM_PRODUCT_PRICE, "'"+price+"'");

			String paymentReference = payment!=null ? payment.getPaymentReference() : "";
			paymentReference = paymentReference!=null ? paymentReference : "";				
			queryMap.put(DBConstants.PARAM_PRODUCT_PAYMENT_REFERENCE, "'"+paymentReference+"'");

			String startDate = product.getStartDate();
			startDate = startDate!=null ? startDate : "";				
			queryMap.put(DBConstants.PARAM_PRODUCT_START_DATE, "'"+startDate+"'");

			String expiryDate = product.getExpiryDate();
			expiryDate = expiryDate!=null ? expiryDate : "";				
			queryMap.put(DBConstants.PARAM_PRODUCT_EXPIRY_DATE, "'"+expiryDate+"'");
			productQuery = productQuery + FileUtils.getQuery(SQLConstants.PRODUCT_INSERT_QUERY, queryMap);
			CMSLogger.logger.info("Log Data Input: Completed preparing Product Insert query with mentioned data :::::::: "+productQuery);
			return productQuery;			
		}

	}

}
