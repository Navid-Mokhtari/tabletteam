package app;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.media.ControllerAdapter;
import javax.media.ControllerEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.RealizeCompleteEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.media.Player;
public class ShowPlayer_old extends JDialog {

	static Player player;
	static JFrame myFrame;

	public static void main(String[] args) {
ShowPlayer_old p = new ShowPlayer_old();
p.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//p.setVisible(true);
		player.start();

	}
	
	public ShowPlayer_old(){
		
		
		setBounds(0, 0, 1366, 768);
		setTitle("UiA eHelse v1.21");
		setResizable(false);
		
		// CREATE FRAME.
		final JFrame myFrame = new JFrame();
		myFrame.setVisible(true);
		myFrame.setSize(1366, 768);
		myFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				player.stop();
				player.close();
				myFrame.dispose();
			}
		});

		// CREATE PLAYER.
		String filename = "C:\\Users\\united4ehealth\\git\\tabletteam\\TEST1.avi";
		System.out.println(filename);
		try {
			player = Manager.createPlayer(new MediaLocator("file:///"
					+ filename));
		} catch (NoPlayerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		player.addControllerListener(new ControllerAdapter() {
			public void controllerUpdate(ControllerEvent event) {
				if (event instanceof RealizeCompleteEvent) {
					
					myFrame.add("Center", player.getVisualComponent());
					myFrame.add("South", player.getControlPanelComponent());
					myFrame.validate();
				}
			}
		});
	}

}
