package com.emulators.cubicCMS.utilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emulators.cubicCMS.constants.DataInputConstants;

public interface DataInputValidationRules extends DataInputConstants{
	
	String DATE_FORMAT_VALIDATION = "dd/MM/yyyy";
	
	//For Customer
	int CUSTOMER_CUSTOMER_REFERENCE_NUMBER_MAXLENGTH = 255;
	int CUSTOMER_TITLE_MAXLENGTH = 10;
	int CUSTOMER_FIRST_NAME_MAXLENGTH = 30;
	int CUSTOMER_MIDDLE_NAME_MAXLENGTH = 30;
	int CUSTOMER_SURNAME_MAXLENGTH = 30;
	int CUSTOMER_TELEPHONE_NUMBER_MAXLENGTH = 35;
	int CUSTOMER_EMAIL_MAXLENGTH = 100;
	int CUSTOMER_ADDRESS_LINE1_MAXLENGTH = 30;
	int CUSTOMER_ADDRESS_LINE2_MAXLENGTH = 30;
	int CUSTOMER_ADDRESS_LINE3_MAXLENGTH = 30;
	int CUSTOMER_ADDRESS_LINE4_MAXLENGTH = 30;
	int CUSTOMER_ADDRESS_LINE5_MAXLENGTH = 30;
	int CUSTOMER_ADDRESS_LINE6_MAXLENGTH = 30;
	int CUSTOMER_POST_CODE_MINLENGTH = 4;
	int CUSTOMER_POST_CODE_MAXLENGTH = 10;
	String CUSTOMER_GENDER_MALE = "Male";
	String CUSTOMER_GENDER_FEMALE = "Female";
	
	//For Card
	int CARD_DESIGNATION_MAXLENGTH = 30;
	int CARD_IDENTIFIER_MAXLENGTH = 30;
	int CARD_STATE_MAXLENGTH = 30;
	int CARD_CARD_NICK_NAME_MAXLENGTH = 30;
	String CARD_STATE_ACTIVE = "Active";		
	String CARD_STATE_EXPIRED = "Expired";
	
	//For Product
	int PRODUCT_CARD_ID_MAXLENGTH = 10;
	int PRODUCT_NAME_MAXLENGTH = 30;

	//For customer
	List<String> DATA_INPUT_REQUESTS_EVENTS = Arrays.asList(new String[] { CUSTOMER, CARD, PRODUCT });

	List<String> CUSTOMER_MANDATORY_XML_TAGS = Arrays.asList(new String[] { CUSTOMER_CUSTOMER_REFERENCE_NUMBER,
			CUSTOMER_FIRST_NAME, CUSTOMER_SURNAME, CUSTOMER_EMAIL, CUSTOMER_POST_CODE, CUSTOMER_ADDRESS_LINE1 });

	List<String> CUSTOMER_MAX_LENGTH_XML_TAGS = Arrays.asList(new String[] { CUSTOMER_CUSTOMER_REFERENCE_NUMBER,
			CUSTOMER_TITLE, CUSTOMER_FIRST_NAME, CUSTOMER_MIDDLE_NAME, CUSTOMER_SURNAME, CUSTOMER_TELEPHONE_NUMBER,
			CUSTOMER_EMAIL, CUSTOMER_ADDRESS_LINE1, CUSTOMER_ADDRESS_LINE2, CUSTOMER_ADDRESS_LINE3,
			CUSTOMER_ADDRESS_LINE4, CUSTOMER_ADDRESS_LINE5, CUSTOMER_ADDRESS_LINE6, CUSTOMER_POST_CODE, CUSTOMER_EMAIL,
			CUSTOMER_POST_CODE });

	List<String> CUSTOMER_MIN_LENGTH_XML_TAGS = Arrays.asList(new String[] { CUSTOMER_POST_CODE});
	List<String> CUSTOMER_GENDER_OPTIONS = Arrays.asList(new String[]{CUSTOMER_GENDER_MALE, CUSTOMER_GENDER_FEMALE});
	List<String> CUSTOMER_DATE_XML_TAGS = Arrays.asList(new String[] { CUSTOMER_DOB });
	
	//For Card
	List<String> CARD_MANDATORY_XML_TAGS = Arrays
			.asList(new String[] { CARD_CUSTOMER_REFERENCE_NUMBER, CARD_DESIGNATION, CARD_CARD_NICK_NAME, CARD_STATE });
	
	List<String> CARD_MAX_LENGTH_XML_TAGS = Arrays.asList(new String[] { CARD_CUSTOMER_REFERENCE_NUMBER,
			CARD_DESIGNATION, CARD_CARD_NICK_NAME, CARD_CARD_NICK_NAME, CARD_IDENTIFIER });

	List<String> CARD_STATE_OPTIONS = Arrays.asList(new String[]{CARD_STATE_ACTIVE, CARD_STATE_EXPIRED});

	List<String> CARD_DATE_XML_TAGS = Arrays.asList(new String[] { CARD_EXPIRY_DATE });
	
	//For Product
	List<String> PRODUCT_MANDATORY_XML_TAGS = Arrays.asList(new String[] { PRODUCT_CARD_ID, PRODUCT_NAME, PRODUCT_PRICE,
			PRODUCT_PAYMENT_REFERENCE, PRODUCT_START_DATE });

	List<String> PRODUCT_MAX_LENGTH_XML_TAGS = Arrays.asList(new String[] { PRODUCT_NAME });
	
	List<String> PRODUCT_DATE_XML_TAGS = Arrays.asList(new String[] { PRODUCT_START_DATE, PRODUCT_EXPIRY_DATE });
	
