package com.emulators.cubicCMS.constants;

import java.io.File;
import java.util.Properties;



public interface DBConstants {

	//DB Connection details
	String DriverName = "org.postgresql.Driver";
	String DBURL = "jdbc:postgresql://127.0.0.1:5432/cmsdb";
	String DBUserName = "postgres";
	String DBPassword = "admin";
	String BASEPath = new File(".").getAbsolutePath();
	Properties prop = new Properties();
		
	

	//Customer Related variables
	String CUSTOMER_REFERENCE_NUMBER = "\"ReferenceNumber\"";
	String PARAM_CUSTOMER_REFERENCE_NUMBER = "{"+CUSTOMER_REFERENCE_NUMBER+"}";
	String CUSTOMER_TITLE = "\"Title\"";
	String PARAM_CUSTOMER_TITLE = "{"+CUSTOMER_TITLE+"}";	
	String CUSTOMER_FIRST_NAME = "\"FirstName\"";
	String PARAM_CUSTOMER_FIRST_NAME = "{"+CUSTOMER_FIRST_NAME+"}";
	String CUSTOMER_MIDDLE_NAME = "\"MiddleName\"";
	String PARAM_CUSTOMER_MIDDLE_NAME = "{"+CUSTOMER_MIDDLE_NAME+"}";
	String CUSTOMER_SURNAME = "\"Surname\"";
	String PARAM_CUSTOMER_SURNAME = "{"+CUSTOMER_SURNAME+"}";
	String CUSTOMER_TELEPHONE_NUMBER = "\"TelephoneNumber\"";
	String PARAM_CUSTOMER_TELEPHONE_NUMBER = "{"+CUSTOMER_TELEPHONE_NUMBER+"}";
	String CUSTOMER_EMAIL = "\"Email\"";
	String PARAM_CUSTOMER_EMAIL = "{"+CUSTOMER_EMAIL+"}";
	String CUSTOMER_DOB = "\"DateOfBirth\"";
	String PARAM_CUSTOMER_DOB = "{"+CUSTOMER_DOB+"}";
	String CUSTOMER_GENDER = "\"Gender\"";
	String PARAM_CUSTOMER_GENDER = "{"+CUSTOMER_GENDER+"}";

	String CUSTOMER_ADDRESS_LINE1 = "\"AddressLine1\"";
	String PARAM_CUSTOMER_ADDRESS_LINE1 = "{"+CUSTOMER_ADDRESS_LINE1+"}";
	String CUSTOMER_ADDRESS_LINE2 = "\"AddressLine2\"";
	String PARAM_CUSTOMER_ADDRESS_LINE2 = "{"+CUSTOMER_ADDRESS_LINE2+"}";
	String CUSTOMER_ADDRESS_LINE3 = "\"AddressLine3\"";
	String PARAM_CUSTOMER_ADDRESS_LINE3 = "{"+CUSTOMER_ADDRESS_LINE3+"}";
	String CUSTOMER_ADDRESS_LINE4 = "\"AddressLine4\"";
	String PARAM_CUSTOMER_ADDRESS_LINE4 = "{"+CUSTOMER_ADDRESS_LINE4+"}";
	String CUSTOMER_ADDRESS_LINE5 = "\"AddressLine5\"";
	String PARAM_CUSTOMER_ADDRESS_LINE5 = "{"+CUSTOMER_ADDRESS_LINE5+"}";
	String CUSTOMER_ADDRESS_LINE6 = "\"AddressLine6\"";
	String PARAM_CUSTOMER_ADDRESS_LINE6 = "{"+CUSTOMER_ADDRESS_LINE6+"}";	

	String CUSTOMER_POST_CODE = "\"PostCode\"";
	String PARAM_CUSTOMER_POST_CODE = "{"+CUSTOMER_POST_CODE+"}";

	//Card Related variables
	String CARD_CUSTOMER_REFERENCE_NUMBER = "\"CustomerId\"";
	String PARAM_CARD_CUSTOMER_REFERENCE_NUMBER = "{"+CARD_CUSTOMER_REFERENCE_NUMBER+"}";
	String CARD_DESIGNATION = "\"Designation\"";
	String PARAM_CARD_DESIGNATION = "{"+CARD_DESIGNATION+"}";
	String CARD_NICK_NAME = "\"CardNickName\"";
	String PARAM_CARD_NICK_NAME = "{"+CARD_NICK_NAME+"}";
	String CARD_IDENTIFIER = "\"Identifier\"";
	String PARAM_CARD_IDENTIFIER = "{"+CARD_IDENTIFIER+"}";
	String CARD_STATE = "\"State\"";
	String PARAM_CARD_STATE = "{"+CARD_STATE+"}";	
	String CARD_PHOTO = "\"Photo\"";
	String PARAM_CARD_PHOTO = "{"+CARD_PHOTO+"}"; 
	String CARD_EXPIRY = "\"Expiry\"";
	String PARAM_CARD_EXPIRY= "{"+CARD_EXPIRY+"}"; 

	//Product related variables
	String PRODUCT_CARD_ID = "\"CardId\"";
	String PARAM_PRODUCT_CARD_ID ="{"+PRODUCT_CARD_ID+"}";
	String PRODUCT_NAME = "\"Name\"";
	String PARAM_PRODUCT_NAME ="{"+PRODUCT_NAME+"}";
	String PRODUCT_PRICE = "\"Price\"";
	String PARAM_PRODUCT_PRICE ="{"+PRODUCT_PRICE+"}";
	String PRODUCT_PAYMENT_REFERENCE = "\"PaymentReference\"";
	String PARAM_PRODUCT_PAYMENT_REFERENCE ="{"+PRODUCT_PAYMENT_REFERENCE+"}";
	String PRODUCT_START_DATE = "\"StartDate\"";
	String PARAM_PRODUCT_START_DATE ="{"+PRODUCT_START_DATE+"}";
	String PRODUCT_EXPIRY_DATE = "\"ExpiryDate\"";
	String PARAM_PRODUCT_EXPIRY_DATE ="{"+PRODUCT_EXPIRY_DATE+"}";
}
