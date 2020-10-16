package Server;

import java.util.ArrayList;
/**
 * Das Interface beschreibt Nutzer wie Lehrer oder Schüler und stellt die notwendigen Methoden zur Verfügung
 * @author Tom Schubert
 * @version 1.0
 */
public interface User {
	public void abwesenheitmelden(ArrayList<Wochentag>tage)throws Uhrzeitexception, DatumException, UngültigeAnfrageException, Kalenderzugriffsexception;
	public Stundenplan getStundenplan();
	public String getnamen();
	public void Terminkalender(User user,String datum)throws DatumException, Kalenderzugriffsexception, Uhrzeitexception;
	
}
