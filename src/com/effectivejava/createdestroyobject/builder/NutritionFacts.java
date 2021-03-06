package com.effectivejava.createdestroyobject.builder;

// Builder Pattern
public class NutritionFacts {

	public int getServingSize() {
		return servingSize;
	}

	public int getServings() {
		return servings;
	}

	public int getCalories() {
		return calories;
	}

	public int getFat() {
		return fat;
	}

	public int getSodium() {
		return sodium;
	}

	public int getCarbohydrate() {
		return carbohydrate;
	}

	private final int servingSize;
	private final int servings;
	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbohydrate;

	public static class NutritionFactsBuilder implements Builder<NutritionFacts> {
		// Required parameters
		private final int servingSize;
		private final int servings;
		// Optional parameters - initialized to default values
		private int calories = 0;
		private int fat = 0;
		private int carbohydrate = 0;
		private int sodium = 0;

		public NutritionFactsBuilder(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}

		public NutritionFactsBuilder calories(int val) {
			calories = val;
			return this;
		}

		public NutritionFactsBuilder fat(int val) {
			fat = val;
			return this;
		}

		public NutritionFactsBuilder carbohydrate(int val) {
			carbohydrate = val;
			return this;
		}

		public NutritionFactsBuilder sodium(int val) {
			sodium = val;
			return this;
		}

		public NutritionFacts build() {
			return new NutritionFacts(this);
		}
	}

	private NutritionFacts(NutritionFactsBuilder builder) {
		servingSize = builder.servingSize;
		servings = builder.servings;
		calories = builder.calories;
		fat = builder.fat;
		sodium = builder.sodium;
		carbohydrate = builder.carbohydrate;
	}
}
