package Server;

import java.util.ArrayList;
/**
 * Die Klasse regelt Anträge auf Schulfrei von Schülern
 * @author Tom Schubert
 * @version 1.0
 */
public class AbwesenheitSchüler implements EditKalender {
	private Terminblock termin;
	private Wochentag tag;
	private User antragsteller;
	private ArrayList<User> genehmigtebeteiligte;
	

	public AbwesenheitSchüler(User antragsteller,Wochentag tag) throws Uhrzeitexception, UngültigeAnfrageException {
			this.genehmigtebeteiligte= new ArrayList<User>();
			this.antragsteller=antragsteller;
			this.termin = new Terminblock("Abwesend");
			this.tag=tag;
			if(!(this.tag.isSchultag())) {
				throw new UngültigeAnfrageException();
			}
			this.genehmigtebeteiligte.add(this.antragsteller);
	}
	/**
	 * Führt die beantragte Änderung aus, nachdem der Admin sie bestätigt hat
	 * @throws DatumException
	 * @throws Uhrzeitexception
	 * @throws Kalenderzugriffsexception
	 */
	@Override
	public void änderungausführen() throws DatumException, Uhrzeitexception, Kalenderzugriffsexception {
		for(User u:this.genehmigtebeteiligte) {
			Stundenplan s=u.getStundenplan();
			String a = this.tag.getDatum();
			Wochentag v = s.getTag(a);
			v.setZeitplan(this.termin);
		}
		
	}
	/**
	 * Getter für Terminblock in der Änderung
	 * @return this.termin
	 */
	@Override
	public Terminblock getTermin() {
		return this.termin;
	}
	/**
	 * Getter für Wochentag in der Änderung
	 * @return this.tag
	 */
	@Override
	public Wochentag getWochentag() {
		return this.tag;
	}
	/**
	 * Getter für Antragsliste in der Änderung
	 * @return this.antragsteller
	 */
	@Override
	public Account getAntragsteller() {
		return (Account) this.antragsteller;
	}
	/**
	 * Getter für überschriebene Anträge,die durch diesen Terminantrag bei anderen Usern entstehen
	 * return ArrayList<Terminblock>
	 */
	@Override
	public ArrayList<Terminblock> getAlleüberschriebeneTerminblöcke() {
		return new ArrayList<Terminblock>();
	}

}
