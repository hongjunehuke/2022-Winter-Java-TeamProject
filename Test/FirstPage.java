import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

 

@SuppressWarnings("serial")
public class FirstPage extends JPanel { // 1번째 패널	
	MainPanel mp;
	JPanel mainPn, Pn1, Pn2, Pn3;
	JLabel textLb, dummyLb;
	JButton startBtn;
 
    public FirstPage (MainPanel mp) {
    	this.mp = mp;
    	
    	mainPn = new JPanel();
    	mainPn.setLayout(new BoxLayout(mainPn, BoxLayout.Y_AXIS));

			Pn1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    	Pn2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    	Pn3 = new JPanel(new FlowLayout(FlowLayout.CENTER));

			dummyLb = new JLabel(" ");	// 간격 띄워주는 역할
    	textLb = new JLabel("레시피 추천 프로그램");
    	startBtn = new JButton("시작");
    	
			dummyLb.setBorder(new EmptyBorder(95, 0, 0, 0));
    	textLb.setBackground(new Color(150, 200, 200));
    	textLb.setFont(new Font("맑은 고딕", Font.BOLD, 20));
    	textLb.setBorder(new EmptyBorder(0, 0, 10, 0));
    	startBtn.setBackground(new Color(150, 200, 200));
    	startBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
    	startBtn.setBorderPainted(false);

			this.add(dummyLb);
    	this.add(textLb);
    	this.add(startBtn);

			mainPn.add(Pn1);
    	mainPn.add(Pn2);
    	mainPn.add(Pn3);

			this.add(mainPn);
    	
    	startBtn.addActionListener(new MyActionListener());
    }
 
    class MyActionListener implements ActionListener { // 버튼 키 눌리면 패널 2번 호출
        @Override
        public void actionPerformed(ActionEvent e) {
        	mp.change("second");
        }
    }
}