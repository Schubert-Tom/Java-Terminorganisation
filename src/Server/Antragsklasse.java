package Server;
/**
 * Die Klasse repräsentiert einen einzelnen Antrag
 * @author Tom Schubert
 * @version 1.0
 */
public class Antragsklasse implements Comparable<Antragsklasse> {
	private EditKalender änderung;
	Integer antragspriorität;
	// 0= nicht bearbeitet, -1=abgelehnt 1= genehmigt
	private Integer genehmigt=0;
	public Antragsklasse(EditKalender änderung,Integer antragspriorität) {
		this.änderung=änderung;
		this.antragspriorität=antragspriorität;
	}
	/**
	 * Getter für die Priorität des Antrags
	 * @return this.antragspriorität
	 */
	public Integer getPriorität() {
		return this.antragspriorität;
	}
	/**
	 * Gibt den Status des Atrags zurück
	 * 0 = In Arbeit
	 * 1 = genehmigt
	 * 2 = abgelehnt
	 * @return this.genehmigt
	 */
	public Integer getGenehmigt() {
		return this.genehmigt;
	}
	/**
	 * Setzt den this.genehmigt Wert
	 * @param genehmigt
	 * @throws DatumException
	 * @throws Uhrzeitexception
	 * @throws Kalenderzugriffsexception
	 */
	public void setGenehmigt(Integer genehmigt) throws DatumException, Uhrzeitexception, Kalenderzugriffsexception {
		this.genehmigt = genehmigt;
		if(genehmigt==1) {
			 this.änderung.änderungausführen();
		}
	}
	/**
	 * Gibt die Änderung welche vom Antrag repräsentiert wird zurück
	 * @return this.änderung
	 */
	public EditKalender getTag() {
		return this.änderung;
	}
	/**
	 * Vergleicht die Anträge nach Antragspriorität
	 * @param o Antragsklasse
	 */
	@Override
	public int compareTo(Antragsklasse o) {
		return this.antragspriorität.compareTo(o.getPriorität());
	}
}
