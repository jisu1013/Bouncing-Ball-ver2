package server;

import java.awt.Color;
import java.util.ArrayList;

public class BallGroup {
	private String groupName;
	private Color groupColor;
	private boolean isMove;
	private ArrayList<Ball> balls;
	
	public BallGroup(String group_, Color c){
		groupName = group_;
		groupColor = c;
		isMove = false;
		balls = new ArrayList<Ball>();
	}
	
	public int getGroupSize() {
		return balls.size();
	}
	
	public String getGroup() {
		return groupName;
	}
	
	public boolean getIsMove() {
		return isMove;
	}
	
	public void setIsMove(boolean isMove_) {
		isMove = isMove_;
	}
	
	public void addBall() {
		//System.out.println(groupColor);
		balls.add(new Ball(groupColor));
	}
	
	public void removeBall() {
		balls.remove(balls.size()-1);
	}	
	
	public int getBallX(int i) {
		return balls.get(i).getX();
	}
	
	public int getBallY(int i) {
		return balls.get(i).getY();
	}
	
	public int getBallSpeed(int i) {
		return balls.get(i).getSpeed();
	}
	
	public int getBallRadius(int i) {
		return balls.get(i).getRadius();
	}
	
	public Color getBallColor(int i) {
		return balls.get(i).getColor();
	}
	
	public boolean getBallRight(int i) {
		return balls.get(i).getRight();
	}
	
	public boolean getBallDown(int i) {
		return balls.get(i).getDown();
	}
	
	public void setBallRight(int i, boolean ballRight) {
		balls.get(i).setRight(ballRight);
	}
	
	public void setBallDown(int i, boolean ballDown) {
		balls.get(i).setDown(ballDown);
	}
	
	public void setBallX(int i, int x) {
		balls.get(i).setX(x);
	}
	
	public void setBallY(int i, int y) {
		balls.get(i).setY(y);
	}
}

