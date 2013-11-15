package app;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class QuestionnareTab extends JComponent {
	private static final long serialVersionUID = -7594299439504858239L;
		
	public Component getView() throws IOException {
		JComponent questionnare = createPanel("My questions");
		questionnare.setPreferredSize(new Dimension(1000, 600));
		return questionnare;
	}
	
	public JPanel createPanel(String title) throws IOException {
        
		//localization
		File file = new File("./questions.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		StringBuffer stringBuffer = new StringBuffer();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line);
		}
		fileReader.close();
		
		String allquestions = stringBuffer.toString();
		
		String[] splitor = allquestions.split(",", 34);
		
		String nextQuestion = splitor[31];
		String previousQuestion = splitor[32];
		String submit = splitor[33];
		
		//Panels
		
		final JPanel panelContainer = new JPanel();
		JPanel panelQuestionOne = new JPanel();
        JPanel panelQuestionTwo = new JPanel();
        JPanel panelQuestionThree = new JPanel();
        JPanel panelQuestionFour = new JPanel();
        JPanel panelQuestionFive = new JPanel();
        JPanel panelQuestionSix = new JPanel();
        JPanel panelQuestionSeven = new JPanel();
        JPanel panelQuestionEight = new JPanel();
        JPanel panelQuestionNine = new JPanel();
        
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
        
		//adding Question one

        
        GridBagConstraints constrainsQuestionOne = new GridBagConstraints();
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 10;
        constrainsQuestionOne.insets = new Insets(5, 5, 5, 5);
//        constrainsQuestionOne.fill = GridBagConstraints.CENTER;
        constrainsQuestionOne.anchor = GridBagConstraints.FIRST_LINE_START;
        
		String qOne = splitor[0];
		String qOneAnsOne = splitor[1];
		String qOneAnsTwo = splitor[2];
		String qOneAnsThree = splitor[3];
		String qOneAnsFour = splitor[4];
        
        JLabel questionOne;
        questionOne = new JLabel("Q1: " + qOne);
        questionOne.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 0;
        panelQuestionOne.add(questionOne, constrainsQuestionOne);
        
        JRadioButton questionOneSelectionOne;
        questionOneSelectionOne = new JRadioButton(qOneAnsOne);
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 1;
        panelQuestionOne.add(questionOneSelectionOne, constrainsQuestionOne);
        
        JRadioButton questionOneSelectionTwo;
        questionOneSelectionTwo = new JRadioButton(qOneAnsTwo);
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 2;
        panelQuestionOne.add(questionOneSelectionTwo, constrainsQuestionOne);
        
        JRadioButton questionOneSelectionThree;
        questionOneSelectionThree = new JRadioButton(qOneAnsThree);
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 3;
        panelQuestionOne.add(questionOneSelectionThree, constrainsQuestionOne);
        
        JRadioButton questionOneSelectionFour;
        questionOneSelectionFour = new JRadioButton(qOneAnsFour);
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 7;
        panelQuestionOne.add(questionOneSelectionFour, constrainsQuestionOne);
        
        ButtonGroup questionOneGroup = new ButtonGroup();
        questionOneGroup.add(questionOneSelectionOne);
        questionOneGroup.add(questionOneSelectionTwo);
        questionOneGroup.add(questionOneSelectionThree);
        questionOneGroup.add(questionOneSelectionFour);

        JButton toQuestionTwo;
        toQuestionTwo = new JButton(nextQuestion);
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 8;
        constrainsQuestionOne.ipadx = 100;
        constrainsQuestionOne.ipady = 60;
        panelQuestionOne.add(toQuestionTwo, constrainsQuestionOne);
        toQuestionTwo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "2");
				
			}
		});
        
        // Adding question number two
        
        GridBagConstraints constrainsQuestionTwo = new GridBagConstraints();
        constrainsQuestionTwo.insets = new Insets(5, 5, 5, 5);
        constrainsQuestionTwo.fill = GridBagConstraints.HORIZONTAL;
        constrainsQuestionTwo.anchor = GridBagConstraints.FIRST_LINE_START;
        
        String qTwo = splitor[5];
		String qTwoAnsOne = splitor[6];
		String qTwoAnsTwo = splitor[7];
		String qTwoAnsThree = splitor[8];
		String qTwoAnsFour = splitor[9];
        
        JLabel questionTwo;
        questionTwo = new JLabel("Q2: " + qTwo);
        questionTwo.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 0;
        panelQuestionTwo.add(questionTwo, constrainsQuestionTwo);
        
        JRadioButton questionTwoSelectionOne;
        questionTwoSelectionOne = new JRadioButton(qTwoAnsOne);
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 1;
        panelQuestionTwo.add(questionTwoSelectionOne, constrainsQuestionTwo);
        
        JRadioButton questionTwoSelectionTwo;
        questionTwoSelectionTwo = new JRadioButton(qTwoAnsTwo);
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 2;
        panelQuestionTwo.add(questionTwoSelectionTwo, constrainsQuestionTwo);
        
        JRadioButton questionTwoSelectionThree;
        questionTwoSelectionThree = new JRadioButton(qTwoAnsThree);
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 3;
        panelQuestionTwo.add(questionTwoSelectionThree, constrainsQuestionTwo);
        
        JRadioButton questionTwoSelectionFour;
        questionTwoSelectionFour = new JRadioButton(qTwoAnsFour);
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 4;
        panelQuestionTwo.add(questionTwoSelectionFour, constrainsQuestionTwo);
        
        
        ButtonGroup questionTwoGroup = new ButtonGroup();
        questionTwoGroup.add(questionTwoSelectionOne);
        questionTwoGroup.add(questionTwoSelectionTwo);
        questionTwoGroup.add(questionTwoSelectionThree);
        questionTwoGroup.add(questionTwoSelectionFour);

        JButton toQuestionOne;
        toQuestionOne = new JButton(previousQuestion);
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
        toQuestionThree = new JButton(nextQuestion);
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 8;
        constrainsQuestionTwo.ipadx = 120;
        constrainsQuestionTwo.ipady = 60;
        panelQuestionTwo.add(toQuestionThree, constrainsQuestionTwo);
        toQuestionThree.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "3");
			}
		});
        
