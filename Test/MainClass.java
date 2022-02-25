import javax.swing.JPanel;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class MainClass extends JPanel{
	public static void main(String[] args) {
		MainPanel mp = new MainPanel();
		mp.first = new FirstPage(mp);
		mp.second = new RecommendPage(mp);
		
		mp.add(mp.first);
		mp.setBounds(100, 100, 600, 400);
		mp.setVisible(true);
		mp.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
}
