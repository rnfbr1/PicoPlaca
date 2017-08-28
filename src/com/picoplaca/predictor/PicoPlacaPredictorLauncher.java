/**
 * 
 */
package com.picoplaca.predictor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

import com.picoplaca.predictor.constant.Message;
import com.picoplaca.predictor.constant.Parameter;
import com.picoplaca.predictor.util.InputValidation;

/**
 * Application Launcher
 * @author Fabricio Jacome
 *
 */
public class PicoPlacaPredictorLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String licensePlateNumber = JOptionPane
				.showInputDialog(null, Message.ENTER_LICENSE_PLATE);
		String dateString = JOptionPane
				.showInputDialog(null, Message.ENTER_DATE_STRING);
		String timeString = JOptionPane
				.showInputDialog(null, Message.ENTER_TIME_STRING);
		String fullDateString = dateString + " " + timeString;
		InputValidation inputValidation = new InputValidation();

		if (inputValidation.validateLicensePlate(licensePlateNumber) 
				&& inputValidation.validateFullDate(fullDateString)) {

			// parsing and formatting
			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern(Parameter.FORMAT_YYYY_MM_DD_HH_MM);
			LocalDateTime localDateTime = LocalDateTime
					.parse(fullDateString, formatter);

			PicoPlaca picoPlaca = new PicoPlaca();

			// calculate pico placa date and time
			Boolean canBeOnRoad = picoPlaca.isRestricted(licensePlateNumber, localDateTime);
			// display message to user
			if (canBeOnRoad) {
				JOptionPane.showMessageDialog(null, Message.CAN_NOT_DRIVE + dateString + Message.AT + timeString);
			} else {
				JOptionPane.showMessageDialog(null, Message.CAN_DRIVE + dateString + Message.AT + timeString);
			}
		} else {
			JOptionPane.showMessageDialog(null, Message.INCORRECT_INPUT);
		}

	}

}
