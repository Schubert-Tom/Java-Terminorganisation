package Server;

import java.util.Iterator;
import java.util.List;

public final class Printer {
	/**
	 * Die Klasse printet alle Daten im Projekt
	 * @author Tom Schubert
	 * @version 1.0
	 */
	/**
	 * Printet den Kalender in die Console also mit Zeiten und Termin
	 * @param tag
	 */
	public static void print(Wochentag tag) {
		System.out.println("-------------"+tag.getDatum()+"-------------------");
		for (int i = 5;i<(Wochentag.ZEITPLANLÄNGE/2)+5;i++) {
				String uhrzeit=i+":00";
				try {
					System.out.println(i+":00-"+i+":30"+"--"+tag.gettermin(uhrzeit).getTitel());
					if(i<20) {
						uhrzeit=i+":30";
							System.out.println(i+":30-"+(i+1)+":00"+"--"+tag.gettermin(uhrzeit).getTitel());
						
						if(i%2==0) {
						System.out.println("--------------------------------");
						}
					}
				}
			catch (Uhrzeitexception e) {
			e.printStackTrace();
		}
	}
}
	/**
	 * Schreibt eine Auswahl von Anträgen in die Console
	 * @param anträge
	 */
	public static void print(List<Antragsklasse> anträge) {
		Iterator<Antragsklasse> it = anträge.iterator();
		int i = 0;
		while (it.hasNext()) {
			System.out.println(i+".) Antrag von "+it.next().getTag().getAntragsteller().getnamen());
			i++;
		}
	}	
	/**
	 * Schreibt Informationen über eine Terminblock in die Console
	 * @param terminblock
	 */
	public static void print(Terminblock terminblock) {
		System.out.println(terminblock.getTitel()+" von "+terminblock.getBeginn()+" bis um "+terminblock.getEnde()+" aufgrund von"+  " mit zusätzlich folgenden Usern:");
		if(terminblock.getBeteiligte().size()==0) {
			Printer.printMessage("keine weiteren User");
		}
		else {
			for(User u:terminblock.getBeteiligte()) {
				System.out.print(u.getnamen()+"; ");
			}
		}
		
	}
	/**
	 * Schreibt einen einfachen String in die Console;
	 * @param message
	 */
	public static void printMessage(String message) {
		System.out.println(message);
	}
}
