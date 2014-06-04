package app;

//import Canvas_Demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.media.Player;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.windows.WindowsCanvas;

import com.sun.jna.NativeLibrary;
import com.sun.jna.Platform;

public class ShowPlayer extends JDialog {

	static Player player;
	static JFrame myFrame;
	static EmbeddedMediaPlayerComponent vlcPlayer;
	private final JPanel contentPanel = new JPanel();

	public static void main(String[] args) {
		ShowPlayer p = new ShowPlayer();
		p.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// p.setVisible(true);
		player.start();

	}

	/*
	 * public ShowPlayer(){
	 * 
	 * setBounds(0, 0, 1366, 768); setTitle("UiA eHelse v1.21");
	 * setResizable(false);
	 * 
	 * // CREATE FRAME. final JFrame myFrame = new JFrame();
	 * myFrame.setVisible(true); myFrame.setSize(1366, 768);
	 * myFrame.addWindowListener(new WindowAdapter() { public void
	 * windowClosing(WindowEvent event) { vlcPlayer.stopped((MediaPlayer)
	 * player);
	 * 
	 * //player.stop(); //player.close(); myFrame.dispose(); } });
	 * 
	 * // CREATE PLAYER. //String filename =
	 * "C:\\Users\\annadi\\Ram\\tabletteam\\TEST.avi"; //String filename =
	 * "C:\\Users\\annadi\\Ram\\tabletteam\\TEST1.avi"; //String filename =
	 * "C:\\Users\\annadi\\Ram\\tabletteam\\Bool.avi"; //String filename =
	 * "C:\\Users\\annadi\\Downloads\\brahmi.avi"; //String filename =
	 * "C:\\Users\\annadi\\Downloads\\Test3.avi"; String filename =
	 * "C:\\Users\\annadi\\Documents\\My CamStudio Videos\\11.avi";
	 * System.out.println(filename); try { player = Manager.createPlayer(new
	 * MediaLocator("file:///" + filename)); } catch (NoPlayerException e1) { //
	 * TODO Auto-generated catch block System.out.println("2");
	 * e1.printStackTrace(); } catch (IOException e1) { // TODO Auto-generated
	 * catch block System.out.println("1"); e1.printStackTrace(); }
	 * player.addControllerListener(new ControllerAdapter() { public void
	 * controllerUpdate(ControllerEvent event) { if (event instanceof
	 * RealizeCompleteEvent) {
	 * 
	 * myFrame.add("Center", player.getVisualComponent()); myFrame.add("South",
	 * player.getControlPanelComponent()); myFrame.validate(); } } }); }
	 */

	public ShowPlayer() {

		setBounds(0, 0, 1366, 768);
		setTitle("UiA eHelse v1.21");
		setResizable(false);

		/*
		 * String currentLang = HealthProperties.getProperty("currentLanguage");
		 * Locale currentLocale = Locale.forLanguageTag(currentLang); final
		 * ResourceBundle currentLanguage = ResourceBundle.getBundle("language",
		 * currentLocale);
		 */
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel buttonPane = new JPanel();
		JDialog playerPane = new JDialog();

		JFrame frame = showVLCPlayer();
		playerPane.add(frame);
		// buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		// playerPane.setLayout(new FlowLayout(FlowLayout.CENTER));

		contentPanel.add(playerPane);
		getContentPane().add(contentPanel, BorderLayout.NORTH);

		// getContentPane().add(playerPane, BorderLayout.CENTER);
		getContentPane().add(buttonPane, BorderLayout.NORTH);

	}

	public void stopPlayer(EmbeddedMediaPlayerComponent mediaPlayerComponent) {
		mediaPlayerComponent.getMediaPlayer().stop();
	}

