package com.effectivejava.createdestroyobject.noninstanciability;

// Noninstantiable utility class
public class UtilityClass {
	// Suppress default constructor for noninstantiability
	private UtilityClass() {
		throw new AssertionError();
	}

	public static String stuff() {
		return "Stuff";
	}
}
