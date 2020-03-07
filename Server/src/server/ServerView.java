package server;

import java.awt.*;
import javax.swing.*;

public class ServerView {
	public int BOX_HEIGHT = 450;
	public int BOX_WIDTH = 720;
	private JFrame frame = new JFrame();
	private BallDrawingPanel panel = new BallDrawingPanel();	
	
	public ServerView() {					
		frame.setTitle("Bouncing Ball Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel,BorderLayout.CENTER);
		panel.setLayout(null);		
		frame.setSize(800,600);
		frame.setVisible(true);
	}
	
	public void errorDiglog() {
		JOptionPane.showMessageDialog(null, "error");
	}
	
	public void setBallPosition(int num, int[][] xy, int[] z, Color[] c) {
		panel.drawBall(num,xy,z,c);
		frame.repaint();
	}	
}

