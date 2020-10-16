package GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import Server.Account;
import Server.Admin;
import Server.Lehrer;
import Server.Organisationszentrum;
import Server.Printer;
import Server.Schüler;

public class Menu {
	public static void main(String args[],Organisationszentrum zentrum) {
			Printer.printMessage("LOGIN: Bist du Lehrer, Schüler oder Admin");
			Printer.printMessage("1) Schüler");
			Printer.printMessage("2) Lehrer");
			Printer.printMessage("3) Admin");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			Integer auswahl;
			try {
				auswahl = Integer.parseInt(in.readLine());
				switch(auswahl) {
				case 1:{
					accountauswahlSchüler(zentrum.getExistierendeaccounts().getSchüler(),zentrum);
					break;
				}
			    case 2:{
					accountauswahlLehrer(zentrum.getExistierendeaccounts().getLehrer(),zentrum);
					break;
			    }
			    	
			    case 3: {
						accountauswahladmin(zentrum.getExistierendeaccounts().getAdmin(),zentrum);
						break;
					}	
			    default:
			    {
		            Printer.printMessage("keine Auswahl getroffen. Sie werden zurück zum Hauptmenü geleitet");
		            Wait.wait(2);
		            Menu.main(null, zentrum);
		            break;
		        }
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (NumberFormatException  e) {
				Printer.printMessage("Ungültige Eingabe...");
				Menu.main(null,zentrum);
			}
			
			
	}
	private static void accountauswahlLehrer(List<Lehrer> list, Organisationszentrum zentrum) {
		Printer.printMessage("Wer bist du?/ Wähle deinen Account:");
		int b=0;
		if(zentrum.getExistierendeaccounts().getLehrer().size()<=0) {
			Printer.printMessage("	Noch keine Accounts vorhanden!");
		}
		else {
			for(Account account:zentrum.getExistierendeaccounts().getLehrer()) {
					Printer.printMessage(b+".)"+account.getnamen());
					b++;
			}
		}
		Printer.printMessage("\n\n\n"+b+".)Zurück zum Menu");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Integer auswahl;
		try {
			auswahl = Integer.parseInt(in.readLine());
			if(auswahl<list.size()) {
				list.get(auswahl).loadMenu();
			}
			else {
				Printer.printMessage("Sie werden zurück zum Hauptmenü geleitet");
				Menu.main(null, zentrum);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (NumberFormatException  e) {
			Printer.printMessage("Ungültige Eingabe...");
			accountauswahlLehrer(list,zentrum);
		}
		
		
	}
	private static void accountauswahlSchüler(List<Schüler> list, Organisationszentrum zentrum) {
		Printer.printMessage("Wer bist du?/ Wähle deinen Account:");
		int b=0;
		if(zentrum.getExistierendeaccounts().getSchüler().size()<=0) {
			Printer.printMessage("	Noch keine Accounts vorhanden!");
		}
		else {
			for(Account account:zentrum.getExistierendeaccounts().getSchüler()) {
					Printer.printMessage(b+".)"+account.getnamen());
					b++;
			}
		}
		Printer.printMessage("\n\n\n"+b+".)Zurück zum Menu");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Integer auswahl;
		try {
			auswahl = Integer.parseInt(in.readLine());
			if(auswahl<list.size()) {
				list.get(auswahl).loadMenu();
			}
			else {
				Printer.printMessage("Sie werden zurück zum Hauptmenü geleitet");
				Menu.main(null, zentrum);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (NumberFormatException  e) {
			Printer.printMessage("Ungültige Eingabe...");
			accountauswahlSchüler(list,zentrum);
		}
		
		
	}
	private static void accountauswahladmin(Admin admin,Organisationszentrum zentrum) {
		Printer.printMessage("Wer bist du?/ Wähle deinen Account:");
		int c=0;
		if(zentrum.getExistierendeaccounts().getAdmin() instanceof Admin) {
				Printer.printMessage(c+".)"+zentrum.getExistierendeaccounts().getAdmin().getnamen());
		}
			Printer.printMessage("\n\n\n"+(c+1)+".)Zurück zum Menu");
			
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Integer auswahl;
		try {
			auswahl = Integer.parseInt(in.readLine());
			if(auswahl==0) {
				admin.loadMenu();
			}
			else {
				Printer.printMessage("Sie werden zurück zum Hauptmenü geleitet");
				Menu.main(null, zentrum);
			
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (NumberFormatException  e) {
			Printer.printMessage("Ungültige Eingabe...");
			accountauswahladmin(admin,zentrum);
		}

		
		
	}
		
}

