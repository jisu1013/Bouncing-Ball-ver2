package server;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BallDrawingPanel extends JPanel {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private int[][] ballXY;
	private int[] ballRadius;
	private Color[] ballColor;
	private int ballTotalNum;
	public int BOX_HEIGHT = 450;
	public int BOX_WIDTH = 720;
	
	public void drawBall(int num, int[][] ballXY_, int[] z, Color[] c) {
		ballXY = ballXY_;		
		ballRadius = z;
		ballColor = c;
		ballTotalNum = num;
	}
	
	public void paintComponent(Graphics g) {		
		g.setColor(Color.white);
		g.drawRect(30, 30, BOX_WIDTH, BOX_HEIGHT);
		g.setColor(Color.white);
		g.fillRect(30, 30, BOX_WIDTH, BOX_HEIGHT);		
		for (int i = 0;i < ballTotalNum;i++) {			
			g.setColor(ballColor[i]);
			g.drawOval(ballXY[i][0], ballXY[i][1], ballRadius[i], ballRadius[i]);
			g.fillOval(ballXY[i][0], ballXY[i][1], ballRadius[i], ballRadius[i]);
		}
	}
}
