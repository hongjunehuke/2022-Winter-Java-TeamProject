package GUIList;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AwtListControl {
	private Frame mainFrame;
	private Label headerLabel;
	private Label statusLabel;
	private Panel controlPanel, buttonPanel;

	public AwtListControl() {
		prepareGUI();
	}

	public static void main(String[] args) {
		AwtListControl awtControlDemo = new AwtListControl();
		awtControlDemo.showList();
	}

	private void prepareGUI() {
		// Frame 에 대한 셋팅
		mainFrame = new Frame("Java AWT 샘플");
		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(4, 1));
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		// 상단에 있는 라벨
		headerLabel = new Label();
		headerLabel.setAlignment(Label.CENTER);
		headerLabel.setText("Control Test : List");

		// 하단 상태값 라벨
		statusLabel = new Label();
		statusLabel.setText("Status Lable");
		statusLabel.setAlignment(Label.CENTER);
		statusLabel.setSize(350, 100);

		controlPanel = new Panel();
		controlPanel.setLayout(new FlowLayout());
		
		buttonPanel = new Panel();
		buttonPanel.setLayout(new FlowLayout());

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(buttonPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}

	private void showList() {

		
		final List fruitList = new List(4, false);
		fruitList.add("감자");
		fruitList.add("고구마");
		fruitList.add("보리");
		fruitList.add("배추");

		final List vegetableList = new List(4, true);
		vegetableList.add("감");
		vegetableList.add("배");
		vegetableList.add("사과");
		vegetableList.add("수박");

		Button showButton = new Button("선택한값보기");
		Button deleteButton = new Button("선택한삭제");
		
		showButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data = "첫번째 선택한값 : " + 
						fruitList.getItem(
								fruitList.getSelectedIndex());
				
				data += ", 두번째 선택한값 : ";
				for (String vegetable : vegetableList.getSelectedItems()) {
					data += vegetable + " ";
				}
				statusLabel.setText(data);
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 단일 리스트 삭제 
				fruitList.remove(fruitList.getSelectedIndex());
				
				// 멀티 선택 리스트 삭제 
				int delCount = 0;
				for (int pos : vegetableList.getSelectedIndexes()) {
					vegetableList.remove(pos - delCount);
					delCount++;
				}
			}
		});

		controlPanel.add(fruitList);
		controlPanel.add(vegetableList);
		buttonPanel.add(showButton);
		buttonPanel.add(deleteButton);

		mainFrame.setVisible(true);
	}
}