// Adding question number three
        
        GridBagConstraints constrainsQuestionThree = new GridBagConstraints();
        constrainsQuestionThree.insets = new Insets(5, 5, 5, 5);
        constrainsQuestionThree.fill = GridBagConstraints.HORIZONTAL;
        constrainsQuestionThree.anchor = GridBagConstraints.FIRST_LINE_START;
        
        String qThree = splitor[10];
		String qThreeAnsOne = splitor[11];
		String qThreeAnsTwo = splitor[12];
		String qThreeAnsThree = splitor[13];
		String qThreeAnsFour = splitor[14];
        
        
        JLabel questionThree;
        questionThree = new JLabel("Q3: " + qThree);
        questionThree.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 0;
        panelQuestionThree.add(questionThree, constrainsQuestionThree);
        
        JRadioButton questionThreeSelectionOne;
        questionThreeSelectionOne = new JRadioButton(qThreeAnsOne);
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 1;
        panelQuestionThree.add(questionThreeSelectionOne, constrainsQuestionThree);
        
        JRadioButton questionThreeSelectionTwo;
        questionThreeSelectionTwo = new JRadioButton(qThreeAnsTwo);
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 2;
        panelQuestionThree.add(questionThreeSelectionTwo, constrainsQuestionThree);
        
        JRadioButton questionThreeSelectionThree;
        questionThreeSelectionThree = new JRadioButton(qThreeAnsThree);
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 3;
        panelQuestionThree.add(questionThreeSelectionThree, constrainsQuestionThree);
        
        JRadioButton questionThreeSelectionFour;
        questionThreeSelectionFour = new JRadioButton(qThreeAnsFour);
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 4;
        panelQuestionThree.add(questionThreeSelectionFour, constrainsQuestionThree);
        
        ButtonGroup questionThreeGroup = new ButtonGroup();
        questionThreeGroup.add(questionThreeSelectionOne);
        questionThreeGroup.add(questionThreeSelectionTwo);
        questionThreeGroup.add(questionThreeSelectionThree);
        questionThreeGroup.add(questionThreeSelectionFour);
        
        JButton backToQuestionTwo;
        backToQuestionTwo = new JButton(previousQuestion);
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
        toQuestionFour = new JButton(nextQuestion);
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 8;
        constrainsQuestionThree.ipadx = 120;
        constrainsQuestionThree.ipady = 60;
        panelQuestionThree.add(toQuestionFour, constrainsQuestionThree);
        toQuestionFour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "4");
			}
		});
        
