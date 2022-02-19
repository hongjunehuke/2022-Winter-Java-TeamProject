package Homework;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/*class MakePanel extends JPanel {
	JFrame frame = new JFrame("레시피 추천 프로그램");
	//frame.getContentPane().add(new ShowRecipe());
	
	frame.setBounds(300, 200, 600, 450); //앞: 프로그램 실행시 화면 내 x/y 좌표 + 뒤: 프로그램 가로 세로 길이 
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}*/

public class ShowRecipe extends JPanel {
	
	JPanel mainPn;		// 메인 패널
	JLabel nameLb;		// 레시피 이름
	JLabel imgLb;			// 음식 사진넣을 라벨
	ImageIcon img = null;	// 음식 사진
	Image subImg;	// 크기 조절용
	
	ShowRecipe(String[] str){
		
		/* 1. 메인 패널 위에 레시피명 넣기 */
		mainPn = new JPanel();
		nameLb = new JLabel(str[1]);
		nameLb.setOpaque(true);	// 배경색 투명->불투명 변경
		nameLb.setBorder(new LineBorder(new Color(150, 150, 200), 3, true));	// 테두리
		nameLb.setBackground(new Color(150, 200, 200));	// 배경색 설정
		nameLb.setFont(new Font("맑은 고딕", Font.BOLD, 30));	// 글씨체, 크기, 두께 설정
		nameLb.setBorder(new EmptyBorder(10, 15, 10, 15));
		this.add(nameLb);
		
		/* 2. 메일 패널 위에 음식 사진 넣기 */
		imgLb = new JLabel();
		try {
			img = new ImageIcon(new URL(str[6]));	// url에서 이미지 가져오기
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		subImg = img.getImage();
		subImg = subImg.getScaledInstance(300, 300,  Image.SCALE_SMOOTH);
		img = new ImageIcon(subImg);
		imgLb.setIcon(img);	// 라벨에 이미지 아이콘 설정
		imgLb.setBounds(30, 30, 122, 130);
		this.add(imgLb);
	
	}

	
	public static void main(String[] args) {
		String[] str = {"1", "오감자치즈후라이" , "안주", "x", "오감자, 체다치즈, 스트링치즈",
				"1. 오감자를 접시에 얹어준다.\\n2. 체다치즈와 스트링치즈를 찢어서 얹는다.\\n3. 전자렌지에 1분 정도 돌리면 완성!",
				"https://recipe1.ezmember.co.kr/cache/recipe/2018/01/25/62f2958b28f11ce914b7afb96d07390b1.png"};
		
		
		/* 기본 베이스 프레임 만들기 */
		JFrame frame = new JFrame("레시피 추천 프로그램");
		frame.getContentPane().add(new ShowRecipe(str));
		frame.setBounds(100, 100, 500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

