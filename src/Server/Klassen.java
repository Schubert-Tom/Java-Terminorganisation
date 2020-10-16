package Server;

import java.util.ArrayList;

public class Klassen{
	Integer Klasse;
	ArrayList<Schüler> klasse;
	Lehrer lehrer;
	public Klassen() {
		klasse=new ArrayList<Schüler>();
	}
	/**
	 * Gibt Lehrer der KLasse zurück
	 * @return lehrer
	 */
	public Lehrer getLehrer() {
		return lehrer;
	}
	/**
	 * Setzt den Lehrer der KLasse
	 * @param lehrer
	 */
	public void setLehrer(Lehrer lehrer) {
		this.lehrer = lehrer;
	}
	/**
	 * Fügt einen Schüler zur KLasse hinzu
	 * @param schüler
	 */
	public void addSchüler(Schüler schüler) {
		klasse.add(schüler);
	}
	/**
	 * Entfernt ein Schüler aus einer Klasse
	 * @param schüler
	 */
	public void removeSchüler(Schüler schüler) {
		klasse.remove(schüler);
	}
}
