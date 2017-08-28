package com.picoplaca.predictor;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import com.picoplaca.predictor.PicoPlaca;

/***
 * Test class for pico placa application
 * @author Fabricio Jacome
 *
 */
public class PicoPlacaTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPicoPlacaPrediction() {
		//input
		String licensePlateNumber = "PXX-2947";
		String dateString = "2017-08-24";
		String timeString="06:11";
		String fullDateString = dateString + " " + timeString;
		//parsing and formatting
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(fullDateString, formatter);
        //Load  values
        PicoPlaca picoPlaca = new PicoPlaca();        
		
		//calculate pico placa date and time
        Boolean canBeOnRoad = 
        		picoPlaca.isRestricted(licensePlateNumber,localDateTime);
		//display message to user
		if (canBeOnRoad) {
			System.out.println("Car cannot circulate on " + dateString + " at " + timeString );
		} else {
			System.out.println("Car can circulate on " + dateString + " at " + timeString );
		}
		assertTrue(canBeOnRoad.getClass() == Boolean.class ? Boolean.TRUE : Boolean.FALSE);
		
	}
	
	@Test
	public void testFindDigits() {
		PicoPlaca picoPlaca = new PicoPlaca();
		picoPlaca.findDigits(0);		
	}
	
	@Test
	public void testFindRestrictedDay() {
		PicoPlaca picoPlaca = new PicoPlaca();
		picoPlaca.findRestrictedDay("7");	
	    assertEquals("3",String.valueOf(picoPlaca.getRestrictedDay()));
	}
	
	@Test
	public void testObtainTimes() {
		PicoPlaca picoPlaca = new PicoPlaca();
		LocalTime localTime = LocalTime.parse("07:00");
		LocalTime morningFrom = picoPlaca.getRestrictedTimes().get("morning_from");
	    assertEquals(localTime, morningFrom);
	}
	
	@Test
	public void testIsMatchingDay() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime date = LocalDateTime.parse("2017-08-31 01:01",formatter);
		Integer restrictedDay = 3; //Thursday
		PicoPlaca picoPlaca = new PicoPlaca();
		assertTrue(picoPlaca.isMatchingDay(date, restrictedDay));
	}
	
	@Test
	public void testIsMatchingRangeInside() {
		PicoPlaca picoPlaca = new PicoPlaca();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateToEvaluate = LocalDateTime.parse("2017-08-31 08:30",formatter);//considering pico placa start time is 7:00
		LocalTime start = picoPlaca.getRestrictedTimes().get("morning_from");
		LocalTime end = picoPlaca.getRestrictedTimes().get("morning_till");
			
		assertTrue(picoPlaca.isMatchingRange(dateToEvaluate, start, end));
	}
	
	
	@Test
	public void testIsMatchingRangeOutside() {
		PicoPlaca picoPlaca = new PicoPlaca();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateToEvaluate = LocalDateTime.parse("2017-08-31 06:30",formatter); //considering pico y placa start time is 7:00
		LocalTime start = picoPlaca.getRestrictedTimes().get("morning_from");
		LocalTime end = picoPlaca.getRestrictedTimes().get("morning_till");
			
		assertTrue(!picoPlaca.isMatchingRange(dateToEvaluate, start, end));
	}
	
	@Test
	public void testEvaluateHasPicoPlaca() {
		PicoPlaca picoPlaca = new PicoPlaca();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime inputDateTime = LocalDateTime.parse("2017-08-31 07:00", formatter);
		Integer restrictedDay = 3; //Thursday
		Boolean holiday = Boolean.FALSE;
		assertTrue(picoPlaca.evaluate(inputDateTime, restrictedDay, holiday));
	}
	
	@Test
	public void testEvaluateHasNoPicoPlaca() {
		PicoPlaca picoPlaca = new PicoPlaca();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime inputDateTime = LocalDateTime.parse("2017-08-31 15:59", formatter);
		Integer restrictedDay = 3; //Thursday
		Boolean holiday = Boolean.FALSE;
		assertTrue(!picoPlaca.evaluate(inputDateTime, restrictedDay, holiday));
	}

}
