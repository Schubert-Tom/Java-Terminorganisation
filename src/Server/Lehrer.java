package Server;

import java.util.ArrayList;

import GUI.MenuLehrer;
/**
 * Die Klasse stellt den Lehrer und all seine Möglichkeiten dar
 * @author Tom Schubert
 * @version 1.0
 */
public class Lehrer implements User, Änderung,Account {
	private Stundenplan stundenplan;
	private String vorname;
	private String nachname;
	private ArrayList<String> fach;
	private Organisationszentrum zentrum;
	private Anträge anträge;
	
	public Lehrer(String vorname, String nachname, ArrayList<String> fach, Organisationszentrum zentrum) throws Uhrzeitexception {
		this.stundenplan = new Stundenplan();
		this.vorname=vorname;
		this.nachname=nachname;
		this.fach=fach;
		this.anträge=zentrum.getAnträge();
		this.zentrum = zentrum;
	}
	/**
	 * Printet den Terminkalender des Lehrers
	 * @param user
	 * @param datum
	 * @throws DatumException
	 * @throws Kalenderzugriffsexception
	 * @throws Uhrzeitexception
	 */
	@Override
	public void Terminkalender(User user,String datum) throws DatumException, Kalenderzugriffsexception, Uhrzeitexception {
		if(user.equals(this)) {
			Printer.print(this.stundenplan.getTag(datum));
		}
		else {
			throw new Kalenderzugriffsexception();
		}
		
	}
	/**
	 * Gibt den Namen des Lehrers zurück
	 * @returns this.vorname +" "+this.nachname
	 */
	@Override
	public String getnamen() {
		return this.vorname +" "+this.nachname;
	}
	/**
	 * Gibt den Terminkalender eines Users zurück
	 * @throws DatumException
	 * @throws Uhrzeitexception
	 * @throws Kalenderzugriffsexception
	 */
	public void Änderungen(Wochentag tag, Terminblock terminblock) throws Uhrzeitexception, DatumException, UngültigeAnfrageException, Kalenderzugriffsexception {
		anträge.addAntrag(new Antragsklasse(new Kalenderänderung(this,tag,terminblock),1));
	}
	/**
	 * Meldet Abwesenheit des Lehrers
	 */
	@Override
	public void abwesenheitmelden(ArrayList<Wochentag>tage) throws Uhrzeitexception, DatumException, UngültigeAnfrageException, Kalenderzugriffsexception{
		for(Wochentag tag:tage) {
		anträge.addAntrag(new Antragsklasse(new Kalenderänderung(this,tag,new Terminblock("Unterricht entfällt")),5));
		}
	}
	/**
	 *  Gibt die Fächer, die der Lehrer unterrichten kann zurück;
	 * @return fach
	 */
	public ArrayList<String> getFach() {
		return fach;
	}
	/**
	 * Fügt Fach  zu Lehrfähigkeit zu
	 * @param fach
	 */
	public void addFachAbility(String fach) {
		this.fach.add(fach);
	}
	/**
	 * Fibt den Stundenplan zurück
	 * @return stundenplan
	 */
	@Override
	public Server.Stundenplan getStundenplan() {
		return this.stundenplan;
	}
	/**
	 * Lädt das Menu des Lehrers (GUI)
	 */
	@Override
	public void loadMenu() {
		new MenuLehrer(this);
	}
	/**
	 * Gibt das Organisationszentrum zurück
	 * @return this.zentrum
	 */
	@Override
	public Organisationszentrum getZentrum() {
		return this.zentrum;
	}
}
