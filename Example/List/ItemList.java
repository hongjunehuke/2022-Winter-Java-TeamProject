package Homework;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;


class Recipe {
	public String id, name, cate, cate2, ingredient, recipe, image;

	Recipe(String id, String name, String cate, String cate2, String ingredient, String recipe, String image){
		this.id = id;
		this.name = name;
		this.cate = cate;
		this.cate2 = cate2;
		this.ingredient = ingredient;	
		this.recipe = recipe;
		this.image = image;
	}
	
	public void printRecipe() {
		System.out.println(id);
		System.out.println(name);
		System.out.println(cate);
		System.out.println(cate2); 
		System.out.println(ingredient);
		System.out.println(recipe);
		System.out.println(image + "\n");
	}
	
	public boolean object_filter(String cate) {
		if (this.cate.equals(cate) || this.cate2.equals(cate)) 
			return true;
		
		else
			return false;
	}
}
	


public class ItemList{
	private final Map<String, ImageIcon> imageMap;
	public String[] Arr;
	public Recipe[] Ra;
	public ArrayList<Integer> idList = new ArrayList<Integer>();	// 현재 id는 문자열로 저장되어있는 상태
	ArrayList<String> nameList = new ArrayList<String>();
	JList list;
		
	public void readData() {
		try{
			Path path = Paths.get("C:\\안민하\\Github\\2022-Winter-Java-TeamProject\\Demo\\DB\\ConvenienceRecipe.txt");
			//Path path = Paths.get("C:\\안민하\\Github\\2022-Winter-Java-TeamProject\\Demo\\DB\\SaladyRecipe.txt");
			Stream<String> lines = Files.lines(path);
			String content = lines.collect(Collectors.joining(System.lineSeparator()));
			Arr = content.split("\t");
			
			Ra = new Recipe[Arr.length/7 - 1];
			for (int j = 7, i = 0; j < Arr.length; i++) {
				Ra[i] = new Recipe(Arr[j++], Arr[j++], Arr[j++], Arr[j++], Arr[j++], Arr[j++], Arr[j++]);
			}
			
			lines.close();
			  
		} catch(IOException e){ e.getStackTrace(); }
		
		for(Recipe rp : Ra) {		// 고민해보기. id값만 저장할지 해당 객체들을 저장할지
			if (rp.object_filter("국/찌개")) {
				idList.add(Integer.parseInt(rp.id));
			}
		}
			
	}
  
	public ItemList() {
		readData();
		
		for (int id : idList) {
			nameList.add(Ra[id-1].name);
		}
		
		//String[] nameList = {"Mario", "Luigi", "Bowser", "Koopa", "Princess"};
		imageMap = createImageMap(nameList);
		list = new JList(nameList.toArray());
		list.setCellRenderer(new MarioListRenderer());
			
		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(400, 400));
			
		JFrame frame = new JFrame();
		frame.add(scroll);
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
		
	public class MarioListRenderer extends DefaultListCellRenderer {
		Font font = new Font("helvitica", Font.BOLD, 18);
			
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value,
                                                 int index, boolean isSelected,
                                                  boolean cellHasFocus) {
			JLabel label = (JLabel)super.getListCellRendererComponent(
					list, value, index, isSelected, cellHasFocus);
			label.setIcon(imageMap.get((String)value));
			label.setHorizontalTextPosition(JLabel.RIGHT);
			label.setFont(font);
			return label;
				
		}
			
	}
		
	private Map<String, ImageIcon> createImageMap(ArrayList<String> list) {
		Map<String, ImageIcon> map = new HashMap<>();
		Image subImg;
		ImageIcon img;
			
		try {
			for (int id : idList) {
				img = new ImageIcon(new URL(Ra[id-1].image));
				subImg = img.getImage();
				subImg = subImg.getScaledInstance(80, 80,  Image.SCALE_SMOOTH);
				map.put(Ra[id-1].name, new ImageIcon(subImg));
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	  
		return map;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { new ItemList(); }
		});
	}
}