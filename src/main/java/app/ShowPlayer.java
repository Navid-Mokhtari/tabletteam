package app;

//import Canvas_Demo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Locale;

import javax.media.Player;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import com.sun.jna.NativeLibrary;
import com.sun.jna.Platform;

public class ShowPlayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*public static void main(String[] args) {
		ShowPlayer p = new ShowPlayer();
		p.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	}*/

	public ShowPlayer() {

		final JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setLocation(0, 0);
		frame.setBounds(MainPage.getWindowSize());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent event) {
				try {
					showVLCPlayer(frame);
					frame.revalidate();
					frame.repaint();
				} catch (URISyntaxException e) {
					frame.setVisible(false);
					e.printStackTrace();
				}
			}
		});
		frame.setVisible(true);
	}

	public void showVLCPlayer(final JFrame frame) throws URISyntaxException {

		String currentLang = HealthProperties.getProperty("currentLanguage");
		Locale currentLocale = Locale.forLanguageTag(currentLang);

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

		NativeLibrary.addSearchPath("libvlc", "C:/Program Files/VideoLAN/VLC");
		NativeLibrary.addSearchPath("libvlc", "C:/Program Files (x86)/VideoLAN/VLC");
		//final String url = Paths.get(getClass().getResource("/manual.avi").toURI()).toString();
		final String url = new File(new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile(), HealthProperties.getProperty("videoPath")).getPath();
		System.out.println(url);

		final int PROGRESS_BAR_STEPS = 800;
		final JProgressBar progressBar = new JProgressBar(0, PROGRESS_BAR_STEPS);
		progressBar.setMaximumSize(new Dimension(2000, 2000));
		progressBar.setStringPainted(true);

		final EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		//mediaPlayerComponent.doLayout();
		//mediaPlayerComponent.setPreferredSize(new Dimension(1366, 728));
		mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
			@Override
			public void positionChanged(MediaPlayer mediaPlayer, float newPosition) {
				progressBar.setValue((int)(newPosition*PROGRESS_BAR_STEPS));
				int playedDuration = (int) (newPosition*mediaPlayer.getLength()/1000);
				int length = (int) (mediaPlayer.getLength()/1000);
				progressBar.setString(formatTime(playedDuration)+"/"+formatTime(length));
			}

			@Override
			public void finished(MediaPlayer mediaPlayer) {
				mediaPlayer.stop();
				pause.setVisible(false);
				play.setVisible(true);
				progressBar.setValue(0);
				progressBar.setString("");
			}

		});
		progressBar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				EmbeddedMediaPlayer mediaPlayer = mediaPlayerComponent.getMediaPlayer();
				if (mediaPlayer.isSeekable())
				{
					float ratio = (float)e.getPoint().x/progressBar.getWidth();
					mediaPlayer.setPosition(ratio);

					progressBar.setValue((int)(ratio*PROGRESS_BAR_STEPS));
					int playedDuration = (int) (ratio*mediaPlayer.getLength()/1000);
					int length = (int) (mediaPlayer.getLength()/1000);
					progressBar.setString(formatTime(playedDuration)+"/"+formatTime(length));
				}
			}
		});

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
				progressBar.setValue(0);
				progressBar.setString("");

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
		menu.setLayout(new BoxLayout(menu, BoxLayout.X_AXIS));
		menu.add(play);
		menu.add(pause);
		menu.add(progressBar);
		menu.add(stop);
		menu.add(exit);
		frame.add(mediaPlayerComponent, BorderLayout.CENTER);
		frame.add(menu, BorderLayout.SOUTH);

		mediaPlayerComponent.getMediaPlayer().playMedia(url);

	}

	static private String formatTime(int seconds)
	{
		return String.format("%d:%02d", seconds/60, seconds%60);
	}
}

