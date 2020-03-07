package client;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class ClientView {	
	private String[] defaultGroups = {"red", "blue", "green"};
	private ArrayList<String> groupNames;
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JButton start = new JButton("Start");
	private JButton stop = new JButton("Stop");
	private JButton add = new JButton("Add");
	private JButton remove = new JButton("Remove");
	private JButton addGroup = new JButton("Add Group");
	private JButton removeGroup = new JButton("Remove Group");
	private JComboBox<String> groupBox = new JComboBox<String>(defaultGroups);
	private JTextField groupNameField = new JTextField();
	
	public ClientView() {
		groupNames = new ArrayList<String>();
		groupNames.add("red");
		groupNames.add("blue");
		groupNames.add("green");
		frame.setTitle("Bouncing Ball Game Button Bar");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel,BorderLayout.CENTER);
		panel.setLayout(null);		
		start.setBounds(20,15,80,30);
		stop.setBounds(110,15,80,30);
		add.setBounds(200,15,80,30);
		remove.setBounds(290,15,80,30);
		groupBox.setBounds(380,15,110,30);
		groupNameField.setBounds(500,15,120,30);
		addGroup.setBounds(630,15,100,30);
		removeGroup.setBounds(740,15,120,30);		
		panel.add(start);
		panel.add(stop);
		panel.add(add);
		panel.add(remove);
		panel.add(groupBox);
		panel.add(groupNameField);
		panel.add(addGroup);
		panel.add(removeGroup);		
		frame.setSize(900,100);
		frame.setVisible(true);
	}
	
	public String getComboBoxGroup() {
		String group = groupBox.getSelectedItem().toString();		
		return group;
	}
	
	public void removeComboBoxGroup(String group) {
		groupBox.removeItem(group);
	}
	
	public void addComboBoxGroup(String group) {
		groupBox.addItem(group);
	}
	
	public boolean checkComboBoxGroup(String group) {
		boolean check = false;
		for(int i = 0;i < groupBox.getItemCount();i++) {
			if(groupBox.getItemAt(i).equals(group))
				check = true;
			else
				check = false;
		}
		
		return check;
	}
	
	public String getGroupFieldText() {
		String groupName = groupNameField.getText();		
		return groupName;
	}
	
	public Color getColorChooser() {
		Color c = JColorChooser.showDialog(null, "Group Color", Color.red);
		if(c == null) {
			c = Color.white;
		}		
		return c;
	}
	
	public void errorDiglog() {
		JOptionPane.showMessageDialog(null, "error");
	}
	
	public void setButtonListener(ButtonListener listener) {
		start.addActionListener(listener);
		stop.addActionListener(listener);
		add.addActionListener(listener);
		remove.addActionListener(listener);
		addGroup.addActionListener(listener);
		removeGroup.addActionListener(listener);
	}		
}


