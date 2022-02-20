package recipe_project;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class RecommendRecipe extends JPanel {
	private static final long serialVersionUID = 1L; //직렬화 관련 코드 - https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=kkson50&logNo=220564273220
	
	JPanel mainPn, Pn1, Pn2, Pn3, Pn4, Pn5; 
	JComboBox<String> combo1, combo2, combo3; 	//차례대로 사용처, 샐러디분류, 편의점분류 콤보박스 
	JCheckBox b1, b2, b3, b4, b5, b6; 					//체크박스 4개
	JLabel l; 									//라벨 - 리스트 자리 표시하려고 넣은거라 나중엔 지울 예정
	JButton confirm_b; 							//확인 버튼
	
	String[] store = {"샐러디", "편의점"}; 			//콤보박스 안에 들어갈 내용 배열
	String[] sd = {"웜볼", "샐러디", "샌드", "랩", "웜랩"};
	String[] cs = {"면", "떡볶이", "리조또", "국/찌개", "안주", "음료"};
	
	RecommendRecipe(){
		//패널 생성과 레이아웃 설정
		this.setLayout(new GridLayout(2,1)); //전체 페이지 레이아웃 (2행 1열)
		mainPn = new JPanel();
		mainPn.setBorder(new TitledBorder("조합 추천"));
		
		Pn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Pn2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Pn3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Pn4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Pn5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		//패널 구분선 (나중에 지울 코드)
		Pn1.setBorder(new TitledBorder("사용처")); 
		Pn2.setBorder(new TitledBorder("분류")); 
		Pn3.setBorder(new TitledBorder("분류")); 
		Pn4.setBorder(new TitledBorder("재료/맛"));
		
		//콤보박스 초기화 (배열의 내용을 콤보박스에 넣는 과정)
		combo1 = new JComboBox<String>(store);
		combo2 = new JComboBox<String>(sd);
		combo3 = new JComboBox<String>(cs);
		
		//버튼, 라벨, 체크박스 초기화
		confirm_b = new JButton("확인");
		l = new JLabel("리스트 나타날 자리");
		
		b1 = new JCheckBox("헬스");
		b2 = new JCheckBox("가벼운 한끼");
		b3 = new JCheckBox("가벼운 간식");
		b4 = new JCheckBox("든든한 한끼");
		b5 = new JCheckBox("든든한 간식");
		b6 = new JCheckBox("상관 없음"); 
		
		//각 패널에 콤보박스, 버튼 넣기
		Pn1.add(combo1); 
		Pn2.add(combo2); 
		Pn3.add(combo3); 
		Pn4.add(b1); Pn4.add(b2); Pn4.add(b3); Pn4.add(b4); Pn4.add(b5); Pn4.add(b6);
		Pn5.add(confirm_b);
		
		//메인 패널에 선택지 패널 넣기
		mainPn.add(Pn1); 
		mainPn.add(Pn2);
		mainPn.add(Pn3); 
		mainPn.add(Pn4);
		mainPn.add(Pn5);
		
		//전체 패널에 메인 패널, 라벨 넣기 (메인 패널이 1행, 라벨이 2행으로 들어감)
		this.add(mainPn);
		this.add(l);
		
		menu1(combo1.getSelectedIndex());
		start();
	}
	
	public int menu1(int n) { //combo1(편의점, 샐러디 중 택1)에서 선택하는 값에 따라 패널의 가시성을 제어하는 메소드입니다. 
		switch(n) {
			case 0 : // 샐러디 선택시
				Pn2.setVisible(true); //샐러디 분류 콤보박스 들어있는 패널
				Pn4.setVisible(true); //재료/맛 체크박스 들어있는 패널
				
				Pn3.setVisible(false); //편의점 분류 콤보박스 들어있는 패널
				return n;
				
			case 1 : // 편의점 선택시
				Pn3.setVisible(true);
				
				Pn4.setVisible(false);
				Pn2.setVisible(false);
				return n;
				
			default : return -1;
		}
	}
	
	public void confirm_click(int n) { //확인 버튼 클릭시 콤보박스, 체크박스에 해당하는 값을 가져와서 라벨의 내용을 바꾸는 메소드입니다.
		String m = "";
		
		switch(n) {
			case 0 : // 샐러디 선택시
				m += (String)combo1.getSelectedItem() + " " + (String)combo2.getSelectedItem() + " ";
				
				if(b1.isSelected()) m += b1.getText();
				if(b2.isSelected()) m += b2.getText();
				if(b3.isSelected()) m += b3.getText();
				if(b4.isSelected()) m += b4.getText();
				if(b5.isSelected()) m += b5.getText(); //b6은 "상관 없음"버튼 이라 값 가져올 필요 X
			
				m += "에 해당하는 리스트 나타날 자리";
				l.setText(m); //콤보박스, 체크박스에서 선택된 내용을 최종적으로 라벨에서 확인 
				return;
				
			case 1 : // 편의점 선택시
				m += (String)combo1.getSelectedItem() + " " + (String)combo3.getSelectedItem() + " ";
				m += "에 해당하는 리스트 나타날 자리";
				
				l.setText(m); //콤보박스, 체크박스에서 선택된 내용을 최종적으로 라벨에서 확인 
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
	
	public void start() { //콤보박스, 체크박스, 버튼에서 발생하는 클릭 이벤트를 처리하는 메소드입니다.
		combo1.addItemListener(e -> { menu1(combo1.getSelectedIndex()); });	//사용처 콤보박스
		combo2.addItemListener(e -> { b_Initialization(0); }); //(샐러디)분류 콤보박스
		
		confirm_b.addActionListener(e -> { confirm_click(menu1(combo1.getSelectedIndex())); }); //확인 버튼
		
		b1.addActionListener(e -> { b6_Initialization(); }); //체크박스 1/2/3/4/5에 이벤트 발생시 "상관없음" 버튼(6)을 초기화합니다.
		b2.addActionListener(e -> { b6_Initialization(); });
		b3.addActionListener(e -> { b6_Initialization(); });
		b4.addActionListener(e -> { b6_Initialization(); });
		b5.addActionListener(e -> { b6_Initialization(); });
		b6.addActionListener(e -> { b_Initialization(1);} ); //체크박스에서 "상관없음"(6)을 선택시 나머지 체크박스(1/2/3/4/5)가 초기화됩니다.
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("레시피 추천 프로그램");
		frame.getContentPane().add(new RecommendRecipe());
		
		frame.setBounds(300, 200, 600, 450); //앞: 프로그램 실행시 화면 내 x/y 좌표 + 뒤: 프로그램 가로 세로 길이 
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
