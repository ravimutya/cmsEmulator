package com.emulators.cubicCMS.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.emulators.cubicCMS.constants.DataInputConstants;
import com.emulators.cubicCMS.constants.WSResponseConstants;
import com.emulators.cubicCMS.entities.CustomResponse;
import com.emulators.cubicCMS.entities.CustomerInformation;
import com.emulators.cubicCMS.entities.Event;
import com.emulators.cubicCMS.entities.Payment;
import com.emulators.cubicCMS.entities.Product;
import com.emulators.cubicCMS.entities.Script;
import com.emulators.cubicCMS.entities.SmartCard;


public class ImportDataInputValidation {
	public CustomResponse validateXML(Script script){
		CustomResponse customResponse = null;
		
		//Verify request xml is not blank/empty.
		if(script==null){
			customResponse = new CustomResponse();
			customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
			customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", Request is blank, provide the request with valid request data");			
			return customResponse;	
		}
		
		//TODO need to validate the time stamp is valid with other validations.
		
		//Verify time stamp is not blank/empty
		if((script.getTimestamp()==null) || (script.getTimestamp().trim().length()==0)){
			customResponse = new CustomResponse();
			customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
			customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", time is not provided or blank in request, provide the time in minutes in request");			
			return customResponse;				
		}
		
		try{
		List<Event> eventList = script.getEvent();
		for(Event event : eventList){
			String requestEvent = event.getRequestEvent();
			
			//Validate the request event is not empty.
			if((requestEvent==null) || (requestEvent.trim().length()==0)){
				customResponse = new CustomResponse();
				customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
				customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", request event is not provided or request event is empty");
				return customResponse;				
			}

			//Verify request event is valid(i.e request event should be 'Customer' or 'Card' or 'product')
			if(!(DataInputValidationRules.DATA_INPUT_REQUESTS_EVENTS.contains(requestEvent))){
				customResponse = new CustomResponse();
				customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
				customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", "+DataInputConstants.REQUEST_EVENT+" '"+requestEvent+"'");
				return customResponse;			
			}			
			
			customResponse = validateRequest(event, requestEvent);
			if(customResponse!=null){
				return customResponse;
			}
			
		}
			
		}catch (Exception e) {
			customResponse = new CustomResponse();
			customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
			customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST);
			return customResponse;
		}
		
		return customResponse;
	}	
	
	private CustomResponse validateRequest(Event event, String requestEvent){
		CustomResponse customResponse = null;
		
			switch(requestEvent){
				case DataInputConstants.CUSTOMER:
					CustomerInformation customerInformation = event.getCustomer();
					if(customerInformation==null){
						customResponse = new CustomResponse();
						customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
						customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", '"+DataInputConstants.CUSTOMER+"' details are missing for request event '"+requestEvent+"'");
						return customResponse;						
					}
					
					//Validate mandatory fields for customer
					customResponse = validateMandatoryFields(requestEvent, customerInformation);
					if(customResponse!=null){
						return customResponse;
					}
					
					//Validate field length for customer
					customResponse = validateFieldLength(requestEvent, customerInformation);
					if(customResponse!=null){
						return customResponse;
					}	
					
					//Validate date for customer
					customResponse = validateDate(requestEvent, customerInformation);
					if(customResponse!=null){
						return customResponse;
					}					
				break; // end of customer.

				case DataInputConstants.CARD:
					SmartCard smartCard = event.getSmartCard();
					if(smartCard==null){
						customResponse = new CustomResponse();
						customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
						customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", '"+DataInputConstants.CARD+"' details are missing for request event '"+requestEvent+"'");
						return customResponse;						
					}
					
					//Validate mandatory fields for card
					customResponse = validateMandatoryFields(requestEvent, smartCard);
					if(customResponse!=null){
						return customResponse;
					}	
					
					//Validate field length for customer
					customResponse = validateFieldLength(requestEvent, smartCard);
					if(customResponse!=null){
						return customResponse;
					}	
					
					//Validate date for card
					customResponse = validateDate(requestEvent, smartCard);
					if(customResponse!=null){
						return customResponse;
					}					
				break; // end of card.

				case DataInputConstants.PRODUCT:
					Product product = event.getProduct();
					if(product==null){
						customResponse = new CustomResponse();
						customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
						customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", '"+DataInputConstants.PRODUCT+"' details are missing for request event '"+requestEvent+"'");
						return customResponse;						
					}
					
					//Validate mandatory fields for product
					customResponse = validateMandatoryFields(requestEvent, product);
					if(customResponse!=null){
						return customResponse;
					}	
					
					//Validate field length for product
					customResponse = validateFieldLength(requestEvent, product);
					if(customResponse!=null){
						return customResponse;
					}	
					
					//Validate date for product
					customResponse = validateDate(requestEvent, product);
					if(customResponse!=null){
						return customResponse;
					}					
				break;
				
			}		
		
		return customResponse;
	}
	
	private CustomResponse checkMandatoryFields(String data, String mandatoryXmlTag, String requestEvent){
		CustomResponse customResponse = null;
		boolean flag = (data!=null) ? ((data.trim().length()!=0) ? true : false) : false;
		
		if(!flag){
			customResponse = new CustomResponse();
			customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
			customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", Mandatory data for '"+mandatoryXmlTag+"' is missing for request event '"+requestEvent+"'");				
		}
		return customResponse;	
	}	
	
	/** Validates mandatory fields for xml elements.
	 * 
	 * @param xmlMap
	 * @param requestEvent
	 * @return
	 */	
	private CustomResponse validateMandatoryFields(String requestEvent, Object object){
		CustomResponse customResponse = null;				
		List<String> mandatoryXmlTags = null;
		 
		CustomerInformation customerInformation = null;
		SmartCard smartCard = null;
		Product product = null;		
		
		if(requestEvent.equalsIgnoreCase(DataInputConstants.CUSTOMER)){
			if(object instanceof CustomerInformation){
				mandatoryXmlTags = DataInputValidationRules.CUSTOMER_MANDATORY_XML_TAGS;
				
				customerInformation = (CustomerInformation) object;
				
				//Validate customer gender is male or female
				String customerGender = customerInformation.getGender();
				if((customerGender != null) ? ((customerGender.trim().length()!=0) ? true : false) : false){
					customerGender = firstLetterCaps(customerGender);
					
					if(!(DataInputValidationRules.CUSTOMER_GENDER_OPTIONS.contains(customerGender))){
						String errMessage = "Customer gender should be either Male or Female for request event '"+requestEvent+"', but gender provided as '"+customerGender+"'";					
						customResponse = new CustomResponse();
						customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
						customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", "+errMessage);					
						return customResponse;					
					}					
				}
				
				//Verify Customer email id format. 
				String customerEmailId = customerInformation.getEmail();
				if(customerEmailId != null){
					boolean flag = isEmailValid(customerEmailId);
					if(!flag){
						customResponse = new CustomResponse();
						customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
						customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", Email format violoation for '"+DataInputConstants.CUSTOMER_EMAIL+"' for request event '"+requestEvent+"'");																		
						return customResponse;
					}					
				}
				
			}
		}//End of customer validation		

		//Validate card
		else if(requestEvent.equalsIgnoreCase(DataInputConstants.CARD)){
			if(object instanceof SmartCard){
				mandatoryXmlTags = DataInputValidationRules.CARD_MANDATORY_XML_TAGS;
				
				smartCard = (SmartCard) object;
				
				String cardState = smartCard.getState();
				if(cardState!=null){
					cardState = firstLetterCaps(cardState);
					
					if(!(DataInputValidationRules.CARD_STATE_OPTIONS.contains(cardState))){
						String errMessage = "Card state should be either Active or Expired for request event '"+requestEvent+"', but card state provided as '"+cardState+"'";					
						customResponse = new CustomResponse();
						customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
						customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", "+errMessage);					
						return customResponse;					
					}					
				}
			}
		}//End of card validation		

		else if(requestEvent.equalsIgnoreCase(DataInputConstants.PRODUCT)){
			if(object instanceof Product){
				mandatoryXmlTags = DataInputValidationRules.PRODUCT_MANDATORY_XML_TAGS;
				
				product = (Product) object;
				
			}
		}
		
		switch(requestEvent){
			case DataInputConstants.CUSTOMER:
			 	for(String mandatoryXmlTag : mandatoryXmlTags){
					switch(mandatoryXmlTag){
						case DataInputValidationRules.CUSTOMER_CUSTOMER_REFERENCE_NUMBER:	
							customResponse = checkMandatoryFields(customerInformation.getCustRefNum(), mandatoryXmlTag, requestEvent);						
							break;
						case DataInputValidationRules.CUSTOMER_FIRST_NAME:
							customResponse = checkMandatoryFields(customerInformation.getFirstName(), mandatoryXmlTag, requestEvent);	
							break;
						case DataInputValidationRules.CUSTOMER_SURNAME:	
							customResponse = checkMandatoryFields(customerInformation.getSurName(), mandatoryXmlTag, requestEvent);
							break;
						case DataInputValidationRules.CUSTOMER_EMAIL:	
							customResponse = checkMandatoryFields(customerInformation.getEmail(), mandatoryXmlTag, requestEvent);
							break;
						case DataInputValidationRules.CUSTOMER_POST_CODE:
							customResponse = checkMandatoryFields(customerInformation.getPostCode(), mandatoryXmlTag, requestEvent);	
							break;
						case DataInputValidationRules.CUSTOMER_ADDRESS_LINE1:	
							customResponse = checkMandatoryFields(customerInformation.getAddr1(), mandatoryXmlTag, requestEvent);
							break;																														
					}	
					
					if(customResponse!=null){
						return customResponse;
					}					 	
			 	}		
				break; //for customer
				
			 case DataInputConstants.CARD:
				 	for(String mandatoryXmlTag : mandatoryXmlTags){
						switch(mandatoryXmlTag){
							case DataInputValidationRules.CARD_CUSTOMER_REFERENCE_NUMBER:	
								customResponse = checkMandatoryFields(smartCard.getCustRefNum(), mandatoryXmlTag, requestEvent);	
								break;
							case DataInputValidationRules.CARD_DESIGNATION:	
								customResponse = checkMandatoryFields(smartCard.getDesignation(), mandatoryXmlTag, requestEvent);
								break;
							case DataInputValidationRules.CARD_CARD_NICK_NAME:
								customResponse = checkMandatoryFields(smartCard.getCardNickName(), mandatoryXmlTag, requestEvent);	
								break;
							case DataInputValidationRules.CARD_STATE:	
								customResponse = checkMandatoryFields(smartCard.getState(), mandatoryXmlTag, requestEvent);
								break;																													
						}	
						
						if(customResponse!=null){
							return customResponse;
						}						 	
				 	}		 
				 	break; //for card		
				 	
			 case DataInputConstants.PRODUCT:
				 	Payment payment = product.getPayment();
					if(payment==null){
						customResponse = new CustomResponse();
						customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
						customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", 'Payment' details are missing for request event '"+requestEvent+"'");
						return customResponse;				
					}	
					
				 	for(String mandatoryXmlTag : mandatoryXmlTags){
						switch(mandatoryXmlTag){
							case DataInputValidationRules.PRODUCT_CARD_ID:
								customResponse = checkMandatoryFields(product.getCardId(), mandatoryXmlTag, requestEvent);		
								break;
							case DataInputValidationRules.PRODUCT_NAME:	
								customResponse = checkMandatoryFields(product.getName(), mandatoryXmlTag, requestEvent);
								break;
							case DataInputValidationRules.PRODUCT_PRICE:	
								customResponse = checkMandatoryFields(payment.getPrice(), mandatoryXmlTag, requestEvent);
								break;
							case DataInputValidationRules.PRODUCT_PAYMENT_REFERENCE:	
								customResponse = checkMandatoryFields(payment.getPaymentReference(), mandatoryXmlTag, requestEvent);
								break;
							case DataInputValidationRules.PRODUCT_START_DATE:	
								customResponse = checkMandatoryFields(product.getStartDate(), mandatoryXmlTag, requestEvent);
								break;																																			
						}
						
						if(customResponse!=null){
							return customResponse;
						}						 	
				 	}					
					
				 break;	//for product	
		}
		return customResponse;
		
	}
	
	private CustomResponse checkMaxFieldLength(String data, int maxLength, String maxLengthXmlTag, String requestEvent){
		CustomResponse customResponse = null;
		if(data!=null){
			int actualMaxLength = data.trim().length();
			
			if(actualMaxLength > maxLength){
				customResponse = new CustomResponse();
				customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
				customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", Maximum length violoation for '"+maxLengthXmlTag+"' length should not be more than "+maxLength+" characters for request event '"+requestEvent+"'");						
				return customResponse;													     	
			}			
			
		}
		return customResponse;	
	}
	
	private CustomResponse checkMinFieldLength(String data, int minLength, String maxLengthXmlTag, String requestEvent){
		CustomResponse customResponse = null;
		if(data!=null){
			int actualMinLength = data.trim().length();
			
			if(!(actualMinLength >= minLength)){
				customResponse = new CustomResponse();
				customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
				customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", Minimum length violoation for '"+maxLengthXmlTag+"' length should not be less than "+minLength+" characters for request event '"+requestEvent+"'");													
				return customResponse;													     	
			}				
			
		}
		return customResponse;	
	}	
	
	/** Verify's maximum and minimum length fields for xml elements.
	 * 
	 * @param xmlMap
	 * @param requestEvent
	 * @return
	 */
	private CustomResponse validateFieldLength(String requestEvent, Object object){
		CustomResponse customResponse = null;				
		
		CustomerInformation customerInformation = null;
		SmartCard smartCard = null;
		Product product = null;
		
		List<String> maxLengthTags = null;
		List<String> minLengthTags = null;
		Map<String, Integer> maxLengthMap = null;	
		Map<String, Integer> minLengthMap = null;
		
		if(object instanceof CustomerInformation){
			customerInformation = (CustomerInformation) object;
		}else if(object instanceof SmartCard){
			smartCard = (SmartCard) object;
		}else if(object instanceof Product){
			product = (Product) object;
		}
		
		switch(requestEvent){
		 //Field length validation for customer
		 case DataInputConstants.CUSTOMER:
			 maxLengthTags = DataInputValidationRules.CUSTOMER_MAX_LENGTH_XML_TAGS;
			 minLengthTags = DataInputValidationRules.CUSTOMER_MIN_LENGTH_XML_TAGS;
			 maxLengthMap = DataInputValidationRules.CUSTOMER_MAX_LENGTH_MAP;
			 minLengthMap = DataInputValidationRules.CUSTOMER_MIN_LENGTH_MAP;
			 
			 //Max Field length validation for customer
			 if(maxLengthTags!=null && customerInformation!=null){
				 for(String maxLengthXmlTag : maxLengthTags){
					 int maxLength = maxLengthMap.get(maxLengthXmlTag);
					 
					 switch(maxLengthXmlTag){
					 	case DataInputValidationRules.CUSTOMER_CUSTOMER_REFERENCE_NUMBER:
					 		customResponse = checkMaxFieldLength(customerInformation.getCustRefNum(), maxLength, maxLengthXmlTag, requestEvent);
					 		break;
						case DataInputValidationRules.CUSTOMER_TITLE:
							customResponse = checkMaxFieldLength(customerInformation.getTitle(), maxLength, maxLengthXmlTag, requestEvent);	
							break;
						case DataInputValidationRules.CUSTOMER_FIRST_NAME:	
							customResponse = checkMaxFieldLength(customerInformation.getFirstName(), maxLength, maxLengthXmlTag, requestEvent);
							break;
						case DataInputValidationRules.CUSTOMER_MIDDLE_NAME:	
							customResponse = checkMaxFieldLength(customerInformation.getMiddleName(), maxLength, maxLengthXmlTag, requestEvent);
							break;
						case DataInputValidationRules.CUSTOMER_SURNAME:
							customResponse = checkMaxFieldLength(customerInformation.getSurName(), maxLength, maxLengthXmlTag, requestEvent);
							break;
						case DataInputValidationRules.CUSTOMER_TELEPHONE_NUMBER:	
							customResponse = checkMaxFieldLength(customerInformation.getTelePhone(), maxLength, maxLengthXmlTag, requestEvent);
							break;
						case DataInputValidationRules.CUSTOMER_EMAIL:	
							customResponse = checkMaxFieldLength(customerInformation.getEmail(), maxLength, maxLengthXmlTag, requestEvent);
							break;								
						case DataInputValidationRules.CUSTOMER_ADDRESS_LINE1:	
							customResponse = checkMaxFieldLength(customerInformation.getAddr1(), maxLength, maxLengthXmlTag, requestEvent);
							break;	
						case DataInputValidationRules.CUSTOMER_ADDRESS_LINE2:	
							customResponse = checkMaxFieldLength(customerInformation.getAddr2(), maxLength, maxLengthXmlTag, requestEvent);
							break;	
						case DataInputValidationRules.CUSTOMER_ADDRESS_LINE3:	
							customResponse = checkMaxFieldLength(customerInformation.getAddr3(), maxLength, maxLengthXmlTag, requestEvent);
							break;	
						case DataInputValidationRules.CUSTOMER_ADDRESS_LINE4:	
							customResponse = checkMaxFieldLength(customerInformation.getAddr4(), maxLength, maxLengthXmlTag, requestEvent);
							break;	
						case DataInputValidationRules.CUSTOMER_ADDRESS_LINE5:	
							customResponse = checkMaxFieldLength(customerInformation.getAddr5(), maxLength, maxLengthXmlTag, requestEvent);
							break;	
						case DataInputValidationRules.CUSTOMER_ADDRESS_LINE6:	
							customResponse = checkMaxFieldLength(customerInformation.getAddr6(), maxLength, maxLengthXmlTag, requestEvent);
							break;	
						case DataInputValidationRules.CUSTOMER_POST_CODE:	
							customResponse = checkMaxFieldLength(customerInformation.getPostCode(), maxLength, maxLengthXmlTag, requestEvent);
							break;								
					 }
					 
					 if(customResponse!=null){
						 return customResponse;
					 }
				 }
			 }
			 
			//Min Field length validation for customer
			 if(minLengthTags!=null && customerInformation!=null){
				 for(String minLengthXmlTag : minLengthTags){
					 int minLength = minLengthMap.get(minLengthXmlTag);
					 switch(minLengthXmlTag){
					 	case DataInputValidationRules.CUSTOMER_POST_CODE:
					 		customResponse = checkMinFieldLength(customerInformation.getPostCode(), minLength, minLengthXmlTag, requestEvent);
					 		break;
					 }
					 if(customResponse!=null){
						 return customResponse;
					 }					 
				 }
			 }
			 
			 break; //end of customer
			 
		 //Max Field length validation for card	 
		 case DataInputConstants.CARD:
				maxLengthTags = DataInputValidationRules.CARD_MAX_LENGTH_XML_TAGS;
				maxLengthMap = DataInputValidationRules.CARD_MAX_LENGTH_MAP;
				 if(maxLengthTags!=null && smartCard!=null){
					 for(String maxLengthXmlTag : maxLengthTags){
						 int maxLength = maxLengthMap.get(maxLengthXmlTag);
						 switch(maxLengthXmlTag){
						 case DataInputValidationRules.CARD_CUSTOMER_REFERENCE_NUMBER:
						 		customResponse = checkMaxFieldLength(smartCard.getCustRefNum(), maxLength, maxLengthXmlTag, requestEvent);
						 		break;
						 case DataInputValidationRules.CARD_DESIGNATION:
						 		customResponse = checkMaxFieldLength(smartCard.getDesignation(), maxLength, maxLengthXmlTag, requestEvent);
						 		break;
						 case DataInputValidationRules.CARD_CARD_NICK_NAME:
						 		customResponse = checkMaxFieldLength(smartCard.getCardNickName(), maxLength, maxLengthXmlTag, requestEvent);
						 		break;
						 case DataInputValidationRules.CARD_IDENTIFIER:
						 		customResponse = checkMaxFieldLength(smartCard.getIdentifier(), maxLength, maxLengthXmlTag, requestEvent);
						 		break;
						 case DataInputValidationRules.CARD_STATE:
						 		customResponse = checkMaxFieldLength(smartCard.getState(), maxLength, maxLengthXmlTag, requestEvent);
						 		break;						 		
						 }
						 if(customResponse!=null){
							 return customResponse;
						 }						 
					 }
					 
				 }
			 break; //end of Card 
			
		 //Max Field length validation for product	 
		 case DataInputConstants.PRODUCT:
				maxLengthTags = DataInputValidationRules.PRODUCT_MAX_LENGTH_XML_TAGS;
				maxLengthMap = DataInputValidationRules.PRODUCT_MAX_LENGTH_MAP;
				if(maxLengthTags!=null && product!=null){
					for(String maxLengthXmlTag : maxLengthTags){
						int maxLength = maxLengthMap.get(maxLengthXmlTag);
						 switch(maxLengthXmlTag){
						 case DataInputValidationRules.PRODUCT_CARD_ID:
						 		customResponse = checkMaxFieldLength(product.getCardId(), maxLength, maxLengthXmlTag, requestEvent);
						 		break;
						 case DataInputValidationRules.PRODUCT_NAME:
						 		customResponse = checkMaxFieldLength(product.getName(), maxLength, maxLengthXmlTag, requestEvent);
						 		break;						 		
						 }
						 if(customResponse!=null){
							 return customResponse;
						 }
					}
				}
			 break;//end of product
			 
		}//end of request event
		
		return customResponse;
	}
	

	/** Verify's maximum and minimum length fields for xml elements.
	 * 
	 * @param xmlMap
	 * @param requestEvent
	 * @return
	 */
	private CustomResponse validateDate(String requestEvent, Object object){
		CustomResponse customResponse = null;				
		
		CustomerInformation customerInformation = null;
		SmartCard smartCard = null;
		Product product = null;
		
		List<String> dateTags = null;
		
		if(object instanceof CustomerInformation){
			customerInformation = (CustomerInformation) object;
		}else if(object instanceof SmartCard){
			smartCard = (SmartCard) object;
		}else if(object instanceof Product){
			product = (Product) object;
		}
		
		//for Customer
		if(requestEvent.equalsIgnoreCase(DataInputConstants.CUSTOMER)){
			
			dateTags = DataInputValidationRules.CUSTOMER_DATE_XML_TAGS;
			if(dateTags!=null){
				for(String dateXmlTag : dateTags){
					switch(dateXmlTag){
					case DataInputConstants.CUSTOMER_DOB:
						String date = customerInformation.getDob();
						if(date!=null){
							boolean flag = isDateValid(date);
							if(!flag){
								customResponse = new CustomResponse();
								customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
								customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", Date format violoation for xml tag '"+dateXmlTag+"' for request event '"+requestEvent+"', date format should be '"+DataInputValidationRules.DATE_FORMAT_VALIDATION+"'");																		
								return customResponse;													     							
							}							
						}
						break;
					}
				}
			}
			
			
		//for Card	
		}else if(requestEvent.equalsIgnoreCase(DataInputConstants.CARD)){
			dateTags = DataInputValidationRules.CARD_DATE_XML_TAGS;
			
			if(dateTags!=null){
				for(String dateXmlTag : dateTags){
					switch(dateXmlTag){
					case DataInputConstants.CARD_EXPIRY_DATE:
						String date = smartCard.getExpiry();
						if(date!=null){
							boolean flag = isDateValid(date);
							if(!flag){
								customResponse = new CustomResponse();
								customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
								customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", Date format violoation for xml tag '"+dateXmlTag+"' for request event '"+requestEvent+"', date format should be '"+DataInputValidationRules.DATE_FORMAT_VALIDATION+"'");																		
								return customResponse;													     							
							}							
						}
						break;
					}
				}
			}			
			
		//for Product	
		}else if(requestEvent.equalsIgnoreCase(DataInputConstants.PRODUCT)){
			dateTags = DataInputValidationRules.PRODUCT_DATE_XML_TAGS;
			
			if(dateTags!=null){
				for(String dateXmlTag : dateTags){
					switch(dateXmlTag){
					case DataInputConstants.PRODUCT_START_DATE:
						String prodStartDate = product.getStartDate();
						if(prodStartDate!=null){
							boolean flag = isDateValid(prodStartDate);
							if(!flag){
								customResponse = new CustomResponse();
								customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
								customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", Date format violoation for xml tag '"+dateXmlTag+"' for request event '"+requestEvent+"', date format should be '"+DataInputValidationRules.DATE_FORMAT_VALIDATION+"'");																		
								return customResponse;													     							
							}							
						}
						break; //end of product start date
						
					case DataInputConstants.PRODUCT_EXPIRY_DATE:
						String prodExpiryDate = product.getExpiryDate();
						if(prodExpiryDate!=null){
							boolean flag = isDateValid(prodExpiryDate);
							if(!flag){
								customResponse = new CustomResponse();
								customResponse.setResponseCode(WSResponseConstants.RESPONSE_CODE_INVALID_REQUEST);
								customResponse.setResponseMessage(WSResponseConstants.RESPONSE_MESSAGE_INVALID_REQUEST+", Date format violoation for xml tag '"+dateXmlTag+"' for request event '"+requestEvent+"', date format should be '"+DataInputValidationRules.DATE_FORMAT_VALIDATION+"'");																		
								return customResponse;													     							
							}							
						}
						break; //end of product end date
					}
				}
			}			
		}				
		

		
		return customResponse;	
	}
	
	private boolean isDateValid(final String dateToValidate){

		if(dateToValidate == null){
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(DataInputValidationRules.DATE_FORMAT_VALIDATION);
		sdf.setLenient(false);

		try {

			//if date is not valid, it will throw ParseException
			sdf.parse(dateToValidate);		

		} catch (ParseException e) {
			//e.printStackTrace();
			return false;
		}

		return true;
	}	
	
	private boolean isEmailValid(final String emailId) {
		String emailPattern =
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";		
		Pattern pattern = Pattern.compile(emailPattern);
		Matcher matcher = pattern.matcher(emailId);
		return matcher.matches();
	}	

	/** Make first letter in word upper case and rest of the letters to lower case
	 * 
	 * @param word
	 * @return
	 */
	private String firstLetterCaps(String word){
		if(word!=null && word.trim().length()>0){
			word = word.substring(0, 1).toUpperCase()+word.substring(1, word.length()).toLowerCase();
		}					
		return word;
	}	
	
	
}


















