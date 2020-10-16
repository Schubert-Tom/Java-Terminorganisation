package Server;

import java.util.ArrayList;
/**
 * Das Interface beschreibt die Fähigkeit einen Terminkalender zu editieren
 * @author Tom Schubert
 * @version 1.0
 */
public interface EditKalender {
	public void änderungausführen() throws DatumException, Uhrzeitexception, Kalenderzugriffsexception ;
	public Terminblock getTermin();
	public Wochentag getWochentag();
	public Account getAntragsteller();
	public ArrayList<Terminblock> getAlleüberschriebeneTerminblöcke();
}
