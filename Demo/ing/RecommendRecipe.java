package ttttttt;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

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
		this.recipe = recipe;
		this.image = image;
		
		recipes.add(this); //recipes 배열에 해당 객체를 추가합니다.
	}
	
	public static void filter(String cate, String cate2) { 
		filtered_recipes = new ArrayList<Recipe>(); //초기화 
		
		for(Recipe aRecipe : Recipe.recipes) 
			if(aRecipe.object_filter(cate, cate2)) 
				filtered_recipes.add(aRecipe); //Recipe 객체의 object_filter() 결과가 t일때만 filtered_recipes에 추가
	}
	
	public boolean object_filter(String cate, String cate2) {
		if (this.cate.contains(cate)) 
			if(cate2.equals("x")) return true; 						        //샐러디 체크박스 "상관없음" 선택시 or 편의점 레시피는 다 여기서 t 반환하고 끝
			else if (cate2.contains(this.cate2)) return true; 		//샐러디 체크박스에서 이외 버튼 클릭시 포함관계 따져서 this.cate2가 cate2에 들어있으면 t 반환
			else return false;

		else return false;
	}
	
	public static Object[] getFilteredList() {
		for(Recipe aRecipe : Recipe.filtered_recipes)
			filtered_recipes_name.add(aRecipe.getName());
		
		return filtered_recipes_name.toArray();
	}
	
	public static Recipe findRecipe(String name) {
		Recipe r = new Recipe("", "", "", "", "", "", "");
		
		for(Recipe aRecipe : Recipe.filtered_recipes)
			if(aRecipe.getName().equals(name))
				return aRecipe;
		
		return r;
	}
	
	public String getId() { return this.id; }
	public String getName() { return this.name; }
	public String getCate() { return this.cate; }
	public String getCate2() { return this.cate2; }
	public String getIngredient() { return this.ingredient; }
	public String getRecipe() { 
		String recipe = this.recipe.replace("\n", "<br>");
		return recipe; 
	}
	public String getImage() { return this.image; }
}

public class RecommendRecipe extends JPanel {
	private static final long serialVersionUID = 1L; 	//직렬화 관련 코드 - https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=kkson50&logNo=220564273220
	
	private JPanel mainPn, Pn1, Pn4, Pn5; 
	private JComboBox<String> combo1, combo2, combo3; 	//사용처, 샐러디분류, 편의점분류 콤보박스 
	private JCheckBox b1, b2, b3, b4, b5, b6; 		      //체크박스 6개
	private JButton confirm_b; 				                  //확인 버튼
	
	private JList<String> list;
	private DefaultListModel<String> dlist;			        //리스트 내용 변경을 위한 DefaultListModel 객체 
	
	String[] store = {"샐러디", "편의점"};			         //콤보박스 안에 들어갈 내용 배열
	String[] sd = {"웜볼", "샐러드", "샌드", "랩", "웜랩"};
	String[] cs = {"면", "떡볶이", "리조또", "국/찌개", "안주", "음료"};
	
	RecommendRecipe(){
		//패널 생성과 레이아웃 설정
		this.setLayout(new GridLayout(2,1)); //전체 페이지 레이아웃 (2행 1열)
		mainPn = new JPanel();
		mainPn.setBorder(new TitledBorder("조합 추천"));
		
		Pn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Pn4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Pn5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		//패널 구분선
		Pn1.setBorder(new TitledBorder("메뉴")); 
		Pn4.setBorder(new TitledBorder(""));
		
		//콤보박스 초기화
		combo1 = new JComboBox<String>(store);
		combo2 = new JComboBox<String>(sd);
		combo3 = new JComboBox<String>(cs);
		
		//버튼, 체크박스, 리스트 초기화
		confirm_b = new JButton("확인");
		
		b1 = new JCheckBox("헬스");
		b2 = new JCheckBox("가벼운 한끼");
		b3 = new JCheckBox("가벼운 간식");
		b4 = new JCheckBox("든든한 한끼");
		b5 = new JCheckBox("든든한 간식");
		b6 = new JCheckBox("상관 없음"); 
		
		dlist = new DefaultListModel<String>();
		list = new JList<String>(dlist);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //리스트에서 하나의 요소만 선택 가능하게 설정 
		
		//각 패널에 콤보박스, 버튼 넣기
		Pn1.add(combo1); Pn1.add(combo2); Pn1.add(combo3); 
		Pn4.add(b1); Pn4.add(b2); Pn4.add(b3); Pn4.add(b4); Pn4.add(b5); Pn4.add(b6);
		Pn5.add(confirm_b);
		
		//메인 패널에 선택지 패널 넣기
		mainPn.add(Pn1); 
		mainPn.add(Pn4);
		mainPn.add(Pn5);
		
		//전체 패널에 메인 패널, 리스트 넣기
		this.add(mainPn);
		this.add(new JScrollPane(list)); //스크롤 달린 패널로 만들어서 넣음

		menu(combo1.getSelectedIndex());
		start();
	}
	
	public int menu(int n) { //combo1에서 선택하는 값에 따라 가시성을 제어하는 메소드입니다. 
		switch(n) {
			case 0 : //샐러디
				combo2.setVisible(true); 	  //샐러디 분류 콤보박스
				Pn4.setVisible(true); 		  //체크박스 들어있는 패널
				
				combo3.setVisible(false); 	//편의점 분류 콤보박스
				return n;
				
			case 1 : //편의점
				combo3.setVisible(true);
				
				Pn4.setVisible(false);
				combo2.setVisible(false);
				return n;
				
			default : return -1;
		}
	}
	
