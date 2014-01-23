package app;
import iipintegration.MySSLSocketFactory;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;

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

public class QuestionnareTab extends JComponent {
	private static final long serialVersionUID = -7594299439504858239L;
		
	public Component getView() throws IOException {
		JComponent questionnare = createPanel("My questions");
		questionnare.setPreferredSize(new Dimension(1000, 600));
		return questionnare;
	}
	
	public JPanel createPanel(String title) throws IOException {
        
		String currentLang = HealthProperties.getProperty("currentLanguage");
		Locale currentLocale = Locale.forLanguageTag(currentLang);
		final ResourceBundle currentLanguage = ResourceBundle.getBundle("language", currentLocale);
		
		//Panels
		
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
        
        //Container panel layout
		
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
        gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0};
        gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
        
		//adding Question one

        final GridBagConstraints constrainsQuestionOne = new GridBagConstraints();
        constrainsQuestionOne.insets = new Insets(5, 5, 5, 5);
        constrainsQuestionOne.anchor = GridBagConstraints.FIRST_LINE_START;
        
        final JLabel questionOne;
        questionOne = new JLabel(currentLanguage.getString("qOne"));
        questionOne.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 0;
        panelQuestionOne.add(questionOne, constrainsQuestionOne);
        
        final JRadioButton questionOneSelectionTwo;
        questionOneSelectionTwo = new JRadioButton(currentLanguage.getString("aAsUsual"));
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 2;
        panelQuestionOne.add(questionOneSelectionTwo, constrainsQuestionOne);
        questionOneSelectionTwo.setActionCommand("2");
        
        final JRadioButton questionOneSelectionThree;
        questionOneSelectionThree = new JRadioButton(currentLanguage.getString("aWorse"));
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 3;
        panelQuestionOne.add(questionOneSelectionThree, constrainsQuestionOne);
        questionOneSelectionThree.setActionCommand("3");
        
        final JRadioButton questionOneSelectionFour;
        questionOneSelectionFour = new JRadioButton(currentLanguage.getString("aMuchWorse"));
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 7;
        panelQuestionOne.add(questionOneSelectionFour, constrainsQuestionOne);
        questionOneSelectionFour.setActionCommand("4");
        
        ButtonGroup questionOneGroup = new ButtonGroup();
        questionOneGroup.add(questionOneSelectionTwo);
        questionOneGroup.add(questionOneSelectionThree);
        questionOneGroup.add(questionOneSelectionFour);
        
        class RadioListenerOne implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sQuestionOneAnswer = null;
				
				
				if (e.getActionCommand() == "2") {
					sQuestionOneAnswer = currentLanguage.getString("aAsUsual");
				} else if (e.getActionCommand() == "3"){
					sQuestionOneAnswer = currentLanguage.getString("aWorse");
				} else if (e.getActionCommand() == "4") {
					sQuestionOneAnswer = currentLanguage.getString("aMuchWorse");
				}
				
