package GUI;

import java.util.concurrent.TimeUnit;

public final class Wait {
	
	public static void wait(int i) {
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
