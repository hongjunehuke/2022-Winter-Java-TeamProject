// 화면 전환 예시 코드 

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

// 처음 뜨는 창 부분
public class Change extends JFrame {

	public Change() {
		super("첫번째 창");
		JPanel jPanel = new JPanel();
		JButton btnl = new JButton("다음 창");
		setSize(300,200);
		jPanel.add(btnl);
		add(jPanel);
		
		Dimension frameSize = getSize();
		 
	    Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
	  //화면 중앙에 띄우기
	    setLocation((windowSize.width - frameSize.width) / 2,
	                (windowSize.height - frameSize.height) / 2);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setVisible(true);
	    
	    btnl.addActionListener(new ActionListener(){
	    	// Override
	    	public void actionPerformed(ActionEvent e) {
	    		new Part1();
	    		setVisible(false);
	    	}
	    });
	}
	public static void main(String[] args) {
		new Change();
	}
}


// 두번째 뜰 창 부분
public class Part1 extends JFrame{
	Part1(){
		super("두번째 창");
		JPanel jPanel = new JPanel();

        jPanel.setBackground(Color.BLUE);

        setSize(300, 200);

        add(jPanel);

        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);		
	}
}