	Map<String, Integer> CUSTOMER_MAX_LENGTH_MAP = getCustomerMaxLengthMap();	
	static Map<String, Integer> getCustomerMaxLengthMap(){
		Map<String, Integer> customerMaxLengthMap = new HashMap<String, Integer>();
		customerMaxLengthMap = new HashMap<String, Integer>();
		customerMaxLengthMap.put(DataInputConstants.CUSTOMER_CUSTOMER_REFERENCE_NUMBER, DataInputValidationRules.CUSTOMER_CUSTOMER_REFERENCE_NUMBER_MAXLENGTH);
		customerMaxLengthMap.put(DataInputConstants.CUSTOMER_TITLE, DataInputValidationRules.CUSTOMER_TITLE_MAXLENGTH);
		customerMaxLengthMap.put(DataInputConstants.CUSTOMER_FIRST_NAME, DataInputValidationRules.CUSTOMER_FIRST_NAME_MAXLENGTH);
		customerMaxLengthMap.put(DataInputConstants.CUSTOMER_MIDDLE_NAME, DataInputValidationRules.CUSTOMER_MIDDLE_NAME_MAXLENGTH);
		customerMaxLengthMap.put(DataInputConstants.CUSTOMER_SURNAME, DataInputValidationRules.CUSTOMER_SURNAME_MAXLENGTH);
		customerMaxLengthMap.put(DataInputConstants.CUSTOMER_TELEPHONE_NUMBER, DataInputValidationRules.CUSTOMER_TELEPHONE_NUMBER_MAXLENGTH);
		customerMaxLengthMap.put(DataInputConstants.CUSTOMER_EMAIL, DataInputValidationRules.CUSTOMER_EMAIL_MAXLENGTH);
		customerMaxLengthMap.put(DataInputConstants.CUSTOMER_ADDRESS_LINE1, DataInputValidationRules.CUSTOMER_ADDRESS_LINE1_MAXLENGTH);
		customerMaxLengthMap.put(DataInputConstants.CUSTOMER_ADDRESS_LINE2, DataInputValidationRules.CUSTOMER_ADDRESS_LINE2_MAXLENGTH);
		customerMaxLengthMap.put(DataInputConstants.CUSTOMER_ADDRESS_LINE3, DataInputValidationRules.CUSTOMER_ADDRESS_LINE3_MAXLENGTH);
		customerMaxLengthMap.put(DataInputConstants.CUSTOMER_ADDRESS_LINE4, DataInputValidationRules.CUSTOMER_ADDRESS_LINE4_MAXLENGTH);
		customerMaxLengthMap.put(DataInputConstants.CUSTOMER_ADDRESS_LINE5, DataInputValidationRules.CUSTOMER_ADDRESS_LINE5_MAXLENGTH);
		customerMaxLengthMap.put(DataInputConstants.CUSTOMER_ADDRESS_LINE6, DataInputValidationRules.CUSTOMER_ADDRESS_LINE6_MAXLENGTH);
		customerMaxLengthMap.put(DataInputConstants.CUSTOMER_POST_CODE, DataInputValidationRules.CUSTOMER_POST_CODE_MAXLENGTH);
		
		return customerMaxLengthMap;
	}
		
	Map<String, Integer> CUSTOMER_MIN_LENGTH_MAP =  getCustomerMinLengthMap();
	static Map<String, Integer> getCustomerMinLengthMap(){
		Map<String, Integer> customerMinLengthMap = new HashMap<String, Integer>();
		customerMinLengthMap.put(DataInputConstants.CUSTOMER_POST_CODE, DataInputValidationRules.CUSTOMER_POST_CODE_MINLENGTH);
		
		return customerMinLengthMap;
	}
	
	Map<String, Integer> CARD_MAX_LENGTH_MAP =  getCardMaxLengthMap();	
	static Map<String, Integer> getCardMaxLengthMap(){
		Map<String, Integer> cardMaxLengthMap = new HashMap<String, Integer>();
		cardMaxLengthMap.put(DataInputConstants.CARD_CUSTOMER_REFERENCE_NUMBER, DataInputValidationRules.CUSTOMER_CUSTOMER_REFERENCE_NUMBER_MAXLENGTH);
		cardMaxLengthMap.put(DataInputConstants.CARD_DESIGNATION, DataInputValidationRules.CARD_DESIGNATION_MAXLENGTH);
		cardMaxLengthMap.put(DataInputConstants.CARD_CARD_NICK_NAME, DataInputValidationRules.CARD_CARD_NICK_NAME_MAXLENGTH);
		cardMaxLengthMap.put(DataInputConstants.CARD_IDENTIFIER, DataInputValidationRules.CARD_IDENTIFIER_MAXLENGTH);
		cardMaxLengthMap.put(DataInputConstants.CARD_STATE, DataInputValidationRules.CARD_STATE_MAXLENGTH); 
		
		return cardMaxLengthMap;
	}	
	
	Map<String, Integer> PRODUCT_MAX_LENGTH_MAP = getProductMaxLengthMap();
	static Map<String, Integer> getProductMaxLengthMap(){
		Map<String, Integer> productMaxLengthMap = new HashMap<String, Integer>();
		productMaxLengthMap.put(DataInputConstants.PRODUCT_CARD_ID, DataInputValidationRules.PRODUCT_CARD_ID_MAXLENGTH);
		productMaxLengthMap.put(DataInputConstants.PRODUCT_NAME, DataInputValidationRules.PRODUCT_NAME_MAXLENGTH);
		
		return productMaxLengthMap;
	}	
	
}
