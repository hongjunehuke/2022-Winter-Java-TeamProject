package recipe_test_code; // 패키지 변경해주세요

/* 
txt 파일 속 레시피 하나 = Recipe 객체 하나에 저장됨
txt 파일 속 모든 레시피 = recipes 배열에 저장됨
txt 파일 속 기준에 해당하는 레시피 = filtered_recieps 배열에 저장됨

진행단계: txt 파일에서 레시피 가져와서 저장 + 입력된 기준에 따라 필터링 후 콘솔에 출력 (2022/02/22 22:00)
*/


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;

class Recipe {
	private String id, name, cate, cate2, ingredient, recipe, image;
	public static ArrayList<Recipe> recipes = new ArrayList<Recipe>(); 	
	public static ArrayList<Recipe> filtered_recipes = new ArrayList<Recipe>(); 	

	Recipe(String id, String name, String cate, String cate2, String ingredient, String recipe, String image){
		this.id = id;
		this.name = name;
		this.cate = cate;
		this.cate2 = cate2;
		this.ingredient = ingredient;	
		this.recipe = recipe;
		this.image = image;
		
		recipes.add(this); //recipes 배열에 해당 객체를 추가합니다.
	}
	
	public void printRecipe() {
		System.out.println(id);
		System.out.println(name);
		System.out.println(cate);
		System.out.println(cate2); 
		System.out.println(ingredient);
		System.out.println(recipe);
		System.out.println(image + "\n");
	}
	
	public static void filter(String cate, String cate2) { 
		filtered_recipes = new ArrayList<Recipe>();
		
		for(Recipe aRecipe : Recipe.recipes) 
			if(aRecipe.object_filter(cate, cate2)) 
				filtered_recipes.add(aRecipe); //Recipe 객체의 object_filter() 결과값이 t일때만 filtered_recipes에 추가
	}
	
	public boolean object_filter(String cate, String cate2) {
		if (this.cate.equals(cate) && this.cate2.equals(cate2)) 
			return true; // 카테고리 2개가 전부 똑같으면 t 반환
		
		else
			return false;
	}
}

public class FilterRecipe {
	public static void main(String[] args) {
		try{
			Path path = Paths.get("경로");
			
			Stream<String> lines = Files.lines(path);
			String content = lines.collect(Collectors.joining(System.lineSeparator()));
			String[] Arr = content.split("\t");
			
			for (int j = 7 ; j < Arr.length ; )
				new Recipe(Arr[j++], Arr[j++], Arr[j++], Arr[j++], Arr[j++], Arr[j++], Arr[j++]); //txt에서 레시피 하나 가져와서 Recipe 객체 만들기
				
            lines.close();

	     } catch(IOException e){ e.getStackTrace(); }
		
		Recipe.filter("음료", "x");
		for(Recipe aRecipe : Recipe.filtered_recipes) aRecipe.printRecipe(); //기준에 해당하는 모든 레시피 출력
	}
}
