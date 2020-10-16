package Server;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Die Klasse stellt einen Terminblock dar, welcher einen physischen TErmin wie eine Klassenstunde repräsentiert
 * @author Tom Schubert
 * @version 1.0
 */
public class Terminblock implements Comparable<Terminblock> {
	private Boolean klausur;
	private String titel;
	private String beginn;
	private Integer priorität;
	private ArrayList<User> beteiligte;
	private ArrayList<Termin> terminblock;
	
	public Terminblock(ArrayList<User> beteiligte,String beginn,String ende, String titel,Boolean klausur) throws Uhrzeitexception {
		this.terminblock = new ArrayList<Termin>(Wochentag.ZEITPLANLÄNGE);
		this.beteiligte=beteiligte;
		this.beginn=beginn;
		this.ende = ende;
		this.klausur=klausur;
		this.titel= titel;
		if(klausur==true)this.priorität=50;
		else this.priorität=0;
		generateBlock();
	}
	public Terminblock(String titel,String beginn) throws Uhrzeitexception{
		this.terminblock = new ArrayList<Termin>(Wochentag.ZEITPLANLÄNGE);
		this.beteiligte = new ArrayList<User>();
		this.beginn=beginn;
		this.ende = halbeStundeaddierer(beginn);
		this.klausur=false;
		this.titel= "frei";
		this.priorität=-100;
		generateBlock();
	}
	public Terminblock(String titel) throws Uhrzeitexception{
		this.terminblock = new ArrayList<Termin>(Wochentag.ZEITPLANLÄNGE);
		this.beteiligte = new ArrayList<User>();
		this.beginn=Wochentag.ZEITPLANSTART;
		this.ende = Wochentag.ZEITPLANENDE;
		this.klausur=false;
		this.titel= titel;
		this.priorität=100;
		generateBlock();
	}
	/**
	 * Setter für den Start des Termins
	 * @param beginn
	 */
	public void setBeginn(String beginn) {
		this.beginn = beginn;
	}
	private String ende;
	/**
	 * Setter für das Ende des Termins
	 * @param ende
	 */
	public void setEnde(String ende) {
		this.ende = ende;
	}
	/**
	 * Setter für den Titel des Termins
	 * @param titel
	 */
	public void setTitel(String titel) {
		this.titel = titel;
	}
	/**
	 * Addiert eine halbe Stunde zu einem String der in Uhrzeitform eingegeben wurde
	 */
	private String halbeStundeaddierer(String uhrzeit) throws Uhrzeitexception {
		if(uhrzeit.split(":")[1].equals("30")) {
			return String.valueOf((Integer.parseInt(uhrzeit.split(":")[0])+1))+":00";
		}
		else if(uhrzeit.split(":")[1].equals("00")){
			return uhrzeit.split(":")[0]+":30";
		}
		else {
			throw new Uhrzeitexception();
		}
	}
	/**
	 * Generiert den Terminblock
	 * @throws Uhrzeitexception
	 */
	private void generateBlock() throws Uhrzeitexception
	{
		Termin s = new Termin(beginn);
		Termin e = new Termin(ende);
		for (int i = 5;i<(Wochentag.ZEITPLANLÄNGE/2)+5;i++) {
			Termin puffer = new Termin(i+":00");
			if(puffer.compareTo(s)>=0&&puffer.compareTo(e)==-1) {
				addTerminschnipsel(puffer);
			}
			if(i<(Wochentag.ZEITPLANLÄNGE/2)+4) {
				Termin puffer2 =new Termin(i+":30");
				if(puffer2.compareTo(s)>=0&&puffer2.compareTo(e)==-1) {
				addTerminschnipsel(puffer2);
				}
			}
		}
	}
	/**
	 * Getter für den Terminblock
	 * @return terminblock
	 */
	public ArrayList<Termin> getTerminblock() {
		Collections.sort(this.terminblock);
		return terminblock;
	}
	/**
	 * 
	 * @param t
	 */
	public void addTerminschnipsel(Termin t) {
		this.terminblock.add(t);
		Collections.sort(this.terminblock);
	}
	/**
	 * 
	 * @return klausur
	 */
	public Boolean isKlausur() {
		return klausur;
	}
	/**
	 * Getter für den Titel des Termins
	 * @return
	 */
	public String getTitel() {
		return titel;
	}
	/**
	 * Getter für den Beginn des Termins
	 * @return beginn
	 */
	public String getBeginn() {
		return beginn;
	}
	/**
	 * Getter für die Priorität des TErmins
	 * @return priorität
	 */
	public Integer getPriorität() {
		return priorität;
	}
	/**
	 * Setter für die Priorität des Termins
	 * @param priorität
	 */
	public void setPriorität(Integer priorität) {
		this.priorität = priorität;
	}
	/**
	 * 
	 */
	@Override
	public int compareTo(Terminblock t) {
		return this.priorität.compareTo(t.getPriorität());
	}
	/**
	 * Getter für die Beteiligten am Termin
	 * @return beteiligte
	 */
	public ArrayList<User> getBeteiligte() {
		return this.beteiligte;
	}
	/**
	 * Setter für die Liste der Beteiligten am Termin
	 */
	public void setBeteiligte(ArrayList<User> beteiligte) {
		this.beteiligte = beteiligte;
	}
	/**
	 * Getter für das Ende des Terminblocks
	 * @return ende
	 */
	public String getEnde() {
		return ende;
	}
}
