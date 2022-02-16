import java.awt.event.*;
import javax.swing.*;

public class JCheckBoxTest extends JFrame implements ActionListener {
  JLabel l;
  JCheckBox cb1, cb2, cb3;
  JButton b;

  JCheckBoxTest() {
    l = new JLabel(
        "<html><body text='orange'><h2>주문하실 제품을 골라 주세요^^</h2></html>");
    l.setBounds(50, 50, 300, 20);
    cb1 = new JCheckBox("치즈버거 4000원");
    cb1.setBounds(100, 100, 150, 20);
    cb2 = new JCheckBox("불고기버거 4500원");
    cb2.setBounds(100, 150, 150, 20);
    cb3 = new JCheckBox("콜라 1000원");
    cb3.setBounds(100, 200, 150, 20);
    b = new JButton("주문");
    b.setBounds(100, 250, 80, 30);
    b.addActionListener(this);
    add(l);
    add(cb1);
    add(cb2);
    add(cb3);
    add(b);
    setSize(400, 400);
    setLayout(null);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public void actionPerformed(ActionEvent e) {
    int amount = 0;
    String msg = "";
    if (cb1.isSelected()) {
      amount += 4000;
      msg = "치즈버거: 4000\n";
    }
    if (cb2.isSelected()) {
      amount += 4500;
      msg += "불고기버거: 4500\n";
    }
    if (cb3.isSelected()) {
      amount += 1000;
      msg += "콜라: 1000\n";
    }
    msg += "----------------------\n";
    JOptionPane.showMessageDialog(this, msg + "합계: " + amount);
  }

  public static void main(String[] args) { new JCheckBoxTest(); }
}
