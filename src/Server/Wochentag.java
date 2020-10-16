package Server;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Die Klasse stellt einen Terminblock dar, welcher einen physischen TErmin wie eine Klassenstunde repräsentiert
 * @author Tom Schubert
 * @version 1.0
 */
public class Wochentag {
	private Calendar cal;
	private String wochentag;
	private boolean schultag=false;
	public static final String ZEITPLANSTART="5:00";
	public static final String ZEITPLANENDE="20:30";
	public static final Integer ZEITPLANLÄNGE=32;
	//Zeitplanung Tag von 5 Uhr bis 21 Uhr --> 16 Stunden--> 32 halbstündige Segmente
	private HashMap<String,Terminblock> Zeitplanung;
	
	public Wochentag(Calendar cal) throws Uhrzeitexception {
		this.Zeitplanung = new HashMap<>(Wochentag.ZEITPLANLÄNGE);
		for (int i = 5;i<(Wochentag.ZEITPLANLÄNGE/2)+5;i++) {
			String uhrzeit=i+":00";
			this.Zeitplanung.put(uhrzeit, new Terminblock("frei",uhrzeit));
				if(i<20) {
					uhrzeit=i+":30";
					this.Zeitplanung.put(uhrzeit, new Terminblock("frei",uhrzeit));
				}
		}
		this.cal = cal;
		this.wochentag = wochentag(cal.get(Calendar.DAY_OF_WEEK));
		if(1<(cal.get(Calendar.DAY_OF_WEEK))&&(cal.get(Calendar.DAY_OF_WEEK))<7) 
		{
			this.schultag=true;
		}
	}
	/**
	 * Getter für das Datum des Tages
	 * @return Datum 
	 */
	public String getDatum() {
	return Integer.toString(cal.get(Calendar.DAY_OF_MONTH))+ "." + Integer.toString(cal.get(Calendar.MONTH)+1)+"."+Integer.toString(cal.get(Calendar.YEAR));
	}
	/**
	 * Ordnet den Zahlen der Wochentage Strings zu
	 * @param i
	 * @return wochentag
	 */
	private String wochentag(int i) {
		switch(i){      
        case 1:
        	return "Sonntag";       
        case 2:
        	return "Montag";         
        case 3:
        	return "Dienstag";         
        case 4:
            return "Mittwoch";         
        case 5:
        	return "Donnerstag";       
        case 6:
        	return "Freitag";
        case 7:
        	return "Samstag";
        default:
        	return null;
           
		}
		
	}
	/**
	 * Gibt zurück ob der Termin frei ist
	 * @param uhrzeit
	 * @return boolean hasfreespace
	 * @throws Uhrzeitexception
	 */
	public Boolean hasfreespace(String uhrzeit) throws Uhrzeitexception {
		if(gettermin(uhrzeit).getPriorität()<0){
			return true;
		}
		else {
			return false;
		}
		
	}
	/**
	 * Gibt einen Terminblock an einer gewissen Uhrzeit zurück
	 * @param uhrzeit
	 * @return
	 * @throws Uhrzeitexception
	 */
	public Terminblock gettermin(String uhrzeit) throws Uhrzeitexception {
			return this.Zeitplanung.get(uhrzeit);
		}
	/**
	 * Setter für ZEitplan
	 * @param terminblock
	 */
	public void setZeitplan(Terminblock terminblock){
		for(Termin t:terminblock.getTerminblock()) {
			this.Zeitplanung.remove(t.getZeitpunkt(),this.Zeitplanung.get(t.getZeitpunkt()));
			this.Zeitplanung.put(t.getZeitpunkt(), terminblock);
		}
	}
				
	/**
	 * Getter ob es sich um ein Schultag handelt oder nicht
	 * @return schultag
	 */
	public boolean isSchultag() {
		return schultag;
	}
	/**
	 * gibt den Wochentag als String zurück
	 * @return wochentag
	 */
	public String getWochentag() {
		return wochentag;
	}
	/**
	 * Gettet den Calendar auf dem der Tag basiert
	 * @return cal
	 */
	public Calendar getCal() {
		return cal;
	}
	
	
}
