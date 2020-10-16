package Server;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * Die Klasse stellt einen einfachen Termin, welcher immer eine halbe Stunde lang geht dar
 * @author Tom Schubert
 * @version 1.0
 */
public class Termin implements Comparable<Termin>{
	static String uhrzeitregex="\\d{1,2}:[0,3]{2}";
	private String zeitpunkt;
	public Termin(String zeitpunkt) throws Uhrzeitexception {
		
		formatierungprüfen(zeitpunkt,uhrzeitregex);
		this.zeitpunkt=zeitpunkt;

	}
	/**
	 * Prüft ob die Engabe des Strings den Uhrzeitsrichtlinien entspricht
	 * @param zuprüfen
	 * @param regex
	 * @throws Uhrzeitexception
	 */
	private static void formatierungprüfen(String zuprüfen,String regex) throws Uhrzeitexception {
		if(!(zuprüfen.matches(regex))) {
				throw new Uhrzeitexception();
		}
		
	}
	/**
	 * Gibt den Zeitpunkt zurück für den der Termin definiert ist (Start des Termins)
	 * @return zeitpunkt
	 */
	public String getZeitpunkt() {
		return zeitpunkt;
	}
	/**
	 * Vergleicht zwei Termine. 1= weiter in der zukunft
	 */
	@Override
	public int compareTo(Termin o) {
		// Es wird ein Calendar mit dem gliechen Datum und unterschiedlichen Zeiten erzeugt und dann verglichen
		// Somit lassen sich Termine zeitlich ordnen
		Calendar cal1=new GregorianCalendar(2020,10,10,Integer.parseInt(this.getZeitpunkt().split(":")[0]),Integer.parseInt(this.getZeitpunkt().split(":")[1]),0);
		Calendar cal2=new GregorianCalendar(2020,10,10,Integer.parseInt(o.getZeitpunkt().split(":")[0]),Integer.parseInt(o.getZeitpunkt().split(":")[1]),0);
		return cal1.compareTo(cal2);
	}
	
	
}

