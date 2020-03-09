package server;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ServerCore {	
	private ServerView view;	
	private BallManager ballManager;
	private ServerSocket server;
	private Socket sock;
	private BufferedReader in;
	private String messageFromClient;
	private String messageToClient;
	private PrintWriter out;
	
	public ServerCore() throws IOException {		
		view = new ServerView();			
		ballManager = new BallManager();		
		addBallDefault();
		initConnection();
		connect();
		while(true) {			
			moveBall();				
			drawBall();	
			try {
				Thread.sleep(20);
			}catch(InterruptedException ex) {
			}		
		}	
	}
	
	public void addBallDefault() {
		String group = "red";
		if(!ballManager.getMovingState(group)){
			ballManager.addBall(group);			
			drawBall();
		}
		else
			view.errorDiglog();
	}
	
	public void initConnection() throws IOException {
		server = new ServerSocket(8000);
		System.out.println("Waiting connect...");			
		sock = server.accept();
		InetAddress inetAddr = sock.getInetAddress();			
		System.out.println(inetAddr.getHostAddress() + " connet");
		out = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));//전송
		in = new BufferedReader(new InputStreamReader(sock.getInputStream()));//읽기
	}
	
	public void connect() throws IOException {
		new Thread(()->{
		try {
			while(true) {				
				messageFromClient = in.readLine();
				if(messageFromClient != null) {
					this.receiveMessage(messageFromClient);
					messageFromClient = null;
					out.println(messageToClient);
					out.flush();//전송
					messageToClient = null;
				}
				else
					System.out.println("error");
			}			
		}catch(Exception e) {
			System.out.println(e);
		}
		finally {
			try {
				sock.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}).start();
	}
	
	public void receiveMessage(String messageFromClient){
		String line[] = messageFromClient.split(":");
		String button = line[0];
		if(button.equals("startButton")) {
			System.out.println(line[0]);
			this.startBallMoving(line);			
		}
		else if(button.equals("stopButton")) {
			System.out.println(line[0]);
			this.stopBallMoving(line);
		}
		else if(button.equals("addBallButton")) {
			System.out.println(line[0]);
			this.addBall(line);			
		}
		else if(button.equals("removeBallButton")) {
			System.out.println(line[0]);
			this.removeBall(line);
		}
		else if(button.equals("addGroupButton")) {
			System.out.println(line[0]);
			this.addGroup(line);
		}
		else if(button.equals("removeGroupButton")) {
			System.out.println(line[0]);
			this.removeGroup(line);
		}
		else
			System.out.println("error");
	}	
	
	public void sendMessage(int i) {
		if(i == 1)
			messageToClient = "success";
		else
			messageToClient = "fail";		
	}
	
	public void startBallMoving(String[] line) {		
		String group = line[1];
		if(ballManager.getGroupSize(group) > 0) {
			ballManager.setMovingGroup(group);
			//this.sendMessage(1);
		}
		else {
			view.errorDiglog();
			//this.sendMessage(0);
		}
	}
	
	public void stopBallMoving(String[] line) {		
		String group = line[1];
		if(ballManager.getGroupSize(group) > 0) {
			ballManager.setStopingGroup(group);
			//this.sendMessage(1);
		}
		else {
			view.errorDiglog();
			//this.sendMessage(0);
		}
	}
	
	public void addBall(String[] line) {
		String group = line[1];
		if(!ballManager.getMovingState(group)){
			ballManager.addBall(group);			
			drawBall();
			//this.sendMessage(1);
		}
		else {
			view.errorDiglog();
			//this.sendMessage(0);
		}
	}
	
	public void removeBall(String[] line) {
		String group = line[1];		
		if((!ballManager.getMovingState(group)) && (ballManager.getTotalNum() > 0)) {
			ballManager.removeBall(group);
			drawBall();
			//this.sendMessage(1);
		}
		else {
			view.errorDiglog();
			//this.sendMessage(0);
		}
	}
	
	public void addGroup(String[] line) {
		String groupName = line[1];
		int red = Integer.parseInt(line[2]);
		int green = Integer.parseInt(line[3]);
		int blue = Integer.parseInt(line[4]);
		Color groupColor = new Color(red, green, blue);
		System.out.println("addGroup");
		System.out.println(groupName);
		if(groupName.equals("")) {
			view.errorDiglog();
			this.sendMessage(0);
		}
		else if(ballManager.checkGroupList(groupName)) {
			view.errorDiglog();
			this.sendMessage(0);
		}
		else {
			System.out.println(groupName);
			ballManager.addGroup(groupName, groupColor);
			this.sendMessage(1);			
		}
	}
	
	public void removeGroup(String[] line) {
		String groupName = line[1];		
		if(!ballManager.getMovingState(groupName)) {			
			ballManager.removeGroup(groupName);
			this.sendMessage(1);
		}
		else {			
			view.errorDiglog();
			this.sendMessage(0);
		}
	}
	
	private void drawBall() {
		view.setBallPosition(ballManager.getTotalNum(), ballManager.getAllBallXY(), ballManager.getAllBallRadius(), ballManager.getAllBallColor());			
	}

	private void moveBall() {		
		boolean[] movingGroup = ballManager.getAllMovingState();
		String[] groups = ballManager.getGroupNameList();
		
		for(int i = 0;i < ballManager.getGroupNum();i++) {
			if(movingGroup[i] == true) {
				for(int j = 0;j < ballManager.getGroupSize(groups[i]);j++) {
					int ballX = ballManager.getGroup(groups[i]).getBallX(j);
					int ballY = ballManager.getGroup(groups[i]).getBallY(j);
					int ballSpeed = ballManager.getGroup(groups[i]).getBallSpeed(j);
					int BOX_WIDTH = view.BOX_WIDTH;
					int BOX_HEIGHT = view.BOX_HEIGHT;		
					boolean ballRight = ballManager.getGroup(groups[i]).getBallRight(j);
					boolean ballDown = ballManager.getGroup(groups[i]).getBallDown(j);
					
					if(ballRight) {			
						if(ballX >= BOX_WIDTH) {
							ballManager.getGroup(groups[i]).setBallRight(j, false);
							ballX -= ballSpeed;
						}
						else {
							ballX += ballSpeed;
						}
					}
					else {			
						if(ballX <= 30) {
							ballManager.getGroup(groups[i]).setBallRight(j, true);
							ballX += ballSpeed;
						}
						else {
							ballX -= ballSpeed;
						}
					}
					
					if(ballDown) {			
						if(ballY >= BOX_HEIGHT) {
							ballManager.getGroup(groups[i]).setBallDown(j, false);
							ballY -= ballSpeed;
						}
						else {
							ballY += ballSpeed;
						}
					}
					else {			
						if(ballY <= 30) {
							ballManager.getGroup(groups[i]).setBallDown(j, true);
							ballY += ballSpeed;
						}
						else {
							ballY -= ballSpeed;
						}
					}
					ballManager.getGroup(groups[i]).setBallX(j, ballX);
					ballManager.getGroup(groups[i]).setBallY(j, ballY);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {		
		new ServerCore();
	}	
}
