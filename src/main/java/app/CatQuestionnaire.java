package app;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class CatQuestionnaire extends JComponent {

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
//		int xSize = ((int) tk.getScreenSize().getWidth());
//		int ySize = ((int) tk.getScreenSize().getWidth());
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
		panelQuestionNine.setLayout(gbl_panel);

		final GridBagConstraints constrainsQuestionNine = new GridBagConstraints();
		constrainsQuestionNine.insets = new Insets(0, 0, 0, 0);
		constrainsQuestionNine.fill = GridBagConstraints.HORIZONTAL;
		constrainsQuestionNine.anchor = GridBagConstraints.FIRST_LINE_START;

		final GridBagConstraints constrainsQuestionEight = new GridBagConstraints();
		constrainsQuestionEight.insets = new Insets(0, 0, 0, 0);
		constrainsQuestionEight.fill = GridBagConstraints.HORIZONTAL;
		constrainsQuestionEight.anchor = GridBagConstraints.FIRST_LINE_START;

		ClassLoader customRadioButton = this.getClass().getClassLoader();
		
		URL iconUrlON = customRadioButton.getResource("radioButtonON.png");
		Icon radioIconON = new ImageIcon(iconUrlON);
		
		URL iconUrlOFF = customRadioButton.getResource("radioButtonOFF.png");
		Icon radioIconOFF = new ImageIcon(iconUrlOFF);
		
		URL urlIconZero = customRadioButton.getResource("radioButtonZero.png");
		Icon radioIconZero = new ImageIcon(urlIconZero);
		
		URL urlIconOne = customRadioButton.getResource("radioButtonOne.png");
		Icon radioIconOne = new ImageIcon(urlIconOne);
		
		URL urlIconTwo = customRadioButton.getResource("radioButtonTwo.png");
		Icon radioIconTwo = new ImageIcon(urlIconTwo);
		
		URL urlIconThree = customRadioButton.getResource("radioButtonThree.png");
		Icon radioIconThree = new ImageIcon(urlIconThree);
		
		URL urlIconFour = customRadioButton.getResource("radioButtonFour.png");
		Icon radioIconFour = new ImageIcon(urlIconFour);
		
		URL urlIconFive = customRadioButton.getResource("radioButtonFive.png");
		Icon radioIconFive = new ImageIcon(urlIconFive);
		
		// adding Question one

		final GridBagConstraints constrainsQuestionOne = new GridBagConstraints();
		constrainsQuestionOne.anchor = GridBagConstraints.FIRST_LINE_START;

		final JLabel questionOneLeft;
		questionOneLeft = new JLabel(currentLanguage.getString("qCatFourLeft"));
		questionOneLeft.setFont(new Font("Tahoma", Font.BOLD, 40));
		questionOneLeft.setForeground(Color.BLACK);
		constrainsQuestionOne.gridx = 0;
		constrainsQuestionOne.gridy = 2;
		panelQuestionOne.add(questionOneLeft, constrainsQuestionOne);
		
		final JLabel questionOneRight;
		questionOneRight = new JLabel(currentLanguage.getString("qCatFourRight"));
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

		class RadioListenerOne implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sQuestionOneAnswer = null;

				if (e.getActionCommand() == "2") {
					sQuestionOneAnswer = currentLanguage.getString("aAsUsual");
				} else if (e.getActionCommand() == "3") {
					sQuestionOneAnswer = currentLanguage.getString("aWorse");
				} else if (e.getActionCommand() == "4") {
					sQuestionOneAnswer = currentLanguage
							.getString("aMuchWorse");
				}

				JLabel questionOneAnswer = new JLabel(sQuestionOneAnswer);
				constrainsQuestionEight.gridx = 1;
				constrainsQuestionEight.gridy = 1;
				panelQuestionEight.add(questionOneAnswer,
						constrainsQuestionEight);
			}

		}

		RadioListenerOne listenerOne = new RadioListenerOne();
		questionOneSelectionTwo.addActionListener(listenerOne);
		questionOneSelectionThree.addActionListener(listenerOne);
		questionOneSelectionFour.addActionListener(listenerOne);

		JButton toQuestionTwo;
		toQuestionTwo = new JButton(currentLanguage.getString("nextQuestion"));
		toQuestionTwo.setFont(new Font("Tahoma", Font.BOLD, 40));
		constrainsQuestionOne.gridx = 7;
		constrainsQuestionOne.gridy = 3;
		constrainsQuestionOne.ipadx = 100;
		constrainsQuestionOne.ipady = 60;
		panelQuestionOne.add(toQuestionTwo, constrainsQuestionOne);
		toQuestionTwo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JLabel questionOne;
				questionOne = new JLabel(currentLanguage.getString("qOne"));
				questionOne.setFont(new Font("Tahoma", Font.BOLD, 20));
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
		
		return panelContainer;
	}
}