	public void clickConfirm(int n) { //확인버튼 클릭시 콤보박스, 체크박스에 해당하는 값을 가져와서 Recipe 클래스의 필터링 메소드 호출
		String m = "";
		
		switch(n) {
			case 0 : // combo1에서 샐러디 선택시
				if(b1.isSelected()) m += b1.getText();
				if(b2.isSelected()) m += " " + b2.getText();
				if(b3.isSelected()) m += " " + b3.getText();
				if(b4.isSelected()) m += " " + b4.getText();
				if(b5.isSelected()) m += " " + b5.getText(); 
				if(b6.isSelected()) m = "x";
			
				Recipe.filter((String)combo2.getSelectedItem(), m); 	  //샐러디는 체크박스 내용을 문자열 하나로 묶어서 보냄
				return;
				
			case 1 : // combo1에서 편의점 선택시
				Recipe.filter((String)combo3.getSelectedItem(), "x"); 	//편의점은 분류가 1개라서 2번째 카테고리는 x로 넣음
				return;
				
			default : return;
		}
	}
	
	public void b_Initialization(int n) { //체크박스의 상태를 초기화하는 메소드입니다.
		b1.setSelected(false); 
		b2.setSelected(false);
		b3.setSelected(false);
		b4.setSelected(false); 
		b5.setSelected(false);
		if(n == 0) b6.setSelected(false); //콤보박스에 이벤트 발생할때만 파라미터를 0으로 줘서 마지막 버튼까지 초기화 
	}
	
	public void b6_Initialization() { //"상관없음" 선택 후 다시 다른 체크박스를 클릭하면 "상관없음" 버튼에서 체크가 해제되도록 하는 역할의 메소드입니다.
		if(b1.isSelected() || b2.isSelected() || b3.isSelected() || b4.isSelected() || b5.isSelected()) 
			b6.setSelected(false);	
	}
	
	public void start() { //이벤트 리스너 연결 
		
		/*
		try{
			Path path = Paths.get("C:\\Users\\Jeongyeon\\Desktop\\temp_recipe.txt");
	
			Stream<String> lines = Files.lines(path);
			String content = lines.collect(Collectors.joining(System.lineSeparator()));
			String[] Arr = content.split("\t");
			
			for (int j = 7 ; j < Arr.length ; ) //7부터 시작하는 이유 : 처음에 id(0) ~ image(6) 건너뛰려고 
				new Recipe(Arr[j++], Arr[j++], Arr[j++], Arr[j++], Arr[j++], Arr[j++], Arr[j++]); //레시피 하나 가져와서 Recipe 객체 만들기
				
            		lines.close();
	     } catch(IOException e){ e.getStackTrace(); }
		*/
		
		combo1.addItemListener(e -> { menu(combo1.getSelectedIndex()); });	//사용처 콤보박스
		combo2.addItemListener(e -> { b_Initialization(0); }); 			        //샐러디 분류 콤보박스에 이벤트 발생시 체크박스 모두 초기화
		
		confirm_b.addActionListener(e -> { 
			clickConfirm(menu(combo1.getSelectedIndex()));
			
			if(e.getSource() == confirm_b)
				dlist.removeAllElements(); 
			
			for(Recipe aRecipe : Recipe.filtered_recipes)
				dlist.addElement(aRecipe.getName()); //필터링된 결과를 리스트에 추가
		}); 
		
		b1.addActionListener(e -> { b6_Initialization(); }); //체크박스 1/2/3/4/5에 이벤트 발생시 "상관없음" 버튼(6) 초기화
		b2.addActionListener(e -> { b6_Initialization(); });
		b3.addActionListener(e -> { b6_Initialization(); });
		b4.addActionListener(e -> { b6_Initialization(); });
		b5.addActionListener(e -> { b6_Initialization(); });
		b6.addActionListener(e -> { b_Initialization(1);} ); //체크박스에서 "상관없음"(6) 선택시 나머지 체크박스(1/2/3/4/5)가 초기화
		
		list.addListSelectionListener(e -> { //리스트 항목 선택
			String selectedRecipe = list.getSelectedValue();
			if(!e.getValueIsAdjusting()) //이벤트 중복 발생 방지 (새 창이 두 개 뜨던 그거)
				ShowRecipe.main(Recipe.findRecipe(selectedRecipe), combo1.getSelectedIndex());
		
		}); 
	}
	
	// (1) FirstPage에서 RecommendRecipe로 넘어가는 과정 확인할 때: start()에 있는 try절 주석 풀고 사용
	// (2) RecommendRecipe만 실행하고 싶을 때: 아래 main() 주석 풀고 사용
	
	/*
	public static void main(String[] args) {
		try{
			Path path = Paths.get("C:\\Users\\Jeongyeon\\Desktop\\temp_recipe.txt");
	
			Stream<String> lines = Files.lines(path);
			String content = lines.collect(Collectors.joining(System.lineSeparator()));
			String[] Arr = content.split("\t");
			
			for (int j = 7 ; j < Arr.length ; ) //7부터 시작하는 이유 : 처음에 id(0) ~ image(6) 건너뛰려고 
				new Recipe(Arr[j++], Arr[j++], Arr[j++], Arr[j++], Arr[j++], Arr[j++], Arr[j++]); //레시피 하나 가져와서 Recipe 객체 만들기
				
            		lines.close();
	     } catch(IOException e){ e.getStackTrace(); }
		
		
		JFrame r_frame = new JFrame("레시피 추천 프로그램");
		r_frame.getContentPane().add(new RecommendRecipe());
		r_frame.setBounds(100, 100, 600, 400);
		r_frame.setVisible(true);
		r_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	*/
}
