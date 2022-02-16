package Test;

import javax.accessibility.AccessibleAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
//----------- 기능 -----------
import javax.swing.JButton;      //버튼
import javax.swing.JComboBox; //콤보박스
import javax.swing.JTextField;  //텍스트필드
import javax.swing.border.TitledBorder;
//----------- 패널 -----------
import javax.swing.JFrame; //(1) 패널 JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel; // (2) JPanel; //각 변수들이 들어갈 패널
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.*;

public class TestGUI extends JPanel {
	// ---------- 변수선언 ----------
	// 콤보박스     메뉴         고객
	JComboBox CBmenu;
	
	//Choice chJob, chJob1;
	//					재료
	JTextArea txtmaterial;

	// 텍스트필드 가격         연락처          주소                   재료            
	JTextField txtcwon, txtcjunhwa, txtjuso; //txtmaterial;

	// 버튼      주문          종료
	JButton btnorder, btnclose;
	// -----------------------------
		
	// 생성자
	public TestGUI() {
		design();
	}
		
	//주문정보 대화상자의 화면 디자인을 해주는 메서드
	public void design() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
		//주문정보 패널 //JPanel을 customerPn으로 객체 생성
		// GridLayout 에러 발생 ->  import java.awt.*; 추가
			
		JPanel customerPn = new JPanel(new GridLayout(4,2));

		// 테두리 "주문"
		customerPn.setBorder(
		new TitledBorder(new TitledBorder("주문"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
			
		JPanel cPn1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn4=new JPanel(new FlowLayout(FlowLayout.LEFT));
		// 콤보박스 (메뉴)
		String CBmenu_1[]= {"선택", "편의점","샐러디","스타벅스","공차","서브웨이"};// 콤보박스 안 값
		CBmenu = new JComboBox(CBmenu_1); 
		CBmenu.setPreferredSize(new Dimension(150,20)); //콤보박스 사이즈 조절
		customerPn.add(CBmenu);
		/*	
		// 텍스트
		txtcwon=new JTextField("",13); //가격
		txtcjunhwa=new JTextField("",13); //연락처
		txtjuso=new JTextField("",32); //주소
		txtmaterial=new JTextArea(2,30); //메모
			
		//메모에 스크롤바 기능
		JScrollPane scroll=new JScrollPane(txtmaterial);
		txtmaterial.setBackground(Color.lightGray);
			
		//편집상태x
		txtcwon.setEditable(false);
		txtcjunhwa.setEditable(false);
		txtjuso.setEditable(false);
		txtmaterial.setEditable(false); //메모 불가능 상태
			
		//txtcwon.setEditable(true);//입력창 비활성화 false //입력창 활성화 true
			
		cPn1.add(new JLabel("카테고리 :"));
		cPn1.add(CBmenu);
			
		cPn2.add(new JLabel("가격 :"));
		cPn2.add(txtcwon);
		cPn2.add(new JLabel(" 연락처 :"));
		cPn2.add(txtcjunhwa);
			
		cPn3.add(new JLabel("주소 :"));
		cPn3.add(txtjuso);
			
		cPn4.add(new JLabel("메모 :"));
		cPn4.add(scroll);*/
			
			
		// JButton(버튼)		
		JPanel bottomPn1=new JPanel();
		bottomPn1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		btnorder=new JButton(" 주문 ");
		bottomPn1.add(btnorder);
		btnclose=new JButton(" 종료 ");
		bottomPn1.add(btnclose);
			
			
		customerPn.add(cPn1); //이걸 해줘야 화면상에 보여줌 (가격)
		customerPn.add(cPn2);
		customerPn.add(cPn3);
		customerPn.add(cPn4);
		this.add(customerPn);// "주문" 테두리를 보여준다
		this.add(bottomPn1);
	}
		
	public static void main(String[] args) {
		// ---------------- 화면 (창) ----------------//
		TestGUI bookCustomer = new TestGUI();
		JFrame frame = new JFrame("주문");
		frame.getContentPane().add(bookCustomer);// JFrame+JPanel(화면디자인)
		frame.setBounds(200, 300, 430, 450);// x,y,w,h
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// X버튼 클릭시 종료
		// ---------------- 화면 (끝) ----------------//

	}// main 끝
}
