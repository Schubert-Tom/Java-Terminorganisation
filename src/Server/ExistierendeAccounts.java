package Server;

import java.util.ArrayList;
import java.util.List;

/**
 * Die KLasse repräsentiert alle jemals erstellten Accounts
 * @author Tom Schubert
 * @version 1.0
 */
public class ExistierendeAccounts {
		List<Schüler> schüler;
		List<Lehrer> lehrer;
		Admin admin;
		public ExistierendeAccounts() {
			schüler = new ArrayList<Schüler>();
			lehrer = new ArrayList<Lehrer>();
		}
		/**
		 * Getter für den Adminaccount
		 * @return this.admin
		 */
		public Admin getAdmin() {
			return admin;
		}
		/**
		 * Setter für den Admin Account
		 * @param admin
		 */
		public void setAdmin(Admin admin) {
			this.admin = admin;
		}
		/**
		 * Getter für einen Schüler Account
		 * @return schüler
		 */
		public List<Schüler> getSchüler() {
			return schüler;
		}
		/**
		 * Setter für einen Schüler Account
		 * @param schüler
		 */
		public void setSchüler(List<Schüler> schüler) {
			this.schüler = schüler;
		}
		/**
		 * Fügt Lehrer zu existierenden Accounts hinzu
		 * @param user
		 */
		public void addLehrer(Lehrer user) {
			lehrer.add(user);
			Printer.printMessage("Neuer User erfolgreich erstellt");
		}
		/**
		 * Getter für einen Lehrer Account
		 * @return lehrer
		 */
		public List<Lehrer> getLehrer() {
			return lehrer;
		}
		/**
		 * Setter für einen Lehrer Account
		 * @param lehrer
		 */
		public void setLehrer(List<Lehrer> lehrer) {
			this.lehrer = lehrer;
		}
		/**
		 * Fügt Schüler zu existierenden Accounts hinzu
		 * @param user
		 */
		public void addSchüler(Schüler user) {
			schüler.add(user);
			Printer.printMessage("Neuer User erfolgreich erstellt");
		}
}
