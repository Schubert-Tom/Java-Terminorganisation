package Server;

public class Organisationszentrum {
	/**
	 * Die Klasse ist das zentrale Verteilungszentrum für alle Daten
	 * @author Tom Schubert
	 * @version 1.0
	 */
	Anträge anträge;
	ExistierendeAccounts existierendeaccounts;
	
	public Organisationszentrum() {
		this.anträge=new Anträge();
		this.existierendeaccounts= new ExistierendeAccounts();

	}
	/**
	 * Um die Anträge, welche in der Instanz des Zentrums gespeichert sind zu bekommen
	 * @return anträge
	 */
	public Anträge getAnträge() {
		return anträge;
	}
	/**
	 * Um die existierenden Accounts, welche in der Instanz des Zentrums gespeichert sind zu bekommen
	 * @return existierendeaccounts
	 */
	public ExistierendeAccounts getExistierendeaccounts() {
		return existierendeaccounts;
	}


	
}
