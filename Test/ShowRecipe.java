//package ttttttt; // 패키지 바꿔주세요

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

		JPanel dummyP = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel dummyL = new JLabel();
		dummyP.add(dummyL);
		mainPn.add(dummyP); 
		
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