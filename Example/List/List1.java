package javaapplication59;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class List1 extends JFrame {
  String[] fruits = {"apple", "banana", "kiwi",       "mango",     "pear",
                     "peach", "berry",  "strawberry", "blackberry"};
  List1() {
    this.setTitle("This is Sample");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new FlowLayout());

    JList strList = new JList(fruits);
    this.add(strList);

    JList scrollList = new JList(fruits);
    this.add(new JScrollPane(scrollList));

    this.setLocationRelativeTo(null);
    this.setSize(300, 300);
    this.setVisible(true);
  }
  public static void main(String[] args) { new List1(); }
}
