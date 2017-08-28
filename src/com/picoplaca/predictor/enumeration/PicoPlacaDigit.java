package com.picoplaca.predictor.enumeration;

/***
 * Enumeration which models days and corresponding digits
 * @author Fabricio Jacome
 *
 */
public enum PicoPlacaDigit {

	MONDAY(0,"1,2"), TUESDAY(1,"3,4"), WEDNESDAY(2,"5,6"),
	THURSDAY(3,"7,8"), FRIDAY(4,"9,0");
	
	private Integer day;
	private String digits;
	
	PicoPlacaDigit(Integer day,String digits) {
		this.day = day;
		this.digits = digits;
	}

	public String getDigits() {
		return digits;
	}

	/*public void setDigits(String digits) {
		this.digits = digits;
	}*/

	/**
	 * @return the day
	 */
	public Integer getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	/*public void setDay(Integer day) {
		this.day = day;
	}*/
			
	
}
