package com.emulators.cubicCMS.constants;

public interface DataInputConstants {
	
	String SCRIPT = "Script";
	String EVENT = "Event";
	String PARAMETERS = "Parameters";
	
	String EVENT_PATH = "/"+SCRIPT+"/"+EVENT;
	String REQUEST_EVENT = "RequestEvent";
	String CUSTOMER = "Customer";
	String CARD = "Card";
	String PRODUCT = "Product";
	String INDEX = "{index}";
	String PARAMETER_PATH = "/"+SCRIPT+"/"+EVENT+"["+INDEX+"]/"+PARAMETERS;
	
	//Customer 
	String CUSTOMER_CUSTOMER_REFERENCE_NUMBER= "CustomerReferenceNumber";
	String CUSTOMER_TITLE = "Title";
	String CUSTOMER_FIRST_NAME = "FirstName";
	String CUSTOMER_MIDDLE_NAME = "MiddleName";
	String CUSTOMER_SURNAME = "Surname";
	String CUSTOMER_TELEPHONE_NUMBER = "TelephoneNumber";
	String CUSTOMER_EMAIL = "Email";
	String CUSTOMER_DOB = "DateOfBirth";
	String CUSTOMER_GENDER = "Gender";
	String CUSTOMER_ADDRESS_LINE1 = "AddressLine1";
	String CUSTOMER_ADDRESS_LINE2 = "AddressLine2";
	String CUSTOMER_ADDRESS_LINE3 = "AddressLine3";
	String CUSTOMER_ADDRESS_LINE4 = "AddressLine4";
	String CUSTOMER_ADDRESS_LINE5 = "AddressLine5";
	String CUSTOMER_ADDRESS_LINE6 = "AddressLine6";
	String CUSTOMER_POST_CODE = "PostCode";
	
	//Card
	String CARD_CUSTOMER_REFERENCE_NUMBER = "CustomerReferenceNumber";
	String CARD_DESIGNATION = "Designation";
	String CARD_CARD_NICK_NAME = "CardNickName";
	String CARD_IDENTIFIER = "Identifier";
	String CARD_STATE = "State";
	String CARD_PHOTO = "Photo";
	String CARD_EXPIRY_DATE = "ExpiryDate";
	
	//Product
	String PRODUCT_CARD_ID = "CardId";
	String PRODUCT_NAME = "Name";
	String PRODUCT_PRICE = "Price";
	String PRODUCT_PAYMENT_REFERENCE = "PaymentReference";
	String PRODUCT_START_DATE = "StartDate";
	String PRODUCT_EXPIRY_DATE = "ExpiryDate";
	
}
