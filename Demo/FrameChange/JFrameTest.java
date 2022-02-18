import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
@SuppressWarnings("serial")
class JPanel01 extends JPanel { // 1번째 패널
 
    private JButton jButton1;
    private JButton jButton2;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextArea jTextArea1;
    private JPanelTest win;
 
    public JPanel01(JPanelTest win) {
        this.win = win;
        setLayout(null);
 
        jButton1 = new JButton("버튼1");
        jButton1.setSize(70, 20);
        jButton1.setLocation(10, 10);
        add(jButton1);
 
        jTextArea1 = new JTextArea();
 
        jScrollPane1 = new JScrollPane(jTextArea1);
        jScrollPane1.setSize(200, 150);
        jScrollPane1.setLocation(10, 40);
        add(jScrollPane1);
 
        jButton1.addActionListener(new MyActionListener_1());
        
        
        
        jButton2 = new JButton("버튼2");
        jButton2.setSize(70, 20);
        jButton2.setLocation(100, 10);
        add(jButton2);
 
        jTextArea1 = new JTextArea();
 
        jScrollPane2 = new JScrollPane(jTextArea1);
        jScrollPane2.setSize(200, 150);
        jScrollPane2.setLocation(10, 40);
        add(jScrollPane2);
 
        jButton2.addActionListener(new MyActionListener_2());
    }
 
    class MyActionListener_1 implements ActionListener { // 버튼 키 눌리면 패널 2번 호출
        @Override
        public void actionPerformed(ActionEvent e) {
            win.change("panel03");
        }
    }
    
    class MyActionListener_2 implements ActionListener { // 버튼 키 눌리면 패널 3번 호출
        @Override
        public void actionPerformed(ActionEvent e) {
            win.change("panel02");
        }
    }
}
 
@SuppressWarnings("serial")
class JPanel02 extends JPanel { // 2번째 패널
    private JTextField textField;
    private JPasswordField passwordField;
    private JPanelTest win;
 
    public JPanel02(JPanelTest win) {
        setLayout(null);
        this.win = win;
        JLabel lblLbl = new JLabel("아이디:");
        lblLbl.setBounds(31, 40, 67, 15);
        add(lblLbl);
 
        textField = new JTextField();
        textField.setBounds(123, 40, 116, 21);
        add(textField);
        textField.setColumns(10);
 
        JLabel lblLbl_1 = new JLabel("암호:");
        lblLbl_1.setBounds(31, 84, 67, 15);
        add(lblLbl_1);
 
        passwordField = new JPasswordField();
        passwordField.setBounds(123, 84, 116, 21);
        add(passwordField);
 
        JButton btn = new JButton("버튼");
        btn.setSize(70, 20);
        btn.setLocation(10, 10);
        add(btn);
        btn.addActionListener(new MyActionListener());
    }
 
    class MyActionListener implements ActionListener { // 버튼 키 눌리면 패널 1번 호출
        @Override
        public void actionPerformed(ActionEvent e) {
            win.change("panel01");
        }
    }
}

@SuppressWarnings("serial")
class JPanel03 extends JPanel { // 3번째 패널
    private JPanelTest win;
 
    public JPanel03(JPanelTest win) {
        setLayout(null);
        this.win = win;
 
        JButton btn = new JButton("뒤로가기");
        btn.setSize(70, 20);
        btn.setLocation(10, 10);
        add(btn);
        btn.addActionListener(new MyActionListener());
    }
 
    class MyActionListener implements ActionListener { // 버튼 키 눌리면 패널 1번 호출
        @Override
        public void actionPerformed(ActionEvent e) {
            win.change("panel01");
        }
    }
}
 
 
@SuppressWarnings("serial")
class JPanelTest extends JFrame {
 
    public JPanel01 jpanel01 = null;
    public JPanel02 jpanel02 = null;
    public JPanel03 jpanel03 = null;
 
    public void change(String panelName) { // 패널 1번과 2번 변경 후 재설정
 
        if (panelName.equals("panel01")) {
            getContentPane().removeAll();
            getContentPane().add(jpanel01);
            revalidate();
            repaint();
        } else if(panelName.equals("panel02")) {
            getContentPane().removeAll();
            getContentPane().add(jpanel02);
            revalidate();
            repaint();
        } else {
        	getContentPane().removeAll();
            getContentPane().add(jpanel03);
            revalidate();
            repaint();
        }
    }
 
}
 
public class JFrameTest {
    public static void main(String[] args) {
 
        JPanelTest win = new JPanelTest();
 
        win.setTitle("frame test");
        win.jpanel01 = new JPanel01(win);
        win.jpanel02 = new JPanel02(win);
        win.jpanel03 = new JPanel03(win);
 
        win.add(win.jpanel01);
        win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        win.setSize(300, 300);
        win.setVisible(true);
    }
}
