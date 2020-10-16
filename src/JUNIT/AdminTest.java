package JUNIT;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Server.Admin;
import Server.Organisationszentrum;
import Server.Uhrzeitexception;

public class AdminTest {

	@Test
	public void test() {
		Organisationszentrum zentrum = new Organisationszentrum();
		Admin admin= new Admin(zentrum);
		try {
			admin.Schülererstellen("test", "test");
			ArrayList<String>fächer= new ArrayList<String>();
			fächer.add("Mathe");
			assertTrue(zentrum.getExistierendeaccounts().getSchüler().get(0).getnamen().equals("test test"));
			admin.Lehrererstellen("Tom", "TEst", fächer);
			assertTrue(zentrum.getExistierendeaccounts().getLehrer().get(0).getnamen().equals("Tom TEst"));
			assertTrue(admin.getnamen().equals("Administrator"));
			
	
		} catch (Uhrzeitexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
