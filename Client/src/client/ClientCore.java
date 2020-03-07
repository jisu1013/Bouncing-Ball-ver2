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
			
	public ClientCore() {
		buttonListener = new ButtonListener(this);
		view = new ClientView();
		view.setButtonListener(buttonListener);
		messageToServer = null;
		messageFromServer = null;		
		eventFlag = false;	
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
		
	public void clickStartButton() {
		String groupName = view.getComboBoxGroup();
		messageToServer = "startButton" + ":" + groupName;		
		eventFlag = true;	
	}	
	
	public void clickStopButton() {
		String groupName = view.getComboBoxGroup();
		messageToServer = "stopButton" + ":" + groupName;		
		eventFlag = true;	
	}
	
	public void clickAddBallButton() {
		String groupName = view.getComboBoxGroup();	
		messageToServer = "addBallButton" + ":" + groupName;		
		eventFlag = true;
	}
	
	public void clickRemoveBallButton() {
		String groupName = view.getComboBoxGroup();
		messageToServer = "removeBallButton" + ":" + groupName;		
		eventFlag = true;		
	}
	
	public void clickAddGroupButton() {
		String groupName = view.getGroupFieldText();
		Color groupColor = view.getColorChooser();			
		String red = Integer.toString(groupColor.getRed());
		String blue = Integer.toString(groupColor.getBlue());
		String green = Integer.toString(groupColor.getGreen());
		messageToServer = "addGroupButton" + ":" 
					+ groupName + ":" + red + ":" + green + ":" + blue;			
		view.addComboBoxGroup(groupName);
		eventFlag = true;	
	}
	
	public void clickRemoveGroupButton() {
		String groupName = view.getComboBoxGroup();
		messageToServer = "removeGroupButton" + ":" + groupName;		
		view.removeComboBoxGroup(groupName);
		eventFlag = true;			
	}	
	
	public static void main(String[] args) {
		new ClientCore();	
	}
	
}