				JLabel questionOneAnswer = new JLabel(sQuestionOneAnswer);
				constrainsQuestionEight.gridx = 1;
				constrainsQuestionEight.gridy = 1;
				panelQuestionEight.add(questionOneAnswer, constrainsQuestionEight);
			}
        	
        }
        
        RadioListenerOne listenerOne = new RadioListenerOne();
        questionOneSelectionTwo.addActionListener(listenerOne);
        questionOneSelectionThree.addActionListener(listenerOne);
        questionOneSelectionFour.addActionListener(listenerOne);

        JButton toQuestionTwo;
        toQuestionTwo = new JButton(currentLanguage.getString("nextQuestion"));
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 8;
        constrainsQuestionOne.ipadx = 100;
        constrainsQuestionOne.ipady = 60;
        panelQuestionOne.add(toQuestionTwo, constrainsQuestionOne);
        toQuestionTwo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JLabel questionOne;
		        questionOne = new JLabel(currentLanguage.getString("qOne"));
		        questionOne.setFont(new Font("Lucida Grande", Font.BOLD, 20));
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
        constrainsQuestionTwo.fill = GridBagConstraints.HORIZONTAL;
        constrainsQuestionTwo.anchor = GridBagConstraints.FIRST_LINE_START;
                
        final JLabel questionTwo;
        questionTwo = new JLabel(currentLanguage.getString("qTwo"));
        questionTwo.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 0;
        panelQuestionTwo.add(questionTwo, constrainsQuestionTwo);
        
        final JRadioButton questionTwoSelectionTwo;
        questionTwoSelectionTwo = new JRadioButton(currentLanguage.getString("aAsUsual"));
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 2;
        panelQuestionTwo.add(questionTwoSelectionTwo, constrainsQuestionTwo);
        questionTwoSelectionTwo.setActionCommand("2");
        
        final JRadioButton questionTwoSelectionThree;
        questionTwoSelectionThree = new JRadioButton(currentLanguage.getString("aWorse"));
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 3;
        panelQuestionTwo.add(questionTwoSelectionThree, constrainsQuestionTwo);
        questionTwoSelectionThree.setActionCommand("3");
        
        final JRadioButton questionTwoSelectionFour;
        questionTwoSelectionFour = new JRadioButton(currentLanguage.getString("aMuchWorse"));
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 4;
        panelQuestionTwo.add(questionTwoSelectionFour, constrainsQuestionTwo);
        questionTwoSelectionFour.setActionCommand("4");
        
        
        ButtonGroup questionTwoGroup = new ButtonGroup();
        questionTwoGroup.add(questionTwoSelectionTwo);
        questionTwoGroup.add(questionTwoSelectionThree);
        questionTwoGroup.add(questionTwoSelectionFour);
        
        class RadioListenerTwo implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sQuestionTwoAnswer = null;
				
				if (e.getActionCommand() == "2") {
					sQuestionTwoAnswer = currentLanguage.getString("aAsUsual");
				} else if (e.getActionCommand() == "3"){
					sQuestionTwoAnswer = currentLanguage.getString("aWorse");
				} else if (e.getActionCommand() == "4") {
					sQuestionTwoAnswer = currentLanguage.getString("aMuchWorse");
				} 
				
				JLabel questiontwoAnswer = new JLabel(sQuestionTwoAnswer);
				constrainsQuestionEight.gridx = 1;
				constrainsQuestionEight.gridy = 2;
				panelQuestionEight.add(questiontwoAnswer, constrainsQuestionEight);
			}
        	
        }
        
        RadioListenerTwo listenerTwo = new RadioListenerTwo();
        questionTwoSelectionTwo.addActionListener(listenerTwo);
        questionTwoSelectionThree.addActionListener(listenerTwo);
        questionTwoSelectionFour.addActionListener(listenerTwo);

        JButton toQuestionOne;
        toQuestionOne = new JButton(currentLanguage.getString("previousQuestion"));
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 7;
        constrainsQuestionTwo.ipadx = 120;
        constrainsQuestionTwo.ipady = 60;
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
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 8;
        constrainsQuestionTwo.ipadx = 120;
        constrainsQuestionTwo.ipady = 60;
        panelQuestionTwo.add(toQuestionThree, constrainsQuestionTwo);
        toQuestionThree.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JLabel questionTwo;
		        questionTwo = new JLabel(currentLanguage.getString("qTwo"));
		        questionTwo.setFont(new Font("Lucida Grande", Font.BOLD, 20));
				constrainsQuestionEight.gridx = 0;
		        constrainsQuestionEight.gridy = 2;
		        panelQuestionEight.add(questionTwo, constrainsQuestionEight);
				if (questionTwoSelectionTwo.isSelected()
						|| questionTwoSelectionThree.isSelected()
						|| questionTwoSelectionFour.isSelected()) {
					cl.show(panelContainer, "3");
				};
			}
		});
        
// Adding question number three
        
        GridBagConstraints constrainsQuestionThree = new GridBagConstraints();
        constrainsQuestionThree.insets = new Insets(5, 5, 5, 5);
        constrainsQuestionThree.fill = GridBagConstraints.HORIZONTAL;
        constrainsQuestionThree.anchor = GridBagConstraints.FIRST_LINE_START;
        
        final JLabel questionThree;
        questionThree = new JLabel(currentLanguage.getString("qThree"));
        questionThree.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 0;
        panelQuestionThree.add(questionThree, constrainsQuestionThree);
        
        final JRadioButton questionThreeSelectionTwo;
        questionThreeSelectionTwo = new JRadioButton(currentLanguage.getString("aAsUsual"));
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 2;
        panelQuestionThree.add(questionThreeSelectionTwo, constrainsQuestionThree);
        questionThreeSelectionTwo.setActionCommand("2");
        
        final JRadioButton questionThreeSelectionThree;
        questionThreeSelectionThree = new JRadioButton(currentLanguage.getString("aWorse"));
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 3;
        panelQuestionThree.add(questionThreeSelectionThree, constrainsQuestionThree);
        questionThreeSelectionThree.setActionCommand("3");
        
        final JRadioButton questionThreeSelectionFour;
        questionThreeSelectionFour = new JRadioButton(currentLanguage.getString("aMuchWorse"));
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 4;
        panelQuestionThree.add(questionThreeSelectionFour, constrainsQuestionThree);
        questionThreeSelectionFour.setActionCommand("4");
        
        ButtonGroup questionThreeGroup = new ButtonGroup();
        questionThreeGroup.add(questionThreeSelectionTwo);
        questionThreeGroup.add(questionThreeSelectionThree);
        questionThreeGroup.add(questionThreeSelectionFour);
        
        class RadioListenerThree implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sQuestionThreeAnswer = null;
				
				
				if (e.getActionCommand() == "2") {
					sQuestionThreeAnswer = currentLanguage.getString("aAsUsual");
				} else if (e.getActionCommand() == "3"){
					sQuestionThreeAnswer = currentLanguage.getString("aWorse");
				} else if (e.getActionCommand() == "4") {
					sQuestionThreeAnswer = currentLanguage.getString("aMuchWorse");
				} 
				
				JLabel questionThreeAnswer = new JLabel(sQuestionThreeAnswer);
				constrainsQuestionEight.gridx = 1;
				constrainsQuestionEight.gridy = 3;
				panelQuestionEight.add(questionThreeAnswer, constrainsQuestionEight);
			}
        	
        }
        
        RadioListenerThree listenerThree = new RadioListenerThree();
        questionThreeSelectionTwo.addActionListener(listenerThree);
        questionThreeSelectionThree.addActionListener(listenerThree);
        questionThreeSelectionFour.addActionListener(listenerThree);
        
        JButton backToQuestionTwo;
        backToQuestionTwo = new JButton(currentLanguage.getString("previousQuestion"));
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 7;
        constrainsQuestionThree.ipadx = 120;
        constrainsQuestionThree.ipady = 60;
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
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 8;
        constrainsQuestionThree.ipadx = 120;
        constrainsQuestionThree.ipady = 60;
        panelQuestionThree.add(toQuestionFour, constrainsQuestionThree);
        toQuestionFour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JLabel questionThree;
		        questionThree = new JLabel(currentLanguage.getString("qThree"));
		        questionThree.setFont(new Font("Lucida Grande", Font.BOLD, 20));
				constrainsQuestionEight.gridx = 0;
		        constrainsQuestionEight.gridy = 3;
		        panelQuestionEight.add(questionThree, constrainsQuestionEight);
				if (questionThreeSelectionTwo.isSelected()
						|| questionThreeSelectionThree.isSelected()
						|| questionThreeSelectionFour.isSelected()) {
					cl.show(panelContainer, "4");
				};;
			}
		});
        
