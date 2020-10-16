package Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Die Klasse verwaltet die Terminanträge aller Accounts
 * @author Tom Schubert
 * @version 1.0
 */
public class Anträge {
	ArrayList<Antragsklasse> Anträge;
	public Anträge() {
		this.Anträge= new ArrayList<>();
	}
	/**
	 * Fügt Antrag zu Liste von aktiven Anträgen hinzu
	 * @param antrag
	 */
	public void addAntrag(Antragsklasse antrag) {
		this.Anträge.add(antrag);
		Printer.printMessage("Antrag wurde erfolgreich eingereicht");
	}
	/**
	 * GIbt die Anträge sortiert nach Priorität zurück
	 * @return this.Anträge
	 */
	public List<Antragsklasse> getAnträgeListe() {
		Collections.sort(Anträge);
		return this.Anträge;
	}
	/**
	 * löscht einen Antrag aus aktiven Anträgen
	 * @param antrag
	 */
	public void remove(Antragsklasse antrag) {
		this.Anträge.remove(antrag);
	}
	/**
	 * Genehmigter Antrag und leitet Schritte zur Umsetzung ein
	 * @param genehmigtstatus
	 * @param index
	 * @throws DatumException
	 * @throws Uhrzeitexception
	 * @throws Kalenderzugriffsexception
	 */
	public void setgenehmigt(Integer genehmigtstatus, Integer index) throws DatumException, Uhrzeitexception, Kalenderzugriffsexception {
		if(genehmigtstatus==1||genehmigtstatus==-1) {
			Antragsklasse antrag=this.Anträge.get(index);
			antrag.setGenehmigt(genehmigtstatus);
			this.Anträge.remove(antrag);
		}
		
		
	}
	
	
}
