package de.dhbw.projektarbeit.customer;

import de.dhbw.projektarbeit.gui.*;

public class Birthdate {

	private GUI gui = new GUI();
	

	private String birthday;
	private int day, month, year;
	private boolean schaltjahr, tage;
	
	public enum enuMonth {
		Januar, Februar, Maerz, April, Mai, Juni, Juli, August, September, Oktober, November, Dezember
	}
	
	public String generateBirthday(String day, String month, String year) throws Exception{
		
		// Parsen des Jahrs von String nach int und Abfrage nach Schaltjahr. tage = true ist ein Monat mit 31 Tagen
		this.year = Integer.parseInt(year);
		if (this.year % 4 == 0 && (this.year % 100 != 0 || this.year % 400 == 0)) {
			schaltjahr = true;
		}
		
		
		
		// Umwandlung des Monatsstring in eine int 
		
		switch (month) {

		case "Januar":
			this.month = 1;
			tage = true;
			break;

		case "Februar":
			this.month = 2;
			break;

		case "Maerz":
			this.month = 3;
			tage = true;
			break;

		case "April":
			this.month = 4;
			break;

		case "Mai":
			this.month = 5;
			tage = true;
			break;

		case "Juni":
			this.month = 6;
			break;

		case "Juli":
			this.month = 7;
			tage = true;
			break;

		case "August":
			this.month = 8;
			tage = true;
			break;

		case "September":
			this.month = 9;
			break;

		case "Oktober":
			this.month = 10;
			tage = true;
			break;

		case "November":
			this.month = 11;
			break;

		case "Dezember":
			this.month = 12;
			tage = true;
			break;

		default:
			// Fehlercode 005
			throw new Exception(
					"Es wurde ein Problem bei der Umwandlung des Datums festgestellt! Fehlercode 005");
		}
		
		// Parsen des Tages von String nach int, Überprüfung, ob Schaltjahr = true und Februar mit falschen Datum ausgewählt ist bzw. der Monat nur 30 Tage hat.
			this.day = Integer.parseInt(day);
			if (this.day == 31 && tage == false){
				this.day = 30;
				throw new Exception("ew");
			}
			
		
		
		return(birthday);
		
	}

	

}
