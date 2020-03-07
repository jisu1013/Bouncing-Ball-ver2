package server;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BallManager {
	
	private Map<String, BallGroup> ballGroups;
	private ArrayList<String> groupNames;
	
	public BallManager() {
		ballGroups = new HashMap<>();
		groupNames = new ArrayList<String>();
		ballGroups.put("red", new BallGroup("red",Color.red));
		ballGroups.put("green", new BallGroup("green", Color.green));
		ballGroups.put("blue", new BallGroup("blue", Color.blue));
		this.updateGroupNames();		
	}
	
	public void addGroup(String groupName, Color c) {
		ballGroups.put(groupName, new BallGroup(groupName, c));		
		this.updateGroupNames();
	}
	
	public void removeGroup(String groupName) {
		ballGroups.remove(groupName);		
		this.updateGroupNames();
	}
	
	public BallGroup getGroup(String group) {		
		return ballGroups.get(group);
	}
	
	public String[] getGroupNameList() {
		String[] list = new String[ballGroups.size()];
		for(int i = 0;i < ballGroups.size();i++) {
			list[i] = groupNames.get(i);			
		}	
		
		return list;
	}
	
	public void updateGroupNames() {
		groupNames.clear();
		for(String key : ballGroups.keySet()) {
			groupNames.add(key);		
		}
	}
	
	public void setMovingGroup(String group) {
		ballGroups.get(group).setIsMove(true);
	}
	
	public void setStopingGroup(String group) {
		ballGroups.get(group).setIsMove(false);
	}
	
	public boolean checkGroupList(String group) {
		String[] list = this.getGroupNameList();
		boolean check = false;
		for(int i=0;i < list.length;i++) {
			if(list[i].equals(group))
				check = true;
			else
				check = false;
		}
		
		return check;
	}
	
	public boolean[] getAllMovingState() {
		boolean[] z = new boolean[ballGroups.size()];
		for(int i = 0;i < ballGroups.size();i++)
			z[i] = ballGroups.get(groupNames.get(i)).getIsMove();
		
		return z;
	}
	
	public boolean checkAllMovingState() {
		boolean z = false;
		for(int i = 0;i < ballGroups.size();i++) {
			if(ballGroups.get(groupNames.get(i)).getIsMove())
				z = true;
		}
		
		return z;
	}
	
	public boolean getMovingState(String group) {
		boolean z;
		z = ballGroups.get(group).getIsMove();
		
		return z;
	}	
	
	public void addBall(String group) {
		ballGroups.get(group).addBall();
	}
	
	public void removeBall(String group) {
		if(ballGroups.get(group).getGroupSize() > 0)
			ballGroups.get(group).removeBall();	
	}	
	
	public int[][] getAllBallXY() {
		int size = this.getTotalNum();
		int count = 0;
		int[][] xy = new int[size][2];
		
		for (int i = 0;i < ballGroups.size();i++) {
			for(int j=0;j < ballGroups.get(groupNames.get(i)).getGroupSize();j++) {
				xy[count][0] = ballGroups.get(groupNames.get(i)).getBallX(j);
				xy[count][1] = ballGroups.get(groupNames.get(i)).getBallY(j);
				count++;
			}
		}
		
		return xy;
	}
	
	public int[] getAllBallRadius() {
		int size = this.getTotalNum();
		int count = 0;
		int[] z = new int[size];

		for(int i = 0;i < ballGroups.size();i++) {
			for(int j=0;j < ballGroups.get(groupNames.get(i)).getGroupSize();j++) {
				z[count] = ballGroups.get(groupNames.get(i)).getBallRadius(j);
				count++;
			}
		}		
		
		return z;
	}
	
	public Color[] getAllBallColor() {
		int size = this.getTotalNum();
		Color[] c = new Color[size];
		int count = 0;
		for(int i = 0;i < ballGroups.size();i++) {
			for(int j = 0;j < ballGroups.get(groupNames.get(i)).getGroupSize();j++) {
				c[count] = ballGroups.get(groupNames.get(i)).getBallColor(j);
				count++;
			}
		}
		
		return c;
	}
	
	public int getGroupNum() {
		return ballGroups.size();
	}
	
	public int getGroupSize(String group) {		
		return ballGroups.get(group).getGroupSize();
	}
	
	public int getTotalNum() {
		int size = 0;
		for(int i = 0;i < ballGroups.size(); i++) {
			size += ballGroups.get(groupNames.get(i)).getGroupSize();
		}
		
		return size;
	}	
}
