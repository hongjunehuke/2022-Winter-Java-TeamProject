import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SecondPage extends JFrame {
  SecondPage() {
    super("This is Second Page");

    JPanel panel2 = new JPanel();
    this.setSize(300, 200);

    Dimension frameSize = getSize();
    Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation((windowSize.width - frameSize.width) / 2,
                     (windowSize.height - frameSize.height) / 2);
    this.add(panel2);

    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
}
