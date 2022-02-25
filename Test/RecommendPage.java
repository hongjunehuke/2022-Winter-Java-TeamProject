// (1) FirstPage에서 RecommendRecipe로 넘어가는 과정 확인할 때: start()에 있는 try절 주석 풀기
// (2) RecommendRecipe만 실행하고 싶을 때: 아래 main() 주석 풀고 사용하기 
	
package recipe_project;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecommendPage extends JPanel {
	private static final long serialVersionUID = 1L; 	
	
	private JPanel mainPn, Pn1, Pn2, Pn3; 
	private JComboBox<String> storeCombo, saladyCombo, cvsCombo; 	
	private JCheckBox b1, b2, b3, b4, b5, b6; 	//체크박스 6개
	private JButton confirmBtn; 				//확인 버튼
	
	private JList<String> resultList;
	private DefaultListModel<String> defaultResultList;			//리스트 내용 변경을 위한 DefaultListModel 객체 
	
	String[] store = {"샐러디", "편의점"};			
	String[] sd = {"웜볼", "샐러드", "샌드", "랩", "웜랩"};
	String[] cs = {"면", "떡볶이", "리조또", "국/찌개", "안주", "음료"};
	
	RecommendPage(){
		//패널 생성과 레이아웃 설정
		this.setLayout(new GridLayout(2,1)); 
		mainPn = new JPanel();
		mainPn.setBorder(new TitledBorder("조합 추천"));
		
		Pn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Pn2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Pn3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		//패널 구분선
		Pn1.setBorder(new TitledBorder("메뉴")); 
		Pn2.setBorder(new TitledBorder(""));
		
		//콤보박스 초기화
		storeCombo = new JComboBox<String>(store);
		saladyCombo = new JComboBox<String>(sd);
		cvsCombo = new JComboBox<String>(cs);
		
		//버튼, 체크박스, 리스트 초기화
		confirmBtn = new JButton("확인");
		
		b1 = new JCheckBox("헬스");
		b2 = new JCheckBox("가벼운 한끼");
		b3 = new JCheckBox("가벼운 간식");
		b4 = new JCheckBox("든든한 한끼");
		b5 = new JCheckBox("든든한 간식");
		b6 = new JCheckBox("상관 없음"); 
		
		defaultResultList = new DefaultListModel<String>();
		resultList = new JList<String>(defaultResultList);
		resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //리스트에서 하나의 요소만 선택 가능하게 설정 
		
		//각 패널에 콤보박스, 버튼 넣기
		Pn1.add(storeCombo); Pn1.add(saladyCombo); Pn1.add(cvsCombo); 
		Pn2.add(b1); Pn2.add(b2); Pn2.add(b3); Pn2.add(b4); Pn2.add(b5); Pn2.add(b6);
		Pn3.add(confirmBtn);
		
		//메인 패널에 선택지 패널 넣기
		mainPn.add(Pn1); 
		mainPn.add(Pn2);
		mainPn.add(Pn3);
		
		//전체 패널에 메인 패널, 리스트 넣기
		this.add(mainPn);
		this.add(new JScrollPane(resultList)); //스크롤 달린 패널로 만들어서 넣음

		changeVisibleComponent(storeCombo.getSelectedIndex());
		start();
	}
	
	public int changeVisibleComponent(int n) {  
		switch(n) {
			case 0 : //샐러디
				saladyCombo.setVisible(true); 	
				Pn2.setVisible(true); 		
				
				cvsCombo.setVisible(false); 	
				return n;
				
			case 1 : //편의점
				cvsCombo.setVisible(true);
				
				Pn2.setVisible(false);
				saladyCombo.setVisible(false);
				return n;
				
			default : return -1;
		}
	}
	
	public void clickConfirmBtn(int n) { 
		String m = "";
		
		switch(n) {
			case 0 : //샐러디 
				if(b1.isSelected()) m += b1.getText();
				if(b2.isSelected()) m += " " + b2.getText();
				if(b3.isSelected()) m += " " + b3.getText();
				if(b4.isSelected()) m += " " + b4.getText();
				if(b5.isSelected()) m += " " + b5.getText(); 
				if(b6.isSelected()) m = "x";
			
				Recipe.filter((String)saladyCombo.getSelectedItem(), m); 
				return;
				
			case 1 : //편의점
				Recipe.filter((String)cvsCombo.getSelectedItem(), "x");
				return;
				
			default : return;
		}
	}
	
	public void changeCheckboxState(int n) {
		b1.setSelected(false); 
		b2.setSelected(false);
		b3.setSelected(false);
		b4.setSelected(false); 
		b5.setSelected(false);
		if(n == 0) b6.setSelected(false); //콤보박스에 이벤트 발생할때만 파라미터를 0으로 줘서 마지막 버튼까지 초기화 
	}
	
	public void change6thCheckboxState() { 
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
		
		storeCombo.addItemListener(e -> { 
			changeVisibleComponent(storeCombo.getSelectedIndex()); 
			changeCheckboxState(0);
		});
		
		saladyCombo.addItemListener(e -> { changeCheckboxState(0); }); 			
		
		confirmBtn.addActionListener(e -> { 
			clickConfirmBtn(changeVisibleComponent(storeCombo.getSelectedIndex()));
			
			if(e.getSource() == confirmBtn)
				defaultResultList.removeAllElements(); 
			
			for(Recipe aRecipe : Recipe.filtered_recipes)
				defaultResultList.addElement(aRecipe.getName()); 
		}); 
		
		b1.addActionListener(e -> { change6thCheckboxState(); }); 	//체크박스 1/2/3/4/5에 이벤트 발생시 "상관없음" 버튼(6) 초기화
		b2.addActionListener(e -> { change6thCheckboxState(); });
		b3.addActionListener(e -> { change6thCheckboxState(); });
		b4.addActionListener(e -> { change6thCheckboxState(); });
		b5.addActionListener(e -> { change6thCheckboxState(); });
		b6.addActionListener(e -> { changeCheckboxState(1);} ); 	//체크박스에서 "상관없음"(6) 선택시 나머지 체크박스(1/2/3/4/5)가 초기화
		
		resultList.addListSelectionListener(e -> { //리스트 항목 선택
			String selectedRecipe = resultList.getSelectedValue();
			
			if(!e.getValueIsAdjusting()) //이벤트 중복 발생 방지
				ShowRecipe.main(Recipe.findRecipe(selectedRecipe), storeCombo.getSelectedIndex());
		}); 
		
	}
	
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
		r_frame.getContentPane().add(new RecommendPage());
		r_frame.setBounds(100, 100, 600, 400);
		r_frame.setVisible(true);
		r_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
}