// Adding question number four
        
        GridBagConstraints constrainsQuestionFour = new GridBagConstraints();
        constrainsQuestionFour.insets = new Insets(5, 5, 5, 5);
        constrainsQuestionFour.fill = GridBagConstraints.HORIZONTAL;
        constrainsQuestionFour.anchor = GridBagConstraints.FIRST_LINE_START;
        
        String qFour = splitor[15];
		String qFourAnsOne = splitor[16];
		String qFourAnsTwo = splitor[17];
        
        JLabel questionFour;
        questionFour = new JLabel("Q4: " + qFour);
        questionFour.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionFour.gridx = 0;
        constrainsQuestionFour.gridy = 0;
        panelQuestionFour.add(questionFour, constrainsQuestionFour);
        
        JRadioButton questionFourSelectionOne;
        questionFourSelectionOne = new JRadioButton(qFourAnsOne);
        constrainsQuestionFour.gridx = 0;
        constrainsQuestionFour.gridy = 1;
        panelQuestionFour.add(questionFourSelectionOne, constrainsQuestionFour);
        
        JRadioButton questionFourSelectionTwo;
        questionFourSelectionTwo = new JRadioButton(qFourAnsTwo);
        constrainsQuestionFour.gridx = 0;
        constrainsQuestionFour.gridy = 2;
        panelQuestionFour.add(questionFourSelectionTwo, constrainsQuestionFour);
        
        ButtonGroup questionFourGroup = new ButtonGroup();
        questionFourGroup.add(questionFourSelectionOne);
        questionFourGroup.add(questionFourSelectionTwo);

        JButton backToQuestionThree;
        backToQuestionThree = new JButton(previousQuestion);
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
        toQuestionFive = new JButton(nextQuestion);
        constrainsQuestionFour.gridx = 0;
        constrainsQuestionFour.gridy = 8;
        constrainsQuestionFour.ipadx = 120;
        constrainsQuestionFour.ipady = 60;
        panelQuestionFour.add(toQuestionFive, constrainsQuestionFour);
        toQuestionFive.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "5");
			}
		});
        
// Adding question number five
        
        GridBagConstraints constrainsQuestionFive = new GridBagConstraints();
        constrainsQuestionFive.insets = new Insets(5, 5, 5, 5);
        constrainsQuestionFive.fill = GridBagConstraints.HORIZONTAL;
        constrainsQuestionFive.anchor = GridBagConstraints.FIRST_LINE_START;
        
        String qFive = splitor[18];
		String qFiveAnsOne = splitor[19];
		String qFiveAnsTwo = splitor[20];
		String qFiveAnsThree = splitor[21];
        
        JLabel questionFive;
        questionFive = new JLabel("Q5: " + qFive);
        questionFive.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 0;
        panelQuestionFive.add(questionFive, constrainsQuestionFive);
        
        JRadioButton questionFiveSelectionOne;
        questionFiveSelectionOne = new JRadioButton(qFiveAnsOne);
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 1;
        panelQuestionFive.add(questionFiveSelectionOne, constrainsQuestionFive);
        
        JRadioButton questionFiveSelectionTwo;
        questionFiveSelectionTwo = new JRadioButton(qFiveAnsTwo);
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 2;
        panelQuestionFive.add(questionFiveSelectionTwo, constrainsQuestionFive);
        
        JRadioButton questionFiveSelectionThree;
        questionFiveSelectionThree = new JRadioButton(qFiveAnsThree);
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 3;
        panelQuestionFive.add(questionFiveSelectionThree, constrainsQuestionFive);
        
        ButtonGroup questionFiveGroup = new ButtonGroup();
        questionFiveGroup.add(questionFiveSelectionOne);
        questionFiveGroup.add(questionFiveSelectionTwo);
        questionFiveGroup.add(questionFiveSelectionThree);

        JButton backToQuestionFour;
        backToQuestionFour = new JButton(previousQuestion);
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
        toQuestionSix = new JButton(nextQuestion);
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 8;
        constrainsQuestionFive.ipadx = 120;
        constrainsQuestionFive.ipady = 60;
        panelQuestionFive.add(toQuestionSix, constrainsQuestionFive);
        toQuestionSix.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "6");
			}
		});
        
