package GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import Server.DatumException;
import Server.Kalenderzugriffsexception;
import Server.Printer;
import Server.Schüler;
import Server.Uhrzeitexception;
import Server.UngültigeAnfrageException;
import Server.Wochentag;

public class MenuSchüler {
	
	public MenuSchüler(Schüler schüler) {
		Printer.printMessage("\nHallo "+ schüler.getnamen()+"\n");
		Printer.printMessage("0.) Terminkalender anschauen");
		Printer.printMessage("1.) Abwesenheit beantragen");
		Printer.printMessage("2.) loggout");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Integer auswahl;
		try {
			auswahl = Integer.parseInt(in.readLine());
			switch(auswahl) {
			case 0:{
				terminkalenderanschauen(schüler);
				schüler.loadMenu();
				break;
			}
		    case 1:{
		    	abwesendmelden(schüler);
		    	schüler.loadMenu();
		    	break;
		    }
		    	
		    case 2: {
		    	Menu.main(null,schüler.getZentrum());
		    	break;
		    }
		    default:
		    {		    	
	            schüler.loadMenu();
	            break;
	        }
		}	
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (NumberFormatException  e) {
			Printer.printMessage("Ungültige Eingabe...");
			new MenuSchüler(schüler);
		}
	}
	private void terminkalenderanschauen(Schüler schüler) {
		try {
			System.out.print("Datum nach folgendem Schema eingeben(1.1.1999 oder 19.10.1999):");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String termin =in.readLine();
			Printer.printMessage("\n\n\n\n\n\n\n\n\n\n");
			schüler.Terminkalender(schüler, termin);
			Printer.printMessage("\n\n");
			Printer.printMessage("0.) Anderer Tag");
			Printer.printMessage("1.) Zurück zum Accountmenu\n\n");
			
			Integer auswahl=Integer.parseInt(in.readLine());
			switch(auswahl) {
				case 0:{
					terminkalenderanschauen(schüler);
				}
			    default:
			    {		    	
		            break;
		        }
			}	
	
		} catch (DatumException e) {
			Printer.printMessage("Ungültige Eingabe...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Uhrzeitexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Kalenderzugriffsexception e) {
			Printer.printMessage("Termin liegt zu weit in der Zukunft/Vergangenheit...");
		}
	}
	private void abwesendmelden(Schüler schüler) {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
					Printer.printMessage("Geben Sie alle Tage als Datum ein, an denen Sie entschuldigt fehlen Datumskonvention(Bsp:1.9.2020 oder 12.12.2020) geben Sie nichts ein für Abbruch:");
					ArrayList<Wochentag> tage=new ArrayList<Wochentag>();
					Wochentag puffer2;
					String puffer;
					while(true)
					{
					System.out.print("Entschuldigt am:");
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
					schüler.abwesenheitmelden(tage);
				
			} catch (Uhrzeitexception e) {
				e.printStackTrace();
			} catch (DatumException e) {
				Printer.printMessage("FEHLER!!!Das Datum ist ungültig. Alle bisherigen Angaben wurden gelöscht. Geben Sie alle Termine erneut ein:" );
				abwesendmelden(schüler);
			}  catch (IOException e) {
				e.printStackTrace();
			}
			catch (NumberFormatException  e) {
				Printer.printMessage("FEHLER!!! Ungültige Eingabe...Alle bisherigen Angaben wurden gelöscht. Geben Sie alle Termine erneut ein");
				abwesendmelden(schüler);
			} catch (UngültigeAnfrageException e) {
				Printer.printMessage("FEHLER!!! Ungültige Eingabe...Alle bisherigen Angaben wurden gelöscht. Geben Sie alle Termine erneut ein");
				abwesendmelden(schüler);
			} catch (Kalenderzugriffsexception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