	public JFrame showVLCPlayer() {

		String currentLang = HealthProperties.getProperty("currentLanguage");
		Locale currentLocale = Locale.forLanguageTag(currentLang);
		final ResourceBundle currentLanguage = ResourceBundle.getBundle(
				"language", currentLocale);

		NativeLibrary.addSearchPath("libvlc", "C:/Program Files/VideoLAN/VLC");
		NativeLibrary.addSearchPath("libvlc",
				"C:/Program Files (x86)/VideoLAN/VLC");
		System.out.println(Platform.is64Bit());
		// final String url = "C:/Users/annadi/Downloads/Bool.avi";
		final String url = "D:\\Films\\Akademiya.Vampirov.2014.D.HDRip.1400MB.avi";
		// C:\Users\annadi\Ram\tabletteam
		// Canvas_Demo d = new Canvas_Demo(url);
		// String u = d.Canvas_Demo1(url);
		// System.out.println(u);

		System.out.println(url);
		final EmbeddedMediaPlayerComponent mediaPlayerComponent;
		// final JFrame frame = new JFrame("vlcj Tutorial");
		final JFrame frame = new JFrame();
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		mediaPlayerComponent.doLayout();
		mediaPlayerComponent.setPreferredSize(new Dimension(1366, 768));
		// Canvas canvas = mediaPlayerComponent.getVideoSurface();

		JMenuBar menubar = new JMenuBar();
		// Image playImage;
		JPanel p = new JPanel(new BorderLayout());
		JButton b = new JButton();
		b.setBackground(Color.black);
		ImageIcon img = new ImageIcon("/pic/play.png");
		b.setIcon(img);

		ImageIcon playImg = new ImageIcon("/pic/play.png");

		ImageIcon pauseImg = new ImageIcon("/pic/play.png");
		JButton stop = new JButton("stop");
		JButton j = new JButton();
		stop.setFont(new Font("Tahoma", Font.BOLD, 30));
		// stop.setBackground(Color.BLUE);

		JButton play = new JButton(playImg);
		play.setSize(5, 5);
		// play.setFont(new Font("Tahoma", Font.BOLD, 30));
		JButton pause = new JButton(pauseImg);
		pause.setSize(5, 5);

		// pause.setFont(new Font("Tahoma", Font.BOLD, 30));
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent play) {
				// frame.setVisible(false);
				// stopPlayer(mediaPlayerComponent)
				// new EmbeddedMediaPlayerComponent().getMediaPlayer().stop();
				mediaPlayerComponent.getMediaPlayer().play();
			}
		});

		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent stop) {
				// frame.setVisible(false);
				// stopPlayer(mediaPlayerComponent)
				// new EmbeddedMediaPlayerComponent().getMediaPlayer().stop();
				mediaPlayerComponent.getMediaPlayer().stop();
				frame.setVisible(false);

			}

		});

		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent pause) {
				// frame.setVisible(false);
				// stopPlayer(mediaPlayerComponent)
				// new EmbeddedMediaPlayerComponent().getMediaPlayer().stop();
				mediaPlayerComponent.getMediaPlayer().pause();
			}
		});

		menubar.add(play);
		menubar.add(pause);
		menubar.add(stop);
		menubar.setSize(WIDTH, HEIGHT);
		JPanel menu = new JPanel();
		menu.add(b);
		menu.add(pause);
		menu.add(stop);
		// mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		// mediaPlayerComponent.setLayout(mgr)
		// frame.setLayout(new GridLayout(2, 1));

		frame.add(mediaPlayerComponent, BorderLayout.EAST);
		frame.add(menu, BorderLayout.SOUTH);
		// frame.setContentPane(mediaPlayerComponent.getVideoSurface().getParent());

		// frame.setContentPane(contentPane)
		// frame.setContentPane(canvas.getParent());
		// frame.setJMenuBar(menubar);
		frame.setLocation(0, 0);
		// frame.setl
		// frame.setSize(1050, 600);
		frame.setSize(1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		mediaPlayerComponent.getMediaPlayer().playMedia(url);
		return frame;

	}

}
