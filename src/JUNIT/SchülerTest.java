package JUNIT;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Test;

import Server.DatumException;
import Server.Kalenderzugriffsexception;
import Server.Organisationszentrum;
import Server.Schüler;
import Server.Uhrzeitexception;
import Server.UngültigeAnfrageException;
import Server.Wochentag;

public class SchülerTest {

	@Test
	public void test() {
		 Organisationszentrum zentrum = new Organisationszentrum();
		try {
			Schüler schüler=new Schüler("Tom","Schubert",zentrum);
			assertTrue(schüler.getnamen().equals("Tom Schubert"));
			schüler.getStundenplan();
			 ArrayList<Wochentag> testtage= new ArrayList<Wochentag>();
			 testtage.add(new Wochentag(new GregorianCalendar()));
			 schüler.abwesenheitmelden(testtage);
			 assertNotNull(zentrum.getAnträge());
			
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