// Adding question number four
        
        GridBagConstraints constrainsQuestionFour = new GridBagConstraints();
        constrainsQuestionFour.insets = new Insets(5, 5, 5, 5);
        constrainsQuestionFour.fill = GridBagConstraints.HORIZONTAL;
        constrainsQuestionFour.anchor = GridBagConstraints.FIRST_LINE_START;
        
        final JLabel questionFour;
        questionFour = new JLabel(currentLanguage.getString("qFour"));
        questionFour.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionFour.gridx = 0;
        constrainsQuestionFour.gridy = 0;
        panelQuestionFour.add(questionFour, constrainsQuestionFour);
        
        final JRadioButton questionFourSelectionOne;
        questionFourSelectionOne = new JRadioButton(currentLanguage.getString("qFourAnsOne"));
        constrainsQuestionFour.gridx = 0;
        constrainsQuestionFour.gridy = 1;
        panelQuestionFour.add(questionFourSelectionOne, constrainsQuestionFour);
        questionFourSelectionOne.setActionCommand("2");
        
        final JRadioButton questionFourSelectionTwo;
        questionFourSelectionTwo = new JRadioButton(currentLanguage.getString("qFourAnsTwo"));
        constrainsQuestionFour.gridx = 0;
        constrainsQuestionFour.gridy = 2;
        panelQuestionFour.add(questionFourSelectionTwo, constrainsQuestionFour);
        questionFourSelectionTwo.setActionCommand("3");
        
        final JRadioButton questionFourSelectionThree;
        questionFourSelectionThree = new JRadioButton(currentLanguage.getString("qFourAnsThree"));
        constrainsQuestionFour.gridx = 0;
        constrainsQuestionFour.gridy = 3;
        panelQuestionFour.add(questionFourSelectionThree, constrainsQuestionFour);
        questionFourSelectionThree.setActionCommand("4");
        
        
        ButtonGroup questionFourGroup = new ButtonGroup();
        questionFourGroup.add(questionFourSelectionOne);
        questionFourGroup.add(questionFourSelectionTwo);
        questionFourGroup.add(questionFourSelectionThree);

        class RadioListenerFour implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sQuestionFourAnswer = null;
				
				
				if (e.getActionCommand() == "2") {
					sQuestionFourAnswer = currentLanguage.getString("qFourAnsOne");
				} else if (e.getActionCommand() == "3"){
					sQuestionFourAnswer = currentLanguage.getString("qFourAnsTwo");
				} else if (e.getActionCommand() == "4"){
					sQuestionFourAnswer = currentLanguage.getString("qFourAnsThree");
				}
				
				JLabel questionFourAnswer = new JLabel(sQuestionFourAnswer);
				constrainsQuestionEight.gridx = 1;
				constrainsQuestionEight.gridy = 4;
				panelQuestionEight.add(questionFourAnswer, constrainsQuestionEight);
			}
        	
        }
        
        RadioListenerFour listenerFour = new RadioListenerFour();
        questionFourSelectionOne.addActionListener(listenerFour);
        questionFourSelectionTwo.addActionListener(listenerFour);
        questionFourSelectionThree.addActionListener(listenerFour);
        
        JButton backToQuestionThree;
        backToQuestionThree = new JButton(currentLanguage.getString("previousQuestion"));
        constrainsQuestionFour.gridx = 0;
        constrainsQuestionFour.gridy = 7;
        constrainsQuestionFour.ipadx = 120;
        constrainsQuestionFour.ipady = 60;
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
        constrainsQuestionFour.gridx = 0;
        constrainsQuestionFour.gridy = 8;
        constrainsQuestionFour.ipadx = 120;
        constrainsQuestionFour.ipady = 60;
        panelQuestionFour.add(toQuestionFive, constrainsQuestionFour);
        toQuestionFive.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JLabel questionFour;
		        questionFour = new JLabel(currentLanguage.getString("qFour"));
		        questionFour.setFont(new Font("Lucida Grande", Font.BOLD, 20));
				constrainsQuestionEight.gridx = 0;
		        constrainsQuestionEight.gridy = 4;
		        panelQuestionEight.add(questionFour, constrainsQuestionEight);
				if (questionFourSelectionOne.isSelected()
						|| questionFourSelectionTwo.isSelected()
						|| questionFourSelectionThree.isSelected()) {
					cl.show(panelContainer, "5");
				};;
			}
		});
        
