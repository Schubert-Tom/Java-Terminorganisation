package GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import Server.Admin;
import Server.DatumException;
import Server.Kalenderzugriffsexception;
import Server.Lehrer;
import Server.Printer;
import Server.Schüler;
import Server.Terminblock;
import Server.Uhrzeitexception;
import Server.UngültigeAnfrageException;
import Server.User;
import Server.Wochentag;


public class MenuAdmin {
	public MenuAdmin(Admin admin) {
		Printer.printMessage("\nHallo "+ admin.getnamen()+"\n");
		Printer.printMessage("0.) Schüler erstellen");
		Printer.printMessage("1.) Lehrer erstellen");
		Printer.printMessage("2.) Klasse erstellen");
		Printer.printMessage("3.) Account suchen");
		Printer.printMessage("4.) Änderungsanträge einsehen");
		Printer.printMessage("5.) Termin erstellen");
		Printer.printMessage("6.) loggout");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Integer auswahl;
		try {
			auswahl = Integer.parseInt(in.readLine());
			switch(auswahl) {
			case 0:{
				schülererstellen(admin);
				admin.loadMenu();
				break;
			}
		    case 1:{
		    	lehrererstellen(admin);
		    	admin.loadMenu();
		    	break;
		    }
		    	
		    case 2: {
		    	Printer.printMessage("Funktion nicht implemntiert aufgrund zu hoher Projektkomplexität");
		    	admin.loadMenu();
		    	break;
		    }
		    case 3:{
		    	Accountssuchen(admin);
		    	admin.loadMenu();
		    	break;
		    }
		    case 4:{
		    	anträgeeinsehen(admin);
		    	admin.loadMenu();
		    	break;
		    }
		    case 5:{
		    	Änderungeneinstellen(admin);
		    	admin.loadMenu();
		    	break;
		    }
		    case 6:{
		    	Menu.main(null,admin.getZentrum());
		    	break;
		    }
		    default:
		    {		    	
	            admin.loadMenu();
	            break;
	        }
		}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NumberFormatException  e) {
			Printer.printMessage("Ungültige Eingabe...");
			new MenuAdmin(admin);
		}
		
	}
	private void schülererstellen(Admin admin){
		System.out.print("Vorname:");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			String vorname=in.readLine();
			System.out.print("Nachname:");
			String nachname=in.readLine();
			Printer.printMessage("Schüler wird erstellt....");
			admin.Schülererstellen(vorname, nachname);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Uhrzeitexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NumberFormatException  e) {
			Printer.printMessage("Ungültige Eingabe...");
			schülererstellen(admin);
		}
		
		
	}
	private void lehrererstellen(Admin admin) {
		try {
			System.out.print("Vorname:");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String vorname=in.readLine();
			System.out.print("Nachname:");
			String nachname=in.readLine();
			ArrayList<String>fächer=new ArrayList<String>();
			while(true)
			{
				System.out.print("Fächer, die unterrichtet werden können (Zwischen jedem Fach bestätigen, wenn fertig enter ohne eingabe):");
				String puffer =in.readLine();
				if(puffer.equals(""))break;
				fächer.add(puffer);
			}
			Printer.printMessage("Lehrer wird erstellt....");
			admin.Lehrererstellen(vorname, nachname, fächer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Uhrzeitexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NumberFormatException  e) {
			Printer.printMessage("Ungültige Eingabe...");
			lehrererstellen(admin);
		}
		
		
	}
	private void anträgeeinsehen(Admin admin) {
		
		try {
			if(admin.getZentrum().getAnträge().getAnträgeListe().size()==0) {
				Printer.printMessage("Keine offenen Anträge vorhanden");
			}
			else {
				Printer.print(admin.getZentrum().getAnträge().getAnträgeListe());
				Printer.printMessage("\n\n"+admin.getZentrum().getAnträge().getAnträgeListe().size()+".) Abbruch");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				int auswahl=Integer.parseInt(in.readLine());
				if(!(auswahl>admin.getZentrum().getAnträge().getAnträgeListe().size()-1)) {
					Printer.printMessage("Folgende Änderung am "+admin.getZentrum().getAnträge().getAnträgeListe().get(auswahl).getTag().getWochentag().getDatum() +" soll vorgenommen werden:");
					Printer.print(admin.anträgeeinsehen().get(auswahl).getTag().getTermin());
					Printer.printMessage("Dabei werden "+ admin.getZentrum().getAnträge().getAnträgeListe().get(auswahl).getTag().getAlleüberschriebeneTerminblöcke().size() +" andere Termine gelöscht");
					Printer.printMessage("Antrag bestätigen? Ja (J)oder Nein (N) oder später entscheiden(S)");
					String antwort=in.readLine();
					if(antwort.toLowerCase().equals("j")) {
						admin.getZentrum().getAnträge().setgenehmigt(1, auswahl);
					}
					else if(antwort.toLowerCase().equals("n")) {
						admin.getZentrum().getAnträge().setgenehmigt(-1, auswahl);
					}
					else {
						admin.getZentrum().getAnträge().setgenehmigt(0, auswahl);
					}
					
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DatumException e) {
			e.printStackTrace();
		} catch (Uhrzeitexception e) {
			e.printStackTrace();
		}
		catch (NumberFormatException  e) {
			Printer.printMessage("Ungültige Eingabe...");
			anträgeeinsehen(admin);
		} catch (Kalenderzugriffsexception e) {
			e.printStackTrace();
		}
		
	}
	public void Änderungeneinstellen(Admin admin) {
		/**
		 * Funktion ist nicht gesichert
		 */
		try {
			if(admin.getZentrum().getExistierendeaccounts().getLehrer().size()<1) {
				Printer.printMessage("Es wurde noch kein Lehrer zur Datenbank hinzugefügt. Es muss immer ein Lehrer an einem Termin anwesend sein");
				throw new UngültigeAnfrageException();
			}
		Printer.printMessage("Die Funktion ist nicht mit einer Ausnahmebehandlung versehn... Stürzt ab bei falscher Eingabe.");
		Printer.printMessage("Geben Sie eine Tag für den Termin ein (Datumskonvention bsp:1.9.2020)");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String antwort=in.readLine();
		if (!(antwort.matches("\\d{1,2}.\\d{1,2}.\\d{4}"))){
			throw new DatumException();
		}
		Wochentag tag=new Wochentag(new GregorianCalendar(Integer.parseInt(antwort.split("\\.")[2]),(Integer.parseInt(antwort.split("\\.")[1])-1),Integer.parseInt(antwort.split("\\.")[0])));
		Printer.printMessage("Geben Sie den Beginn des Termins ein(Bsp:7:00 oder 8:30 Es können nur halbstündige Termine eingegeben werden)");
		String beginn= in.readLine();
		Printer.printMessage("Geben Sie das Ende des Termins ein(Bsp:7:00 oder 8:30 Es können nur halbstündige Termine eingegeben werden)");
		String ende= in.readLine();
		Printer.printMessage("Geben Sie den Titel des Termins ein");
		String titel = in.readLine();
		Printer.printMessage("Handelt es sich um eine Klausur wenn ja dann (J) wen nicht dann (N)");
		String antwort2 = in.readLine();
		boolean klausur;
		if(antwort2.toLowerCase().equals("j")){
			klausur=true;
		}
		else {
			klausur=false;
		}
		ArrayList<User> beteiligteArrayList = new ArrayList<>();
		Printer.printMessage("Wählen Sie alle Accounts aus, die Sie zu diesem Termin einladen möchten");
		Printer.printMessage("Falls schon Termine bei Teilnehmern vorhanden sind, diese Termine jedoch niedriger priorisiert sind wie dieser, können Sie überschrieben werden");
		Printer.printMessage("Wählen Sie mindestens einen Lehrer als Autoritätsperson aus");
		Boolean lehrerselected=false;
		Boolean breakall=false;
		while(true) {
			if(lehrerselected==false) {
				Printer.printMessage("Bisher wurde noch kein Lehrer ausgewählt, wählen Sie pro Termin mindestens einen Lehrer");
			}
			ArrayList<User> puffer=Accountssuchen(admin);
			while(true) {
					Printer.printMessage("Accounts auswählen leer für nix:");
					String antwort1=in.readLine();
					if(antwort1.equals(""))break;
					if(Integer.parseInt(antwort1)<puffer.size()) {
						if(puffer.get(Integer.parseInt(antwort1)) instanceof Lehrer) {
							beteiligteArrayList.add(puffer.get(Integer.parseInt(antwort1)));
							lehrerselected=true;
						}
						else
						{
							beteiligteArrayList.add(puffer.get(Integer.parseInt(antwort1)));
						}
					}
					
			}
				Printer.printMessage("1.)Weitere Accounts hinzufügen");
				Printer.printMessage("2.)Alle Teilnehmer ausgewählt");
				Printer.printMessage("3.) Abbruch");
				String antwort99=in.readLine();
				if(antwort99.equals("2")&&lehrerselected==true){
					break;
				}
				if(antwort99.equals("3")){
					breakall=true;
					break;
				}
			}
			if(breakall==false) {
				Printer.printMessage("Geben Sie die Wichtigkeit(0-100) ein, falls nichts eingegebn wird, wird diese auf 0 oder bei einer Klausur automatisch auf 50 gesetzt");
				String antwort3= in.readLine();
				Terminblock termin= new Terminblock(beteiligteArrayList,beginn,ende,titel,klausur);
				if(!(antwort3.equals(""))) {
					termin.setPriorität(Integer.parseInt(antwort3));
				}
				
					admin.Änderungen(tag, termin);
			}
			else {
				Printer.printMessage("Abbruch...");
			}
		
		} catch (Uhrzeitexception e) {
			Printer.printMessage("\n\nUngültige Eingabe...");
			Änderungeneinstellen(admin);
		} catch (DatumException e) {
			Printer.printMessage("\n\nUngültige Eingabe...");
			Änderungeneinstellen(admin);
		} catch (UngültigeAnfrageException e) {
			Printer.printMessage("\n\nUngültige Eingabe...");
			Änderungeneinstellen(admin);
		} catch (Kalenderzugriffsexception e) {
			Printer.printMessage("\n\nUngültige Eingabe...");
			Änderungeneinstellen(admin);
		} catch (IOException e) {
			Printer.printMessage("\n\nUngültige Eingabe...");
			Änderungeneinstellen(admin);
		}
	}

private ArrayList<User> Accountssuchen(Admin admin) {
		
		try {
			
			ArrayList<User> ergebnis =new ArrayList<User>();
			Printer.printMessage("Geben Sie den vollständigen Namen des zu suchenden Accounts ein:");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String name = in.readLine();
			for(Lehrer lehrer :admin.getZentrum().getExistierendeaccounts().getLehrer()) {
				if(lehrer.getnamen().equals(name)) {
					ergebnis.add(lehrer);
				}
			}
			for(Schüler schüler :admin.getZentrum().getExistierendeaccounts().getSchüler()) {
				if(schüler.getnamen().equals(name)) {
					ergebnis.add(schüler);
				}
			}
			if (ergebnis.size()>0) {
				int i=0;
				for(User account: ergebnis) {
					Printer.printMessage(i+".) "+account.getnamen());
					i++;
				}
				return ergebnis;
			}
			else {
				Printer.printMessage("Kein Account gefunden...");
				return null;
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
}

