package server;

import java.awt.Color;
import java.util.Random;

public class Ball {
	private int radius;
	private int x;
	private int y;
	private int speed;
	private boolean right;
	private boolean down;
	private Color color;
	
	Random random = new Random();
	
	public Ball(Color c) {
		radius = 30;
		x = random.nextInt(690) + 30;
		y = random.nextInt(420) + 30;
		speed = random.nextInt(5) + 4;
		right  = random.nextBoolean();
		down = random.nextBoolean();
		color = c;		
	}
	
	public boolean getRight() {
		return right;
	}
	
	public void setRight(boolean ballRight) {
		right = ballRight;
	}
	
	public boolean getDown() {
		return down;
	}
	
	public void setDown(boolean ballDown) {
		down = ballDown;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int ballX) {
		x = ballX;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int ballY) {
		y = ballY;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int BallSpeed) {
		speed = BallSpeed;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int BallRadius) {
		radius = BallRadius;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color_) {
		color = color_;
	}
}