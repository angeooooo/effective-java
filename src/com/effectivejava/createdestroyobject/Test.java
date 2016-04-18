package com.effectivejava.createdestroyobject;

import com.effectivejava.createdestroyobject.builder.NutritionFacts;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NutritionFacts cocaCola = new NutritionFacts.NutritionFactsBuilder(240, 8).
			     calories(100).sodium(35).carbohydrate(27).build();
		System.out.println(cocaCola);
	}
}
