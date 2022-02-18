import java.awt.event.*;
import javax.swing.*;

public class Demo extends JFrame implements ActionListener {
  JLabel l;
  JCheckBox cb1, cb2;
  JButton b;

  Demo() {
    l = new JLabel("Choose Options");
    l.setBounds(50, 50, 300, 20);
    cb1 = new JCheckBox("Sweet Taste");
    cb1.setBounds(100, 100, 150, 20);
    cb2 = new JCheckBox("Hot Taste");
    cb2.setBounds(100, 150, 150, 20);

    b = new JButton("Confirmed");
    b.setBounds(100, 250, 100, 30);
    b.addActionListener(this);
    add(l);
    add(cb1);
    add(cb2);
    add(b);
    setSize(400, 400);
    setLayout(null);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public void actionPerformed(ActionEvent e) {
    String message;
    if (cb1.isSelected() && cb2.isSelected()) {
      message = "It's Hot and Sweet!.";
    } else if (cb1.isSelected() && !cb2.isSelected()) {
      message = "It's Sweet!.";
    } else if (!cb1.isSelected() && cb2.isSelected()) {
      message = "Its' Hot.";
    } else {
      message = "Didn't choose any options.";
    }

    JOptionPane.showMessageDialog(this, message);
  }

  public static void main(String[] args) { new Demo(); }
}
