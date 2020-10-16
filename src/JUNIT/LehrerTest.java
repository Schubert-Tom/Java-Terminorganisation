package JUNIT;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Test;

import Server.Admin;
import Server.DatumException;
import Server.Kalenderzugriffsexception;
import Server.Lehrer;
import Server.Organisationszentrum;
import Server.Uhrzeitexception;
import Server.UngültigeAnfrageException;
import Server.Wochentag;

public class LehrerTest {

	@Test
	public void test() {
		 Organisationszentrum zentrum = new Organisationszentrum();
		try {
			ArrayList<String>fächer= new ArrayList<String>();
			fächer.add("Mathe");
			Lehrer lehrer=new Lehrer("Tom","Schubert",fächer,zentrum);
			assertTrue(lehrer.getnamen().equals("Tom Schubert"));
			Admin admin = new Admin(zentrum);
			 ArrayList<Wochentag> testtage= new ArrayList<Wochentag>();
			 testtage.add(new Wochentag(new GregorianCalendar()));
			 lehrer.abwesenheitmelden(testtage);
			 assertNotNull(admin.getZentrum().getAnträge());
			
		} catch (Uhrzeitexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatumException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UngültigeAnfrageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Kalenderzugriffsexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

