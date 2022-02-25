import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class SecondPage extends JFrame {
  SecondPage() {
    super("This is Second Page");

    JPanel panel2 = new JPanel();
    this.setSize(600, 400);

    Dimension frameSize = getSize();
    Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation((windowSize.width - frameSize.width) / 2,
                     (windowSize.height - frameSize.height) / 2);
    this.add(panel2);

    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
}
