package Server;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
/**
 * Die Klasse stellt den Stundenplan dar
 * @author Tom Schubert
 * @version 1.0
 */
public class Stundenplan{
	final int CAPACITY=200; // Größe des Kalenders
	HashMap<String,Wochentag> Kalender;
	
	public Stundenplan() throws Uhrzeitexception {
		Kalender=new HashMap<>(CAPACITY);
		for(int i=0;i<CAPACITY;i++) {
			Calendar cal = new GregorianCalendar();
			cal.add(Calendar.DAY_OF_WEEK, i);
			this.Kalender.put(new Wochentag(cal).getDatum(),new Wochentag(cal));
			
		}
	}
	
	/**
	 * Gibt den Wochentag aus der Map zurück basierend auf dem Parameter
	 * @param datum
	 * @return tag
	 * @throws Kalenderzugriffsexception
	 * @throws Uhrzeitexception
	 * @throws DatumException
	 */
	public Wochentag getTag(String datum) throws Kalenderzugriffsexception, Uhrzeitexception, DatumException {
		String regex="\\d{1,2}.\\d{1,2}.\\d{4}";
		Wochentag tag;
		if(datum.matches(regex)){
			kalenderaktualisieren();
				tag=this.Kalender.get(datum);
				if(tag==null)
				{
					throw new Kalenderzugriffsexception();
				}
				else {
					return tag;
				}
		}
		else {
			throw new DatumException();
		}
		
}
	/**
	 * Gibt den heutigen Wochentag zurück
	 * @return tag
	 * @throws Uhrzeitexception
	 */
	public Wochentag getTagheute() throws Uhrzeitexception {
		kalenderaktualisieren();
		Calendar cal = new GregorianCalendar();
		return this.Kalender.get(new Wochentag(cal).getDatum());
	}
	/**
	 * Der Kalender ist immer nur für die nächsten 100 Tage definiert
	 * Bei aufrufen der Funktion wird er aktualisiert basierend auf dem heutigen Datum
	 * @throws Uhrzeitexception
	 */
	private void kalenderaktualisieren() throws Uhrzeitexception {
		// Differenz zwischen dem heutigen Tag nd dem Tag Index 0
		int days=0;
		// Referenzdatum
		Calendar cal =  new GregorianCalendar();
		//Entfernt alle vergangenen Tage
		this.Kalender.entrySet().removeIf(e -> e.getValue().getCal().compareTo(cal)<0);
		//Fügt den heutigen Tag nochmal dazu, weil er immer entfernt wird
		this.Kalender.put(new Wochentag(new GregorianCalendar()).getDatum(), new Wochentag(new GregorianCalendar()));
		days=this.CAPACITY-this.Kalender.size();
		cal.add(Calendar.DAY_OF_WEEK, this.Kalender.size());
		//Füllt den Kalender wieder mit Tagen auf
		for(int i=0;i<days;i++){
			this.Kalender.put(new Wochentag(cal).getDatum(),new Wochentag(cal));	
			cal.add(Calendar.DAY_OF_WEEK, 1);
		}
	}
	
}

 