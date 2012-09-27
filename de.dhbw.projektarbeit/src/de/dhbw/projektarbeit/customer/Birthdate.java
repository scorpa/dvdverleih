package de.dhbw.projektarbeit.customer;

import de.dhbw.projektarbeit.gui.*;

public class Birthdate {

	private GUI gui = new GUI();

	private String birthday;
	private int day, month, year;
	private boolean bday, bmonth, byear;

	public void getDay(String day) {
		this.day = Integer.parseInt(day);
		bday = true;
	}

	public void getMonth(String month) throws Exception {
		switch (month) {

		case "Januar":
			this.month = 1;
			break;

		case "Februar":
			this.month = 2;
			break;

		case "Maerz":
			this.month = 3;
			break;

		case "April":
			this.month = 4;
			break;

		case "Mai":
			this.month = 5;
			break;

		case "Juni":
			this.month = 6;
			break;

		case "Juli":
			this.month = 7;
			break;

		case "August":
			this.month = 8;
			break;

		case "September":
			this.month = 9;
			break;

		case "Oktober":
			this.month = 10;
			break;

		case "November":
			this.month = 11;
			break;

		case "Dezember":
			this.month = 12;
			break;

		default:
			// Fehlercode 005
			throw new Exception(
					"Es wurde ein Problem bei der Umwandlung des Datums festgestellt! Fehlercode 005");
		}

		bmonth = true;

	}

	public void getYear(String year) {
		this.year = Integer.parseInt(year);
		byear = true;
	}
	
	{
	
		year = Integer.parseInt(birthday.substring(6));

		if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {

		}

	}

}