// Adding question number five
        
        GridBagConstraints constrainsQuestionFive = new GridBagConstraints();
        constrainsQuestionFive.insets = new Insets(5, 5, 5, 5);
        constrainsQuestionFive.fill = GridBagConstraints.HORIZONTAL;
        constrainsQuestionFive.anchor = GridBagConstraints.FIRST_LINE_START;
        
        final JLabel questionFive;
        questionFive = new JLabel(currentLanguage.getString("qFive"));
        questionFive.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 0;
        panelQuestionFive.add(questionFive, constrainsQuestionFive);
        
        final JRadioButton questionFiveSelectionOne;
        questionFiveSelectionOne = new JRadioButton(currentLanguage.getString("qFiveAnsOne"));
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 1;
        panelQuestionFive.add(questionFiveSelectionOne, constrainsQuestionFive);
        questionFiveSelectionOne.setActionCommand("1");
        
        final JRadioButton questionFiveSelectionTwo;
        questionFiveSelectionTwo = new JRadioButton(currentLanguage.getString("qFiveAnsTwo"));
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 2;
        panelQuestionFive.add(questionFiveSelectionTwo, constrainsQuestionFive);
        questionFiveSelectionTwo.setActionCommand("2");
        
        final JRadioButton questionFiveSelectionThree;
        questionFiveSelectionThree = new JRadioButton(currentLanguage.getString("qFiveAnsThree"));
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 3;
        panelQuestionFive.add(questionFiveSelectionThree, constrainsQuestionFive);
        questionFiveSelectionThree.setActionCommand("3");
        
        final JRadioButton questionFiveSelectionFour;
        questionFiveSelectionFour = new JRadioButton(currentLanguage.getString("qFiveAnsFour"));
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 4;
        panelQuestionFive.add(questionFiveSelectionFour, constrainsQuestionFive);
        questionFiveSelectionFour.setActionCommand("4");
        
        ButtonGroup questionFiveGroup = new ButtonGroup();
        questionFiveGroup.add(questionFiveSelectionOne);
        questionFiveGroup.add(questionFiveSelectionTwo);
        questionFiveGroup.add(questionFiveSelectionThree);
        questionFiveGroup.add(questionFiveSelectionFour);
        
        class RadioListenerFive implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sQuestionFiveAnswer = null;
				
				
				if (e.getActionCommand() == "1") {
					sQuestionFiveAnswer = currentLanguage.getString("qFiveAnsOne");
				} else if (e.getActionCommand() == "2") {
					sQuestionFiveAnswer = currentLanguage.getString("qFiveAnsTwo");
				} else if (e.getActionCommand() == "3") {
					sQuestionFiveAnswer = currentLanguage.getString("qFiveAnsThree");
				} else if (e.getActionCommand() == "4") {
					sQuestionFiveAnswer = currentLanguage.getString("qFiveAnsFour");
				}
				
				JLabel questionFiveAnswer = new JLabel(sQuestionFiveAnswer);
				constrainsQuestionNine.gridx = 1;
				constrainsQuestionNine.gridy = 1;
				panelQuestionNine.add(questionFiveAnswer, constrainsQuestionNine);
			}
        	
        }
        
        RadioListenerFive listenerFive = new RadioListenerFive();
        questionFiveSelectionOne.addActionListener(listenerFive);
        questionFiveSelectionTwo.addActionListener(listenerFive);
        questionFiveSelectionThree.addActionListener(listenerFive);
        questionFiveSelectionFour.addActionListener(listenerFive);

        JButton backToQuestionFour;
        backToQuestionFour = new JButton(currentLanguage.getString("previousQuestion"));
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 7;
        constrainsQuestionFive.ipadx = 120;
        constrainsQuestionFive.ipady = 60;
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
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 8;
        constrainsQuestionFive.ipadx = 120;
        constrainsQuestionFive.ipady = 60;
        panelQuestionFive.add(toQuestionSix, constrainsQuestionFive);
        toQuestionSix.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JLabel questionFive;
		        questionFive = new JLabel(currentLanguage.getString("qFive"));
		        questionFive.setFont(new Font("Lucida Grande", Font.BOLD, 20));
				constrainsQuestionNine.gridx = 0;
		        constrainsQuestionNine.gridy = 1;
		        panelQuestionNine.add(questionFive, constrainsQuestionNine);
				if (questionFiveSelectionOne.isSelected()) {
					cl.show(panelContainer, "8");
				} else if (questionFiveSelectionTwo.isSelected()
						|| questionFiveSelectionThree.isSelected()
						|| questionFiveSelectionFour.isSelected()) {
					cl.show(panelContainer, "6");
				};;
			}
		});
        
