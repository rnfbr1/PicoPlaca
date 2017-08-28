package com.picoplaca.predictor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import com.picoplaca.predictor.constant.Parameter;
import com.picoplaca.predictor.dao.PicoPlacaData;
import com.picoplaca.predictor.dao.impl.PicoPlacaEnumData;

/***
 * Class for pico placa application
 * @author Fabricio Jacome
 *
 */
public class PicoPlaca {
    private String digits;
    private PicoPlacaData picoPlacaData = new PicoPlacaEnumData();
    private Map<String,LocalTime> restrictedTimes = new HashMap<String,LocalTime>();
    private Integer restrictedDay;
       
    /**
     * Constructor
     */
	public PicoPlaca() {
    	init();
    }
    
	/***
	 * Initialization
	 */
	public void init() {
		restrictedTimes = picoPlacaData.obtainRestrictedTimes();//PicoPlacaTime.MORNING_FROM.obtainRestrictedTimes();
		
	}
	
	/***
	 * Finds whether a vehicle has pico placa restriction based on
	 * license plate number and a given date and time
	 * @param licensePlateNumber
	 * @param inputDateTime
	 * @return Boolean
	 */
	public Boolean isRestricted(String licensePlateNumber, LocalDateTime inputDateTime) {
		String licensePlateDigit = String.valueOf(this.obtainLicensePlateLastDigit(licensePlateNumber));
		//Determines restriction day
		this.findRestrictedDay(licensePlateDigit);
		inputDateTime.getDayOfWeek().getValue();
		 //test for holiday
		 Boolean holiday = isHoliday(inputDateTime);
		 //compare time with restrictedTimes
		 Boolean restricted = evaluate(inputDateTime, this.restrictedDay, holiday);
	     return restricted;		
	}
	
	/***
	 * Evaluates based on a date and time, restricted day code and 
	 * a flag for holiday
	 * @param inputDateTime
	 * @param restrictedDay2
	 * @param holiday
	 * @return Boolean
	 */
	public Boolean evaluate(LocalDateTime inputDateTime, Integer restrictedDay2,Boolean holiday) {
		Boolean resultEvaluation = Boolean.FALSE;
		if (holiday) {
			resultEvaluation = Boolean.FALSE;
		} else {
			
			Boolean matchesDay = isMatchingDay(inputDateTime,restrictedDay2);
			Boolean matchesMorningRange = isMatchingRange(inputDateTime,restrictedTimes
					.get(Parameter.MORNING_FROM),restrictedTimes.get(Parameter.MORNING_TILL));
			Boolean matchesAfternoonRange = isMatchingRange(inputDateTime,restrictedTimes
					.get(Parameter.AFTERNOON_FROM),restrictedTimes.get(Parameter.AFTERNOON_TILL));
			resultEvaluation = matchesDay && ( matchesMorningRange || matchesAfternoonRange);
			
		}
		
		return resultEvaluation;
	}

	/***
	 * Evaluates a date against a range of times
	 * @param date
	 * @param start
	 * @param end
	 * @return Boolean
	 */
	public Boolean isMatchingRange(LocalDateTime date, LocalTime start, LocalTime end) {
		LocalDateTime dateFrom = date.withHour(start.getHour()).withMinute(start.getMinute());
		LocalDateTime dateTo = date.withHour(end.getHour()).withMinute(end.getMinute());
		Boolean matchingRangeFrom = date.isEqual(dateFrom) || date.isAfter(dateFrom);
		Boolean matchingRangeTo = date.isEqual(dateTo) || date.isBefore(dateTo);
		return matchingRangeFrom && matchingRangeTo;
	}

	/***
	 * Obtains a day of the week from a datetime and finds a match
	 * @param date
	 * @param restrictedDay2
	 * @return Boolean
	 */
	public Boolean isMatchingDay(LocalDateTime date, Integer restrictedDay2) {
		Boolean matchingDay = date.getDayOfWeek().getValue() == restrictedDay2 + 1;
		return matchingDay;
	}

	public Boolean isHoliday(LocalDateTime inputDateTime) {
		return Boolean.FALSE;
	}

	public void findDigits(Integer day) {
		this.setDigits(picoPlacaData.findDigits(day));
	}
	
   public void findRestrictedDay(String digit) {		
		this.restrictedDay = picoPlacaData.findDayFromDigit(digit);
	}
   
   public Character obtainLicensePlateLastDigit(String licensePlate) {
	   return licensePlate.charAt(licensePlate.length()-1);
   }
	
	public String getDigits() {
		return digits;
	}

	public void setDigits(String digits) {
		this.digits = digits;
	}

	public Map<String,LocalTime> getRestrictedTimes() {
		return restrictedTimes;
	}

	public void setRestrictedTimes(Map<String,LocalTime> restrictedTimes) {
		this.restrictedTimes = restrictedTimes;
	}

	public Integer getRestrictedDay() {
		return restrictedDay;
	}

	public void setRestrictedDay(Integer restrictedDay) {
		this.restrictedDay = restrictedDay;
	}
	
	
}
