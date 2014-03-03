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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

import sun.java2d.Disposer;

public class CatQuestionnaire extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6027479319491254860L;

	String sQuestionOneAnswer = null;
	String sQuestionTwoAnswer = null;
	String sQuestionThreeAnswer = null;
	String sQuestionFourAnswer = null;
	String sQuestionFiveAnswer = null;
	String sQuestionSixAnswer = null;
	String sQuestionSevenAnswer = null;
	String sQuestionEightAnswer = null;

	public Component getView() throws IOException {
		JComponent questionnare = createPanel("Cat Questions");
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
		panelContainer.setBackground(Color.GREEN);
		panelQuestionOne.setBackground(Color.GREEN);
		panelQuestionTwo.setBackground(Color.GREEN);
		panelQuestionThree.setBackground(Color.GREEN);
		panelQuestionFour.setBackground(Color.GREEN);
		panelQuestionFive.setBackground(Color.GREEN);
		panelQuestionSix.setBackground(Color.GREEN);
		panelQuestionSeven.setBackground(Color.GREEN);
		panelQuestionEight.setBackground(Color.GREEN);
		panelQuestionNine.setBackground(Color.GREEN);

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

		GridBagLayout gblNine = new GridBagLayout();
		panelQuestionNine.setLayout(gblNine);

		ClassLoader customRadioButton = this.getClass().getClassLoader();

		URL iconUrlON = customRadioButton.getResource("radioButtonCatON.png");
		Icon radioIconON = new ImageIcon(iconUrlON);

		URL iconUrlOFF = customRadioButton.getResource("radioButtonOFF.png");
		Icon radioIconOFF = new ImageIcon(iconUrlOFF);

		URL urlIconZero = customRadioButton.getResource("radioButtonZero.png");
		Icon radioIconZero = new ImageIcon(urlIconZero);

		URL urlIconOne = customRadioButton.getResource("radioButtonOne.png");
		Icon radioIconOne = new ImageIcon(urlIconOne);

		URL urlIconTwo = customRadioButton.getResource("radioButtonTwo.png");
		Icon radioIconTwo = new ImageIcon(urlIconTwo);

		URL urlIconThree = customRadioButton
				.getResource("radioButtonThree.png");
		Icon radioIconThree = new ImageIcon(urlIconThree);

		URL urlIconFour = customRadioButton.getResource("radioButtonFour.png");
		Icon radioIconFour = new ImageIcon(urlIconFour);

		URL urlIconFive = customRadioButton.getResource("radioButtonFive.png");
		Icon radioIconFive = new ImageIcon(urlIconFive);

		// adding Question one

		final GridBagConstraints constrainsQuestionOne = new GridBagConstraints();
		constrainsQuestionOne.anchor = GridBagConstraints.FIRST_LINE_START;

		final JLabel questionOneLeft;
		questionOneLeft = new JLabel(currentLanguage.getString("qCatOneLeft"));
		questionOneLeft.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionOneLeft.setForeground(Color.BLACK);
		constrainsQuestionOne.gridx = 0;
		constrainsQuestionOne.gridy = 2;
		panelQuestionOne.add(questionOneLeft, constrainsQuestionOne);

		final JLabel questionOneRight;
		questionOneRight = new JLabel(currentLanguage.getString("qCatOneRight"));
		questionOneRight.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionOneRight.setForeground(Color.BLACK);
		constrainsQuestionOne.gridx = 7;
		constrainsQuestionOne.gridy = 2;
		panelQuestionOne.add(questionOneRight, constrainsQuestionOne);

		final JLabel questionOne;
		questionOne = new JLabel(currentLanguage.getString("questionOne"));
		questionOne.setFont(new Font("Tahoma", Font.BOLD, 50));
		questionOne.setForeground(Color.BLUE);
		constrainsQuestionOne.gridx = 0;
		constrainsQuestionOne.gridy = 0;
		panelQuestionOne.add(questionOne, constrainsQuestionOne);

		final JRadioButton questionOneSelectionZero;
		questionOneSelectionZero = new JRadioButton();
		questionOneSelectionZero.setBackground(Color.GREEN);
		questionOneSelectionZero.setIcon(radioIconZero);
		questionOneSelectionZero.setSelectedIcon(radioIconON);
		constrainsQuestionOne.gridx = 1;
		constrainsQuestionOne.gridy = 2;
		panelQuestionOne.add(questionOneSelectionZero, constrainsQuestionOne);
		questionOneSelectionZero.setActionCommand("0");

		final JRadioButton questionOneSelectionOne;
		questionOneSelectionOne = new JRadioButton();
		questionOneSelectionOne.setBackground(Color.GREEN);
		questionOneSelectionOne.setIcon(radioIconOne);
		questionOneSelectionOne.setSelectedIcon(radioIconON);
		constrainsQuestionOne.gridx = 2;
		constrainsQuestionOne.gridy = 2;
		panelQuestionOne.add(questionOneSelectionOne, constrainsQuestionOne);
		questionOneSelectionOne.setActionCommand("1");

		final JRadioButton questionOneSelectionTwo;
		questionOneSelectionTwo = new JRadioButton();
		questionOneSelectionTwo.setBackground(Color.GREEN);
		questionOneSelectionTwo.setIcon(radioIconTwo);
		questionOneSelectionTwo.setSelectedIcon(radioIconON);
		constrainsQuestionOne.gridx = 3;
		constrainsQuestionOne.gridy = 2;
		panelQuestionOne.add(questionOneSelectionTwo, constrainsQuestionOne);
		questionOneSelectionTwo.setActionCommand("2");

		final JRadioButton questionOneSelectionThree;
		questionOneSelectionThree = new JRadioButton();
		questionOneSelectionThree.setBackground(Color.GREEN);
		questionOneSelectionThree.setIcon(radioIconThree);
		questionOneSelectionThree.setSelectedIcon(radioIconON);
		constrainsQuestionOne.gridx = 4;
		constrainsQuestionOne.gridy = 2;
		panelQuestionOne.add(questionOneSelectionThree, constrainsQuestionOne);
		questionOneSelectionThree.setActionCommand("3");

		final JRadioButton questionOneSelectionFour;
		questionOneSelectionFour = new JRadioButton();
		questionOneSelectionFour.setBackground(Color.GREEN);
		questionOneSelectionFour.setIcon(radioIconFour);
		questionOneSelectionFour.setSelectedIcon(radioIconON);
		constrainsQuestionOne.gridx = 5;
		constrainsQuestionOne.gridy = 2;
		panelQuestionOne.add(questionOneSelectionFour, constrainsQuestionOne);
		questionOneSelectionFour.setActionCommand("4");

		final JRadioButton questionOneSelectionFive;
		questionOneSelectionFive = new JRadioButton();
		questionOneSelectionFive.setBackground(Color.GREEN);
		questionOneSelectionFive.setIcon(radioIconFive);
		questionOneSelectionFive.setSelectedIcon(radioIconON);
		constrainsQuestionOne.gridx = 6;
		constrainsQuestionOne.gridy = 2;
		panelQuestionOne.add(questionOneSelectionFive, constrainsQuestionOne);
		questionOneSelectionFive.setActionCommand("5");

		final ButtonGroup questionOneGroup = new ButtonGroup();
		questionOneGroup.add(questionOneSelectionZero);
		questionOneGroup.add(questionOneSelectionOne);
		questionOneGroup.add(questionOneSelectionTwo);
		questionOneGroup.add(questionOneSelectionThree);
		questionOneGroup.add(questionOneSelectionFour);
		questionOneGroup.add(questionOneSelectionFive);

		JButton toQuestionTwo;
		toQuestionTwo = new JButton(currentLanguage.getString("nextQuestion"));
		toQuestionTwo.setFont(new Font("Tahoma", Font.BOLD, 40));
		toQuestionTwo.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionOne.gridx = 7;
		constrainsQuestionOne.gridy = 3;
		panelQuestionOne.add(toQuestionTwo, constrainsQuestionOne);
		toQuestionTwo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (questionOneSelectionZero.isSelected()
						|| questionOneSelectionOne.isSelected()
						|| questionOneSelectionTwo.isSelected()
						|| questionOneSelectionThree.isSelected()
						|| questionOneSelectionFour.isSelected()
						|| questionOneSelectionFive.isSelected()) {
					cl.show(panelContainer, "2");
				}
			}
		});

		// Question Two

		GridBagConstraints constrainsQuestionTwo = new GridBagConstraints();
		constrainsQuestionTwo.anchor = GridBagConstraints.FIRST_LINE_START;

		final JLabel questionTwo;
		questionTwo = new JLabel(currentLanguage.getString("questionTwo"));
		questionTwo.setFont(new Font("Tahoma", Font.BOLD, 50));
		questionTwo.setForeground(Color.BLUE);
		constrainsQuestionTwo.gridx = 0;
		constrainsQuestionTwo.gridy = 0;
		panelQuestionTwo.add(questionTwo, constrainsQuestionTwo);

		final JLabel questionTwoLeft;
		questionTwoLeft = new JLabel(currentLanguage.getString("qCatTwoLeft"));
		questionTwoLeft.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionTwoLeft.setForeground(Color.BLACK);
		constrainsQuestionTwo.gridx = 0;
		constrainsQuestionTwo.gridy = 2;
		panelQuestionTwo.add(questionTwoLeft, constrainsQuestionTwo);

		final JLabel questionTwoRight;
		questionTwoRight = new JLabel(currentLanguage.getString("qCatTwoRight"));
		questionTwoRight.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionTwoRight.setForeground(Color.BLACK);
		constrainsQuestionTwo.gridx = 7;
		constrainsQuestionTwo.gridy = 2;
		panelQuestionTwo.add(questionTwoRight, constrainsQuestionTwo);

		final JRadioButton questionTwoSelectionZero;
		questionTwoSelectionZero = new JRadioButton();
		questionTwoSelectionZero.setBackground(Color.GREEN);
		questionTwoSelectionZero.setIcon(radioIconZero);
		questionTwoSelectionZero.setSelectedIcon(radioIconON);
		constrainsQuestionTwo.gridx = 1;
		constrainsQuestionTwo.gridy = 2;
		panelQuestionTwo.add(questionTwoSelectionZero, constrainsQuestionTwo);
		questionTwoSelectionZero.setActionCommand("0");

		final JRadioButton questionTwoSelectionOne;
		questionTwoSelectionOne = new JRadioButton();
		questionTwoSelectionOne.setBackground(Color.GREEN);
		questionTwoSelectionOne.setIcon(radioIconOne);
		questionTwoSelectionOne.setSelectedIcon(radioIconON);
		constrainsQuestionTwo.gridx = 2;
		constrainsQuestionTwo.gridy = 2;
		panelQuestionTwo.add(questionTwoSelectionOne, constrainsQuestionTwo);
		questionTwoSelectionOne.setActionCommand("1");

		final JRadioButton questionTwoSelectionTwo;
		questionTwoSelectionTwo = new JRadioButton();
		questionTwoSelectionTwo.setBackground(Color.GREEN);
		questionTwoSelectionTwo.setIcon(radioIconTwo);
		questionTwoSelectionTwo.setSelectedIcon(radioIconON);
		constrainsQuestionTwo.gridx = 3;
		constrainsQuestionTwo.gridy = 2;
		panelQuestionTwo.add(questionTwoSelectionTwo, constrainsQuestionTwo);
		questionTwoSelectionTwo.setActionCommand("2");

		final JRadioButton questionTwoSelectionThree;
		questionTwoSelectionThree = new JRadioButton();
		questionTwoSelectionThree.setBackground(Color.GREEN);
		questionTwoSelectionThree.setIcon(radioIconThree);
		questionTwoSelectionThree.setSelectedIcon(radioIconON);
		constrainsQuestionTwo.gridx = 4;
		constrainsQuestionTwo.gridy = 2;
		panelQuestionTwo.add(questionTwoSelectionThree, constrainsQuestionTwo);
		questionTwoSelectionThree.setActionCommand("3");

		final JRadioButton questionTwoSelectionFour;
		questionTwoSelectionFour = new JRadioButton();
		questionTwoSelectionFour.setBackground(Color.GREEN);
		questionTwoSelectionFour.setIcon(radioIconFour);
		questionTwoSelectionFour.setSelectedIcon(radioIconON);
		constrainsQuestionTwo.gridx = 5;
		constrainsQuestionTwo.gridy = 2;
		panelQuestionTwo.add(questionTwoSelectionFour, constrainsQuestionTwo);
		questionTwoSelectionFour.setActionCommand("4");

		final JRadioButton questionTwoSelectionFive;
		questionTwoSelectionFive = new JRadioButton();
		questionTwoSelectionFive.setBackground(Color.GREEN);
		questionTwoSelectionFive.setIcon(radioIconFive);
		questionTwoSelectionFive.setSelectedIcon(radioIconON);
		constrainsQuestionTwo.gridx = 6;
		constrainsQuestionTwo.gridy = 2;
		panelQuestionTwo.add(questionTwoSelectionFive, constrainsQuestionTwo);
		questionTwoSelectionFive.setActionCommand("5");

		final ButtonGroup questionTwoGroup = new ButtonGroup();
		questionTwoGroup.add(questionTwoSelectionZero);
		questionTwoGroup.add(questionTwoSelectionOne);
		questionTwoGroup.add(questionTwoSelectionTwo);
		questionTwoGroup.add(questionTwoSelectionThree);
		questionTwoGroup.add(questionTwoSelectionFour);
		questionTwoGroup.add(questionTwoSelectionFive);

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

		JButton toQuestionThree;
		toQuestionThree = new JButton(currentLanguage.getString("nextQuestion"));
		toQuestionThree.setFont(new Font("Tahoma", Font.BOLD, 40));
		toQuestionThree.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionTwo.gridx = 7;
		constrainsQuestionTwo.gridy = 7;
		panelQuestionTwo.add(toQuestionThree, constrainsQuestionTwo);
		toQuestionThree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (questionTwoSelectionZero.isSelected()
						|| questionTwoSelectionOne.isSelected()
						|| questionTwoSelectionTwo.isSelected()
						|| questionTwoSelectionThree.isSelected()
						|| questionTwoSelectionFour.isSelected()
						|| questionTwoSelectionFive.isSelected()) {
					cl.show(panelContainer, "3");
				}
				;
			}
		});

		// Question Three

		GridBagConstraints constrainsQuestionThree = new GridBagConstraints();
		constrainsQuestionThree.anchor = GridBagConstraints.FIRST_LINE_START;

		final JLabel questionThree;
		questionThree = new JLabel(currentLanguage.getString("questionThree"));
		questionThree.setFont(new Font("Tahoma", Font.BOLD, 50));
		questionThree.setForeground(Color.BLUE);
		constrainsQuestionThree.gridx = 0;
		constrainsQuestionThree.gridy = 0;
		panelQuestionThree.add(questionThree, constrainsQuestionThree);

		final JLabel questionThreeLeft;
		questionThreeLeft = new JLabel(
				currentLanguage.getString("qCatThreeLeft"));
		questionThreeLeft.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionThreeLeft.setForeground(Color.BLACK);
		constrainsQuestionThree.gridx = 0;
		constrainsQuestionThree.gridy = 2;
		panelQuestionThree.add(questionThreeLeft, constrainsQuestionThree);

		final JLabel questionThreeRight;
		questionThreeRight = new JLabel(
				currentLanguage.getString("qCatThreeRight"));
		questionThreeRight.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionThreeRight.setForeground(Color.BLACK);
		constrainsQuestionThree.gridx = 7;
		constrainsQuestionThree.gridy = 2;
		panelQuestionThree.add(questionThreeRight, constrainsQuestionThree);

		final JRadioButton questionThreeSelectionZero;
		questionThreeSelectionZero = new JRadioButton();
		questionThreeSelectionZero.setBackground(Color.GREEN);
		questionThreeSelectionZero.setIcon(radioIconZero);
		questionThreeSelectionZero.setSelectedIcon(radioIconON);
		constrainsQuestionThree.gridx = 1;
		constrainsQuestionThree.gridy = 2;
		panelQuestionThree.add(questionThreeSelectionZero,
				constrainsQuestionThree);
		questionThreeSelectionZero.setActionCommand("0");

		final JRadioButton questionThreeSelectionOne;
		questionThreeSelectionOne = new JRadioButton();
		questionThreeSelectionOne.setBackground(Color.GREEN);
		questionThreeSelectionOne.setIcon(radioIconOne);
		questionThreeSelectionOne.setSelectedIcon(radioIconON);
		constrainsQuestionThree.gridx = 2;
		constrainsQuestionThree.gridy = 2;
		panelQuestionThree.add(questionThreeSelectionOne,
				constrainsQuestionThree);
		questionThreeSelectionOne.setActionCommand("1");

		final JRadioButton questionThreeSelectionTwo;
		questionThreeSelectionTwo = new JRadioButton();
		questionThreeSelectionTwo.setBackground(Color.GREEN);
		questionThreeSelectionTwo.setIcon(radioIconTwo);
		questionThreeSelectionTwo.setSelectedIcon(radioIconON);
		constrainsQuestionThree.gridx = 3;
		constrainsQuestionThree.gridy = 2;
		panelQuestionThree.add(questionThreeSelectionTwo,
				constrainsQuestionThree);
		questionThreeSelectionTwo.setActionCommand("2");

		final JRadioButton questionThreeSelectionThree;
		questionThreeSelectionThree = new JRadioButton();
		questionThreeSelectionThree.setBackground(Color.GREEN);
		questionThreeSelectionThree.setIcon(radioIconThree);
		questionThreeSelectionThree.setSelectedIcon(radioIconON);
		constrainsQuestionThree.gridx = 4;
		constrainsQuestionThree.gridy = 2;
		panelQuestionThree.add(questionThreeSelectionThree,
				constrainsQuestionThree);
		questionThreeSelectionThree.setActionCommand("3");

		final JRadioButton questionThreeSelectionFour;
		questionThreeSelectionFour = new JRadioButton();
		questionThreeSelectionFour.setBackground(Color.GREEN);
		questionThreeSelectionFour.setIcon(radioIconFour);
		questionThreeSelectionFour.setSelectedIcon(radioIconON);
		constrainsQuestionThree.gridx = 5;
		constrainsQuestionThree.gridy = 2;
		panelQuestionThree.add(questionThreeSelectionFour,
				constrainsQuestionThree);
		questionThreeSelectionFour.setActionCommand("4");

		final JRadioButton questionThreeSelectionFive;
		questionThreeSelectionFive = new JRadioButton();
		questionThreeSelectionFive.setBackground(Color.GREEN);
		questionThreeSelectionFive.setIcon(radioIconFive);
		questionThreeSelectionFive.setSelectedIcon(radioIconON);
		constrainsQuestionThree.gridx = 6;
		constrainsQuestionThree.gridy = 2;
		panelQuestionThree.add(questionThreeSelectionFive,
				constrainsQuestionThree);
		questionThreeSelectionFive.setActionCommand("5");

		final ButtonGroup questionThreeGroup = new ButtonGroup();
		questionThreeGroup.add(questionThreeSelectionZero);
		questionThreeGroup.add(questionThreeSelectionOne);
		questionThreeGroup.add(questionThreeSelectionTwo);
		questionThreeGroup.add(questionThreeSelectionThree);
		questionThreeGroup.add(questionThreeSelectionFour);
		questionThreeGroup.add(questionThreeSelectionFive);

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

		JButton toQuestionFour;
		toQuestionFour = new JButton(currentLanguage.getString("nextQuestion"));
		toQuestionFour.setFont(new Font("Tahoma", Font.BOLD, 40));
		toQuestionFour.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionThree.gridx = 7;
		constrainsQuestionThree.gridy = 7;
		panelQuestionThree.add(toQuestionFour, constrainsQuestionThree);
		toQuestionFour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (questionThreeSelectionZero.isSelected()
						|| questionThreeSelectionOne.isSelected()
						|| questionThreeSelectionTwo.isSelected()
						|| questionThreeSelectionThree.isSelected()
						|| questionThreeSelectionFour.isSelected()
						|| questionThreeSelectionFive.isSelected()) {
					cl.show(panelContainer, "4");
				}
				;
			}
		});

		// Question Four

		GridBagConstraints constrainsQuestionFour = new GridBagConstraints();
		constrainsQuestionFour.anchor = GridBagConstraints.FIRST_LINE_START;

		final JLabel questionFour;
		questionFour = new JLabel(currentLanguage.getString("questionFour"));
		questionFour.setFont(new Font("Tahoma", Font.BOLD, 50));
		questionFour.setForeground(Color.BLUE);
		constrainsQuestionFour.gridx = 0;
		constrainsQuestionFour.gridy = 0;
		panelQuestionFour.add(questionFour, constrainsQuestionFour);

		final JLabel questionFourLeft;
		questionFourLeft = new JLabel(currentLanguage.getString("qCatFourLeft"));
		questionFourLeft.setFont(new Font("Tahoma", Font.BOLD, 40));
		constrainsQuestionFour.gridx = 0;
		constrainsQuestionFour.gridy = 2;
		panelQuestionFour.add(questionFourLeft, constrainsQuestionFour);

		final JLabel questionFourRight;
		questionFourRight = new JLabel(
				currentLanguage.getString("qCatFourRight"));
		questionFourRight.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionFourRight.setForeground(Color.BLACK);
		constrainsQuestionFour.gridx = 7;
		constrainsQuestionFour.gridy = 2;
		panelQuestionFour.add(questionFourRight, constrainsQuestionFour);

		final JRadioButton questionFourSelectionZero;
		questionFourSelectionZero = new JRadioButton();
		questionFourSelectionZero.setBackground(Color.GREEN);
		questionFourSelectionZero.setIcon(radioIconZero);
		questionFourSelectionZero.setSelectedIcon(radioIconON);
		constrainsQuestionFour.gridx = 1;
		constrainsQuestionFour.gridy = 2;
		panelQuestionFour
				.add(questionFourSelectionZero, constrainsQuestionFour);
		questionFourSelectionZero.setActionCommand("0");

		final JRadioButton questionFourSelectionOne;
		questionFourSelectionOne = new JRadioButton();
		questionFourSelectionOne.setBackground(Color.GREEN);
		questionFourSelectionOne.setIcon(radioIconOne);
		questionFourSelectionOne.setSelectedIcon(radioIconON);
		constrainsQuestionFour.gridx = 2;
		constrainsQuestionFour.gridy = 2;
		panelQuestionFour.add(questionFourSelectionOne, constrainsQuestionFour);
		questionFourSelectionOne.setActionCommand("1");

		final JRadioButton questionFourSelectionTwo;
		questionFourSelectionTwo = new JRadioButton();
		questionFourSelectionTwo.setBackground(Color.GREEN);
		questionFourSelectionTwo.setIcon(radioIconTwo);
		questionFourSelectionTwo.setSelectedIcon(radioIconON);
		constrainsQuestionFour.gridx = 3;
		constrainsQuestionFour.gridy = 2;
		panelQuestionFour.add(questionFourSelectionTwo, constrainsQuestionFour);
		questionFourSelectionTwo.setActionCommand("2");

		final JRadioButton questionFourSelectionThree;
		questionFourSelectionThree = new JRadioButton();
		questionFourSelectionThree.setBackground(Color.GREEN);
		questionFourSelectionThree.setIcon(radioIconThree);
		questionFourSelectionThree.setSelectedIcon(radioIconON);
		constrainsQuestionFour.gridx = 4;
		constrainsQuestionFour.gridy = 2;
		panelQuestionFour.add(questionFourSelectionThree,
				constrainsQuestionFour);
		questionFourSelectionThree.setActionCommand("3");

		final JRadioButton questionFourSelectionFour;
		questionFourSelectionFour = new JRadioButton();
		questionFourSelectionFour.setBackground(Color.GREEN);
		questionFourSelectionFour.setIcon(radioIconFour);
		questionFourSelectionFour.setSelectedIcon(radioIconON);
		constrainsQuestionFour.gridx = 5;
		constrainsQuestionFour.gridy = 2;
		panelQuestionFour
				.add(questionFourSelectionFour, constrainsQuestionFour);
		questionFourSelectionFour.setActionCommand("4");

		final JRadioButton questionFourSelectionFive;
		questionFourSelectionFive = new JRadioButton();
		questionFourSelectionFive.setBackground(Color.GREEN);
		questionFourSelectionFive.setIcon(radioIconFive);
		questionFourSelectionFive.setSelectedIcon(radioIconON);
		constrainsQuestionFour.gridx = 6;
		constrainsQuestionFour.gridy = 2;
		panelQuestionFour
				.add(questionFourSelectionFive, constrainsQuestionFour);
		questionFourSelectionFive.setActionCommand("5");

		final ButtonGroup questionFourGroup = new ButtonGroup();
		questionFourGroup.add(questionFourSelectionZero);
		questionFourGroup.add(questionFourSelectionOne);
		questionFourGroup.add(questionFourSelectionTwo);
		questionFourGroup.add(questionFourSelectionThree);
		questionFourGroup.add(questionFourSelectionFour);
		questionFourGroup.add(questionFourSelectionFive);

		JButton backToQuestionThree;
		backToQuestionThree = new JButton(
				currentLanguage.getString("previousQuestion"));
		backToQuestionThree.setFont(new Font("Tahoma", Font.BOLD, 40));
		backToQuestionThree.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionFour.ipadx = 80;
		constrainsQuestionFour.ipady = 60;
		constrainsQuestionFour.gridx = 0;
		constrainsQuestionFour.gridy = 7;
		panelQuestionFour.add(backToQuestionThree, constrainsQuestionFour);
		backToQuestionThree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "3");

			}
		});

		JButton toQuestionFive;
		toQuestionFive = new JButton(currentLanguage.getString("nextQuestion"));
		toQuestionFive.setFont(new Font("Tahoma", Font.BOLD, 40));
		toQuestionFive.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionFour.gridx = 7;
		constrainsQuestionFour.gridy = 7;
		panelQuestionFour.add(toQuestionFive, constrainsQuestionFour);
		toQuestionFive.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (questionFourSelectionZero.isSelected()
						|| questionFourSelectionOne.isSelected()
						|| questionFourSelectionTwo.isSelected()
						|| questionFourSelectionThree.isSelected()
						|| questionFourSelectionFour.isSelected()
						|| questionFourSelectionFive.isSelected()) {
					cl.show(panelContainer, "5");
				}
				;
			}
		});

		// Question Five

		GridBagConstraints constrainsQuestionFive = new GridBagConstraints();
		constrainsQuestionFive.anchor = GridBagConstraints.FIRST_LINE_START;

		final JLabel questionFive;
		questionFive = new JLabel(currentLanguage.getString("questionFive"));
		questionFive.setFont(new Font("Tahoma", Font.BOLD, 50));
		questionFive.setForeground(Color.BLUE);
		constrainsQuestionFive.gridx = 0;
		constrainsQuestionFive.gridy = 0;
		panelQuestionFive.add(questionFive, constrainsQuestionFive);

		final JLabel questionFiveLeft;
		questionFiveLeft = new JLabel(currentLanguage.getString("qCatFiveLeft"));
		questionFiveLeft.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionFiveLeft.setForeground(Color.BLACK);
		constrainsQuestionFive.gridx = 0;
		constrainsQuestionFive.gridy = 2;
		panelQuestionFive.add(questionFiveLeft, constrainsQuestionFive);

		final JLabel questionFiveRight;
		questionFiveRight = new JLabel(
				currentLanguage.getString("qCatFiveRight"));
		questionFiveRight.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionFiveRight.setForeground(Color.BLACK);
		constrainsQuestionFive.gridx = 7;
		constrainsQuestionFive.gridy = 2;
		panelQuestionFive.add(questionFiveRight, constrainsQuestionFive);

		final JRadioButton questionFiveSelectionZero;
		questionFiveSelectionZero = new JRadioButton();
		questionFiveSelectionZero.setBackground(Color.GREEN);
		questionFiveSelectionZero.setIcon(radioIconZero);
		questionFiveSelectionZero.setSelectedIcon(radioIconON);
		constrainsQuestionFive.gridx = 1;
		constrainsQuestionFive.gridy = 2;
		panelQuestionFive
				.add(questionFiveSelectionZero, constrainsQuestionFive);
		questionFiveSelectionZero.setActionCommand("0");

		final JRadioButton questionFiveSelectionOne;
		questionFiveSelectionOne = new JRadioButton();
		questionFiveSelectionOne.setBackground(Color.GREEN);
		questionFiveSelectionOne.setIcon(radioIconOne);
		questionFiveSelectionOne.setSelectedIcon(radioIconON);
		constrainsQuestionFive.gridx = 2;
		constrainsQuestionFive.gridy = 2;
		panelQuestionFive.add(questionFiveSelectionOne, constrainsQuestionFive);
		questionFiveSelectionOne.setActionCommand("1");

		final JRadioButton questionFiveSelectionTwo;
		questionFiveSelectionTwo = new JRadioButton();
		questionFiveSelectionTwo.setBackground(Color.GREEN);
		questionFiveSelectionTwo.setIcon(radioIconTwo);
		questionFiveSelectionTwo.setSelectedIcon(radioIconON);
		constrainsQuestionFive.gridx = 3;
		constrainsQuestionFive.gridy = 2;
		panelQuestionFive.add(questionFiveSelectionTwo, constrainsQuestionFive);
		questionFiveSelectionTwo.setActionCommand("2");

		final JRadioButton questionFiveSelectionThree;
		questionFiveSelectionThree = new JRadioButton();
		questionFiveSelectionThree.setBackground(Color.GREEN);
		questionFiveSelectionThree.setIcon(radioIconThree);
		questionFiveSelectionThree.setSelectedIcon(radioIconON);
		constrainsQuestionFive.gridx = 4;
		constrainsQuestionFive.gridy = 2;
		panelQuestionFive.add(questionFiveSelectionThree,
				constrainsQuestionFive);
		questionFiveSelectionThree.setActionCommand("3");

		final JRadioButton questionFiveSelectionFour;
		questionFiveSelectionFour = new JRadioButton();
		questionFiveSelectionFour.setBackground(Color.GREEN);
		questionFiveSelectionFour.setIcon(radioIconFour);
		questionFiveSelectionFour.setSelectedIcon(radioIconON);
		constrainsQuestionFive.gridx = 5;
		constrainsQuestionFive.gridy = 2;
		panelQuestionFive
				.add(questionFiveSelectionFour, constrainsQuestionFive);
		questionFiveSelectionFour.setActionCommand("4");

		final JRadioButton questionFiveSelectionFive;
		questionFiveSelectionFive = new JRadioButton();
		questionFiveSelectionFive.setBackground(Color.GREEN);
		questionFiveSelectionFive.setIcon(radioIconFive);
		questionFiveSelectionFive.setSelectedIcon(radioIconON);
		constrainsQuestionFive.gridx = 6;
		constrainsQuestionFive.gridy = 2;
		panelQuestionFive
				.add(questionFiveSelectionFive, constrainsQuestionFive);
		questionFiveSelectionFive.setActionCommand("5");

		final ButtonGroup questionFiveGroup = new ButtonGroup();
		questionFiveGroup.add(questionFiveSelectionZero);
		questionFiveGroup.add(questionFiveSelectionOne);
		questionFiveGroup.add(questionFiveSelectionTwo);
		questionFiveGroup.add(questionFiveSelectionThree);
		questionFiveGroup.add(questionFiveSelectionFour);
		questionFiveGroup.add(questionFiveSelectionFive);

		JButton backToQuestionFour;
		backToQuestionFour = new JButton(
				currentLanguage.getString("previousQuestion"));
		backToQuestionFour.setFont(new Font("Tahoma", Font.BOLD, 40));
		backToQuestionFour.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionFive.ipadx = 80;
		constrainsQuestionFive.ipady = 60;
		constrainsQuestionFive.gridx = 0;
		constrainsQuestionFive.gridy = 7;
		panelQuestionFive.add(backToQuestionFour, constrainsQuestionFive);
		backToQuestionFour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "4");

			}
		});

		JButton toQuestionSix;
		toQuestionSix = new JButton(currentLanguage.getString("nextQuestion"));
		toQuestionSix.setFont(new Font("Tahoma", Font.BOLD, 40));
		toQuestionSix.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionFive.gridx = 7;
		constrainsQuestionFive.gridy = 7;
		panelQuestionFive.add(toQuestionSix, constrainsQuestionFive);
		toQuestionSix.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (questionFiveSelectionZero.isSelected()
						|| questionFiveSelectionOne.isSelected()
						|| questionFiveSelectionTwo.isSelected()
						|| questionFiveSelectionThree.isSelected()
						|| questionFiveSelectionFour.isSelected()
						|| questionFiveSelectionFive.isSelected()) {
					cl.show(panelContainer, "6");
				}
				;
			}
		});

		// Question Six

		GridBagConstraints constrainsQuestionSix = new GridBagConstraints();
		constrainsQuestionSix.anchor = GridBagConstraints.FIRST_LINE_START;

		final JLabel questionSix;
		questionSix = new JLabel(currentLanguage.getString("questionSix"));
		questionSix.setFont(new Font("Tahoma", Font.BOLD, 50));
		questionSix.setForeground(Color.BLUE);
		constrainsQuestionSix.gridx = 0;
		constrainsQuestionSix.gridy = 0;
		panelQuestionSix.add(questionSix, constrainsQuestionSix);

		final JLabel questionSixLeft;
		questionSixLeft = new JLabel(currentLanguage.getString("qCatSixLeft"));
		questionSixLeft.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionSixLeft.setForeground(Color.BLACK);
		constrainsQuestionSix.gridx = 0;
		constrainsQuestionSix.gridy = 2;
		panelQuestionSix.add(questionSixLeft, constrainsQuestionSix);

		final JLabel questionSixRight;
		questionSixRight = new JLabel(currentLanguage.getString("qCatSixRight"));
		questionSixRight.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionSixRight.setForeground(Color.BLACK);
		constrainsQuestionSix.gridx = 7;
		constrainsQuestionSix.gridy = 2;
		panelQuestionSix.add(questionSixRight, constrainsQuestionSix);

		final JRadioButton questionSixSelectionZero;
		questionSixSelectionZero = new JRadioButton();
		questionSixSelectionZero.setBackground(Color.GREEN);
		questionSixSelectionZero.setIcon(radioIconZero);
		questionSixSelectionZero.setSelectedIcon(radioIconON);
		constrainsQuestionSix.gridx = 1;
		constrainsQuestionSix.gridy = 2;
		panelQuestionSix.add(questionSixSelectionZero, constrainsQuestionSix);
		questionSixSelectionZero.setActionCommand("0");

		final JRadioButton questionSixSelectionOne;
		questionSixSelectionOne = new JRadioButton();
		questionSixSelectionOne.setBackground(Color.GREEN);
		questionSixSelectionOne.setIcon(radioIconOne);
		questionSixSelectionOne.setSelectedIcon(radioIconON);
		constrainsQuestionSix.gridx = 2;
		constrainsQuestionSix.gridy = 2;
		panelQuestionSix.add(questionSixSelectionOne, constrainsQuestionSix);
		questionSixSelectionOne.setActionCommand("1");

		final JRadioButton questionSixSelectionTwo;
		questionSixSelectionTwo = new JRadioButton();
		questionSixSelectionTwo.setBackground(Color.GREEN);
		questionSixSelectionTwo.setIcon(radioIconTwo);
		questionSixSelectionTwo.setSelectedIcon(radioIconON);
		constrainsQuestionSix.gridx = 3;
		constrainsQuestionSix.gridy = 2;
		panelQuestionSix.add(questionSixSelectionTwo, constrainsQuestionSix);
		questionSixSelectionTwo.setActionCommand("2");

		final JRadioButton questionSixSelectionThree;
		questionSixSelectionThree = new JRadioButton();
		questionSixSelectionThree.setBackground(Color.GREEN);
		questionSixSelectionThree.setIcon(radioIconThree);
		questionSixSelectionThree.setSelectedIcon(radioIconON);
		constrainsQuestionSix.gridx = 4;
		constrainsQuestionSix.gridy = 2;
		panelQuestionSix.add(questionSixSelectionThree, constrainsQuestionSix);
		questionSixSelectionThree.setActionCommand("3");

		final JRadioButton questionSixSelectionFour;
		questionSixSelectionFour = new JRadioButton();
		questionSixSelectionFour.setBackground(Color.GREEN);
		questionSixSelectionFour.setIcon(radioIconFour);
		questionSixSelectionFour.setSelectedIcon(radioIconON);
		constrainsQuestionSix.gridx = 5;
		constrainsQuestionSix.gridy = 2;
		panelQuestionSix.add(questionSixSelectionFour, constrainsQuestionSix);
		questionSixSelectionFour.setActionCommand("4");

		final JRadioButton questionSixSelectionFive;
		questionSixSelectionFive = new JRadioButton();
		questionSixSelectionFive.setBackground(Color.GREEN);
		questionSixSelectionFive.setIcon(radioIconFive);
		questionSixSelectionFive.setSelectedIcon(radioIconON);
		constrainsQuestionSix.gridx = 6;
		constrainsQuestionSix.gridy = 2;
		panelQuestionSix.add(questionSixSelectionFive, constrainsQuestionSix);
		questionSixSelectionFive.setActionCommand("5");

		final ButtonGroup questionSixGroup = new ButtonGroup();
		questionSixGroup.add(questionSixSelectionZero);
		questionSixGroup.add(questionSixSelectionOne);
		questionSixGroup.add(questionSixSelectionTwo);
		questionSixGroup.add(questionSixSelectionThree);
		questionSixGroup.add(questionSixSelectionFour);
		questionSixGroup.add(questionSixSelectionFive);

		JButton backToQuestionFive;
		backToQuestionFive = new JButton(
				currentLanguage.getString("previousQuestion"));
		backToQuestionFive.setFont(new Font("Tahoma", Font.BOLD, 40));
		backToQuestionFive.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionSix.ipadx = 80;
		constrainsQuestionSix.ipady = 60;
		constrainsQuestionSix.gridx = 0;
		constrainsQuestionSix.gridy = 7;
		panelQuestionSix.add(backToQuestionFive, constrainsQuestionSix);
		backToQuestionFive.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "5");

			}
		});

		JButton toQuestionSeven;
		toQuestionSeven = new JButton(currentLanguage.getString("nextQuestion"));
		toQuestionSeven.setFont(new Font("Tahoma", Font.BOLD, 40));
		toQuestionSeven.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionSix.gridx = 7;
		constrainsQuestionSix.gridy = 7;
		panelQuestionSix.add(toQuestionSeven, constrainsQuestionSix);
		toQuestionSeven.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (questionSixSelectionZero.isSelected()
						|| questionSixSelectionOne.isSelected()
						|| questionSixSelectionTwo.isSelected()
						|| questionSixSelectionThree.isSelected()
						|| questionSixSelectionFour.isSelected()
						|| questionSixSelectionFive.isSelected()) {
					cl.show(panelContainer, "7");
				}
				;
			}
		});

		// Question Seven

		GridBagConstraints constrainsQuestionSeven = new GridBagConstraints();
		constrainsQuestionSeven.anchor = GridBagConstraints.FIRST_LINE_START;

		final JLabel questionSeven;
		questionSeven = new JLabel(currentLanguage.getString("questionSeven"));
		questionSeven.setFont(new Font("Tahoma", Font.BOLD, 50));
		questionSeven.setForeground(Color.BLUE);
		constrainsQuestionSeven.gridx = 0;
		constrainsQuestionSeven.gridy = 0;
		panelQuestionSeven.add(questionSeven, constrainsQuestionSeven);

		final JLabel questionSevenLeft;
		questionSevenLeft = new JLabel(
				currentLanguage.getString("qCatSevenLeft"));
		questionSevenLeft.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionSevenLeft.setForeground(Color.BLACK);
		constrainsQuestionSeven.gridx = 0;
		constrainsQuestionSeven.gridy = 2;
		panelQuestionSeven.add(questionSevenLeft, constrainsQuestionSeven);

		final JLabel questionSevenRight;
		questionSevenRight = new JLabel(
				currentLanguage.getString("qCatSevenRight"));
		questionSevenRight.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionSevenRight.setForeground(Color.BLACK);
		constrainsQuestionSeven.gridx = 7;
		constrainsQuestionSeven.gridy = 2;
		panelQuestionSeven.add(questionSevenRight, constrainsQuestionSeven);

		final JRadioButton questionSevenSelectionZero;
		questionSevenSelectionZero = new JRadioButton();
		questionSevenSelectionZero.setBackground(Color.GREEN);
		questionSevenSelectionZero.setIcon(radioIconZero);
		questionSevenSelectionZero.setSelectedIcon(radioIconON);
		constrainsQuestionSeven.gridx = 1;
		constrainsQuestionSeven.gridy = 2;
		panelQuestionSeven.add(questionSevenSelectionZero,
				constrainsQuestionSeven);
		questionSevenSelectionZero.setActionCommand("0");

		final JRadioButton questionSevenSelectionOne;
		questionSevenSelectionOne = new JRadioButton();
		questionSevenSelectionOne.setBackground(Color.GREEN);
		questionSevenSelectionOne.setIcon(radioIconOne);
		questionSevenSelectionOne.setSelectedIcon(radioIconON);
		constrainsQuestionSeven.gridx = 2;
		constrainsQuestionSeven.gridy = 2;
		panelQuestionSeven.add(questionSevenSelectionOne,
				constrainsQuestionSeven);
		questionSevenSelectionOne.setActionCommand("1");

		final JRadioButton questionSevenSelectionTwo;
		questionSevenSelectionTwo = new JRadioButton();
		questionSevenSelectionTwo.setBackground(Color.GREEN);
		questionSevenSelectionTwo.setIcon(radioIconTwo);
		questionSevenSelectionTwo.setSelectedIcon(radioIconON);
		constrainsQuestionSeven.gridx = 3;
		constrainsQuestionSeven.gridy = 2;
		panelQuestionSeven.add(questionSevenSelectionTwo,
				constrainsQuestionSeven);
		questionSevenSelectionTwo.setActionCommand("2");

		final JRadioButton questionSevenSelectionThree;
		questionSevenSelectionThree = new JRadioButton();
		questionSevenSelectionThree.setBackground(Color.GREEN);
		questionSevenSelectionThree.setIcon(radioIconThree);
		questionSevenSelectionThree.setSelectedIcon(radioIconON);
		constrainsQuestionSeven.gridx = 4;
		constrainsQuestionSeven.gridy = 2;
		panelQuestionSeven.add(questionSevenSelectionThree,
				constrainsQuestionSeven);
		questionSevenSelectionThree.setActionCommand("3");

		final JRadioButton questionSevenSelectionFour;
		questionSevenSelectionFour = new JRadioButton();
		questionSevenSelectionFour.setBackground(Color.GREEN);
		questionSevenSelectionFour.setIcon(radioIconFour);
		questionSevenSelectionFour.setSelectedIcon(radioIconON);
		constrainsQuestionSeven.gridx = 5;
		constrainsQuestionSeven.gridy = 2;
		panelQuestionSeven.add(questionSevenSelectionFour,
				constrainsQuestionSeven);
		questionSevenSelectionFour.setActionCommand("4");

		final JRadioButton questionSevenSelectionFive;
		questionSevenSelectionFive = new JRadioButton();
		questionSevenSelectionFive.setBackground(Color.GREEN);
		questionSevenSelectionFive.setIcon(radioIconFive);
		questionSevenSelectionFive.setSelectedIcon(radioIconON);
		constrainsQuestionSeven.gridx = 6;
		constrainsQuestionSeven.gridy = 2;
		panelQuestionSeven.add(questionSevenSelectionFive,
				constrainsQuestionSeven);
		questionSevenSelectionFive.setActionCommand("5");

		final ButtonGroup questionSevenGroup = new ButtonGroup();
		questionSevenGroup.add(questionSevenSelectionZero);
		questionSevenGroup.add(questionSevenSelectionOne);
		questionSevenGroup.add(questionSevenSelectionTwo);
		questionSevenGroup.add(questionSevenSelectionThree);
		questionSevenGroup.add(questionSevenSelectionFour);
		questionSevenGroup.add(questionSevenSelectionFive);

		JButton backToQuestionSix;
		backToQuestionSix = new JButton(
				currentLanguage.getString("previousQuestion"));
		backToQuestionSix.setFont(new Font("Tahoma", Font.BOLD, 40));
		backToQuestionSix.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionSeven.ipadx = 80;
		constrainsQuestionSeven.ipady = 60;
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

		JButton toQuestionEight;
		toQuestionEight = new JButton(currentLanguage.getString("nextQuestion"));
		toQuestionEight.setFont(new Font("Tahoma", Font.BOLD, 40));
		toQuestionEight.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionSeven.gridx = 7;
		constrainsQuestionSeven.gridy = 7;
		panelQuestionSeven.add(toQuestionEight, constrainsQuestionSeven);
		toQuestionEight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (questionSevenSelectionZero.isSelected()
						|| questionSevenSelectionOne.isSelected()
						|| questionSevenSelectionTwo.isSelected()
						|| questionSevenSelectionThree.isSelected()
						|| questionSevenSelectionFour.isSelected()
						|| questionSevenSelectionFive.isSelected()) {
					cl.show(panelContainer, "8");
				}
				;
			}
		});

		// Question Eight

		GridBagConstraints constrainsQuestionEight = new GridBagConstraints();
		constrainsQuestionEight.anchor = GridBagConstraints.FIRST_LINE_START;

		final JLabel questionEight;
		questionEight = new JLabel(currentLanguage.getString("questionEight"));
		questionEight.setFont(new Font("Tahoma", Font.BOLD, 50));
		questionEight.setForeground(Color.BLUE);
		constrainsQuestionEight.gridx = 0;
		constrainsQuestionEight.gridy = 0;
		panelQuestionEight.add(questionEight, constrainsQuestionEight);

		final JLabel questionEightLeft;
		questionEightLeft = new JLabel(
				currentLanguage.getString("qCatEightLeft"));
		questionEightLeft.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionEightLeft.setForeground(Color.BLACK);
		constrainsQuestionEight.gridx = 0;
		constrainsQuestionEight.gridy = 2;
		panelQuestionEight.add(questionEightLeft, constrainsQuestionEight);

		final JLabel questionEightRight;
		questionEightRight = new JLabel(
				currentLanguage.getString("qCatEightRight"));
		questionEightRight.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionEightRight.setForeground(Color.BLACK);
		constrainsQuestionEight.gridx = 7;
		constrainsQuestionEight.gridy = 2;
		panelQuestionEight.add(questionEightRight, constrainsQuestionEight);

		final JRadioButton questionEightSelectionZero;
		questionEightSelectionZero = new JRadioButton();
		questionEightSelectionZero.setBackground(Color.GREEN);
		questionEightSelectionZero.setIcon(radioIconZero);
		questionEightSelectionZero.setSelectedIcon(radioIconON);
		constrainsQuestionEight.gridx = 1;
		constrainsQuestionEight.gridy = 2;
		panelQuestionEight.add(questionEightSelectionZero,
				constrainsQuestionEight);
		questionEightSelectionZero.setActionCommand("0");

		final JRadioButton questionEightSelectionOne;
		questionEightSelectionOne = new JRadioButton();
		questionEightSelectionOne.setBackground(Color.GREEN);
		questionEightSelectionOne.setIcon(radioIconOne);
		questionEightSelectionOne.setSelectedIcon(radioIconON);
		constrainsQuestionEight.gridx = 2;
		constrainsQuestionEight.gridy = 2;
		panelQuestionEight.add(questionEightSelectionOne,
				constrainsQuestionEight);
		questionEightSelectionOne.setActionCommand("1");

		final JRadioButton questionEightSelectionTwo;
		questionEightSelectionTwo = new JRadioButton();
		questionEightSelectionTwo.setBackground(Color.GREEN);
		questionEightSelectionTwo.setIcon(radioIconTwo);
		questionEightSelectionTwo.setSelectedIcon(radioIconON);
		constrainsQuestionEight.gridx = 3;
		constrainsQuestionEight.gridy = 2;
		panelQuestionEight.add(questionEightSelectionTwo,
				constrainsQuestionEight);
		questionEightSelectionTwo.setActionCommand("2");

		final JRadioButton questionEightSelectionThree;
		questionEightSelectionThree = new JRadioButton();
		questionEightSelectionThree.setBackground(Color.GREEN);
		questionEightSelectionThree.setIcon(radioIconThree);
		questionEightSelectionThree.setSelectedIcon(radioIconON);
		constrainsQuestionEight.gridx = 4;
		constrainsQuestionEight.gridy = 2;
		panelQuestionEight.add(questionEightSelectionThree,
				constrainsQuestionEight);
		questionEightSelectionThree.setActionCommand("3");

		final JRadioButton questionEightSelectionFour;
		questionEightSelectionFour = new JRadioButton();
		questionEightSelectionFour.setBackground(Color.GREEN);
		questionEightSelectionFour.setIcon(radioIconFour);
		questionEightSelectionFour.setSelectedIcon(radioIconON);
		constrainsQuestionEight.gridx = 5;
		constrainsQuestionEight.gridy = 2;
		panelQuestionEight.add(questionEightSelectionFour,
				constrainsQuestionEight);
		questionEightSelectionFour.setActionCommand("4");

		final JRadioButton questionEightSelectionFive;
		questionEightSelectionFive = new JRadioButton();
		questionEightSelectionFive.setBackground(Color.GREEN);
		questionEightSelectionFive.setIcon(radioIconFive);
		questionEightSelectionFive.setSelectedIcon(radioIconON);
		constrainsQuestionEight.gridx = 6;
		constrainsQuestionEight.gridy = 2;
		panelQuestionEight.add(questionEightSelectionFive,
				constrainsQuestionEight);
		questionEightSelectionFive.setActionCommand("5");

		final ButtonGroup questionEightGroup = new ButtonGroup();
		questionEightGroup.add(questionEightSelectionZero);
		questionEightGroup.add(questionEightSelectionOne);
		questionEightGroup.add(questionEightSelectionTwo);
		questionEightGroup.add(questionEightSelectionThree);
		questionEightGroup.add(questionEightSelectionFour);
		questionEightGroup.add(questionEightSelectionFive);

		JButton backToQuestionSeven;
		backToQuestionSeven = new JButton(
				currentLanguage.getString("previousQuestion"));
		backToQuestionSeven.setFont(new Font("Tahoma", Font.BOLD, 40));
		backToQuestionSeven.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionEight.gridx = 0;
		constrainsQuestionEight.gridy = 7;
		panelQuestionEight.add(backToQuestionSeven, constrainsQuestionEight);
		backToQuestionSeven.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "7");

			}
		});

		JButton toQuestionNine;
		toQuestionNine = new JButton(currentLanguage.getString("nextQuestion"));
		toQuestionNine.setFont(new Font("Tahoma", Font.BOLD, 40));
		toQuestionNine.setPreferredSize(new Dimension(225, 125));
		constrainsQuestionEight.gridx = 7;
		constrainsQuestionEight.gridy = 7;
		panelQuestionEight.add(toQuestionNine, constrainsQuestionEight);
		toQuestionNine.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (questionEightSelectionZero.isSelected()
						|| questionEightSelectionOne.isSelected()
						|| questionEightSelectionTwo.isSelected()
						|| questionEightSelectionThree.isSelected()
						|| questionEightSelectionFour.isSelected()
						|| questionEightSelectionFive.isSelected()) {
					cl.show(panelContainer, "9");
				}
				;
			}
		});

		// Submit Panel
		
		final GridBagConstraints constrainsQuestionNine = new GridBagConstraints();
		constrainsQuestionNine.insets = new Insets(5, 5, 5, 5);
		constrainsQuestionNine.anchor = GridBagConstraints.CENTER;

		JLabel submitLabel;
		submitLabel = new JLabel(currentLanguage.getString("submitLabel"));
		submitLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		submitLabel.setForeground(Color.BLUE);
		constrainsQuestionNine.gridx = 0;
		constrainsQuestionNine.gridy = 0;
		panelQuestionNine.add(submitLabel, constrainsQuestionNine);

		JButton backToQuestionEight;
		backToQuestionEight = new JButton(
				currentLanguage.getString("previousQuestion"));
		backToQuestionEight.setFont(new Font("Tahoma", Font.BOLD, 40));
		backToQuestionEight.setPreferredSize(new Dimension(400, 125));
		constrainsQuestionNine.gridx = 0;
		constrainsQuestionNine.gridy = 1;
		panelQuestionNine.add(backToQuestionEight, constrainsQuestionNine);
		backToQuestionEight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "8");
			}
		});

		JButton resetCatQuestionnaire;
		resetCatQuestionnaire = new JButton(currentLanguage.getString("reset"));
		resetCatQuestionnaire.setFont(new Font("Tahoma", Font.BOLD, 40));
		resetCatQuestionnaire.setPreferredSize(new Dimension(400, 125));
		constrainsQuestionNine.gridx = 0;
		constrainsQuestionNine.gridy = 2;
		panelQuestionNine.add(resetCatQuestionnaire, constrainsQuestionNine);
		resetCatQuestionnaire.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				questionOneGroup.clearSelection();
				questionTwoGroup.clearSelection();
				questionThreeGroup.clearSelection();
				questionFourGroup.clearSelection();
				questionFiveGroup.clearSelection();
				questionSixGroup.clearSelection();
				questionSevenGroup.clearSelection();
				questionEightGroup.clearSelection();
				cl.show(panelContainer, "1");
			}
		});

		JButton submitCat;
		submitCat = new JButton(currentLanguage.getString("submit"));
		submitCat.setFont(new Font("Tahoma", Font.BOLD, 40));
		submitCat.setPreferredSize(new Dimension(400, 125));
		constrainsQuestionNine.gridy = 3;
		panelQuestionNine.add(submitCat, constrainsQuestionNine);
		submitCat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {

					// Question One

					if (questionOneSelectionZero.isSelected()) {
						sQuestionOneAnswer = "0";
					} else if (questionOneSelectionOne.isSelected()) {
						sQuestionOneAnswer = "1";
					} else if (questionOneSelectionTwo.isSelected()) {
						sQuestionOneAnswer = "2";
					} else if (questionOneSelectionThree.isSelected()) {
						sQuestionOneAnswer = "3";
					} else if (questionOneSelectionFour.isSelected()) {
						sQuestionOneAnswer = "4";
					} else if (questionOneSelectionFive.isSelected()) {
						sQuestionOneAnswer = "5";
					}

					// Question Two

					if (questionTwoSelectionZero.isSelected()) {
						sQuestionTwoAnswer = "0";
					} else if (questionTwoSelectionOne.isSelected()) {
						sQuestionTwoAnswer = "1";
					} else if (questionTwoSelectionTwo.isSelected()) {
						sQuestionTwoAnswer = "2";
					} else if (questionTwoSelectionThree.isSelected()) {
						sQuestionTwoAnswer = "3";
					} else if (questionTwoSelectionFour.isSelected()) {
						sQuestionTwoAnswer = "4";
					} else if (questionTwoSelectionFive.isSelected()) {
						sQuestionTwoAnswer = "5";
					}

					// Question Three

					if (questionThreeSelectionZero.isSelected()) {
						sQuestionThreeAnswer = "0";
					} else if (questionThreeSelectionOne.isSelected()) {
						sQuestionThreeAnswer = "1";
					} else if (questionThreeSelectionTwo.isSelected()) {
						sQuestionThreeAnswer = "2";
					} else if (questionThreeSelectionThree.isSelected()) {
						sQuestionThreeAnswer = "3";
					} else if (questionThreeSelectionFour.isSelected()) {
						sQuestionThreeAnswer = "4";
					} else if (questionThreeSelectionFive.isSelected()) {
						sQuestionThreeAnswer = "5";
					}

					// Question Four

					if (questionFourSelectionZero.isSelected()) {
						sQuestionFourAnswer = "0";
					} else if (questionFourSelectionOne.isSelected()) {
						sQuestionFourAnswer = "1";
					} else if (questionFourSelectionTwo.isSelected()) {
						sQuestionFourAnswer = "2";
					} else if (questionFourSelectionThree.isSelected()) {
						sQuestionFourAnswer = "3";
					} else if (questionFourSelectionFour.isSelected()) {
						sQuestionFourAnswer = "4";
					} else if (questionFourSelectionFive.isSelected()) {
						sQuestionFourAnswer = "5";
					}

					// Question Five

					if (questionFiveSelectionZero.isSelected()) {
						sQuestionFiveAnswer = "0";
					} else if (questionFiveSelectionOne.isSelected()) {
						sQuestionFiveAnswer = "1";
					} else if (questionFiveSelectionTwo.isSelected()) {
						sQuestionFiveAnswer = "2";
					} else if (questionFiveSelectionThree.isSelected()) {
						sQuestionFiveAnswer = "3";
					} else if (questionFiveSelectionFour.isSelected()) {
						sQuestionFiveAnswer = "4";
					} else if (questionFiveSelectionFive.isSelected()) {
						sQuestionFiveAnswer = "5";
					}

					// Question Six

					if (questionSixSelectionZero.isSelected()) {
						sQuestionSixAnswer = "0";
					} else if (questionSixSelectionOne.isSelected()) {
						sQuestionSixAnswer = "1";
					} else if (questionSixSelectionTwo.isSelected()) {
						sQuestionSixAnswer = "2";
					} else if (questionSixSelectionThree.isSelected()) {
						sQuestionSixAnswer = "3";
					} else if (questionSixSelectionFour.isSelected()) {
						sQuestionSixAnswer = "4";
					} else if (questionSixSelectionFive.isSelected()) {
						sQuestionSixAnswer = "5";
					}

					// Question Seven

					if (questionSevenSelectionZero.isSelected()) {
						sQuestionSevenAnswer = "0";
					} else if (questionSevenSelectionOne.isSelected()) {
						sQuestionSevenAnswer = "1";
					} else if (questionSevenSelectionTwo.isSelected()) {
						sQuestionSevenAnswer = "2";
					} else if (questionSevenSelectionThree.isSelected()) {
						sQuestionSevenAnswer = "3";
					} else if (questionSevenSelectionFour.isSelected()) {
						sQuestionSevenAnswer = "4";
					} else if (questionSevenSelectionFive.isSelected()) {
						sQuestionSevenAnswer = "5";
					}

					// Question Eight

					if (questionEightSelectionZero.isSelected()) {
						sQuestionEightAnswer = "0";
					} else if (questionEightSelectionOne.isSelected()) {
						sQuestionEightAnswer = "1";
					} else if (questionEightSelectionTwo.isSelected()) {
						sQuestionEightAnswer = "2";
					} else if (questionEightSelectionThree.isSelected()) {
						sQuestionEightAnswer = "3";
					} else if (questionEightSelectionFour.isSelected()) {
						sQuestionEightAnswer = "4";
					} else if (questionEightSelectionFive.isSelected()) {
						sQuestionEightAnswer = "5";
					}

					final String[] questions = new String[] {
							sQuestionOneAnswer, sQuestionTwoAnswer,
							sQuestionThreeAnswer, sQuestionFourAnswer,
							sQuestionFiveAnswer, sQuestionSixAnswer,
							sQuestionSevenAnswer, sQuestionEightAnswer };

					System.out.println("Trying to send data!");
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
									// calling pack()
									try {
										dialog.setLocationRelativeTo(getView()
												.getParent());
									} catch (IOException e) {
										System.out.println("Can't get View");
									}
									dialog.setVisible(true);
								}
							});
							updateSendGui(questions);

						}
					}).start();

				}

				catch (HeadlessException e1) {
					e1.printStackTrace();
				}
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

	private void updateSendGui(String[] questions) {
		// Make all QuestionAnwer variable public and visible

		String username = HealthProperties.getProperty("iipUsername");
		String password = HealthProperties.getProperty("iipPassword");
		String iipUrl = HealthProperties.getProperty("iipUrl");
		String questionnaireChannel = HealthProperties
				.getProperty("catQuestionnaireChannel");
		String patientId = HealthProperties.getProperty("patientId");
		String URL = "https://" + username + ":" + password + "@" + iipUrl
				+ questionnaireChannel;

		HttpClient httpclient = getNewHttpClient();
		HttpPost httpPost = new HttpPost(URL);
		HttpResponse response = null;

		// Array to send to IIP
		// Date

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss");
		String timeAndDate = simpleDateFormat.format(new Date());
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("q_1", sQuestionOneAnswer));
		params.add(new BasicNameValuePair("q_2", sQuestionTwoAnswer));
		params.add(new BasicNameValuePair("q_3", sQuestionThreeAnswer));
		params.add(new BasicNameValuePair("q_4", sQuestionFourAnswer));
		params.add(new BasicNameValuePair("q_5", sQuestionFiveAnswer));
		params.add(new BasicNameValuePair("q_6", sQuestionSixAnswer));
		params.add(new BasicNameValuePair("q_7", sQuestionSevenAnswer));
		params.add(new BasicNameValuePair("q_8", sQuestionEightAnswer));
		params.add(new BasicNameValuePair("patientId", patientId));
		params.add(new BasicNameValuePair("dateTime", timeAndDate));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			response = httpclient.execute(httpPost);
			System.out.println("\nTesting sending CAT values: "
					+ response.getStatusLine());
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
