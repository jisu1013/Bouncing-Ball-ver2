package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ButtonListener implements ActionListener{

	private ClientCore core;
		
	public ButtonListener(ClientCore core_) {
		core = core_;
	}
		
	public void startButton() {
		core.clickStartButton();	
	}
	
	public void stopButton() {
		core.clickStopButton();
	}
	
	public void addBallButton() {
		core.clickAddBallButton();
	}
	
	public void removeBallButton() {
		core.clickRemoveBallButton();
	}
	
	public void groupAddButton() {
		core.clickAddGroupButton();
	}
	
	public void groupRemoveButton() {
		core.clickRemoveGroupButton();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();		
		if(b.getText().equals("Start"))
			this.startButton();
		
		else if(b.getText().equals("Stop"))
			this.stopButton();
		
		else if(b.getText().equals("Add"))
			this.addBallButton();
		
		else if (b.getText().equals("Remove"))
			this.removeBallButton();
		
		else if(b.getText().equals("Add Group"))
			this.groupAddButton();
		
		else if(b.getText().equals("Remove Group"))
			this.groupRemoveButton();
		
		else
			System.out.println("error");				
	}	
}

