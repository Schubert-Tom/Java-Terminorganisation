package Server;
/**
 * Das Interface beschreibt die Fähigkeit das Recht zu besitzen Termine zu erstellen
 * @author Tom Schubert
 * @version 1.0
 */
public interface Änderung {
	public void Terminkalender(User user,String datum) throws DatumException, Kalenderzugriffsexception, Uhrzeitexception;
	public void Änderungen(Wochentag tag, Terminblock terminblock) throws Uhrzeitexception, DatumException, UngültigeAnfrageException, Kalenderzugriffsexception;
}
