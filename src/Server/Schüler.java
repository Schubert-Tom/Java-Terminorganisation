package Server;

import java.util.ArrayList;

import GUI.MenuSchüler;
/**
 * Die Klasse stellt den Schüler und all seine Möglichkeiten dar
 * @author Tom Schubert
 * @version 1.0
 */
public class Schüler implements User,Account{
	private Stundenplan Stundenplan;
	private String vorname;
	private String nachname;
	private Anträge anträge;
	private Organisationszentrum zentrum;
	
	public Schüler(String vorname, String nachname, Organisationszentrum zentrum) throws Uhrzeitexception {
		this.Stundenplan=new Stundenplan();
		this.vorname=vorname;
		this.nachname=nachname;
		this.zentrum=zentrum;
		this.anträge=zentrum.getAnträge();
	}
	/**
	 * Printet den Terminkalender des Schülers
	 */
	@Override
	public void Terminkalender(User user,String datum) throws DatumException, Kalenderzugriffsexception, Uhrzeitexception {
		if(user.equals(this)) {
			Printer.print(this.Stundenplan.getTag(datum));
		}
		else {
			throw new Kalenderzugriffsexception();
		}
	}
	/**
	 * Meldet Abwesenheit des Lehrers
	 */
	@Override
	public void abwesenheitmelden(ArrayList<Wochentag>tage) throws Uhrzeitexception, DatumException, UngültigeAnfrageException, Kalenderzugriffsexception{
		for(Wochentag tag:tage) {
			anträge.addAntrag(new Antragsklasse(new AbwesenheitSchüler(this,tag),5));
			}
	}
	/**
	 * Gibt den amen des Schülers zurück
	 */
	@Override
	public String getnamen() {
		return this.vorname+" "+ this.nachname;
	}
	/**
	 * Gibt den Stundenplan ds Schülers urück
	 * @return this.Stundenplan
	 */
	@Override
	public Server.Stundenplan getStundenplan() {
		return this.Stundenplan;
	}
	/**
	 * Lädt das Menu des Schülers (GUI)
	 */
	@Override
	public void loadMenu() {
		new MenuSchüler(this);
	}
	/**
	 * Gibt das zentrale Organisationszentrum zurück
	 */
	@Override
	public Organisationszentrum getZentrum() {
		return this.zentrum;
	}
}