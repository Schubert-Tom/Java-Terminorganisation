package GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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

public class MenuLehrer {
	public MenuLehrer(Lehrer lehrer) {
		Printer.printMessage("\nHallo "+ lehrer.getnamen()+"\n");
		Printer.printMessage("0.) Terminkalender anschauen");
		Printer.printMessage("1.) Abwesenheit beantragen");
		Printer.printMessage("2.)  Termin vereinbaren");
		Printer.printMessage("3.) loggout");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Integer auswahl;
		try {
			auswahl = Integer.parseInt(in.readLine());
			switch(auswahl) {
			case 0:{
				terminkalenderanschauen(lehrer);
				lehrer.loadMenu();
				break;
			}
		    case 1:{
		    	abwesendmelden(lehrer);
		    	lehrer.loadMenu();
		    	break;
		    }
		    	
		    case 2: {
		    	Änderungeneinstellen(lehrer);
		    	lehrer.loadMenu();
		    	break;
		    }
		    case 3:{
		    	Menu.main(null,lehrer.getZentrum());
		    	break;
		    }
		    default:
		    {		    	
	            lehrer.loadMenu();
	            break;
	        }
		}	
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NumberFormatException  e) {
			Printer.printMessage("Ungültige Eingabe...");
			new MenuLehrer(lehrer);
		}
		
	}
	private void terminkalenderanschauen(Lehrer lehrer) {
		try {
			System.out.print("Datum nach folgendem Schema eingeben(1.1.1999 oder 19.10.1999):");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String termin =in.readLine();
			Printer.printMessage("\n\n\n\n\n\n\n\n\n\n");
			lehrer.Terminkalender(lehrer, termin);
			Printer.printMessage("\n\n");
			Printer.printMessage("0.) Anderer Tag");
			Printer.printMessage("1.) Zurück zum Accountmenu\n\n");
			
			Integer auswahl=Integer.parseInt(in.readLine());
			switch(auswahl) {
				case 0:{
					terminkalenderanschauen(lehrer);
				}
			    default:
			    {		    	
		            lehrer.loadMenu();
		            break;
		        }
			}	
			
		} catch (DatumException | Kalenderzugriffsexception e) {
			e.printStackTrace();
			terminkalenderanschauen(lehrer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Uhrzeitexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void abwesendmelden(Lehrer lehrer) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				Printer.printMessage("Geben Sie alle Tage als Datum ein, an denen Sie fehlen Datumskonvention(Bsp:1.9.2020 oder 12.12.2020) geben Sie nichts ein für Abbruch:");
				ArrayList<Wochentag> tage=new ArrayList<Wochentag>();
				Wochentag puffer2;
				String puffer;
				while(true)
				{
				System.out.print("Fehltage am:");
				puffer =in.readLine();
				if(puffer.equals(""))break;
				if(!(puffer.matches("\\d{1,2}.\\d{1,2}.\\d{4}"))) {
					throw new DatumException();
				}
					
				puffer2 = new Wochentag(new GregorianCalendar(Integer.parseInt(puffer.split("\\.")[2]),(Integer.parseInt(puffer.split("\\.")[1])-1),Integer.parseInt(puffer.split("\\.")[0])));
				if(!(tage.contains(puffer2))) {
					if(puffer2.isSchultag()) {
						tage.add(puffer2);
					}
					else {
						Printer.printMessage("Anträg konnten nicht gesendet werden Termin befindet sich an einem Wochenende" );
					}
					
				}
				
				}
				lehrer.abwesenheitmelden(tage);
			
		} catch (Uhrzeitexception e) {
			e.printStackTrace();
		} catch (DatumException e) {
			Printer.printMessage("FEHLER!!!Das Datum ist ungültig. Alle bisherigen Angaben wurden gelöscht. Geben Sie alle Termine erneut ein:" );
			abwesendmelden(lehrer);
		}  catch (IOException e) {
			e.printStackTrace();
		}
		catch (NumberFormatException  e) {
			Printer.printMessage("FEHLER!!! Ungültige Eingabe...Alle bisherigen Angaben wurden gelöscht. Geben Sie alle Termine erneut ein");
			abwesendmelden(lehrer);
		} catch (UngültigeAnfrageException e) {
			Printer.printMessage("FEHLER!!! Ungültige Eingabe...Alle bisherigen Angaben wurden gelöscht. Geben Sie alle Termine erneut ein");
			abwesendmelden(lehrer);
		} catch (Kalenderzugriffsexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public void Änderungeneinstellen(Lehrer lehrer) {
		/**
		 * Funktion ist nicht gesichert
		 */
		try {
			if(lehrer.getZentrum().getExistierendeaccounts().getLehrer().size()<1) {
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
		Boolean lehrerselected=true;
		Boolean breakall=false;
		while(true) {
			if(lehrerselected==false) {
				Printer.printMessage("Bisher wurde noch kein Lehrer ausgewählt, wählen Sie pro Termin mindestens einen Lehrer");
			}
			ArrayList<User> puffer=Accountssuchen(lehrer);
			while(true) {
					Printer.printMessage("Accounts auswählen leer für nix:");
					String antwort1=in.readLine();
					if(antwort1.equals(""))break;
					if(antwort1.matches("\\d")) {
						if(Integer.parseInt(antwort1)>=puffer.size()) {
							throw new UngültigeAnfrageException();
						}
					}	
					else {
							throw new UngültigeAnfrageException();
						}
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
				
					lehrer.Änderungen(tag, termin);
			}
			else {
				Printer.printMessage("Abbruch...");
			}
		
		} catch (Uhrzeitexception e) {
			Printer.printMessage("\n\nUngültige Eingabe...");
			Änderungeneinstellen(lehrer);
		} catch (DatumException e) {
			Printer.printMessage("\n\nUngültige Eingabe...");
			Änderungeneinstellen(lehrer);
		} catch (UngültigeAnfrageException e) {
			Printer.printMessage("\n\nUngültige Eingabe...");
			Änderungeneinstellen(lehrer);
		} catch (Kalenderzugriffsexception e) {
			Printer.printMessage("\n\nUngültige Eingabe...");
			Änderungeneinstellen(lehrer);
		} catch (IOException e) {
			Printer.printMessage("\n\nUngültige Eingabe...");
			Änderungeneinstellen(lehrer);
		}
	}

private ArrayList<User> Accountssuchen(Lehrer lehrer) {
		
		try {
			
			ArrayList<User> ergebnis =new ArrayList<User>();
			Printer.printMessage("Geben Sie den vollständigen Namen des zu suchenden Accounts ein:");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String name = in.readLine();
			for(Lehrer lehhreraccount :lehrer.getZentrum().getExistierendeaccounts().getLehrer()) {
				if(lehhreraccount.getnamen().equals(name)) {
					ergebnis.add(lehhreraccount);
				}
			}
			for(Schüler schüler :lehrer.getZentrum().getExistierendeaccounts().getSchüler()) {
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
