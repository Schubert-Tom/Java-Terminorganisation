package GUI;
import Server.Admin;
import Server.Organisationszentrum;
import Server.Printer;

public class Main {
	
	public static void main(String[] args) {
		Printer.printMessage("Es handelt sich um ein Kalendersystem wo Lehrer und Schüler Termine machen können. Es ist keine Datenbank eingebaut bitt alle Schüler von Hand erstellen. Es existiert bereits ein Admin Account");
	Printer.printMessage("Information: Es muss immer die Zahl der auszuwählenden Schaltfläche gedrückt werden + enter\n Ein Antrag von einem Lehrer oder Admin auf einen Termin oder sonstige Änderungen müssen beim admin unter dem Stichpunkt 4 bestätigt werden bevor Sie in Kraft treten können");
	
	Organisationszentrum zentrum = new Organisationszentrum();
	Admin admin = new Admin(zentrum);
	zentrum.getExistierendeaccounts().setAdmin(admin);;
	Menu.main(null, zentrum);
	}
}
		
		