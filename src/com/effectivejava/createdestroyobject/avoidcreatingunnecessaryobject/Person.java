package com.effectivejava.createdestroyobject.avoidcreatingunnecessaryobject;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Person {

	private final Date birthDate;

	public Person(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	// Hideously slow program! Can you spot the object creation?
	public void badSun() {
		Long sum = 0L;
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;
		}
		System.out.println(sum);
	}
	
	public  void BetterSum() {
		long betterSum = 0L;
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
			betterSum += i;
		}
		System.out.println(betterSum);
	}
	
	public void dontDoThis() {
		String s = new String("stringette"); // DON'T DO THIS!
	    String sBetter = "stringette";
	    System.out.println(s);
	    System.out.println(sBetter);
	}

	// DON'T DO THIS!
	public boolean isBabyBoomerOne() {
		// Unnecessary allocation of expensive object
		Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
		Date boomStart = gmtCal.getTime();
		gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
		Date boomEnd = gmtCal.getTime();
		return birthDate.compareTo(boomStart) >= 0
				&& birthDate.compareTo(boomEnd) < 0;
	}

	/**
	 * The starting and ending dates of the baby boom.
	 */
	private static final Date BOOM_START;
	private static final Date BOOM_END;
	static {
		Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
		BOOM_START = gmtCal.getTime();
		gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
		BOOM_END = gmtCal.getTime();
	}

	public boolean isBabyBoomer() {
		return birthDate.compareTo(BOOM_START) >= 0
				&& birthDate.compareTo(BOOM_END) < 0;
	}
}
