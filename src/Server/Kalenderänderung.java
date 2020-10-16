package Server;

import java.util.ArrayList;
/**
 * Die Klasse repräsentiert eine Erzeugung eines Termins
 * @author Tom Schubert
 * @version 1.0
 */
public class Kalenderänderung implements EditKalender{
private Terminblock termin;
private Wochentag tag;
private Änderung antragsteller;
private ArrayList<User> aktivbeteiligte;
private ArrayList<User> genehmigteaktivebeteiligte;
private ArrayList<Terminblock>alleüberschriebeneTerminblöcke;

	public Kalenderänderung(Änderung Antragsteller,Wochentag tag,Terminblock termin) throws Uhrzeitexception, DatumException, UngültigeAnfrageException, Kalenderzugriffsexception{
		this.aktivbeteiligte = new ArrayList<User>();
		this.genehmigteaktivebeteiligte = new ArrayList<User>();
		this.alleüberschriebeneTerminblöcke = new ArrayList<Terminblock>();
		 this.tag=tag;
		 this.termin=termin;
		 this.antragsteller=Antragsteller;
		 this.aktivbeteiligte=this.termin.getBeteiligte();
		 if (!(Antragsteller instanceof Admin)) {
				this.aktivbeteiligte.add((User)Antragsteller);
		 }
		 this.termin.setBeteiligte(aktivbeteiligte);
		 terminverifikation();
		
	}
	/**
	 * Getter für alle überschriebenen Terminblöcke aus der Terminerstellung
	 * @return alleüberschriebeneTerminblöcke
	 */
	public ArrayList<Terminblock> getAlleüberschriebeneTerminblöcke() {
		return alleüberschriebeneTerminblöcke;
	}
	/**
	 * Getter für den Tag an dem der Termin stattfinden soll
	 *  @return tag
	 */
	@Override
	public Wochentag getWochentag() {
		return this.tag;
	}
	/**
	 * Getter für den Namen des Antragstellers des Termins
	 *  @return this.Antragsteller
	 */
	@Override
	public Account getAntragsteller() {
		return (Account) this.antragsteller;
	}
	/**
	 * Getter für den erzeugten Terminblock in der Terminerstellung
	 *  @return this.termin
	 */
	@Override
	public Terminblock getTermin() {
		return this.termin;
	}
	/**
	 * Verifiziert ob der Termin mit den gesetzten Paramtern so zulässig ist
	 * @throws Uhrzeitexception
	 * @throws DatumException
	 * @throws UngültigeAnfrageException
	 * @throws Kalenderzugriffsexception
	 */
	private void terminverifikation() throws Uhrzeitexception, DatumException, UngültigeAnfrageException, Kalenderzugriffsexception {
		if(!(this.tag.isSchultag())) {
			throw new UngültigeAnfrageException();
		}
		String message="";
		// Iteriert über alle USer die am Termin aktiv beteiligt sind
		for(User user: this.aktivbeteiligte) {
			ArrayList<Terminblock>überschriebeneTerminblöcke = new ArrayList<Terminblock>();
			boolean hasfreeTerminspace = true;
			boolean termincanbeset = true;
			// Jeder einzelne halbstündige Termin wird mit dem KAlender vom User abgeglichen und überprüft ob schon ein Termin in der Zeitspanne existiert
			for(Termin t:this.termin.getTerminblock()) {
				if(!(user.getStundenplan().getTag(tag.getDatum()).hasfreespace(t.getZeitpunkt()))) {
					hasfreeTerminspace=false;
					break;
				}
			}
			// Wenn in der Zeitspanne schon ein Termin existiert muss geprüft werden ob dieser überschrieben werden kann:
			if(hasfreeTerminspace==false) {
				// Es wird geschaut ob die Termine überschrieben werden können (Prioritätsvergleich)
				// Dazu wird das gesamte Array des angeforderten Termins abgeglichen mit den vorhanden Terminen
				String zeitpunkte;
				Terminblock puffer = user.getStundenplan().getTag(tag.getDatum()).gettermin(this.termin.getBeginn());
				for(int i = 0; i<this.termin.getTerminblock().size();i++) {
					zeitpunkte=this.termin.getTerminblock().get(i).getZeitpunkt();
					// Dem puffer ist immer dem hochzählenden Zeitpunkt bereits vorhandene zugeordnete Terminblock des Wochentags zugewiesen
					puffer=user.getStundenplan().getTag(tag.getDatum()).gettermin(zeitpunkte);
					if(!(puffer.equals(null))) {
						if(this.termin.compareTo(puffer)<=0) {
							// Wenn der angeforderte Termin nicht so wichtig wie der bestehende ist, wird er für den User abgelehnt
							termincanbeset=false;
							break;
						}
						else{
							// Falls der vorhandene Termin überschrieben wird, wird dieser hier abgespeichert falls noch nicht geschehn
							if(!(überschriebeneTerminblöcke.contains(puffer))&&puffer.getPriorität()!=-100) {
								überschriebeneTerminblöcke.add(puffer);
							}
						}
					}
				}
			}	
			if(hasfreeTerminspace||termincanbeset) {
				message+=user.getnamen()+" ";
				this.genehmigteaktivebeteiligte.add(user);
			}
			else {
				überschriebeneTerminblöcke.clear();
			}
			for(Terminblock t:überschriebeneTerminblöcke) {
				if(!(this.alleüberschriebeneTerminblöcke.contains(t))) {
					this.alleüberschriebeneTerminblöcke.add(t);
				}	
			}	
		}
		if(genehmigteaktivebeteiligte.size()!=aktivbeteiligte.size()) {
			Printer.printMessage("Die angeforderte Änderung ist für folgende Schüler nicht möglich!:");
			Printer.printMessage(message);
			}
			
	}
	/**
	 * Führt die Änderung schlussendlich nach der Genehmigung anderer Klassen aus
	 * @throws DatumException
	 * @throws Uhrzeitexception
	 * @throws Kalenderzugriffsexception
	 */
	@Override
	public void änderungausführen() throws DatumException, Uhrzeitexception, Kalenderzugriffsexception {
		// Es werden Termine überschrieben also müssen alle die an den Terminen beteiligt waren auch eine Änderung erfahren
		// --> passivebeteiligte
		for(Terminblock t:this.alleüberschriebeneTerminblöcke) {
					for(User u:t.getBeteiligte()) {
						Terminblock zwischenspeicher =t;
						zwischenspeicher.setTitel("frei");
						zwischenspeicher.setPriorität(-100);
						u.getStundenplan().getTag(this.tag.getDatum()).setZeitplan(zwischenspeicher);
					}
				}
		for(User user: this.genehmigteaktivebeteiligte) {
			user.getStundenplan().getTag(this.tag.getDatum()).setZeitplan(this.termin);
		}
	}		
		
}
