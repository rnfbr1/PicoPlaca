/**
 * 
 */
package com.picoplaca.predictor.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.picoplaca.predictor.constant.Parameter;
import com.picoplaca.predictor.constant.Validation;

/**
 * Helper class for input validation
 * @author Fabricio Jacome
 *
 */
public class InputValidation {

	/**
	 * 
	 */
	public InputValidation() {
		super();
		
	}

	/***
	 * Validates a full date
	 * @param fullDateString
	 * @return Boolean
	 */
	public Boolean validateFullDate(String fullDateString) {
		Boolean returnValue = Boolean.FALSE;
		DateTimeFormatter formatter =  DateTimeFormatter
				.ofPattern(Parameter.FORMAT_YYYY_MM_DD_HH_MM);
		try {
			LocalDate localDate = LocalDate.parse(fullDateString, formatter);
		    returnValue = Boolean.TRUE;
		} catch (DateTimeParseException e) {
			returnValue = Boolean.FALSE;
		} catch (Exception e) {
			returnValue = Boolean.FALSE;
		}
		
		return returnValue;
	}

	/***
	 * Validates a license plate
	 * @param licensePlateNumber
	 * @return
	 */
	public Boolean validateLicensePlate(String licensePlateNumber) {
		Boolean returnValue = Boolean.FALSE;
		if (null != licensePlateNumber) {
			Boolean hasMinimumLength = licensePlateNumber.length() == 
					Validation.MIN_ECUADORIAN_PLATE_LENGTH ? 
							Boolean.TRUE : Boolean.FALSE;
			Boolean hasMaximumLength = licensePlateNumber.length() == 
					Validation.MAX_ECUADORIAN_PLATE_LENGTH ? 
							Boolean.TRUE : Boolean.FALSE;
			Boolean containsDash = licensePlateNumber.contains("-");
			Character firstDash = licensePlateNumber.charAt(3);
			Boolean dashCorrectPlace = firstDash.equals('-');
			
			returnValue = (hasMinimumLength || hasMaximumLength) && containsDash && dashCorrectPlace;
		} 
		
		return returnValue;
	}
}
