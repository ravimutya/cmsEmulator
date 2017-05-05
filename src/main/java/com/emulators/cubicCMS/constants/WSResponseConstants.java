package com.emulators.cubicCMS.constants;

public interface WSResponseConstants {
    String RESPONSE_SUCCESS_RESPONSE_CODE = "0001";
    
    String RESPONSE_CODE_INVALID_REQUEST = "0002";
	String RESPONSE_MESSAGE_INVALID_REQUEST = "Invalid Request";
    
    String RESPONSE_CODE_CUSTOMER_ALREADY_EXISTS = "0003";
    String RESPONSE_MESSAGE_CUSTOMER_ALREADY_EXISTS = "Customer already exists";
    
    String RESPONSE_CODE_UNKNOWN_CUSTOMER = "0007";
    String RESPONSE_MESSAGE_UNKNOWN_CUSTOMER = "Unknown Customer";
    
    String RESPONSE_CODE_UNKNOWN_CARD_DESIGNATOR = "0006";
    String RESPONSE_MESSAGE_UNKNOWN_CARD_DESIGNATOR = "Unknown Card Designator";    

    String RESPONSE_CODE_UNKNOWN_PRODUCT_NAME = "0009";
    String RESPONSE_MESSAGE_UNKNOWN_PRODUCT_NAME = "Unknown Product Name";    

    String RESPONSE_CODE_UNKNOWN_ISRN = "0008";
    String RESPONSE_MESSAGE_UNKNOWN_ISRN = "Unknown ISRN";
}
