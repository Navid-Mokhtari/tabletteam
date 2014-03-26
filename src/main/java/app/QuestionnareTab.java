package app;

import iipintegration.MySSLSocketFactory;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class QuestionnareTab extends JDialog {
	public QuestionnareTab() {
	}

	private static final long serialVersionUID = -7594299439504858239L;
 
	//Final answers
	
	String sQuestionOneAnswer = null;
	String sQuestionTwoAnswer = null;
	String sQuestionThreeAnswer = null;
	String sQuestionFourAnswer = null;
	String sQuestionFiveAnswer = null;
	String sQuestionSixAnswer = "0";
	String sQuestionSevenAnswer = "0";
	
	//DB values
	
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private String dbName = HealthProperties.getProperty("dbName");
	private String dbUsername = HealthProperties.getProperty("dbUsername");
	private String dbPassword = HealthProperties.getProperty("dbPassword");
	Integer statusDaily = 0;
	
	// Date
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss");
	String timeAndDate = simpleDateFormat.format(new Date());

	public Component getView() throws IOException {
		JComponent questionnare = createPanel("My questions");
		Toolkit tk = Toolkit.getDefaultToolkit();
		// int xSize = ((int) tk.getScreenSize().getWidth());
		// int ySize = ((int) tk.getScreenSize().getWidth());
		questionnare.setPreferredSize(new Dimension(1366, 768));
		return questionnare;
	}

	public JPanel createPanel(String title) throws IOException {
		String currentLang = HealthProperties.getProperty("currentLanguage");
		Locale currentLocale = Locale.forLanguageTag(currentLang);
		final ResourceBundle currentLanguage = ResourceBundle.getBundle(
				"language", currentLocale);

		// Panels

		final JPanel panelContainer = new JPanel();
		JPanel panelQuestionOne = new JPanel();
		JPanel panelQuestionTwo = new JPanel();
		JPanel panelQuestionThree = new JPanel();
		JPanel panelQuestionFour = new JPanel();
		JPanel panelQuestionFive = new JPanel();
		JPanel panelQuestionSix = new JPanel();
		JPanel panelQuestionSeven = new JPanel();
		final JPanel panelQuestionEight = new JPanel();
		final JPanel panelQuestionNine = new JPanel();
		panelContainer.setBackground(Color.ORANGE);
		panelQuestionOne.setBackground(Color.ORANGE);
		panelQuestionTwo.setBackground(Color.ORANGE);
		panelQuestionThree.setBackground(Color.ORANGE);
		panelQuestionFour.setBackground(Color.ORANGE);
		panelQuestionFive.setBackground(Color.ORANGE);
		panelQuestionSix.setBackground(Color.ORANGE);
		panelQuestionSeven.setBackground(Color.ORANGE);
		panelQuestionEight.setBackground(Color.ORANGE);
		panelQuestionNine.setBackground(Color.ORANGE);

		// Container panel layout

		final CardLayout cl = new CardLayout();
		panelContainer.setLayout(cl);
		panelContainer.add(panelQuestionOne, "1");
		panelContainer.add(panelQuestionTwo, "2");
		panelContainer.add(panelQuestionThree, "3");
		panelContainer.add(panelQuestionFour, "4");
		panelContainer.add(panelQuestionFive, "5");
		panelContainer.add(panelQuestionSix, "6");
		panelContainer.add(panelQuestionSeven, "7");
		panelContainer.add(panelQuestionEight, "8");
		panelContainer.add(panelQuestionNine, "9");
		cl.show(panelContainer, "1");

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		
		panelQuestionOne.setLayout(gbl_panel);
		panelQuestionTwo.setLayout(gbl_panel);
		panelQuestionThree.setLayout(gbl_panel);
		panelQuestionFour.setLayout(gbl_panel);
		panelQuestionFive.setLayout(gbl_panel);
		panelQuestionSix.setLayout(gbl_panel);
		panelQuestionSeven.setLayout(gbl_panel);
		panelQuestionEight.setLayout(gbl_panel);
		panelQuestionNine.setLayout(gbl_panel);

		final GridBagConstraints constrainsQuestionNine = new GridBagConstraints();
		constrainsQuestionNine.insets = new Insets(10, 10, 10, 10);
		constrainsQuestionNine.anchor = GridBagConstraints.FIRST_LINE_START;

		final GridBagConstraints constrainsQuestionEight = new GridBagConstraints();
		constrainsQuestionEight.insets = new Insets(10, 10, 10, 10);
		constrainsQuestionEight.anchor = GridBagConstraints.FIRST_LINE_START;

		ClassLoader customRadioButton = this.getClass().getClassLoader();

		URL iconUrlON = customRadioButton.getResource("radioButtonON.png");
		Icon radioIconON = new ImageIcon(iconUrlON);

		URL iconUrlOFF = customRadioButton.getResource("radioButtonOFF.png");
		Icon radioIconOFF = new ImageIcon(iconUrlOFF);

		// adding Question one

		final GridBagConstraints constrainsQuestionOne = new GridBagConstraints();
		constrainsQuestionOne.insets = new Insets(5, 5, 5, 5);
		constrainsQuestionOne.anchor = GridBagConstraints.FIRST_LINE_START;

		final JLabel questionOne;
		questionOne = new JLabel(currentLanguage.getString("qOne"));
		questionOne.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionOne.setForeground(Color.BLUE);
		constrainsQuestionOne.gridx = 0;
		constrainsQuestionOne.gridy = 0;
		panelQuestionOne.add(questionOne, constrainsQuestionOne);

		final JRadioButton questionOneSelectionTwo;
		questionOneSelectionTwo = new JRadioButton(
				currentLanguage.getString("aAsUsual"));
		questionOneSelectionTwo.setBackground(Color.ORANGE);
		questionOneSelectionTwo.setIcon(radioIconOFF);
		questionOneSelectionTwo.setSelectedIcon(radioIconON);
		questionOneSelectionTwo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		constrainsQuestionOne.gridx = 0;
		constrainsQuestionOne.gridy = 2;
		panelQuestionOne.add(questionOneSelectionTwo, constrainsQuestionOne);
		questionOneSelectionTwo.setActionCommand("2");

		final JRadioButton questionOneSelectionThree;
		questionOneSelectionThree = new JRadioButton(
				currentLanguage.getString("aWorse"));
		questionOneSelectionThree.setBackground(Color.ORANGE);
		questionOneSelectionThree.setIcon(radioIconOFF);
		questionOneSelectionThree.setSelectedIcon(radioIconON);
		questionOneSelectionThree.setFont(new Font("Tahoma", Font.PLAIN, 40));
		constrainsQuestionOne.gridx = 0;
		constrainsQuestionOne.gridy = 3;
		panelQuestionOne.add(questionOneSelectionThree, constrainsQuestionOne);
		questionOneSelectionThree.setActionCommand("3");

		final JRadioButton questionOneSelectionFour;
		questionOneSelectionFour = new JRadioButton(
				currentLanguage.getString("aMuchWorse"));
		questionOneSelectionFour.setBackground(Color.ORANGE);
		questionOneSelectionFour.setIcon(radioIconOFF);
		questionOneSelectionFour.setSelectedIcon(radioIconON);
		questionOneSelectionFour.setFont(new Font("Tahoma", Font.PLAIN, 40));
		constrainsQuestionOne.gridx = 0;
		constrainsQuestionOne.gridy = 7;
		panelQuestionOne.add(questionOneSelectionFour, constrainsQuestionOne);
		questionOneSelectionFour.setActionCommand("4");

		final ButtonGroup questionOneGroup = new ButtonGroup();
		questionOneGroup.add(questionOneSelectionTwo);
		questionOneGroup.add(questionOneSelectionThree);
		questionOneGroup.add(questionOneSelectionFour);

		final JLabel questionOneAnswer = new JLabel();
		questionOneAnswer.setFont(new Font("Tahoma", Font.ITALIC, 30));
		questionOneAnswer.setForeground(Color.BLUE);
		constrainsQuestionEight.gridx = 1;
		constrainsQuestionEight.gridy = 1;
		panelQuestionEight.add(questionOneAnswer, constrainsQuestionEight);

		JButton backButton;
		backButton = new JButton(currentLanguage.getString("previousQuestion"));
		backButton.setFont(new Font("Tahoma", Font.BOLD, 40));
		backButton.setPreferredSize(new Dimension(225, 125));
		backButton.setEnabled(false);
		constrainsQuestionOne.gridx = 0;
		constrainsQuestionOne.gridy = 8;
		panelQuestionOne.add(backButton, constrainsQuestionOne);
		
		JButton toQuestionTwo;
		toQuestionTwo = new JButton(currentLanguage.getString("nextQuestion"));
		toQuestionTwo.setFont(new Font("Tahoma", Font.BOLD, 40));
		toQuestionTwo.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionOne.gridx = 1;
		constrainsQuestionOne.gridy = 8;
		panelQuestionOne.add(toQuestionTwo, constrainsQuestionOne);
		toQuestionTwo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (questionOneSelectionTwo.isSelected()) {
					questionOneAnswer.setText(currentLanguage
							.getString("aAsUsual"));
				} else if (questionOneSelectionThree.isSelected()) {
					questionOneAnswer.setText(currentLanguage
							.getString("aWorse"));
				} else if (questionOneSelectionFour.isSelected()) {
					questionOneAnswer.setText(currentLanguage
							.getString("aMuchWorse"));
				}

				final JLabel questionOne;
				questionOne = new JLabel(currentLanguage.getString("qOne"));
				questionOne.setFont(new Font("Tahoma", Font.BOLD, 40));
				questionOne.setForeground(Color.BLUE);
				constrainsQuestionEight.gridx = 0;
				constrainsQuestionEight.gridy = 1;
				panelQuestionEight.add(questionOne, constrainsQuestionEight);
				if (questionOneSelectionTwo.isSelected()
						|| questionOneSelectionThree.isSelected()
						|| questionOneSelectionFour.isSelected()) {
					cl.show(panelContainer, "2");
				}

			}
		});

		// Adding question number two

		GridBagConstraints constrainsQuestionTwo = new GridBagConstraints();
		constrainsQuestionTwo.insets = new Insets(5, 5, 5, 5);
		constrainsQuestionTwo.anchor = GridBagConstraints.FIRST_LINE_START;

		final JLabel questionTwo;
		questionTwo = new JLabel(currentLanguage.getString("qTwo"));
		questionTwo.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionTwo.setForeground(Color.BLUE);
		constrainsQuestionTwo.gridx = 0;
		constrainsQuestionTwo.gridy = 0;
		panelQuestionTwo.add(questionTwo, constrainsQuestionTwo);

		final JRadioButton questionTwoSelectionTwo;
		questionTwoSelectionTwo = new JRadioButton(
				currentLanguage.getString("aAsUsual"));
		questionTwoSelectionTwo.setBackground(Color.ORANGE);
		questionTwoSelectionTwo.setIcon(radioIconOFF);
		questionTwoSelectionTwo.setSelectedIcon(radioIconON);
		questionTwoSelectionTwo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		constrainsQuestionTwo.gridx = 0;
		constrainsQuestionTwo.gridy = 2;
		panelQuestionTwo.add(questionTwoSelectionTwo, constrainsQuestionTwo);
		questionTwoSelectionTwo.setActionCommand("2");

		final JRadioButton questionTwoSelectionThree;
		questionTwoSelectionThree = new JRadioButton(
				currentLanguage.getString("aWorse"));
		questionTwoSelectionThree.setBackground(Color.ORANGE);
		questionTwoSelectionThree.setIcon(radioIconOFF);
		questionTwoSelectionThree.setSelectedIcon(radioIconON);
		questionTwoSelectionThree.setFont(new Font("Tahoma", Font.PLAIN, 40));
		constrainsQuestionTwo.gridx = 0;
		constrainsQuestionTwo.gridy = 3;
		panelQuestionTwo.add(questionTwoSelectionThree, constrainsQuestionTwo);
		questionTwoSelectionThree.setActionCommand("3");

		final JRadioButton questionTwoSelectionFour;
		questionTwoSelectionFour = new JRadioButton(
				currentLanguage.getString("aMuchWorse"));
		questionTwoSelectionFour.setBackground(Color.ORANGE);
		questionTwoSelectionFour.setIcon(radioIconOFF);
		questionTwoSelectionFour.setSelectedIcon(radioIconON);
		questionTwoSelectionFour.setFont(new Font("Tahoma", Font.PLAIN, 40));
		constrainsQuestionTwo.gridx = 0;
		constrainsQuestionTwo.gridy = 4;
		panelQuestionTwo.add(questionTwoSelectionFour, constrainsQuestionTwo);
		questionTwoSelectionFour.setActionCommand("4");

		final ButtonGroup questionTwoGroup = new ButtonGroup();
		questionTwoGroup.add(questionTwoSelectionTwo);
		questionTwoGroup.add(questionTwoSelectionThree);
		questionTwoGroup.add(questionTwoSelectionFour);

		JButton toQuestionOne;
		toQuestionOne = new JButton(
				currentLanguage.getString("previousQuestion"));
		toQuestionOne.setFont(new Font("Tahoma", Font.BOLD, 40));
		toQuestionOne.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionTwo.gridx = 0;
		constrainsQuestionTwo.gridy = 7;
		panelQuestionTwo.add(toQuestionOne, constrainsQuestionTwo);
		toQuestionOne.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "1");

			}
		});

		final JLabel questionTwoAnswer = new JLabel();
		questionTwoAnswer.setFont(new Font("Tahoma", Font.ITALIC, 30));
		questionTwoAnswer.setForeground(Color.BLUE);
		constrainsQuestionEight.gridx = 1;
		constrainsQuestionEight.gridy = 2;
		panelQuestionEight.add(questionTwoAnswer, constrainsQuestionEight);

		JButton toQuestionThree;
		toQuestionThree = new JButton(currentLanguage.getString("nextQuestion"));
		toQuestionThree.setFont(new Font("Tahoma", Font.BOLD, 40));
		toQuestionThree.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionTwo.gridx = 1;
		constrainsQuestionTwo.gridy = 7;
		panelQuestionTwo.add(toQuestionThree, constrainsQuestionTwo);
		toQuestionThree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (questionTwoSelectionTwo.isSelected()) {
					questionTwoAnswer.setText(currentLanguage
							.getString("aAsUsual"));
				} else if (questionTwoSelectionThree.isSelected()) {
					questionTwoAnswer.setText(currentLanguage
							.getString("aWorse"));
				} else if (questionTwoSelectionFour.isSelected()) {
					questionTwoAnswer.setText(currentLanguage
							.getString("aMuchWorse"));
				}

				final JLabel questionTwo;
				questionTwo = new JLabel(currentLanguage.getString("qTwo"));
				questionTwo.setFont(new Font("Tahoma", Font.BOLD, 40));
				questionTwo.setForeground(Color.BLUE);
				constrainsQuestionEight.gridx = 0;
				constrainsQuestionEight.gridy = 2;
				panelQuestionEight.add(questionTwo, constrainsQuestionEight);
				if (questionTwoSelectionTwo.isSelected()
						|| questionTwoSelectionThree.isSelected()
						|| questionTwoSelectionFour.isSelected()) {
					cl.show(panelContainer, "3");
				}
				;
			}
		});

		// Adding question number three

		GridBagConstraints constrainsQuestionThree = new GridBagConstraints();
		constrainsQuestionThree.insets = new Insets(5, 5, 5, 5);
		constrainsQuestionThree.anchor = GridBagConstraints.FIRST_LINE_START;

		final JLabel questionThree;
		questionThree = new JLabel(currentLanguage.getString("qThree"));
		questionThree.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionThree.setForeground(Color.BLUE);
		constrainsQuestionThree.gridx = 0;
		constrainsQuestionThree.gridy = 0;
		panelQuestionThree.add(questionThree, constrainsQuestionThree);

		final JRadioButton questionThreeSelectionTwo;
		questionThreeSelectionTwo = new JRadioButton(
				currentLanguage.getString("aAsUsual"));
		questionThreeSelectionTwo.setBackground(Color.ORANGE);
		questionThreeSelectionTwo.setIcon(radioIconOFF);
		questionThreeSelectionTwo.setSelectedIcon(radioIconON);
		questionThreeSelectionTwo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		constrainsQuestionThree.gridx = 0;
		constrainsQuestionThree.gridy = 2;
		panelQuestionThree.add(questionThreeSelectionTwo,
				constrainsQuestionThree);
		questionThreeSelectionTwo.setActionCommand("2");

		final JRadioButton questionThreeSelectionThree;
		questionThreeSelectionThree = new JRadioButton(
				currentLanguage.getString("aWorse"));
		questionThreeSelectionThree.setBackground(Color.ORANGE);
		questionThreeSelectionThree.setFont(new Font("Tahoma", Font.PLAIN, 40));
		questionThreeSelectionThree.setIcon(radioIconOFF);
		questionThreeSelectionThree.setSelectedIcon(radioIconON);
		constrainsQuestionThree.gridx = 0;
		constrainsQuestionThree.gridy = 3;
		panelQuestionThree.add(questionThreeSelectionThree,
				constrainsQuestionThree);
		questionThreeSelectionThree.setActionCommand("3");

		final JRadioButton questionThreeSelectionFour;
		questionThreeSelectionFour = new JRadioButton(
				currentLanguage.getString("aMuchWorse"));
		questionThreeSelectionFour.setBackground(Color.ORANGE);
		questionThreeSelectionFour.setFont(new Font("Tahoma", Font.PLAIN, 40));
		questionThreeSelectionFour.setIcon(radioIconOFF);
		questionThreeSelectionFour.setSelectedIcon(radioIconON);
		constrainsQuestionThree.gridx = 0;
		constrainsQuestionThree.gridy = 4;
		panelQuestionThree.add(questionThreeSelectionFour,
				constrainsQuestionThree);
		questionThreeSelectionFour.setActionCommand("4");

		final ButtonGroup questionThreeGroup = new ButtonGroup();
		questionThreeGroup.add(questionThreeSelectionTwo);
		questionThreeGroup.add(questionThreeSelectionThree);
		questionThreeGroup.add(questionThreeSelectionFour);

		JButton backToQuestionTwo;
		backToQuestionTwo = new JButton(
				currentLanguage.getString("previousQuestion"));
		backToQuestionTwo.setFont(new Font("Tahoma", Font.BOLD, 40));
		backToQuestionTwo.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionThree.gridx = 0;
		constrainsQuestionThree.gridy = 7;
		panelQuestionThree.add(backToQuestionTwo, constrainsQuestionThree);
		backToQuestionTwo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "2");

			}
		});

		final JLabel questionThreeAnswer = new JLabel();
		questionThreeAnswer.setFont(new Font("Tahoma", Font.ITALIC, 30));
		questionThreeAnswer.setForeground(Color.BLUE);
		constrainsQuestionEight.gridx = 1;
		constrainsQuestionEight.gridy = 3;
		panelQuestionEight.add(questionThreeAnswer, constrainsQuestionEight);

		JButton toQuestionFour;
		toQuestionFour = new JButton(currentLanguage.getString("nextQuestion"));
		toQuestionFour.setFont(new Font("Tahoma", Font.BOLD, 40));
		toQuestionFour.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionThree.gridx = 1;
		constrainsQuestionThree.gridy = 7;
		panelQuestionThree.add(toQuestionFour, constrainsQuestionThree);
		toQuestionFour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (questionThreeSelectionTwo.isSelected()) {
					questionThreeAnswer.setText(currentLanguage
							.getString("aAsUsual"));
				} else if (questionThreeSelectionThree.isSelected()) {
					questionThreeAnswer.setText(currentLanguage
							.getString("aWorse"));
				} else if (questionThreeSelectionFour.isSelected()) {
					questionThreeAnswer.setText(currentLanguage
							.getString("aMuchWorse"));
				}

				final JLabel questionThree;
				questionThree = new JLabel(currentLanguage.getString("qThree"));
				questionThree.setForeground(Color.BLUE);
				questionThree.setFont(new Font("Tahoma", Font.BOLD, 40));
				constrainsQuestionEight.gridx = 0;
				constrainsQuestionEight.gridy = 3;
				panelQuestionEight.add(questionThree, constrainsQuestionEight);
				if (questionThreeSelectionTwo.isSelected()
						|| questionThreeSelectionThree.isSelected()
						|| questionThreeSelectionFour.isSelected()) {
					cl.show(panelContainer, "4");
				}
				;
				;
			}
		});

		// Adding question number four

		GridBagConstraints constrainsQuestionFour = new GridBagConstraints();
		constrainsQuestionFour.insets = new Insets(5, 5, 5, 5);
		constrainsQuestionFour.anchor = GridBagConstraints.FIRST_LINE_START;

		final JLabel questionFour;
		questionFour = new JLabel(currentLanguage.getString("qFour"));
		questionFour.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionFour.setForeground(Color.BLUE);
		constrainsQuestionFour.gridx = 0;
		constrainsQuestionFour.gridy = 0;
		panelQuestionFour.add(questionFour, constrainsQuestionFour);

		final JRadioButton questionFourSelectionOne;
		questionFourSelectionOne = new JRadioButton(
				currentLanguage.getString("qFourAnsOne"));
		questionFourSelectionOne.setBackground(Color.ORANGE);
		questionFourSelectionOne.setIcon(radioIconOFF);
		questionFourSelectionOne.setSelectedIcon(radioIconON);
		questionFourSelectionOne.setFont(new Font("Tahoma", Font.PLAIN, 40));
		constrainsQuestionFour.gridx = 0;
		constrainsQuestionFour.gridy = 1;
		panelQuestionFour.add(questionFourSelectionOne, constrainsQuestionFour);
		questionFourSelectionOne.setActionCommand("2");

		final JRadioButton questionFourSelectionTwo;
		questionFourSelectionTwo = new JRadioButton(
				currentLanguage.getString("qFourAnsTwo"));
		questionFourSelectionTwo.setBackground(Color.ORANGE);
		questionFourSelectionTwo.setIcon(radioIconOFF);
		questionFourSelectionTwo.setSelectedIcon(radioIconON);
		questionFourSelectionTwo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		constrainsQuestionFour.gridx = 0;
		constrainsQuestionFour.gridy = 2;
		panelQuestionFour.add(questionFourSelectionTwo, constrainsQuestionFour);
		questionFourSelectionTwo.setActionCommand("3");

		final ButtonGroup questionFourGroup = new ButtonGroup();
		questionFourGroup.add(questionFourSelectionOne);
		questionFourGroup.add(questionFourSelectionTwo);

		JButton backToQuestionThree;
		backToQuestionThree = new JButton(
				currentLanguage.getString("previousQuestion"));
		backToQuestionThree.setFont(new Font("Tahoma", Font.BOLD, 40));
		backToQuestionThree.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionFour.gridx = 0;
		constrainsQuestionFour.gridy = 8;
		panelQuestionFour.add(backToQuestionThree, constrainsQuestionFour);
		backToQuestionThree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "3");

			}
		});

		final JLabel questionFourAnswer = new JLabel();
		questionFourAnswer.setFont(new Font("Tahoma", Font.ITALIC, 30));
		questionFourAnswer.setForeground(Color.BLUE);
		constrainsQuestionEight.gridx = 1;
		constrainsQuestionEight.gridy = 4;
		panelQuestionEight.add(questionFourAnswer, constrainsQuestionEight);

		JButton toQuestionFive;
		toQuestionFive = new JButton(currentLanguage.getString("nextQuestion"));
		toQuestionFive.setFont(new Font("Tahoma", Font.BOLD, 40));
		toQuestionFive.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionFour.gridx = 1;
		constrainsQuestionFour.gridy = 8;
		panelQuestionFour.add(toQuestionFive, constrainsQuestionFour);
		toQuestionFive.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (questionFourSelectionOne.isSelected()) {
					questionFourAnswer.setText(currentLanguage
							.getString("qFourAnsOne"));
				} else if (questionFourSelectionTwo.isSelected()) {
					questionFourAnswer.setText(currentLanguage
							.getString("qFourAnsTwo"));
				}

				final JLabel questionFour;
				questionFour = new JLabel(currentLanguage.getString("qFour"));
				questionFour.setFont(new Font("Tahoma", Font.BOLD, 40));
				questionFour.setForeground(Color.BLUE);
				constrainsQuestionEight.gridx = 0;
				constrainsQuestionEight.gridy = 4;
				panelQuestionEight.add(questionFour, constrainsQuestionEight);
				if (questionFourSelectionOne.isSelected()
						|| questionFourSelectionTwo.isSelected()) {
					cl.show(panelContainer, "5");
				}
				;
				;
			}
		});

		// Adding question number five

		GridBagConstraints constrainsQuestionFive = new GridBagConstraints();
		constrainsQuestionFive.insets = new Insets(5, 5, 5, 5);
		constrainsQuestionFive.anchor = GridBagConstraints.FIRST_LINE_START;

		final JLabel questionFive;
		questionFive = new JLabel(currentLanguage.getString("qFive"));
		questionFive.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionFive.setForeground(Color.BLUE);
		constrainsQuestionFive.gridx = 0;
		constrainsQuestionFive.gridy = 0;
		panelQuestionFive.add(questionFive, constrainsQuestionFive);

		final JRadioButton questionFiveSelectionOne;
		questionFiveSelectionOne = new JRadioButton(
				currentLanguage.getString("qFiveAnsOne"));
		questionFiveSelectionOne.setBackground(Color.ORANGE);
		questionFiveSelectionOne.setFont(new Font("Tahoma", Font.PLAIN, 40));
		questionFiveSelectionOne.setIcon(radioIconOFF);
		questionFiveSelectionOne.setSelectedIcon(radioIconON);
		constrainsQuestionFive.gridx = 0;
		constrainsQuestionFive.gridy = 1;
		panelQuestionFive.add(questionFiveSelectionOne, constrainsQuestionFive);
		questionFiveSelectionOne.setActionCommand("1");

		final JRadioButton questionFiveSelectionTwo;
		questionFiveSelectionTwo = new JRadioButton(
				currentLanguage.getString("qFiveAnsTwo"));
		questionFiveSelectionTwo.setBackground(Color.ORANGE);
		questionFiveSelectionTwo.setIcon(radioIconOFF);
		questionFiveSelectionTwo.setSelectedIcon(radioIconON);
		questionFiveSelectionTwo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		constrainsQuestionFive.gridx = 0;
		constrainsQuestionFive.gridy = 2;
		panelQuestionFive.add(questionFiveSelectionTwo, constrainsQuestionFive);
		questionFiveSelectionTwo.setActionCommand("2");

		final JRadioButton questionFiveSelectionThree;
		questionFiveSelectionThree = new JRadioButton(
				currentLanguage.getString("qFiveAnsThree"));
		questionFiveSelectionThree.setBackground(Color.ORANGE);
		questionFiveSelectionThree.setIcon(radioIconOFF);
		questionFiveSelectionThree.setSelectedIcon(radioIconON);
		questionFiveSelectionThree.setFont(new Font("Tahoma", Font.PLAIN, 40));
		constrainsQuestionFive.gridx = 0;
		constrainsQuestionFive.gridy = 3;
		panelQuestionFive.add(questionFiveSelectionThree,
				constrainsQuestionFive);
		questionFiveSelectionThree.setActionCommand("3");

		final JRadioButton questionFiveSelectionFour;
		questionFiveSelectionFour = new JRadioButton(
				currentLanguage.getString("qFiveAnsFour"));
		questionFiveSelectionFour.setBackground(Color.ORANGE);
		questionFiveSelectionFour.setIcon(radioIconOFF);
		questionFiveSelectionFour.setSelectedIcon(radioIconON);
		questionFiveSelectionFour.setFont(new Font("Tahoma", Font.PLAIN, 40));
		constrainsQuestionFive.gridx = 0;
		constrainsQuestionFive.gridy = 4;
		panelQuestionFive
				.add(questionFiveSelectionFour, constrainsQuestionFive);
		questionFiveSelectionFour.setActionCommand("4");

		final ButtonGroup questionFiveGroup = new ButtonGroup();
		questionFiveGroup.add(questionFiveSelectionOne);
		questionFiveGroup.add(questionFiveSelectionTwo);
		questionFiveGroup.add(questionFiveSelectionThree);
		questionFiveGroup.add(questionFiveSelectionFour);

		JButton backToQuestionFour;
		backToQuestionFour = new JButton(
				currentLanguage.getString("previousQuestion"));
		backToQuestionFour.setFont(new Font("Tahoma", Font.BOLD, 40));
		backToQuestionFour.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionFive.gridx = 0;
		constrainsQuestionFive.gridy = 8;
		panelQuestionFive.add(backToQuestionFour, constrainsQuestionFive);
		backToQuestionFour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "4");

			}
		});

		final JLabel questionFiveAnswer = new JLabel();
		questionFiveAnswer.setFont(new Font("Tahoma", Font.ITALIC, 30));
		questionFiveAnswer.setForeground(Color.BLUE);
		constrainsQuestionNine.gridx = 1;
		constrainsQuestionNine.gridy = 1;
		panelQuestionNine.add(questionFiveAnswer, constrainsQuestionNine);

		JButton toQuestionSix;
		toQuestionSix = new JButton(currentLanguage.getString("nextQuestion"));
		toQuestionSix.setFont(new Font("Tahoma", Font.BOLD, 40));
		toQuestionSix.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionFive.gridx = 1;
		constrainsQuestionFive.gridy = 8;
		panelQuestionFive.add(toQuestionSix, constrainsQuestionFive);
		toQuestionSix.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (questionFiveSelectionOne.isSelected()) {
					questionFiveAnswer.setText(currentLanguage
							.getString("qFiveAnsOne"));
				} else if (questionFiveSelectionTwo.isSelected()) {
					questionFiveAnswer.setText(currentLanguage
							.getString("qFiveAnsTwo"));
				} else if (questionFiveSelectionThree.isSelected()) {
					questionFiveAnswer.setText(currentLanguage
							.getString("qFiveAnsThree"));
				} else if (questionFiveSelectionFour.isSelected()) {
					questionFiveAnswer.setText(currentLanguage
							.getString("qFiveAnsFour"));
				}

				final JLabel questionFive;
				questionFive = new JLabel(currentLanguage.getString("qFive"));
				questionFive.setFont(new Font("Tahoma", Font.BOLD, 40));
				questionFive.setForeground(Color.BLUE);
				constrainsQuestionNine.gridx = 0;
				constrainsQuestionNine.gridy = 1;
				panelQuestionNine.add(questionFive, constrainsQuestionNine);

				if (questionFiveSelectionOne.isSelected()) {
					cl.show(panelContainer, "8");
				} else if (questionFiveSelectionTwo.isSelected()
						|| questionFiveSelectionThree.isSelected()
						|| questionFiveSelectionFour.isSelected()) {
					cl.show(panelContainer, "6");
				}
				;
				;
			}
		});

		// Adding question number six

		GridBagConstraints constrainsQuestionSix = new GridBagConstraints();
		constrainsQuestionSix.insets = new Insets(5, 5, 5, 5);
		constrainsQuestionSix.anchor = GridBagConstraints.FIRST_LINE_START;

		final JLabel questionSix;
		questionSix = new JLabel(currentLanguage.getString("qSix"));
		questionSix.setFont(new Font("Tahoma", Font.BOLD, 30));
		questionSix.setForeground(Color.BLUE);
		constrainsQuestionSix.gridx = 0;
		constrainsQuestionSix.gridy = 0;
		panelQuestionSix.add(questionSix, constrainsQuestionSix);

		final JRadioButton questionSixSelectionOne;
		questionSixSelectionOne = new JRadioButton(
				currentLanguage.getString("qSixAnsOne"));
		questionSixSelectionOne.setBackground(Color.ORANGE);
		questionSixSelectionOne.setIcon(radioIconOFF);
		questionSixSelectionOne.setSelectedIcon(radioIconON);
		questionSixSelectionOne.setFont(new Font("Tahoma", Font.PLAIN, 30));
		constrainsQuestionSix.gridx = 0;
		constrainsQuestionSix.gridy = 1;
		panelQuestionSix.add(questionSixSelectionOne, constrainsQuestionSix);
		questionSixSelectionOne.setActionCommand("0");

		final JRadioButton questionSixSelectionTwo;
		questionSixSelectionTwo = new JRadioButton(
				currentLanguage.getString("qSixAnsTwo"));
		questionSixSelectionTwo.setBackground(Color.ORANGE);
		questionSixSelectionTwo.setIcon(radioIconOFF);
		questionSixSelectionTwo.setSelectedIcon(radioIconON);
		questionSixSelectionTwo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		constrainsQuestionSix.gridx = 0;
		constrainsQuestionSix.gridy = 2;
		panelQuestionSix.add(questionSixSelectionTwo, constrainsQuestionSix);
		questionSixSelectionTwo.setActionCommand("1");

		final JRadioButton questionSixSelectionThree;
		questionSixSelectionThree = new JRadioButton(
				currentLanguage.getString("qSixAnsThree"));
		questionSixSelectionThree.setBackground(Color.ORANGE);
		questionSixSelectionThree.setIcon(radioIconOFF);
		questionSixSelectionThree.setSelectedIcon(radioIconON);
		questionSixSelectionThree.setFont(new Font("Tahoma", Font.PLAIN, 30));
		constrainsQuestionSix.gridx = 0;
		constrainsQuestionSix.gridy = 3;
		panelQuestionSix.add(questionSixSelectionThree, constrainsQuestionSix);
		questionSixSelectionThree.setActionCommand("2");

		final JRadioButton questionSixSelectionFour;
		questionSixSelectionFour = new JRadioButton(
				currentLanguage.getString("qSixAnsFour"));
		questionSixSelectionFour.setBackground(Color.ORANGE);
		questionSixSelectionFour.setIcon(radioIconOFF);
		questionSixSelectionFour.setSelectedIcon(radioIconON);
		questionSixSelectionFour.setFont(new Font("Tahoma", Font.PLAIN, 30));
		constrainsQuestionSix.gridx = 0;
		constrainsQuestionSix.gridy = 4;
		panelQuestionSix.add(questionSixSelectionFour, constrainsQuestionSix);
		questionSixSelectionFour.setActionCommand("3");

		final JRadioButton questionSixSelectionFive;
		questionSixSelectionFive = new JRadioButton(
				currentLanguage.getString("qSixAnsFive"));
		questionSixSelectionFive.setBackground(Color.ORANGE);
		questionSixSelectionFive.setIcon(radioIconOFF);
		questionSixSelectionFive.setSelectedIcon(radioIconON);
		questionSixSelectionFive.setFont(new Font("Tahoma", Font.PLAIN, 30));
		constrainsQuestionSix.gridx = 0;
		constrainsQuestionSix.gridy = 5;
		panelQuestionSix.add(questionSixSelectionFive, constrainsQuestionSix);
		questionSixSelectionFive.setActionCommand("4");

		final JRadioButton questionSixSelectionSix;
		questionSixSelectionSix = new JRadioButton(
				currentLanguage.getString("qSixAnsSix"));
		questionSixSelectionSix.setBackground(Color.ORANGE);
		questionSixSelectionSix.setIcon(radioIconOFF);
		questionSixSelectionSix.setSelectedIcon(radioIconON);
		questionSixSelectionSix.setFont(new Font("Tahoma", Font.PLAIN, 30));
		constrainsQuestionSix.gridx = 0;
		constrainsQuestionSix.gridy = 6;
		panelQuestionSix.add(questionSixSelectionSix, constrainsQuestionSix);
		questionSixSelectionSix.setActionCommand("5");

		final ButtonGroup questionSixGroup = new ButtonGroup();
		questionSixGroup.add(questionSixSelectionOne);
		questionSixGroup.add(questionSixSelectionTwo);
		questionSixGroup.add(questionSixSelectionThree);
		questionSixGroup.add(questionSixSelectionFour);
		questionSixGroup.add(questionSixSelectionFive);
		questionSixGroup.add(questionSixSelectionSix);

		JButton backToQuestionFive;
		backToQuestionFive = new JButton(
				currentLanguage.getString("previousQuestion"));
		backToQuestionFive.setFont(new Font("Tahoma", Font.BOLD, 40));
		backToQuestionFive.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionSix.gridx = 0;
		constrainsQuestionSix.gridy = 8;
		panelQuestionSix.add(backToQuestionFive, constrainsQuestionSix);
		backToQuestionFive.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "5");
			}
		});

		final JLabel questionSixAnswer = new JLabel();
		questionSixAnswer.setFont(new Font("Tahoma", Font.ITALIC, 30));
		questionSixAnswer.setForeground(Color.BLUE);
		constrainsQuestionNine.gridx = 1;
		constrainsQuestionNine.gridy = 2;
		panelQuestionNine.add(questionSixAnswer, constrainsQuestionNine);

		JButton toQuestionSeven;
		toQuestionSeven = new JButton(currentLanguage.getString("nextQuestion"));
		toQuestionSeven.setFont(new Font("Tahoma", Font.BOLD, 40));
		toQuestionSeven.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionSix.gridx = 1;
		constrainsQuestionSix.gridy = 8;
		panelQuestionSix.add(toQuestionSeven, constrainsQuestionSix);
		toQuestionSeven.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (questionSixSelectionOne.isSelected()) {
					questionSixAnswer.setText(currentLanguage
							.getString("qSixAnsOne"));
				} else if (questionSixSelectionTwo.isSelected()) {
					questionSixAnswer.setText(currentLanguage
							.getString("qSixAnsTwo"));
				} else if (questionSixSelectionThree.isSelected()) {
					questionSixAnswer.setText(currentLanguage
							.getString("qSixAnsThree"));
				} else if (questionSixSelectionFour.isSelected()) {
					questionSixAnswer.setText(currentLanguage
							.getString("qSixAnsFour"));
				} else if (questionSixSelectionFive.isSelected()) {
					questionSixAnswer.setText(currentLanguage
							.getString("qSixAnsFive"));
				} else if (questionSixSelectionSix.isSelected()) {
					questionSixAnswer.setText(currentLanguage
							.getString("qSixAnsSix"));
				}

				final JLabel questionSix;
				questionSix = new JLabel(currentLanguage.getString("qSix"));
				questionSix.setFont(new Font("Tahoma", Font.BOLD, 40));
				questionSix.setForeground(Color.BLUE);
				constrainsQuestionNine.gridx = 0;
				constrainsQuestionNine.gridy = 2;
				panelQuestionNine.add(questionSix, constrainsQuestionNine);
				if (questionSixSelectionOne.isSelected()) {
					cl.show(panelContainer, "8");
				} else if (questionSixSelectionTwo.isSelected()
						|| questionSixSelectionThree.isSelected()
						|| questionSixSelectionFour.isSelected()
						|| questionSixSelectionFive.isSelected()
						|| questionSixSelectionSix.isSelected()) {
					cl.show(panelContainer, "7");
				}
			}
		});

		// adding question seven

		GridBagConstraints constrainsQuestionSeven = new GridBagConstraints();
		constrainsQuestionSeven.insets = new Insets(5, 5, 5, 5);
		constrainsQuestionSeven.anchor = GridBagConstraints.FIRST_LINE_START;

		final JLabel questionSeven;
		questionSeven = new JLabel(currentLanguage.getString("qSeven"));
		questionSeven.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionSeven.setForeground(Color.BLUE);
		constrainsQuestionSeven.gridx = 0;
		constrainsQuestionSeven.gridy = 0;
		panelQuestionSeven.add(questionSeven, constrainsQuestionSeven);

		final JRadioButton questionSevenSelectionOne;
		questionSevenSelectionOne = new JRadioButton(
				currentLanguage.getString("qSevenAnsOne"));
		questionSevenSelectionOne.setBackground(Color.ORANGE);
		questionSevenSelectionOne.setIcon(radioIconOFF);
		questionSevenSelectionOne.setSelectedIcon(radioIconON);
		questionSevenSelectionOne.setFont(new Font("Tahoma", Font.PLAIN, 40));
		constrainsQuestionSeven.gridx = 0;
		constrainsQuestionSeven.gridy = 1;
		panelQuestionSeven.add(questionSevenSelectionOne,
				constrainsQuestionSeven);
		questionSevenSelectionOne.setActionCommand("1");

		final JRadioButton questionSevenSelectionTwo;
		questionSevenSelectionTwo = new JRadioButton(
				currentLanguage.getString("qSevenAnsTwo"));
		questionSevenSelectionTwo.setBackground(Color.ORANGE);
		questionSevenSelectionTwo.setIcon(radioIconOFF);
		questionSevenSelectionTwo.setSelectedIcon(radioIconON);
		questionSevenSelectionTwo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		constrainsQuestionSeven.gridx = 0;
		constrainsQuestionSeven.gridy = 2;
		panelQuestionSeven.add(questionSevenSelectionTwo,
				constrainsQuestionSeven);
		questionSevenSelectionTwo.setActionCommand("2");

		final JRadioButton questionSevenSelectionThree;
		questionSevenSelectionThree = new JRadioButton(
				currentLanguage.getString("qSevenAnsThree"));
		questionSevenSelectionThree.setBackground(Color.ORANGE);
		questionSevenSelectionThree.setIcon(radioIconOFF);
		questionSevenSelectionThree.setSelectedIcon(radioIconON);
		questionSevenSelectionThree.setFont(new Font("Tahoma", Font.PLAIN, 40));
		constrainsQuestionSeven.gridx = 0;
		constrainsQuestionSeven.gridy = 3;
		panelQuestionSeven.add(questionSevenSelectionThree,
				constrainsQuestionSeven);
		questionSevenSelectionThree.setActionCommand("3");

		final JRadioButton questionSevenSelectionFour;
		questionSevenSelectionFour = new JRadioButton(
				currentLanguage.getString("qSevenAnsFour"));
		questionSevenSelectionFour.setBackground(Color.ORANGE);
		questionSevenSelectionFour.setIcon(radioIconOFF);
		questionSevenSelectionFour.setSelectedIcon(radioIconON);
		questionSevenSelectionFour.setFont(new Font("Tahoma", Font.PLAIN, 40));
		constrainsQuestionSeven.gridx = 0;
		constrainsQuestionSeven.gridy = 4;
		panelQuestionSeven.add(questionSevenSelectionFour,
				constrainsQuestionSeven);
		questionSevenSelectionFour.setActionCommand("4");

		final ButtonGroup questionSevenGroup = new ButtonGroup();
		questionSevenGroup.add(questionSevenSelectionOne);
		questionSevenGroup.add(questionSevenSelectionTwo);
		questionSevenGroup.add(questionSevenSelectionThree);
		questionSevenGroup.add(questionSevenSelectionFour);

		JButton backToQuestionSix;
		backToQuestionSix = new JButton(
				currentLanguage.getString("previousQuestion"));
		backToQuestionSix.setFont(new Font("Tahoma", Font.BOLD, 40));
		backToQuestionSix.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionSeven.gridx = 0;
		constrainsQuestionSeven.gridy = 7;
		panelQuestionSeven.add(backToQuestionSix, constrainsQuestionSeven);
		backToQuestionSix.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "6");
			}
		});

		final JLabel questionSevenAnswer = new JLabel();
		questionSevenAnswer.setFont(new Font("Tahoma", Font.ITALIC, 30));
		questionSevenAnswer.setForeground(Color.BLUE);
		constrainsQuestionNine.gridx = 1;
		constrainsQuestionNine.gridy = 3;
		panelQuestionNine.add(questionSevenAnswer, constrainsQuestionNine);

		JButton toQuestionEight;
		toQuestionEight = new JButton(currentLanguage.getString("nextQuestion"));
		toQuestionEight.setFont(new Font("Tahoma", Font.BOLD, 40));
		toQuestionEight.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionSeven.gridx = 1;
		constrainsQuestionSeven.gridy = 7;
		panelQuestionSeven.add(toQuestionEight, constrainsQuestionSeven);
		toQuestionEight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (questionSevenSelectionOne.isSelected()) {
					questionSevenAnswer.setText(currentLanguage
							.getString("qSevenAnsOne"));
				} else if (questionSevenSelectionTwo.isSelected()) {
					questionSevenAnswer.setText(currentLanguage
							.getString("qSevenAnsTwo"));
				} else if (questionSevenSelectionThree.isSelected()) {
					questionSevenAnswer.setText(currentLanguage
							.getString("qSevenAnsThree"));
				} else if (questionSevenSelectionFour.isSelected()) {
					questionSevenAnswer.setText(currentLanguage
							.getString("qSevenAnsFour"));
				}

				final JLabel questionSeven;
				questionSeven = new JLabel(currentLanguage.getString("qSeven"));
				questionSeven.setFont(new Font("Tahoma", Font.BOLD, 40));
				questionSeven.setForeground(Color.BLUE);
				constrainsQuestionNine.gridx = 0;
				constrainsQuestionNine.gridy = 3;
				panelQuestionNine.add(questionSeven, constrainsQuestionNine);
				if (questionSevenSelectionOne.isSelected()
						|| questionSevenSelectionTwo.isSelected()
						|| questionSevenSelectionThree.isSelected()
						|| questionSevenSelectionFour.isSelected()) {
					cl.show(panelContainer, "8");
				}
			}
		});

		// Panel 8

		JLabel reviewlabel;
		reviewlabel = new JLabel(currentLanguage.getString("qReview"));
		reviewlabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		constrainsQuestionEight.gridx = 0;
		constrainsQuestionEight.gridy = 0;
		panelQuestionEight.add(reviewlabel, constrainsQuestionEight);

		JButton backToQuestionSeven;
		backToQuestionSeven = new JButton(
				currentLanguage.getString("previousQuestion"));
		backToQuestionSeven.setFont(new Font("Tahoma", Font.BOLD, 40));
		backToQuestionSeven.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionEight.gridx = 0;
		constrainsQuestionEight.gridy = 9;
		panelQuestionEight.add(backToQuestionSeven, constrainsQuestionEight);
		backToQuestionSeven.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (questionFiveSelectionOne.isSelected()) {
					cl.show(panelContainer, "5");
				} else if (questionSixSelectionOne.isSelected()) {
					cl.show(panelContainer, "6");
				} else {
					cl.show(panelContainer, "7");
				}
			}
		});

		JButton toQuestionNine;
		toQuestionNine = new JButton(currentLanguage.getString("nextQuestion"));
		toQuestionNine.setFont(new Font("Tahoma", Font.BOLD, 40));
		toQuestionNine.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionEight.gridx = 1;
		constrainsQuestionEight.gridy = 9;
		panelQuestionEight.add(toQuestionNine, constrainsQuestionEight);
		toQuestionNine.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "9");
			}
		});

		// Panel 9

		reviewlabel = new JLabel(currentLanguage.getString("qReview"));
		reviewlabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		constrainsQuestionNine.gridx = 0;
		constrainsQuestionNine.gridy = 0;
		panelQuestionNine.add(reviewlabel, constrainsQuestionNine);

		JButton backToQuestionEight;
		backToQuestionEight = new JButton(
				currentLanguage.getString("previousQuestion"));
		backToQuestionEight.setFont(new Font("Tahoma", Font.BOLD, 40));
		backToQuestionEight.setPreferredSize(new Dimension(300,125));
		constrainsQuestionNine.gridx = 0;
		constrainsQuestionNine.gridy = 7;
		panelQuestionNine.add(backToQuestionEight, constrainsQuestionNine);
		backToQuestionEight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "8");
			}
		});
		
		JButton resetQuestionnaire;
		resetQuestionnaire = new JButton(currentLanguage.getString("reset"));
		resetQuestionnaire.setFont(new Font("Tahoma", Font.BOLD, 40));
		resetQuestionnaire.setPreferredSize(new Dimension(300, 125));
		constrainsQuestionNine.gridx = 1;
		constrainsQuestionNine.gridy = 7;
		panelQuestionNine.add(resetQuestionnaire, constrainsQuestionNine);
		resetQuestionnaire.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				questionSevenAnswer.setText(null);

				questionOneGroup.clearSelection();
				questionTwoGroup.clearSelection();
				questionThreeGroup.clearSelection();
				questionFourGroup.clearSelection();
				questionFiveGroup.clearSelection();
				questionSixGroup.clearSelection();
				questionSevenGroup.clearSelection();
				cl.show(panelContainer, "1");
			}
		});

		JButton submitQuestionnare;
		submitQuestionnare = new JButton(currentLanguage.getString("submit"));
		submitQuestionnare.setFont(new Font("Tahoma", Font.BOLD, 40));
		submitQuestionnare.setPreferredSize(new Dimension(300, 125));
		constrainsQuestionNine.gridx = 0;
		constrainsQuestionNine.gridy = 8;
		panelQuestionNine.add(submitQuestionnare, constrainsQuestionNine);

		submitQuestionnare.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					if (questionOneSelectionTwo.isSelected()) {
						sQuestionOneAnswer = "2";
					} else if (questionOneSelectionThree.isSelected()) {
						sQuestionOneAnswer = "3";
					} else if (questionOneSelectionFour.isSelected()) {
						sQuestionOneAnswer = "4";
					}

					if (questionTwoSelectionTwo.isSelected()) {
						sQuestionTwoAnswer = "2";
					} else if (questionTwoSelectionThree.isSelected()) {
						sQuestionTwoAnswer = "3";
					} else if (questionTwoSelectionFour.isSelected()) {
						sQuestionTwoAnswer = "4";
					}

					if (questionThreeSelectionTwo.isSelected()) {
						sQuestionThreeAnswer = "2";
					} else if (questionThreeSelectionThree.isSelected()) {
						sQuestionThreeAnswer = "3";
					} else if (questionThreeSelectionFour.isSelected()) {
						sQuestionThreeAnswer = "4";
					}

					if (questionFourSelectionOne.isSelected()) {
						sQuestionFourAnswer = "2";
					} else if (questionFourSelectionTwo.isSelected()) {
						sQuestionFourAnswer = "3";
					}

					if (questionFiveSelectionOne.isSelected()) {
						sQuestionFiveAnswer = "1";
					} else if (questionFiveSelectionTwo.isSelected()) {
						sQuestionFiveAnswer = "2";
					} else if (questionFiveSelectionThree.isSelected()) {
						sQuestionFiveAnswer = "3";
					} else if (questionFiveSelectionFour.isSelected()) {
						sQuestionFiveAnswer = "4";
					}

					if (questionSixSelectionOne.isSelected()) {
						sQuestionSixAnswer = "0";
					} else if (questionSixSelectionTwo.isSelected()) {
						sQuestionSixAnswer = "1";
					} else if (questionSixSelectionThree.isSelected()) {
						sQuestionSixAnswer = "2";
					} else if (questionSixSelectionFour.isSelected()) {
						sQuestionSixAnswer = "3";
					} else if (questionSixSelectionFive.isSelected()) {
						sQuestionSixAnswer = "4";
					} else if (questionSixSelectionSix.isSelected()) {
						sQuestionSixAnswer = "5";
					}

					if (questionSevenSelectionOne.isSelected()) {
						sQuestionSevenAnswer = "1";
					} else if (questionSevenSelectionTwo.isSelected()) {
						sQuestionSevenAnswer = "2";
					} else if (questionSevenSelectionThree.isSelected()) {
						sQuestionSevenAnswer = "3";
					} else if (questionSevenSelectionFour.isSelected()) {
						sQuestionSevenAnswer = "4";
					}

					System.out.println("Trying to send data...");
					new Thread(new Runnable() {

						@Override
						public void run() {
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									ClassLoader cldr = this.getClass()
											.getClassLoader();
									java.net.URL imageURL = cldr
											.getResource("sending.gif");
									ImageIcon sendingImage = new ImageIcon(
											imageURL);
									final JDialog dialog = new JDialog();
									dialog.setPreferredSize(new Dimension(380, 170));
									dialog.setTitle(currentLanguage.getString("sendingAnswers"));
									dialog.setName("TemporarySend");
									dialog.setUndecorated(false);
									JPanel panel = new JPanel();
									final JLabel label = new JLabel(
											sendingImage);
									sendingImage.setImageObserver(label);
									panel.add(label);
									dialog.getContentPane().add(panel);
									dialog.pack();
									// Public method to center the dialog after
									try {
										dialog.setLocationRelativeTo(getView()
												.getParent());
									} catch (IOException e) {
										System.out.println("Can't get View");
									}
									dialog.setVisible(true);
								}
							});

							updateSendGui();
							System.out.println("\nDaily sent status " + statusDaily);
							
							try {
								sendEHRToDB();
								sendDailyValuesToDB();
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}).start();

				}

				catch (HeadlessException e1) {
					e1.printStackTrace();
				}
				Window w = SwingUtilities.getWindowAncestor(panelContainer);
				w.dispose();
			}
		
		});
		return panelContainer;

	}

	private HttpClient getNewHttpClient() {
		// TODO Auto-generated method stub
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);

			SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 8080));
			registry.register(new Scheme("https", sf, 8181));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);

			return new DefaultHttpClient(ccm, params);
		} catch (Exception e) {
			return new DefaultHttpClient();
		}
	}

	public void updateSendGui() {

		String username = HealthProperties.getProperty("iipUsername");
		String password = HealthProperties.getProperty("iipPassword");
		String iipUrl = HealthProperties.getProperty("iipUrl");
		String questionnaireChannel = HealthProperties
				.getProperty("questionnaireChannel");
		String patientId = HealthProperties.getProperty("patientId");
		String URL = "https://" + username + ":" + password + "@" + iipUrl
				+ questionnaireChannel;

		HttpClient httpclient = getNewHttpClient();
		HttpPost httpPost = new HttpPost(URL);
		HttpResponse response = null;

		// Array to send to IIP
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("q_1", sQuestionOneAnswer));
		params.add(new BasicNameValuePair("q_2", sQuestionTwoAnswer));
		params.add(new BasicNameValuePair("q_3", sQuestionThreeAnswer));
		params.add(new BasicNameValuePair("q_4", sQuestionFourAnswer));
		params.add(new BasicNameValuePair("q_5", sQuestionFiveAnswer));
		params.add(new BasicNameValuePair("q_6", sQuestionSixAnswer));
		params.add(new BasicNameValuePair("q_7", sQuestionSevenAnswer));
		params.add(new BasicNameValuePair("patientId", patientId));
		params.add(new BasicNameValuePair("dateTime", timeAndDate));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			response = httpclient.execute(httpPost);
			System.out.println("\nTesting sending daily questionnaire: "
					+ response.getStatusLine());
			
			if (response.getStatusLine().getStatusCode() == 200 ) {
				statusDaily = 1;
			} else 
				statusDaily = 0;
			
		} catch (ClientProtocolException e1) {
			System.out.println(e1.toString());
		} catch (IOException e1) {
			System.out.println(e1.toString());
		} finally {
			Utilities.disposeDialog(null);
		}
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = response.getEntity().getContent();
			Document doc = db.parse(is);
			Element root2 = doc.getDocumentElement();
			printAllNodes(root2);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	// Inserting EHR column in DB

			public void sendEHRToDB() throws Exception {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connect = (Connection) DriverManager
							.getConnection("jdbc:mysql://localhost:3306/" + dbName
									+ "?user=" + dbUsername + "&password=" + dbPassword);

					statement = (Statement) connect.createStatement();
					statement.executeUpdate("INSERT INTO " + dbName + ".EHR" + 
											"(EHRID, pasientID, conceptIDFromConcept, EHRDateTime, EHRSentStatus) " + 
											"VALUES (NULL, " + HealthProperties.getProperty("patientId")
											+ ", 4, '" + timeAndDate + "', " + statusDaily + ");"
													);
					statement = null;
					System.out.println("Successfully inserted into EHR table");
					
				} catch (Exception e) {
					e.printStackTrace();
					} finally {
						closeForEHR();
				}
			} 
			// closing
			      
			private void closeForEHR() {
				try {
					
					if (statement != null) {
						statement.close();
					}

					if (connect != null) {
						connect.close();
					}
				} catch (Exception e) {

				}
			}
	
	
	// Questionnaire values to DB

		public void sendDailyValuesToDB() throws Exception {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connect = (Connection) DriverManager
						.getConnection("jdbc:mysql://localhost:3306/" + dbName
								+ "?user=" + dbUsername + "&password=" + dbPassword);

				Statement s = connect.createStatement();
				s.executeQuery("SELECT `EHRID` FROM `EHR` WHERE `conceptIDFromConcept` = 4 ORDER BY EHRID DESC LIMIT 1");
				ResultSet rs = s.getResultSet();
				rs.next();
				int EHRID = rs.getInt("EHRID");
				System.out.println("Latest EHRID read from DB = " + EHRID);
				
				statement = (Statement) connect.createStatement();
				statement.executeUpdate("INSERT INTO " + dbName + ".EHRContent" + 
										"(EHRContentID, EHRIDFromEHR, parameterIDFromConceptParameters, parameterValue) " + 
											"VALUES (NULL, " + EHRID + ", 19, " + sQuestionOneAnswer + "),"
												+ " (NULL, " + EHRID + ", 20, " + sQuestionTwoAnswer + "),"
												+ " (NULL, " + EHRID + ", 21, " + sQuestionThreeAnswer + "),"
												+ " (NULL, " + EHRID + ", 22, " + sQuestionFourAnswer + "),"
												+ " (NULL, " + EHRID + ", 22, " + sQuestionFiveAnswer + "),"
												+ " (NULL, " + EHRID + ", 24, " + sQuestionSixAnswer + "),"
												+ " (NULL, " + EHRID + ", 25, " + sQuestionSevenAnswer + ")"
												+ ";");
				statement = null;
				System.out.println("Successfully inserted into EHRContent table");
						
			} catch (Exception e) {
				e.printStackTrace();
				} finally {
					closeForEHRContent();
			}
		}

		// closing
		      
		private void closeForEHRContent() {
			try {
				if (resultSet != null) {
					resultSet.close();
				}

				if (statement != null) {
					statement.close();
				}

				if (connect != null) {
					connect.close();
				}
			} catch (Exception e) {

			}
		}
			
	//Print all nodes
		
	private static void printAllNodes(Node doc) {
		// System.out.println("Node: " + doc.getNodeName() + ", Value: " +
		// doc.getNodeValue());
		System.out.print("Tag: " + doc.getNodeName());
		NodeList childrenNodes = doc.getChildNodes();
		for (int i = 0; i < childrenNodes.getLength(); i++) {
			Node childNode = childrenNodes.item(i);
			if (childNode instanceof Element) {
				System.out.println();
				printAllNodes(childNode);
			} else {
				System.out.print(", Value: " + childNode.getNodeValue());
			}
		}
	}
}
