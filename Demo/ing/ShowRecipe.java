package ttttttt; // 패키지 바꿔주세요

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ShowRecipe extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JPanel mainPn, Pn1, Pn2, Pn3, Pn4;
	JLabel nameLb;			// 레시피 이름
	JLabel imgLb;			// 음식 사진넣을 라벨
	JLabel ingreLb;			// 음식 재료
	JLabel recipeLb;		// 음식 레시피
	ImageIcon img = null;		// 음식 사진
	Image subImg;			// 크기 조절용
	
	ShowRecipe(Recipe r, int i){
		mainPn = new JPanel();
		mainPn.setBackground(Color.GREEN);
		mainPn.setLayout(new BoxLayout(mainPn, BoxLayout.Y_AXIS));
		
		Pn1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Pn2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Pn3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Pn4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		/* 음식명 */
		nameLb = new JLabel(r.getName());
		nameLb.setOpaque(true);	// 배경색 투명->불투명 변경
		nameLb.setBackground(new Color(150, 200, 200));	// 배경색 설정
		nameLb.setFont(new Font("맑은 고딕", Font.BOLD, 30));	// 글씨체, 크기, 두께 설정
		nameLb.setBorder(new EmptyBorder(5, 10, 5, 10));
		
		if(i == 1) { //편의점 레시피일때만 이미지 속성 초기화
			imgLb = new JLabel();
			try {
				img = new ImageIcon(new URL(r.getImage()));	// url에서 이미지 가져오기
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			subImg = img.getImage();
			subImg = subImg.getScaledInstance(240, 240,  Image.SCALE_SMOOTH);
			img = new ImageIcon(subImg);
			imgLb.setIcon(img);	// 라벨에 이미지 아이콘 설정
			imgLb.setBounds(30, 30, 122, 130);
		}
		
		/* 재료 */
		ingreLb = new JLabel("재료 : " + r.getIngredient());
		ingreLb.setOpaque(true);
		ingreLb.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		/* 레시피 */
		recipeLb = new JLabel("<html>" + r.getRecipe() + "</html>");
		recipeLb.setOpaque(true);
		recipeLb.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		/* 패널에 라벨이나 버튼 추가 */
		Pn1.add(nameLb);
		if(i == 1) Pn2.add(imgLb);
		Pn3.add(ingreLb);
		Pn4.add(recipeLb);
		
		/* 메인 패널에 서브 패널 삽입 */
		mainPn.add(Pn1); 
		if(i == 1) mainPn.add(Pn2);
		mainPn.add(Pn3); 
		mainPn.add(Pn4);
		
		/* 프레임에 메인 매널 삽입 */
		this.add(mainPn);
	}

	public static void main(Recipe args, int i) {
		if(args.getId().equals("x")) //비정상 레시피 들어오면 종료
			return;
		else {
			JFrame r_frame = new JFrame("상세 레시피");
			r_frame.getContentPane().add(new ShowRecipe(args, i)); 

			if(i == 0) r_frame.setBounds(100, 100, 700, 200);
			else r_frame.setBounds(100, 100, 700, 550);

			r_frame.setVisible(true);
			r_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
	}
}




/*

package Homework;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ShowRecipe extends JPanel {
	JPanel mainPn, Pn1, Pn2, Pn3, Pn4, Pn5;
	JLabel nameLb;			// 레시피 이름
	JLabel imgLb;			// 음식 사진넣을 라벨
	JLabel ingreLb;			// 음식 재료
	JLabel recipeLb;		// 음식 레시피
	ImageIcon img = null;	// 음식 사진
	Image subImg;			// 크기 조절용
	JButton btn;			// 목록으로 돌아가는 버튼
	
	ShowRecipe(String[] str){
		
		mainPn = new JPanel();
		mainPn.setBackground(Color.GREEN);
		mainPn.setLayout(new BoxLayout(mainPn, BoxLayout.Y_AXIS));
		
		Pn1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Pn2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Pn3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Pn4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Pn5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		/* 음식명 */
		nameLb = new JLabel(str[1]);
		nameLb.setOpaque(true);	// 배경색 투명->불투명 변경
		nameLb.setBackground(new Color(150, 200, 200));	// 배경색 설정
		nameLb.setFont(new Font("맑은 고딕", Font.BOLD, 30));	// 글씨체, 크기, 두께 설정
		nameLb.setBorder(new EmptyBorder(5, 10, 5, 10));
		
		/* 이미지 */
		imgLb = new JLabel();
		try {
			img = new ImageIcon(new URL(str[6]));	// url에서 이미지 가져오기
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		subImg = img.getImage();
		subImg = subImg.getScaledInstance(240, 240,  Image.SCALE_SMOOTH);
		img = new ImageIcon(subImg);
		imgLb.setIcon(img);	// 라벨에 이미지 아이콘 설정
		imgLb.setBounds(30, 30, 122, 130);
		
		/* 재료 */
		ingreLb = new JLabel("재료 : " + str[4]);
		ingreLb.setOpaque(true);
		ingreLb.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		/* 레시피 */
		// 이 방법으로 사용하려면 txt파일의 \n을 <br>로 바꿔야함. (HTML 사용)
		recipeLb = new JLabel("<html>" + str[5] + "</html>");
		recipeLb.setOpaque(true);
		recipeLb.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		/* 목록 버튼 */
		btn = new JButton("목록");
		btn.setBackground(new Color(150, 200, 200));
		btn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		btn.setBorderPainted(false);
		
		/* 패널에 라벨이나 버튼 추가 */
		Pn1.add(nameLb);
		Pn2.add(imgLb);
		Pn3.add(ingreLb);
		Pn4.add(recipeLb);
		Pn5.add(btn);
		
		/* 메인 패널에 서브 패널 삽입 */
		mainPn.add(Pn1); 
		mainPn.add(Pn2);
		mainPn.add(Pn3); 
		mainPn.add(Pn4);
		mainPn.add(Pn5);
		
		/* 프레임에 메인 매널 삽입 */
		this.add(mainPn);
		
		onClick();
	}
	
	public void onClick() {
		btn.addActionListener(e -> {
			// 목록으로 돌아가서 다시 리스트를 보여주는 창으로 전환하는 코드로 변경해야 함.
			JOptionPane aa=new JOptionPane();
			aa.showMessageDialog(null, "목록");
		});
	}

	
	public static void main(String[] args) {
		/* 리스트 중 하나를 클릭할 때 이렇게 Sting 배열 정보를 넘기는 방법이 없을까..? */
		/* 레시피 String 배열을 전역으로 설정한다면 Sting 배열 정보를 다 넘기지 않고 id값만 넘겨도 괜찮음 */
		String[] str = {"1", "오감자치즈후라이" , "안주", "x", "오감자, 체다치즈, 스트링치즈",
				"1. 오감자를 접시에 얹어준다.<br>2. 체다치즈와 스트링치즈를 찢어서 얹는다.<br>3. 전자렌지에 1분 정도 돌리면 완성!",
				"https://recipe1.ezmember.co.kr/cache/recipe/2018/01/25/62f2958b28f11ce914b7afb96d07390b1.png"};
		
		
		/* 기본 베이스 프레임 만들기 */
		JFrame frame = new JFrame("레시피 추천 프로그램");
		frame.getContentPane().add(new ShowRecipe(str));
		frame.setBounds(100, 100, 500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

*/
