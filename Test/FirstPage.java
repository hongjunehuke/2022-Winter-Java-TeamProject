import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.BoxLayout;

 

@SuppressWarnings("serial")
public class FirstPage extends JPanel { // 1번째 패널	
	MainPanel mp;
	JPanel mainPn;
	JLabel text;
	JButton startBtn;
 
    public FirstPage (MainPanel mp) {
    	this.mp = mp;
    	
    	mainPn = new JPanel();
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    	text = new JLabel("레시피 추천 프로그램");
    	startBtn = new JButton("시작");
    	
    	this.add(text);
    	this.add(startBtn);
    	
    	startBtn.addActionListener(new MyActionListener());
    }
 
    class MyActionListener implements ActionListener { // 버튼 키 눌리면 패널 2번 호출
        @Override
        public void actionPerformed(ActionEvent e) {
        	mp.change("second");
        }
    }
}