// Adding question number six
        
        GridBagConstraints constrainsQuestionSix = new GridBagConstraints();
        constrainsQuestionSix.insets = new Insets(5, 5, 5, 5);
        constrainsQuestionSix.fill = GridBagConstraints.HORIZONTAL;
        constrainsQuestionSix.anchor = GridBagConstraints.FIRST_LINE_START;
        
        final JLabel questionSix;
        questionSix = new JLabel(currentLanguage.getString("qSix"));
        questionSix.setFont(new Font("Lucida Grande", Font.BOLD, 17));
        constrainsQuestionSix.gridx = 0;
        constrainsQuestionSix.gridy = 0;
        panelQuestionSix.add(questionSix, constrainsQuestionSix);
        
        final JRadioButton questionSixSelectionOne;
        questionSixSelectionOne = new JRadioButton(currentLanguage.getString("qSixAnsOne"));
        constrainsQuestionSix.gridx = 0;
        constrainsQuestionSix.gridy = 1;
        panelQuestionSix.add(questionSixSelectionOne, constrainsQuestionSix);
        questionSixSelectionOne.setActionCommand("0");
        
        final JRadioButton questionSixSelectionTwo;
        questionSixSelectionTwo = new JRadioButton(currentLanguage.getString("qSixAnsTwo"));
        constrainsQuestionSix.gridx = 0;
        constrainsQuestionSix.gridy = 2;
        panelQuestionSix.add(questionSixSelectionTwo, constrainsQuestionSix);
        questionSixSelectionTwo.setActionCommand("1");
        
        final JRadioButton questionSixSelectionThree;
        questionSixSelectionThree = new JRadioButton(currentLanguage.getString("qSixAnsThree"));
        constrainsQuestionSix.gridx = 0;
        constrainsQuestionSix.gridy = 3;
        panelQuestionSix.add(questionSixSelectionThree, constrainsQuestionSix);
        questionSixSelectionThree.setActionCommand("2");
        
        final JRadioButton questionSixSelectionFour;
        questionSixSelectionFour = new JRadioButton(currentLanguage.getString("qSixAnsFour"));
        constrainsQuestionSix.gridx = 0;
        constrainsQuestionSix.gridy = 4;
        panelQuestionSix.add(questionSixSelectionFour, constrainsQuestionSix);
        questionSixSelectionFour.setActionCommand("3");
        
        final JRadioButton questionSixSelectionFive;
        questionSixSelectionFive = new JRadioButton(currentLanguage.getString("qSixAnsFive"));
        constrainsQuestionSix.gridx = 0;
        constrainsQuestionSix.gridy = 5;
        panelQuestionSix.add(questionSixSelectionFive, constrainsQuestionSix);
        questionSixSelectionFive.setActionCommand("4");
        
        final JRadioButton questionSixSelectionSix;
        questionSixSelectionSix = new JRadioButton(currentLanguage.getString("qSixAnsSix"));
        constrainsQuestionSix.gridx = 0;
        constrainsQuestionSix.gridy = 6;
        panelQuestionSix.add(questionSixSelectionSix, constrainsQuestionSix);
        questionSixSelectionSix.setActionCommand("5");
         
        ButtonGroup questionSixGroup = new ButtonGroup();
        questionSixGroup.add(questionSixSelectionOne);
        questionSixGroup.add(questionSixSelectionTwo);
        questionSixGroup.add(questionSixSelectionThree);
        questionSixGroup.add(questionSixSelectionFour);
        questionSixGroup.add(questionSixSelectionFive);
        questionSixGroup.add(questionSixSelectionSix);
        
        class RadioListenerSix implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sQuestionSixAnswer = null;
				
				
				if (e.getActionCommand() == "0") {
					sQuestionSixAnswer = currentLanguage.getString("qSixAnsOne");
				} else if (e.getActionCommand() == "1") {
					sQuestionSixAnswer = currentLanguage.getString("qSixAnsTwo");
				} else if (e.getActionCommand() == "2") {
					sQuestionSixAnswer = currentLanguage.getString("qSixAnsThree");
				} else if (e.getActionCommand() == "3") {
					sQuestionSixAnswer = currentLanguage.getString("qSixAnsFour");
				} else if (e.getActionCommand() == "4") {
					sQuestionSixAnswer = currentLanguage.getString("qSixAnsFive");
				} else if (e.getActionCommand() == "5") {
					sQuestionSixAnswer = currentLanguage.getString("qSixAnsSix");
				}
				
				JLabel questionSixAnswer = new JLabel(sQuestionSixAnswer);
				constrainsQuestionNine.gridx = 1;
				constrainsQuestionNine.gridy = 2;
				panelQuestionNine.add(questionSixAnswer, constrainsQuestionNine);
			}
        }
        
        RadioListenerSix listenerSix = new RadioListenerSix();
        questionSixSelectionOne.addActionListener(listenerSix);
        questionSixSelectionTwo.addActionListener(listenerSix);
        questionSixSelectionThree.addActionListener(listenerSix);
        questionSixSelectionFour.addActionListener(listenerSix);
        questionSixSelectionFive.addActionListener(listenerSix);
        questionSixSelectionSix.addActionListener(listenerSix);
        
        JButton backToQuestionFive;
        backToQuestionFive = new JButton(currentLanguage.getString("previousQuestion"));
        constrainsQuestionSix.gridx = 0;
        constrainsQuestionSix.gridy = 7;
        constrainsQuestionSix.ipadx = 120;
        constrainsQuestionSix.ipady = 60;
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
        constrainsQuestionSix.insets = new Insets(30, 5, 5, 5);
        constrainsQuestionSix.anchor = GridBagConstraints.SOUTH;
        constrainsQuestionSix.gridx = 0;
        constrainsQuestionSix.gridy = 8;
        constrainsQuestionSix.ipadx = 120;
        constrainsQuestionSix.ipady = 60;
        panelQuestionSix.add(toQuestionSeven, constrainsQuestionSix);
        toQuestionSeven.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JLabel questionSix;
		        questionSix = new JLabel(currentLanguage.getString("qSix"));
		        questionSix.setFont(new Font("Lucida Grande", Font.BOLD, 17));
				constrainsQuestionNine.gridx = 0;
		        constrainsQuestionNine.gridy = 2;
		        panelQuestionNine.add(questionSix, constrainsQuestionNine);
				if (questionSixSelectionOne.isSelected()) {
					cl.show(panelContainer, "8");
				} else if (questionSixSelectionTwo.isSelected()
					|| questionSixSelectionThree.isSelected()
					|| questionSixSelectionFour.isSelected()
					|| questionSixSelectionFive.isSelected()
					|| questionSixSelectionSix.isSelected()){
					cl.show(panelContainer, "7");
				}
			}
		});
        
        //adding question seven
        
        
        GridBagConstraints constrainsQuestionSeven = new GridBagConstraints();
        constrainsQuestionSeven.insets = new Insets(5, 5, 5, 5);
        constrainsQuestionSeven.fill = GridBagConstraints.HORIZONTAL;
        constrainsQuestionSeven.anchor = GridBagConstraints.FIRST_LINE_START;
        		        
        final JLabel questionSeven;
        questionSeven = new JLabel(currentLanguage.getString("qSeven"));
        questionSeven.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionSeven.gridx = 0;
        constrainsQuestionSeven.gridy = 0;
        panelQuestionSeven.add(questionSeven, constrainsQuestionSeven);
        
        final JRadioButton questionSevenSelectionOne;
        questionSevenSelectionOne = new JRadioButton(currentLanguage.getString("qSevenAnsOne"));
        constrainsQuestionSeven.gridx = 0;
        constrainsQuestionSeven.gridy = 1;
        panelQuestionSeven.add(questionSevenSelectionOne, constrainsQuestionSeven);
        questionSevenSelectionOne.setActionCommand("1");
        
        final JRadioButton questionSevenSelectionTwo;
        questionSevenSelectionTwo = new JRadioButton(currentLanguage.getString("qSevenAnsTwo"));
        constrainsQuestionSeven.gridx = 0;
        constrainsQuestionSeven.gridy = 2;
        panelQuestionSeven.add(questionSevenSelectionTwo, constrainsQuestionSeven);
        questionSevenSelectionTwo.setActionCommand("2");
        
        final JRadioButton questionSevenSelectionThree;
        questionSevenSelectionThree = new JRadioButton(currentLanguage.getString("qSevenAnsThree"));
        constrainsQuestionSeven.gridx = 0;
        constrainsQuestionSeven.gridy = 3;
        panelQuestionSeven.add(questionSevenSelectionThree, constrainsQuestionSeven);
        questionSevenSelectionThree.setActionCommand("3");
        
        final JRadioButton questionSevenSelectionFour;
        questionSevenSelectionFour = new JRadioButton(currentLanguage.getString("qSevenAnsFour"));
        constrainsQuestionSeven.gridx = 0;
        constrainsQuestionSeven.gridy = 4;
        panelQuestionSeven.add(questionSevenSelectionFour, constrainsQuestionSeven);
        questionSevenSelectionFour.setActionCommand("4");
         
        ButtonGroup questionSevenGroup = new ButtonGroup();
        questionSevenGroup.add(questionSevenSelectionOne);
        questionSevenGroup.add(questionSevenSelectionTwo);
        questionSevenGroup.add(questionSevenSelectionThree);
        questionSevenGroup.add(questionSevenSelectionFour);
        
        class RadioListenerSeven implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sQuestionSevenAnswer = null;
				
				if (e.getActionCommand() == "1") {
					sQuestionSevenAnswer = currentLanguage.getString("qSevenAnsOne");
				} else if (e.getActionCommand() == "2") {
					sQuestionSevenAnswer = currentLanguage.getString("qSevenAnsTwo");
				} else if (e.getActionCommand() == "3") {
					sQuestionSevenAnswer = currentLanguage.getString("qSevenAnsThree");
				} else if (e.getActionCommand() == "4") {
					sQuestionSevenAnswer = currentLanguage.getString("qSevenAnsFour");
				}
				
				JLabel questionSevenAnswer = new JLabel(sQuestionSevenAnswer);
				constrainsQuestionNine.gridx = 1;
				constrainsQuestionNine.gridy = 3;
				panelQuestionNine.add(questionSevenAnswer, constrainsQuestionNine);
			}
        }
        
        RadioListenerSeven listenerSeven = new RadioListenerSeven();
        questionSevenSelectionOne.addActionListener(listenerSeven);
        questionSevenSelectionTwo.addActionListener(listenerSeven);
        questionSevenSelectionThree.addActionListener(listenerSeven);
        questionSevenSelectionFour.addActionListener(listenerSeven);

        JButton backToQuestionSix;
        backToQuestionSix = new JButton(currentLanguage.getString("previousQuestion"));
        constrainsQuestionSeven.gridx = 0;
        constrainsQuestionSeven.gridy = 5;
        constrainsQuestionSeven.ipadx = 120;
        constrainsQuestionSeven.ipady = 60;
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
        constrainsQuestionSeven.insets = new Insets(30, 5, 5, 5);
        constrainsQuestionSeven.anchor = GridBagConstraints.SOUTH;
        constrainsQuestionSeven.gridx = 0;
        constrainsQuestionSeven.gridy = 6;
        constrainsQuestionSeven.ipadx = 120;
        constrainsQuestionSeven.ipady = 60;
        panelQuestionSeven.add(toQuestionEight, constrainsQuestionSeven);
        toQuestionEight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JLabel questionSeven;
		        questionSeven = new JLabel(currentLanguage.getString("qSeven"));
		        questionSeven.setFont(new Font("Lucida Grande", Font.BOLD, 20));
				constrainsQuestionNine.gridx = 0;
		        constrainsQuestionNine.gridy = 3;
		        panelQuestionNine.add(questionSeven, constrainsQuestionNine);
				if (questionSevenSelectionOne.isSelected()
					|| questionSevenSelectionTwo.isSelected()
					|| questionSevenSelectionThree.isSelected()
					|| questionSevenSelectionFour.isSelected()){
					cl.show(panelContainer, "8");
				}
			}
		});
        
        // Panel 8
        
        JLabel reviewlabel;
        reviewlabel = new JLabel(currentLanguage.getString("qReview"));
        reviewlabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionEight.gridx = 0;
        constrainsQuestionEight.gridy = 0;
        panelQuestionEight.add(reviewlabel, constrainsQuestionEight);
        
        JButton backToQuestionSeven;
        backToQuestionSeven = new JButton(currentLanguage.getString("previousQuestion"));
        constrainsQuestionEight.gridx = 0;
        constrainsQuestionEight.gridy = 8;
        constrainsQuestionEight.ipadx = 120;
        constrainsQuestionEight.ipady = 60;
        panelQuestionEight.add(backToQuestionSeven, constrainsQuestionEight);
        backToQuestionSeven.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (questionFiveSelectionOne.isSelected()) {
					cl.show(panelContainer, "5");
					}
				else if (questionSixSelectionOne.isSelected()) {
					cl.show(panelContainer, "6");
				} else {cl.show(panelContainer, "7");
				}
			}
		});
        
        JButton toQuestoinNine;
        toQuestoinNine = new JButton(currentLanguage.getString("nextQuestion"));
        constrainsQuestionEight.insets = new Insets(5, 5, 5, 5);
        constrainsQuestionEight.anchor = GridBagConstraints.SOUTH;
        constrainsQuestionEight.gridx = 0;
        constrainsQuestionEight.gridy = 9;
        constrainsQuestionEight.ipadx = 120;
        constrainsQuestionEight.ipady = 60;
        panelQuestionEight.add(toQuestoinNine, constrainsQuestionEight);
        toQuestoinNine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "9");
			}
		});
        
        //Panel 9
        
        reviewlabel = new JLabel(currentLanguage.getString("qReview"));
        reviewlabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionNine.gridx = 0;
        constrainsQuestionNine.gridy = 0;
        panelQuestionNine.add(reviewlabel, constrainsQuestionNine);
 
        JButton backToQuestionEight;
        backToQuestionEight = new JButton(currentLanguage.getString("previousQuestion"));
        constrainsQuestionNine.gridx = 0;
        constrainsQuestionNine.gridy = 7;
        constrainsQuestionNine.ipadx = 120;
        constrainsQuestionNine.ipady = 60;
        panelQuestionNine.add(backToQuestionEight, constrainsQuestionNine);
        backToQuestionEight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "8");
			}
		});
        
        JButton SubmitQuestionnare;
        SubmitQuestionnare = new JButton(currentLanguage.getString("submit"));
        constrainsQuestionNine.anchor = GridBagConstraints.SOUTH;
        constrainsQuestionNine.gridx = 0;
        constrainsQuestionNine.gridy = 8;
        constrainsQuestionNine.ipadx = 120;
        constrainsQuestionNine.ipady = 60;
        panelQuestionNine.add(SubmitQuestionnare, constrainsQuestionNine);
        
        
        SubmitQuestionnare.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HttpClient httpClient = getNewHttpClient();
