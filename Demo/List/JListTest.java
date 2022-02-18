import java.awt.event.*;
import javax.swing.*;

public class JListTest {
  JListTest() {
    JFrame f = new JFrame("리스트 예제");
    final JLabel lb = new JLabel();
    lb.setSize(500, 100);
    JButton b = new JButton("결과");
    b.setBounds(200, 150, 80, 30);

    final DefaultListModel<String> lm = new DefaultListModel<>();
    lm.addElement("C");
    lm.addElement("C++");
    lm.addElement("Java");
    lm.addElement("PHP");
    final JList<String> ls = new JList<>(lm);
    ls.setBounds(100, 100, 75, 75);

    f.add(ls);
    f.add(b);
    f.add(lb);
    f.setSize(300, 300);
    f.setLayout(null);
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String data = "프로그래밍 언어 선택: " + ls.getSelectedValue();
        lb.setText(data);
      }
    });
  }
  public static void main(String args[]) { new JListTest(); }
}
