import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FirstPage extends JFrame {
  public FirstPage() {
    super("This is First Page");

    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);

    JPanel panel1 = new JPanel();
    // GUI 사이즈 통일해야 할 듯 합니다!
    panel1.setSize(300, 200);

    JButton startBtn = new JButton("시작");

    JLabel label1 = new JLabel();
    label1.setText("레시피 추천 프로그램");

    Dimension frameSize = getSize();
    Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation((windowSize.width - frameSize.width) / 2,
                     (windowSize.height - frameSize.height) / 2);

    this.add(panel1);
    panel1.add(label1);
    panel1.add(startBtn);

    startBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // 여기에 조합추천 페이지를 넣으면 됩니다!
        new SecondPage();
        panel1.setVisible(false);
      }
    });
  }
}
