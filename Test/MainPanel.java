import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainPanel extends JFrame {
    public FirstPage first = null;
    public RecommendPage second = null;
 
    public void change(String panelName) { // 패널 1번과 2번 변경 후 재설정
        if (panelName.equals("first")) {
            getContentPane().removeAll();
            getContentPane().add(first);
            revalidate();
            repaint();
        } else {
        	getContentPane().removeAll();
            getContentPane().add(second);
            revalidate();
            repaint();
        }
    }
}