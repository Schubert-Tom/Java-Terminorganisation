package Server;

import java.util.ArrayList;
import java.util.List;

import GUI.MenuAdmin;
/**
 * Die Klasse stellt den Admin und all seine Möglichkeiten dar
 * @author Tom Schubert
 * @version 1.0
 */
public class Admin implements Änderung,Account{
	private String name="Administrator";
	private Anträge anträge;
	private Organisationszentrum zentrum;
	public Organisationszentrum getZentrum() {
		return zentrum;
	}
	public Admin(Organisationszentrum zentrum) {
		this.anträge= zentrum.getAnträge();
		this.zentrum = zentrum;
	}
	/**
	 * Erstellt einen Schüler und fügt ihn der Datenbank hinzu
	 * @param vorname
	 * @param nachname
	 * @throws Uhrzeitexception
	 */
	public void Schülererstellen(String vorname, String nachname) throws Uhrzeitexception {
		this.zentrum.getExistierendeaccounts().addSchüler(new Schüler(vorname,nachname,this.zentrum));
	}
	/**
	 * Erstellt einen Lehrer und fügt ihn der Datenbank zu
	 * @param vorname
	 * @param nachname
	 * @param fächer
	 * @throws Uhrzeitexception
	 */
	public void Lehrererstellen(String vorname, String nachname, ArrayList<String>fächer) throws Uhrzeitexception {
		this.zentrum.getExistierendeaccounts().addLehrer(new Lehrer(vorname,nachname,fächer,this.zentrum));
	}
	public List <Antragsklasse> anträgeeinsehen() {
		
		return this.anträge.getAnträgeListe();
		
	}
	/**
	 * Fügt Schüler zu Klasse hinzu
	 * @param schüler
	 * @param klasse
	 */
	public void Schülerhinzufügen(Schüler schüler,Klassen klasse) {
		klasse.addSchüler(schüler);
	}

	/**
	 * Getter für Namen vom Admin--> Administrator
	 * @return this.name
	 */
	@Override
	public String getnamen() {
		return this.name;
	}
	/**
	 * Gibt den Terminkalender eines Users zurück
	 * @throws DatumException
	 * @throws Uhrzeitexception
	 * @throws Kalenderzugriffsexception
	 */
	@Override
	public void Terminkalender(User user, String datum) throws DatumException, Uhrzeitexception, Kalenderzugriffsexception {
		Printer.print(user.getStundenplan().getTag(datum));
	}
	/**
	 * Damitlässt sich ein Termin erstellen
	 * @throws DatumException
	 * @throws Uhrzeitexception
	 * @throws Kalenderzugriffsexception
	 * @throws UngültigeAnfrageException
	 */
	@Override
	public void Änderungen(Wochentag tag, Terminblock terminblock) throws Uhrzeitexception, DatumException, UngültigeAnfrageException, Kalenderzugriffsexception {
		anträge.addAntrag(new Antragsklasse(new Kalenderänderung(this,tag,terminblock),1));
		
	}
	/**
	 *Lädt das Menu des Admins (GUI)
	 */
	@Override
	public void loadMenu() {
		new MenuAdmin(this);
		
	}
}