/**
 * 
 */
package com.picoplaca.predictor;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import com.picoplaca.predictor.util.InputValidation;

/**
 * @author Fabricio Jacome
 *
 */
public class InputValidationTest {

	/**
	 * Test method for {@link com.picoplaca.predictor.util.InputValidation#validateFullDate(java.lang.String)}.
	 */
	@Test
	public void testValidateFullDate() {
		String dateString = "2017-08-24";
		String timeString="06:11";
		String fullDateString = dateString + " " + timeString;
		InputValidation validation = new InputValidation();
		assertTrue(validation.validateFullDate(fullDateString));
	}

	/**
	 * Test method for {@link com.picoplaca.predictor.util.InputValidation#validateLicensePlate(java.lang.String)}.
	 */
	@Test
	public void testValidateLicensePlate() {
		String licensePlateNumber = "PXX-2947";
		InputValidation validation = new InputValidation();
		assertTrue(validation.validateLicensePlate(licensePlateNumber));
		
	}

}
