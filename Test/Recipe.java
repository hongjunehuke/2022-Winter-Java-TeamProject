//package recipe_project;

import java.util.ArrayList;

class Recipe {
	private String id, name, cate, cate2, ingredient, recipe, image;
	public static ArrayList<Recipe> recipes = new ArrayList<Recipe>(); 	
	public static ArrayList<Recipe> filtered_recipes = new ArrayList<Recipe>();
	public static ArrayList<String> filtered_recipes_name = new ArrayList<String>();

	Recipe(String id, String name, String cate, String cate2, String ingredient, String recipe, String image){
		this.id = id;
		this.name = name;
		this.cate = cate;
		this.cate2 = cate2;
		this.ingredient = ingredient;	
		this.recipe = recipe.replace("\n", "<br>");
		this.image = image;
		
		recipes.add(this); 
	}
	
	public boolean compareCategories(String cate, String cate2) {
		if (this.cate.contains(cate)) 
			if(cate2.equals("x")) return true; 						
			else if (cate2.contains(this.cate2)) return true; 
			else return false;

		else return false;
	}
	
	public String getId() { return this.id; }
	public String getName() { return this.name; }
	public String getCate() { return this.cate; }
	public String getCate2() { return this.cate2; }
	public String getIngredient() { return this.ingredient; }
	public String getRecipe() { return recipe; }
	public String getImage() { return this.image; }
	
	public static void filter(String cate, String cate2) { 
		filtered_recipes = new ArrayList<Recipe>(); 
		
		for(Recipe aRecipe : Recipe.recipes) 
			if(aRecipe.compareCategories(cate, cate2)) 
				filtered_recipes.add(aRecipe);
	}
	
	public static Object[] getFilteredList() {
		for(Recipe aRecipe : Recipe.filtered_recipes)
			filtered_recipes_name.add(aRecipe.getName());
		
		return filtered_recipes_name.toArray();
	}
	
	public static Recipe findRecipe(String name) {
		Recipe r = new Recipe("x", "", "", "", "", "", "");
		
		for(Recipe aRecipe : Recipe.filtered_recipes)
			if(aRecipe.getName().equals(name))
				return aRecipe;
		
		return r;
	}
	
}
