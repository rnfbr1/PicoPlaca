package com.picoplaca.predictor.enumeration;

/***
 * Enumeration that models the time ranges of Pico y Placa
 * @author Fabricio Jacome
 *
 */
public enum PicoPlacaTime {

	MORNING_FROM("morning_from","07:00"),MORNING_TILL("morning_till","09:30"),
	AFTERNOON_FROM("afternoon_from","16:00"), AFTERNOON_TILL("afternoon_till","19:30");
	
	String name;
	String time;
	
	PicoPlacaTime(String name,String time) {
		this.name = name;
		this.time = time;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	/*public void setName(String name) {
		this.name = name;
	}*/

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	
		
}
