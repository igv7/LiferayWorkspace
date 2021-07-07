package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
	private static Utils instance = null;

	// private constructor restricted to this class itself
	private Utils() {
	}

	// static method to create instance of Singleton class
	public static Utils getInstance() {
		if (instance == null) {
			synchronized (Utils.class) {
				if (instance == null) {
					instance = new Utils();
				}
			}
		}

		return instance;
	}

	public boolean isToday(String date) {
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		now.set(Calendar.YEAR, 1990);
		try {
			Date dateObjDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			Calendar birthdate = Calendar.getInstance();
			birthdate.setTime(dateObjDate);
			birthdate.set(Calendar.HOUR_OF_DAY, 0);
			birthdate.set(Calendar.MINUTE, 0);
			birthdate.set(Calendar.SECOND, 0);
			birthdate.set(Calendar.MILLISECOND, 0);
			birthdate.set(Calendar.YEAR, 1990);
			return birthdate.compareTo(now) == 0;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
