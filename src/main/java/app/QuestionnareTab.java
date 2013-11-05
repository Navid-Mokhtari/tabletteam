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

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class QuestionnareTab extends JComponent {
	private static final long serialVersionUID = -7594299439504858239L;
		
	public Component getView() {
		JComponent questionnare = createPanel("My questions");
		questionnare.setPreferredSize(new Dimension(1000, 600));
		return questionnare;
	}
	
	public JPanel createPanel(String title) {
        
		//Panels
		
		final JPanel panelContainer = new JPanel();
		JPanel panelQuestionOne = new JPanel();
        JPanel panelQuestionTwo = new JPanel();
        JPanel panelQuestionThree = new JPanel();
        JPanel panelQuestionFour = new JPanel();
        JPanel panelQuestionFive = new JPanel();
        JPanel panelQuestionSix = new JPanel();
        
        //Container panel layout
		
		final CardLayout cl = new CardLayout();
		panelContainer.setLayout(cl);
		panelContainer.add(panelQuestionOne, "1");
		panelContainer.add(panelQuestionTwo, "2");
		panelContainer.add(panelQuestionThree, "3");
		panelContainer.add(panelQuestionFour, "4");
		panelContainer.add(panelQuestionFive, "5");
		panelContainer.add(panelQuestionSix, "6");
		cl.show(panelContainer, "1");
		
		//adding Question one
		
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelQuestionOne.setLayout(gbl_panel);
        panelQuestionTwo.setLayout(gbl_panel);
        panelQuestionThree.setLayout(gbl_panel);
        panelQuestionFour.setLayout(gbl_panel);
        panelQuestionFive.setLayout(gbl_panel);
        panelQuestionSix.setLayout(gbl_panel);
        

        
        GridBagConstraints constrainsQuestionOne = new GridBagConstraints();
        constrainsQuestionOne.insets = new Insets(5, 5, 5, 5);
        constrainsQuestionOne.fill = GridBagConstraints.HORIZONTAL;
        constrainsQuestionOne.anchor = GridBagConstraints.FIRST_LINE_START;
        
        JLabel questionOne;
        questionOne = new JLabel("Q1: How do you feel today?");
        questionOne.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionOne.ipadx = 220;
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 0;
        panelQuestionOne.add(questionOne, constrainsQuestionOne);
        
        JRadioButton questionOneSelectionOne;
        questionOneSelectionOne = new JRadioButton("Much worse");
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 1;
        panelQuestionOne.add(questionOneSelectionOne, constrainsQuestionOne);
        
        JRadioButton questionOneSelectionTwo;
        questionOneSelectionTwo = new JRadioButton("Worse");
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 2;
        panelQuestionOne.add(questionOneSelectionTwo, constrainsQuestionOne);
        
        JRadioButton questionOneSelectionThree;
        questionOneSelectionThree = new JRadioButton("Bit worse");
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 3;
        panelQuestionOne.add(questionOneSelectionThree, constrainsQuestionOne);
        
        JRadioButton questionOneSelectionFour;
        questionOneSelectionFour = new JRadioButton("Bit better");
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 4;
        panelQuestionOne.add(questionOneSelectionFour, constrainsQuestionOne);
        
        JRadioButton questionOneSelectionFive;
        questionOneSelectionFive = new JRadioButton("Better");
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 5;
        panelQuestionOne.add(questionOneSelectionFive, constrainsQuestionOne);
        
        JRadioButton questionOneSelectionSix;
        questionOneSelectionSix = new JRadioButton("Much Better");
        constrainsQuestionOne.gridx = 0;
        constrainsQuestionOne.gridy = 6;
        panelQuestionOne.add(questionOneSelectionSix, constrainsQuestionOne);
        
        ButtonGroup questionOneGroup = new ButtonGroup();
        questionOneGroup.add(questionOneSelectionOne);
        questionOneGroup.add(questionOneSelectionTwo);
        questionOneGroup.add(questionOneSelectionThree);
        questionOneGroup.add(questionOneSelectionFour);
        questionOneGroup.add(questionOneSelectionFive);
        questionOneGroup.add(questionOneSelectionSix);

        JButton toQuestionTwo;
        toQuestionTwo = new JButton("Next question");
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
        
        JLabel questionTwo;
        questionTwo = new JLabel("Q2: How is your breathing?");
        questionTwo.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionTwo.ipadx = 180;
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 0;
        panelQuestionTwo.add(questionTwo, constrainsQuestionTwo);
        
        JRadioButton questionTwoSelectionOne;
        questionTwoSelectionOne = new JRadioButton("Much worse");
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 1;
        panelQuestionTwo.add(questionTwoSelectionOne, constrainsQuestionTwo);
        
        JRadioButton questionTwoSelectionTwo;
        questionTwoSelectionTwo = new JRadioButton("Worse");
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 2;
        panelQuestionTwo.add(questionTwoSelectionTwo, constrainsQuestionTwo);
        
        JRadioButton questionTwoSelectionThree;
        questionTwoSelectionThree = new JRadioButton("Bit worse");
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 3;
        panelQuestionTwo.add(questionTwoSelectionThree, constrainsQuestionTwo);
        
        JRadioButton questionTwoSelectionFour;
        questionTwoSelectionFour = new JRadioButton("Bit better");
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 4;
        panelQuestionTwo.add(questionTwoSelectionFour, constrainsQuestionTwo);
        
        JRadioButton questionTwoSelectionFive;
        questionTwoSelectionFive = new JRadioButton("Better");
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 5;
        panelQuestionTwo.add(questionTwoSelectionFive, constrainsQuestionTwo);
        
        JRadioButton questionTwoSelectionSix;
        questionTwoSelectionSix = new JRadioButton("Much Better");
        constrainsQuestionTwo.gridx = 0;
        constrainsQuestionTwo.gridy = 6;
        panelQuestionTwo.add(questionTwoSelectionSix, constrainsQuestionTwo);
        
        ButtonGroup questionTwoGroup = new ButtonGroup();
        questionTwoGroup.add(questionTwoSelectionOne);
        questionTwoGroup.add(questionTwoSelectionTwo);
        questionTwoGroup.add(questionTwoSelectionThree);
        questionTwoGroup.add(questionTwoSelectionFour);
        questionTwoGroup.add(questionTwoSelectionFive);
        questionTwoGroup.add(questionTwoSelectionSix);

        JButton toQuestionOne;
        toQuestionOne = new JButton("Previous question");
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
        toQuestionThree = new JButton("Next question");
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
        
        JLabel questionThree;
        questionThree = new JLabel("Q3: How is the amount of your sputum?");
        questionThree.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionThree.ipadx = 180;
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 0;
        panelQuestionThree.add(questionThree, constrainsQuestionThree);
        
        JRadioButton questionThreeSelectionOne;
        questionThreeSelectionOne = new JRadioButton("Much worse");
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 1;
        panelQuestionThree.add(questionThreeSelectionOne, constrainsQuestionThree);
        
        JRadioButton questionThreeSelectionTwo;
        questionThreeSelectionTwo = new JRadioButton("Worse");
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 2;
        panelQuestionThree.add(questionThreeSelectionTwo, constrainsQuestionThree);
        
        JRadioButton questionThreeSelectionThree;
        questionThreeSelectionThree = new JRadioButton("Bit worse");
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 3;
        panelQuestionThree.add(questionThreeSelectionThree, constrainsQuestionThree);
        
        JRadioButton questionThreeSelectionFour;
        questionThreeSelectionFour = new JRadioButton("Bit better");
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 4;
        panelQuestionThree.add(questionThreeSelectionFour, constrainsQuestionThree);
        
        JRadioButton questionThreeSelectionFive;
        questionThreeSelectionFive = new JRadioButton("Better");
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 5;
        panelQuestionThree.add(questionThreeSelectionFive, constrainsQuestionThree);
        
        JRadioButton questionThreeSelectionSix;
        questionThreeSelectionSix = new JRadioButton("Much Better");
        constrainsQuestionThree.gridx = 0;
        constrainsQuestionThree.gridy = 6;
        panelQuestionThree.add(questionThreeSelectionSix, constrainsQuestionThree);
        
        ButtonGroup questionThreeGroup = new ButtonGroup();
        questionThreeGroup.add(questionThreeSelectionOne);
        questionThreeGroup.add(questionThreeSelectionTwo);
        questionThreeGroup.add(questionThreeSelectionThree);
        questionThreeGroup.add(questionThreeSelectionFour);
        questionThreeGroup.add(questionThreeSelectionFive);
        questionThreeGroup.add(questionThreeSelectionSix);

        JButton backToQuestionTwo;
        backToQuestionTwo = new JButton("Previous question");
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
        toQuestionFour = new JButton("Next question");
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
        
        JLabel questionFour;
        questionFour = new JLabel("Q4: How is your sputum colour?");
        questionFour.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionFour.ipadx = 180;
        constrainsQuestionFour.gridx = 0;
        constrainsQuestionFour.gridy = 0;
        panelQuestionFour.add(questionFour, constrainsQuestionFour);
        
        JRadioButton questionFourSelectionOne;
        questionFourSelectionOne = new JRadioButton("Brown");
        constrainsQuestionFour.gridx = 0;
        constrainsQuestionFour.gridy = 1;
        panelQuestionFour.add(questionFourSelectionOne, constrainsQuestionFour);
        
        JRadioButton questionFourSelectionTwo;
        questionFourSelectionTwo = new JRadioButton("Dark green");
        constrainsQuestionFour.gridx = 0;
        constrainsQuestionFour.gridy = 2;
        panelQuestionFour.add(questionFourSelectionTwo, constrainsQuestionFour);
        
        JRadioButton questionFourSelectionThree;
        questionFourSelectionThree = new JRadioButton("Yellow");
        constrainsQuestionFour.gridx = 0;
        constrainsQuestionFour.gridy = 3;
        panelQuestionFour.add(questionFourSelectionThree, constrainsQuestionFour);
        
        JRadioButton questionFourSelectionFour;
        questionFourSelectionFour = new JRadioButton("Clear/White");
        constrainsQuestionFour.gridx = 0;
        constrainsQuestionFour.gridy = 4;
        panelQuestionFour.add(questionFourSelectionFour, constrainsQuestionFour);
        
                
        ButtonGroup questionFourGroup = new ButtonGroup();
        questionFourGroup.add(questionFourSelectionOne);
        questionFourGroup.add(questionFourSelectionTwo);
        questionFourGroup.add(questionFourSelectionThree);
        questionFourGroup.add(questionFourSelectionFour);
        

        JButton backToQuestionThree;
        backToQuestionThree = new JButton("Previous question");
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
        toQuestionFive = new JButton("Next question");
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
        
        JLabel questionFive;
        questionFive = new JLabel("Q5: How are you using your reliever Inhalers/ nebs or oxygen?");
        questionFive.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionFive.ipadx = 180;
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 0;
        panelQuestionFive.add(questionFive, constrainsQuestionFive);
        
        JRadioButton questionFiveSelectionOne;
        questionFiveSelectionOne = new JRadioButton("Much more than usual");
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 1;
        panelQuestionFive.add(questionFiveSelectionOne, constrainsQuestionFive);
        
        JRadioButton questionFiveSelectionTwo;
        questionFiveSelectionTwo = new JRadioButton("More than usual");
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 2;
        panelQuestionFive.add(questionFiveSelectionTwo, constrainsQuestionFive);
        
        JRadioButton questionFiveSelectionThree;
        questionFiveSelectionThree = new JRadioButton("Bit more than usual");
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 3;
        panelQuestionFive.add(questionFiveSelectionThree, constrainsQuestionFive);
        
        JRadioButton questionFiveSelectionFour;
        questionFiveSelectionFour = new JRadioButton("Bit less than usual");
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 4;
        panelQuestionFive.add(questionFiveSelectionFour, constrainsQuestionFive);
        
        JRadioButton questionFiveSelectionFive;
        questionFiveSelectionFive = new JRadioButton("Less than usual");
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 5;
        panelQuestionFive.add(questionFiveSelectionFive, constrainsQuestionFive);
        
        JRadioButton questionFiveSelectionSix;
        questionFiveSelectionSix = new JRadioButton("Much less than usual");
        constrainsQuestionFive.gridx = 0;
        constrainsQuestionFive.gridy = 6;
        panelQuestionFive.add(questionFiveSelectionSix, constrainsQuestionFive);
        
        ButtonGroup questionFiveGroup = new ButtonGroup();
        questionFiveGroup.add(questionFiveSelectionOne);
        questionFiveGroup.add(questionFiveSelectionTwo);
        questionFiveGroup.add(questionFiveSelectionThree);
        questionFiveGroup.add(questionFiveSelectionFour);
        questionFiveGroup.add(questionFiveSelectionFive);
        questionFiveGroup.add(questionFiveSelectionSix);

        JButton backToQuestionFour;
        backToQuestionFour = new JButton("Previous question");
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
        toQuestionSix = new JButton("Next question");
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
        
        JLabel questionSix;
        questionSix = new JLabel("Q6: Are you taking any EXTRA antibiotics or steriods at the moment?");
        questionSix.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        constrainsQuestionSix.ipadx = 180;
        constrainsQuestionSix.gridx = 0;
        constrainsQuestionSix.gridy = 0;
        panelQuestionSix.add(questionSix, constrainsQuestionSix);
        
        JRadioButton questionSixSelectionOne;
        questionSixSelectionOne = new JRadioButton("Yes");
        constrainsQuestionSix.gridx = 0;
        constrainsQuestionSix.gridy = 1;
        panelQuestionSix.add(questionSixSelectionOne, constrainsQuestionSix);
        
        JRadioButton questionSixSelectionTwo;
        questionSixSelectionTwo = new JRadioButton("No");
        constrainsQuestionSix.gridx = 0;
        constrainsQuestionSix.gridy = 2;
        panelQuestionSix.add(questionSixSelectionTwo, constrainsQuestionSix);
        
        
                
        ButtonGroup questionSixGroup = new ButtonGroup();
        questionSixGroup.add(questionSixSelectionOne);
        questionSixGroup.add(questionSixSelectionTwo);
                

        JButton backToQuestionFive;
        backToQuestionFive = new JButton("Previous question");
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
        
        JButton SubmitQuestionnare;
        SubmitQuestionnare = new JButton("Submit");
        constrainsQuestionSix.insets = new Insets(30, 5, 5, 5);
        constrainsQuestionSix.anchor = GridBagConstraints.SOUTH;
        constrainsQuestionSix.gridx = 0;
        constrainsQuestionSix.gridy = 8;
        constrainsQuestionSix.ipadx = 120;
        constrainsQuestionSix.ipady = 60;
        panelQuestionSix.add(SubmitQuestionnare, constrainsQuestionSix);
        SubmitQuestionnare.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(panelContainer, "6");
			}
		});
        
        return panelContainer;
    }		
}

