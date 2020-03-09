package client;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientCore {
	private ButtonListener buttonListener;
	private ClientView view;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private String messageToServer;
	private String messageFromServer;
	private boolean eventFlag;
	private int buttonNumber;
			
	public ClientCore() {
		buttonListener = new ButtonListener(this);
		view = new ClientView();
		view.setButtonListener(buttonListener);
		messageToServer = null;
		messageFromServer = null;		
		eventFlag = false;
		buttonNumber = 0;
		this.connect();
	}
	
	public void connect() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String ipAddrStr = addr.getHostAddress();		
			socket = new Socket(ipAddrStr,8000);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));			
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));				
			while(true) {
				if(socket.isClosed())
					break;				
				if(eventFlag) {
					this.sendMessage();//송신
					this.receiveMessage();//수신
					this.doAction();
					eventFlag = false;					
				}
			}			
			out.close();
			in.close();
			socket.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void sendMessage() {	
		out.println(messageToServer);
		out.flush();//전송
	}
	
	public void receiveMessage() throws IOException {
		messageFromServer = in.readLine();
		System.out.println(messageFromServer);
	}
	
	public void doAction() {
		String groupName;
		if(buttonNumber == 5) {
			if(messageFromServer.equals("success")) {
				groupName = view.getGroupFieldText();
				view.addComboBoxGroup(groupName);
			}
		}
		else if(buttonNumber == 6) {
			if(messageFromServer.equals("success")) {
				groupName = view.getComboBoxGroup();
				view.removeComboBoxGroup(groupName);
			}
		}
		System.out.println("do action");
	}
		
	public void clickStartButton() {
		String groupName = view.getComboBoxGroup();
		messageToServer = "startButton" + ":" + groupName;		
		eventFlag = true;
		buttonNumber = 1;
	}	
	
	public void clickStopButton() {
		String groupName = view.getComboBoxGroup();
		messageToServer = "stopButton" + ":" + groupName;		
		eventFlag = true;
		buttonNumber = 2;
	}
	
	public void clickAddBallButton() {
		String groupName = view.getComboBoxGroup();	
		messageToServer = "addBallButton" + ":" + groupName;		
		eventFlag = true;
		buttonNumber = 3;
	}
	
	public void clickRemoveBallButton() {
		String groupName = view.getComboBoxGroup();
		messageToServer = "removeBallButton" + ":" + groupName;		
		eventFlag = true;
		buttonNumber = 4;
	}
	
	public void clickAddGroupButton() {
		String groupName = view.getGroupFieldText();
		Color groupColor = view.getColorChooser();			
		String red = Integer.toString(groupColor.getRed());
		String blue = Integer.toString(groupColor.getBlue());
		String green = Integer.toString(groupColor.getGreen());
		messageToServer = "addGroupButton" + ":" 
				+ groupName + ":" + red + ":" + green + ":" + blue;			
		buttonNumber = 5;
		eventFlag = true;
		System.out.println(messageToServer);
		//view.addComboBoxGroup(groupName);
	}
	
	public void clickRemoveGroupButton() {
		String groupName = view.getComboBoxGroup();
		messageToServer = "removeGroupButton" + ":" + groupName;
		buttonNumber = 6;
		eventFlag = true;	
		//view.removeComboBoxGroup(groupName);				
	}	
	
	public static void main(String[] args) {
		new ClientCore();	
	}
	
}
