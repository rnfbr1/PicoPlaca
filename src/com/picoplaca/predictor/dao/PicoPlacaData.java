package com.picoplaca.predictor.dao;

import java.time.LocalTime;
import java.util.Map;

/***
 * Interface for various types of datasources
 * @author Fabricio Jacome
 *
 */
public interface PicoPlacaData {

	/***
	 * Obtains a map of restricted time ranges
	 * @return Map
	 */
	public Map<String, LocalTime> obtainRestrictedTimes();
	
	/***
	 * Obtains a map of digit information
	 * @return Map
	 */
	public String findDigits(Integer digit); 
	
	/****
	 * Obtains a day code from a license plate digit
	 * @param digit
	 * @return Integer
	 */
	public Integer findDayFromDigit(String digit);
}