// Adding question number six
        
        GridBagConstraints constrainsQuestionSix = new GridBagConstraints();
        constrainsQuestionSix.insets = new Insets(5, 5, 5, 5);
        constrainsQuestionSix.fill = GridBagConstraints.HORIZONTAL;
        constrainsQuestionSix.anchor = GridBagConstraints.FIRST_LINE_START;
        
        String qSix = splitor[22];
		String qSixAnsOne = splitor[23];
		String qSixAnsTwo = splitor[24];
        
        JLabel questionSix;
        questionSix = new JLabel("Q6: " + qSix);
        questionSix.setFont(new Font("Lucida Grande", Font.BOLD, 17));
        constrainsQuestionSix.gridx = 0;
        constrainsQuestionSix.gridy = 0;
        panelQuestionSix.add(questionSix, constrainsQuestionSix);
        
        final JRadioButton questionSixSelectionOne;
        questionSixSelectionOne = new JRadioButton(qSixAnsOne);
        constrainsQuestionSix.gridx = 0;
        constrainsQuestionSix.gridy = 1;
        panelQuestionSix.add(questionSixSelectionOne, constrainsQuestionSix);
        
        final JRadioButton questionSixSelectionTwo;
        questionSixSelectionTwo = new JRadioButton(qSixAnsTwo);
        constrainsQuestionSix.gridx = 0;
        constrainsQuestionSix.gridy = 2;
        panelQuestionSix.add(questionSixSelectionTwo, constrainsQuestionSix);
         
        ButtonGroup questionSixGroup = new ButtonGroup();
        questionSixGroup.add(questionSixSelectionOne);
        questionSixGroup.add(questionSixSelectionTwo);
        
        JButton backToQuestionFive;
        backToQuestionFive = new JButton(previousQuestion);
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
        toQuestionSeven = new JButton(nextQuestion);
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
				if (questionSixSelectionTwo.isSelected()){
					cl.show(panelContainer, "7");
				}
				if (questionSixSelectionOne.isSelected()) {
					cl.show(panelContainer, "9");
				}
			}
		});
        
        //adding question seven
        
        
        GridBagConstraints constrainsQuestionSeven = new GridBagConstraints();
        constrainsQuestionSeven.insets = new Insets(5, 5, 5, 5);
        constrainsQuestionSeven.fill = GridBagConstraints.HORIZONTAL;
        constrainsQuestionSeven.anchor = GridBagConstraints.FIRST_LINE_START;
        
        String qSeven = splitor[25];
		String qSevenAnsOne = splitor[26];
		String qSevenAnsTwo = splitor[27];
        
        JLabel questionSeven;
        questionSeven = new JLabel("Q7: " + qSeven);
        questionSeven.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionSeven.gridx = 0;
        constrainsQuestionSeven.gridy = 0;
        panelQuestionSeven.add(questionSeven, constrainsQuestionSeven);
        
        final JRadioButton questionSevenSelectionOne;
        questionSevenSelectionOne = new JRadioButton(qSevenAnsOne);
        constrainsQuestionSeven.gridx = 0;
        constrainsQuestionSeven.gridy = 1;
        panelQuestionSeven.add(questionSevenSelectionOne, constrainsQuestionSeven);
        
        final JRadioButton questionSevenSelectionTwo;
        questionSevenSelectionTwo = new JRadioButton(qSevenAnsTwo);
        constrainsQuestionSeven.gridx = 0;
        constrainsQuestionSeven.gridy = 2;
        panelQuestionSeven.add(questionSevenSelectionTwo, constrainsQuestionSeven);
         
        ButtonGroup questionSevenGroup = new ButtonGroup();
        questionSevenGroup.add(questionSevenSelectionOne);
        questionSevenGroup.add(questionSevenSelectionTwo);

        JButton backToQuestionSix;
        backToQuestionSix = new JButton(previousQuestion);
        constrainsQuestionSeven.gridx = 0;
        constrainsQuestionSeven.gridy = 3;
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
        toQuestionEight = new JButton(nextQuestion);
        constrainsQuestionSeven.insets = new Insets(30, 5, 5, 5);
        constrainsQuestionSeven.anchor = GridBagConstraints.SOUTH;
        constrainsQuestionSeven.gridx = 0;
        constrainsQuestionSeven.gridy = 4;
        constrainsQuestionSeven.ipadx = 120;
        constrainsQuestionSeven.ipady = 60;
        panelQuestionSeven.add(toQuestionEight, constrainsQuestionSeven);
        toQuestionEight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (questionSevenSelectionOne.isSelected()){
					cl.show(panelContainer, "9");
				}
				if (questionSevenSelectionTwo.isSelected()){
					cl.show(panelContainer, "8");
				}
			}
		});
        
        // Panel 8
        
        GridBagConstraints constrainsQuestionEight = new GridBagConstraints();
        constrainsQuestionEight.insets = new Insets(5, 5, 5, 5);
        constrainsQuestionEight.fill = GridBagConstraints.HORIZONTAL;
        constrainsQuestionEight.anchor = GridBagConstraints.FIRST_LINE_START;
        
        String qEightOne = splitor[28];
		String qEightTwo = splitor[29];
        
        JLabel questionEightOne;
        questionEightOne = new JLabel(qEightOne);
        questionEightOne.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionEight.gridx = 0;
        constrainsQuestionEight.gridy = 1;
        panelQuestionEight.add(questionEightOne, constrainsQuestionEight);
        
        JTextField eightOne;
        eightOne = new JTextField();
        constrainsQuestionEight.gridx = 0;
        constrainsQuestionEight.gridy = 2;
        panelQuestionEight.add(eightOne, constrainsQuestionEight);
        
        JLabel questionEightTwo;
        questionEightTwo = new JLabel(qEightTwo);
        questionEightTwo.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionEight.gridx = 0;
        constrainsQuestionEight.gridy = 3;
        panelQuestionEight.add(questionEightTwo, constrainsQuestionEight);
        
        JTextField eightTwo;
        eightTwo = new JTextField();
        constrainsQuestionEight.gridx = 0;
        constrainsQuestionEight.gridy = 4;
        panelQuestionEight.add(eightTwo, constrainsQuestionEight);
        
        JButton backToQuestionSeven;
        backToQuestionSeven = new JButton(previousQuestion);
        constrainsQuestionEight.gridx = 0;
        constrainsQuestionEight.gridy = 5;
        constrainsQuestionEight.ipadx = 120;
        constrainsQuestionEight.ipady = 60;
        panelQuestionEight.add(backToQuestionSeven, constrainsQuestionEight);
        backToQuestionSeven.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "7");
				}
		});
        
        JButton toQuestoinNine;
        toQuestoinNine = new JButton(nextQuestion);
        constrainsQuestionEight.insets = new Insets(30, 5, 5, 5);
        constrainsQuestionEight.anchor = GridBagConstraints.SOUTH;
        constrainsQuestionEight.gridx = 0;
        constrainsQuestionEight.gridy = 6;
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
        
        GridBagConstraints constrainsQuestionNine = new GridBagConstraints();
        constrainsQuestionNine.insets = new Insets(5, 5, 5, 5);
        constrainsQuestionNine.fill = GridBagConstraints.HORIZONTAL;
        constrainsQuestionNine.anchor = GridBagConstraints.FIRST_LINE_START;
        
        String qNineOne = splitor[30];
        
        JLabel questionNineOne;
        questionNineOne = new JLabel(qNineOne);
        questionNineOne.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionNine.gridx = 0;
        constrainsQuestionNine.gridy = 1;
        panelQuestionNine.add(questionNineOne, constrainsQuestionNine);
        
        JButton backToQuestionEight;
        backToQuestionEight = new JButton(previousQuestion);
        constrainsQuestionNine.gridx = 0;
        constrainsQuestionNine.gridy = 3;
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
        SubmitQuestionnare = new JButton(submit);
        constrainsQuestionNine.insets = new Insets(30, 5, 5, 5);
        constrainsQuestionNine.anchor = GridBagConstraints.SOUTH;
        constrainsQuestionNine.gridx = 0;
        constrainsQuestionNine.gridy = 4;
        constrainsQuestionNine.ipadx = 120;
        constrainsQuestionNine.ipady = 60;
        panelQuestionNine.add(SubmitQuestionnare, constrainsQuestionNine);
        SubmitQuestionnare.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "9");
			}
		});
        
        return panelContainer;
    }		
}

