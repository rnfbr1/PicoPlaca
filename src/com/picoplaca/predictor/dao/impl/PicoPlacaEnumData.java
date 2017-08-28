package com.picoplaca.predictor.dao.impl;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import com.picoplaca.predictor.dao.PicoPlacaData;
import com.picoplaca.predictor.enumeration.PicoPlacaDigit;
import com.picoplaca.predictor.enumeration.PicoPlacaTime;

/***
 * Implementation class that gets data from Enumerations
 * @author Fabricio Jacome
 *
 */
public class PicoPlacaEnumData implements PicoPlacaData {

	@Override
	public Map<String, LocalTime> obtainRestrictedTimes() {
		Map<String, LocalTime> timesMap = new HashMap<>();
		for (PicoPlacaTime p : PicoPlacaTime.values()) {
			LocalTime localTime = LocalTime.parse(p.getTime());
			timesMap.put(p.getName(), localTime);
		}

		return timesMap;

	}

	@Override
	public String findDigits(Integer day) {

		String digits = null;
		for (PicoPlacaDigit v : PicoPlacaDigit.values()) {
			if (v.getDay().equals(day)) {
				digits = v.getDigits();
				break;
			}
		}
		return digits;

	}

	@Override
	public Integer findDayFromDigit(String digit) {
		Integer day = null;
		for (PicoPlacaDigit v : PicoPlacaDigit.values()) {
			if (v.getDigits().contains(digit)) {
				day = v.getDay();
				break;
			}
		}
		return day;
	}

}