//		    	HttpPost httpPost = new HttpPost("https://iip3:iip3@128.39.147.213:8181/IipDevU4H/root/provider/publication/info:375745058");
		    	HttpPost httpPost = new HttpPost("https://tablet_0001:tablet_0001@172.25.5.15:8181/IipDevU4H/root/provider/publication/info:634752814");
		    	HttpResponse response = null;
		    	try {
		    		
		    		//Question One
		    		String sQuestionOneAnswer = null;
		    		
		    		if (questionOneSelectionTwo.isSelected()){
		    			sQuestionOneAnswer = "2";
		    		} else if (questionOneSelectionThree.isSelected()){
		    			sQuestionOneAnswer = "3";
		    		} else if (questionOneSelectionFour.isSelected()){
		    			sQuestionOneAnswer = "4";
		    		}
		    		
		    		//Question Two
		    		String sQuestionTwoAnswer = null;
		    		
		    		if(questionTwoSelectionTwo.isSelected()){
		    			sQuestionTwoAnswer = "2";
		    		} else if (questionTwoSelectionThree.isSelected()){
		    			sQuestionTwoAnswer = "3";
		    		} else if (questionTwoSelectionFour.isSelected()){
		    			sQuestionTwoAnswer = "4";
		    		}
		    		
		    		//Question Three
		    		String sQuestionThreeAnswer = null;
		    		
		    		if(questionThreeSelectionTwo.isSelected()){
		    			sQuestionThreeAnswer = "2";
		    		} else if (questionThreeSelectionThree.isSelected()){
		    			sQuestionThreeAnswer = "3";
		    		} else if (questionThreeSelectionFour.isSelected()){
		    			sQuestionThreeAnswer = "4";
		    		}
		    		
		    		//QuestionFour
		    		String sQuestionFourAnswer = null;
		    		
		    		if (questionFourSelectionOne.isSelected()) {
		    			sQuestionFourAnswer = "2";
		    		} else if (questionFourSelectionTwo.isSelected()) {
		    			sQuestionFourAnswer = "3";
		    		} else if (questionFourSelectionThree.isSelected()) {
		    			sQuestionFourAnswer = "4";
		    		}
		    		
		    		//Question Five
		    		String sQuestionFiveAnswer = null;
		    		
		    		if (questionFiveSelectionOne.isSelected()) {
		    			sQuestionFiveAnswer = "1";
		    		} else if (questionFiveSelectionTwo.isSelected()) {
		    			sQuestionFiveAnswer = "2";
		    		} else if (questionFiveSelectionThree.isSelected()) {
		    			sQuestionFiveAnswer = "3";
		    		} else if (questionFiveSelectionFour.isSelected()) {
		    			sQuestionFiveAnswer = "4";
		    		}
		    		
		    		// Question Six
		    		String sQuestionSixAnswer = null;
		    		
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
		    		
		    		//Question Seven
		    		String sQuestionSevenAnswer = null;
		    		
		    		if (questionSevenSelectionOne.isSelected()) {
		    			sQuestionSevenAnswer = "1";
		    		} else if (questionSevenSelectionTwo.isSelected()) {
		    			sQuestionSevenAnswer = "2";
		    		} else if (questionSevenSelectionThree.isSelected()) {
		    			sQuestionSevenAnswer = "3";
		    		} else if (questionSevenSelectionFour.isSelected()) {
		    			sQuestionSevenAnswer = "4";
		    		}
		    		
		    		//Array to send to IIP
		    		
		    		List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("q_1", sQuestionOneAnswer));
		        	params.add(new BasicNameValuePair("q_2", sQuestionTwoAnswer));
		        	params.add(new BasicNameValuePair("q_3", sQuestionThreeAnswer));
		        	params.add(new BasicNameValuePair("q_4", sQuestionFourAnswer));
		        	params.add(new BasicNameValuePair("q_5", sQuestionFiveAnswer));
		        	params.add(new BasicNameValuePair("q_6", sQuestionSixAnswer));
		        	params.add(new BasicNameValuePair("q_7", sQuestionSevenAnswer));
		        	params.add(new BasicNameValuePair("patientId", "1234567"));
		            
		        	httpPost.setEntity(new UrlEncodedFormEntity(params));
		            response = httpClient.execute(httpPost);
		            
		    	} catch (ClientProtocolException e1) {
					// TODO Auto-generated catch block
				} catch (IOException e1) {
					// TODO Auto-generated catch block
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
}

