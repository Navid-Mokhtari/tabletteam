package app;

//import Canvas_Demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.media.Player;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

import com.sun.jna.NativeLibrary;
import com.sun.jna.Platform;

public class ShowPlayer extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Player player;
	static JFrame myFrame;
	static EmbeddedMediaPlayerComponent vlcPlayer;
	private final JPanel contentPanel = new JPanel();

	public static void main(String[] args) {
		ShowPlayer p = new ShowPlayer();
		p.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	}

	public ShowPlayer() {

		setBounds(0, 0, 1366, 768);
		setTitle("UiA eHelse v1.21");
		setResizable(false);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(contentPanel, BorderLayout.SOUTH);
		

		
		showVLCPlayer();

	
	}

	public JFrame showVLCPlayer() {

		String currentLang = HealthProperties.getProperty("currentLanguage");
		Locale currentLocale = Locale.forLanguageTag(currentLang);
		NativeLibrary.addSearchPath("libvlc", "C:/Program Files/VideoLAN/VLC");
		final String url = HealthProperties.getProperty("videoPath");
		System.out.println(url);
		final EmbeddedMediaPlayerComponent mediaPlayerComponent;
		final JFrame frame = new JFrame();
		frame.setUndecorated(true);
		
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		mediaPlayerComponent.doLayout();
		mediaPlayerComponent.setPreferredSize(new Dimension(1366, 768));

		/*---------------Media Player Button Creation starts----------------*/
		ImageIcon playImg = new ImageIcon(
				MainPage.class.getResource("/pic/play-button-th.png"));
		ImageIcon pauseImg = new ImageIcon(
				MainPage.class.getResource("/pic/pause-button-th.png"));
		ImageIcon stopImg = new ImageIcon(
				MainPage.class.getResource("/pic/stop-button-th.png"));
		ImageIcon exitImg = new ImageIcon(
				MainPage.class.getResource("/pic/exit-button-th.png"));

		final JButton play = new JButton(playImg);
		play.setSize(5, 5);
		play.setToolTipText("Play");
		final JButton pause = new JButton(pauseImg);
		pause.setSize(5, 5);
		pause.setToolTipText("Pause");
		final JButton stop = new JButton(stopImg);
		stop.setSize(5, 5);
		stop.setToolTipText("Stop");
		final JButton exit = new JButton(exitImg);
		stop.setSize(5, 5);
		stop.setToolTipText("Exit");
		/*-------------Media player Button Creation Ends-------*/

		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent play1) {
				System.out.println("playing ........");
				// mediaPlayerComponent.getMediaPlayer().pause();
				mediaPlayerComponent.getMediaPlayer().play();
				play.setVisible(false);
				pause.setVisible(true);

			}
		});

		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent stop) {

				mediaPlayerComponent.getMediaPlayer().stop();
				pause.setVisible(false);
				play.setVisible(true);
				//frame.setVisible(false);

			}

		});

		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent pause1) {

				mediaPlayerComponent.getMediaPlayer().pause();
				pause.setVisible(false);
				play.setVisible(true);

			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exit) {

				mediaPlayerComponent.getMediaPlayer().stop();
				//pause.setVisible(false);
				//play.setVisible(true);
				frame.setVisible(false);

			}

		});
		play.setVisible(false);

		/*-----------------Button Menu ---------------------------------*/
		JPanel menu = new JPanel();
		menu.add(play);
		menu.add(pause);
		menu.add(stop);
		menu.add(exit);
		frame.add(mediaPlayerComponent, BorderLayout.EAST);
		frame.add(menu, BorderLayout.SOUTH);
		frame.setLocation(0, 0);
		frame.setSize(1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		mediaPlayerComponent.getMediaPlayer().playMedia(url);
		return frame;

	}

}

