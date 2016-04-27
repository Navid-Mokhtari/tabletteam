package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.media.Player;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.eclipse.swt.internal.win32.OS;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MainPage implements DatabaseUpdateListener {

	private static JFrame frmUiaEhelse;

	// Initializing labels for sent status

	SendUnsent data = new SendUnsent();
	static Player player;
	static JFrame myFrame;
	private JLabel lblLastMeasurement;
	private JLabel lblLastQuestionnaire;
	private JLabel lblLastCAT;
	private static Rectangle windowSize;
	public static Rectangle getWindowSize() { return windowSize; }


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frmUiaEhelse.setVisible(true);
					NativeInterface.open();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		HealthProperties healthProperties = new HealthProperties();
		healthProperties.loadProperties();

		// Get system DPI (scaling level: 100% ~ 96dpi)
		int hDC = OS.GetDC(0);
		int verticalDPI = OS.GetDeviceCaps(hDC, OS.LOGPIXELSY);
		// SEB Taskbar is 40px on an unscaled display
		int taskbarHeight = 40*verticalDPI/96;

		Toolkit tk = Toolkit.getDefaultToolkit();
		windowSize = new Rectangle(0, 0, tk.getScreenSize().width, tk.getScreenSize().height-taskbarHeight);

		//		data.sendUnsentValues();
		initialize();
		readFromDB();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		String currentLang = HealthProperties.getProperty("currentLanguage");
		Locale currentLocale = Locale.forLanguageTag(currentLang);
		final ResourceBundle currentLanguage = ResourceBundle.getBundle(
				"language", currentLocale);

		frmUiaEhelse = new JFrame();
		frmUiaEhelse.setIconImage(Toolkit.getDefaultToolkit().getImage(
				MainPage.class.getResource("/pic/Doctor-icon2.png")));
		frmUiaEhelse.setTitle("UiA eHelse v1.21b");
		frmUiaEhelse.setResizable(false);
		frmUiaEhelse.setBounds(windowSize);
		frmUiaEhelse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUiaEhelse.setUndecorated(true);

		frmUiaEhelse.setLayout(new BorderLayout(10, 25));

		final JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridLayout(2, 3, 10, 20));
		pnlButtons.setBorder(new EmptyBorder(30, 10, 10, 0));
		frmUiaEhelse.getContentPane().add(pnlButtons, BorderLayout.CENTER);

		JButton btnQuestions = new JButton("");
		btnQuestions.setIcon(new ImageIcon(MainPage.class.getResource("/pic/Question.JPG")));
		btnQuestions.setMargin(new Insets(4, 4, 4, 4));
		btnQuestions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				QuestionDial questiondial = new QuestionDial(MainPage.this);
				questiondial.setVisible(true);

			}
		});
		pnlButtons.add(btnQuestions);

		JButton btnMeasurement = new JButton("");
		btnMeasurement.setIcon(new ImageIcon(MainPage.class.getResource("/pic/Final-measurment.jpg")));
		btnMeasurement.setMargin(new Insets(4, 4, 2, 4));
		btnMeasurement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Measurement dial = new Measurement(MainPage.this);
				dial.setVisible(true);

			}
		});
		pnlButtons.add(btnMeasurement);

		JButton btnCat = new JButton("");
		btnCat.setIcon(new ImageIcon(MainPage.class.getResource("/pic/Cat1.jpg")));
		btnCat.setMargin(new Insets(4, 4, 4, 4));
		btnCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CatQuestionDial catquestiondial = new CatQuestionDial(MainPage.this);
				catquestiondial.setVisible(true);

			}
		});
		pnlButtons.add(btnCat);

		JButton btnHistory = new JButton("");
		btnHistory.setIcon(new ImageIcon(MainPage.class.getResource("/pic/Final-History.jpg")));
		btnHistory.setMargin(new Insets(4, 12, 4, 4));
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				History history = new History();
				history.setVisible(true);
			}
		});
		pnlButtons.add(btnHistory);

		JButton btnVideo = new JButton("");
		btnVideo.setIcon(new ImageIcon(MainPage.class.getResource("/pic/Final-Bruk.jpg")));
		btnVideo.setMargin(new Insets(4, 4, 4, 4));
		btnVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowPlayer show  = new ShowPlayer();
			}
		});
		pnlButtons.add(btnVideo);

		JButton btnInfo = new JButton("");
		btnInfo.setIcon(new ImageIcon(MainPage.class.getResource("/pic/Final-Info.jpg")));
		btnInfo.setMargin(new Insets(4, 4, 4, 4));
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FlashPlayer flash = new FlashPlayer();
				flash.setVisible(true);
			}
		});
		pnlButtons.add(btnInfo);


		Panel pnlSide = new Panel();
		pnlSide.setLayout(new BoxLayout(pnlSide, BoxLayout.Y_AXIS));
		frmUiaEhelse.getContentPane().add(pnlSide, BorderLayout.EAST);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(MainPage.class.getResource("/pic/Doctor-icon2.png")));
		pnlSide.add(lblLogo);

		JPanel pnlInfo = new JPanel();
		pnlSide.add(pnlInfo);
		pnlInfo.setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.GLUE_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblLastMeasurementLabel = new JLabel(
				currentLanguage.getString("lastMeasurement"));
		lblLastMeasurementLabel.setBorder(new TitledBorder(null, "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblLastMeasurementLabel.setFont(new Font("Arial", Font.BOLD, 20));
		pnlInfo.add(lblLastMeasurementLabel, "2, 2");

		lblLastMeasurement = new JLabel("N/A");
		lblLastMeasurement.setBorder(new TitledBorder(null, "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblLastMeasurement.setFont(new Font("Arial", Font.BOLD, 20));
		pnlInfo.add(lblLastMeasurement, "2, 4");

		JLabel lblLastQuestionnaireLabel = new JLabel(
				currentLanguage.getString("lastQuestionnaire"));
		lblLastQuestionnaireLabel.setBorder(new TitledBorder(null, "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblLastQuestionnaireLabel.setFont(new Font("Arial", Font.BOLD, 20));
		pnlInfo.add(lblLastQuestionnaireLabel, "2, 8");

		lblLastQuestionnaire = new JLabel("N/A");
		lblLastQuestionnaire.setBorder(new TitledBorder(null, "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblLastQuestionnaire.setFont(new Font("Arial", Font.BOLD, 20));
		pnlInfo.add(lblLastQuestionnaire, "2, 10");

		JLabel lblLastCATLabel = new JLabel(currentLanguage.getString("lastCAT"));
		lblLastCATLabel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		lblLastCATLabel.setFont(new Font("Arial", Font.BOLD, 20));
		pnlInfo.add(lblLastCATLabel, "2, 14");

		lblLastCAT = new JLabel("N/A");
		lblLastCAT.setBorder(new TitledBorder(null, "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblLastCAT.setFont(new Font("Arial", Font.BOLD, 20));
		pnlInfo.add(lblLastCAT, "2, 16");


		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
		pnlStatus.setBorder(new EmptyBorder(10, 10, 10, 10));
		frmUiaEhelse.getContentPane().add(pnlStatus, BorderLayout.SOUTH);

		JLabel lblNewLabel_1 = new JLabel(currentLanguage.getString("connStat"));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		pnlStatus.add(lblNewLabel_1);

		JLabel lblUiaEhelse = new JLabel("  \u00A9 UiA, eHelse, 2015, v1.7.3  \r\n ");
		lblUiaEhelse.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		lblUiaEhelse.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 25));
		pnlStatus.add(lblUiaEhelse);

		JLabel lblUsername = new JLabel("Tablet ID: " + HealthProperties.getProperty("iipUsername"));
		lblUsername.setFont(new Font("Arial", Font.BOLD, 20));
		pnlStatus.add(lblUsername);
	}

	public void readFromDB() {
		Connection connect = null;
		String dbName = app.HealthProperties.getProperty("dbName");
		String dbUsername = app.HealthProperties.getProperty("dbUsername");
		String dbPassword = app.HealthProperties.getProperty("dbPassword");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/" + dbName
							+ "?user=" + dbUsername + "&password=" + dbPassword);

			// Getting the time for last daily Q
			Statement dailyStatement = connect.createStatement();
			dailyStatement
			.executeQuery("SELECT `EHRDateTime` FROM `EHR` WHERE `conceptIDFromConcept` = 4 ORDER BY EHRID DESC LIMIT 1");
			ResultSet dailyRS = dailyStatement.getResultSet();
			if (dailyRS.next()) {
				if (lblLastQuestionnaire != null) lblLastQuestionnaire.setText(dailyRS.getString("EHRDateTime"));
			}

			dailyStatement.close();
			dailyRS.close();

			System.out
			.println("\nSuccessfully read last daily questionnaire from DB!");

			// Getting the time for last CAT Q
			Statement CATStatement = connect.createStatement();
			CATStatement
			.executeQuery("SELECT `EHRDateTime` FROM `EHR` WHERE `conceptIDFromConcept` = 5 ORDER BY EHRID DESC LIMIT 1");
			ResultSet CATRS = CATStatement.getResultSet();
			if (CATRS.next()) {
				if (lblLastCAT != null) lblLastCAT.setText(CATRS.getString("EHRDateTime"));
			}

			CATStatement.close();
			CATRS.close();

			Statement pulseStatement = connect.createStatement();
			pulseStatement
			.execute("SELECT `EHRDateTime` FROM `EHR` WHERE `conceptIDFromConcept` = 1 ORDER BY EHRID DESC LIMIT 1");
			ResultSet resultSet = pulseStatement.getResultSet();
			if (resultSet.next()) {
				if (lblLastMeasurement != null) lblLastMeasurement.setText(resultSet.getString(1));
			}
			resultSet.close();
			pulseStatement.close();
			System.out.println("\nSuccessfully read last CAT from DB!");

		} catch (Exception e) {
			System.out.println("Reading from DB on main page failed");
			System.out.println("SQLException: " + e.getMessage());
			//			e.printStackTrace();
		}
	}

	public static void showMessageDialog(String message, boolean isSent) {
		String currentLang = HealthProperties.getProperty("currentLanguage");
		Locale currentLocale = Locale.forLanguageTag(currentLang);
		final ResourceBundle currentLanguage = ResourceBundle.getBundle(
				"language", currentLocale);
		Icon icon = null;
		ClassLoader cldr = MainPage.class.getClassLoader();
		java.net.URL imageURL = null;
		if (isSent) {
			imageURL = cldr.getResource("glad.png");
		} else {
			imageURL = cldr.getResource("sad.png");
		}
		icon = new ImageIcon(imageURL);
		String title = currentLanguage.getString("sendStatus");
		JOptionPane
		.showConfirmDialog(frmUiaEhelse, message, title, JOptionPane.PLAIN_MESSAGE, 2, icon);
	}

	@Override
	public void databaseUpdated() {
		readFromDB();
	}
}
