package app;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class ChatPanel1 extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6110014274129679072L;

	public Component getView() {
		JComponent chat = createPanel("Chat Panel");
		chat.setPreferredSize(new Dimension(300, 600));
		return chat;
	}

	
	private JPanel createPanel(String string) {
	
		final JPanel chatPanelContainer = new JPanel();
		JPanel chatPanel = new JPanel();
		chatPanel.setName("cp");

		final CardLayout cl = new CardLayout();
		chatPanelContainer.setLayout(cl);
		chatPanelContainer.add(chatPanel, "chatPanel");
		JButton callButton;
		callButton = new JButton("Call");
		chatPanel.add(callButton);
		callButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JButton answer;
		answer = new JButton("Answer");
		chatPanel.add(answer);
		answer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(chatPanelContainer, "answer");

			}
		});

		JButton disconnect;
		disconnect = new JButton("Disconnect");

		chatPanel.add(disconnect);
		disconnect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(chatPanelContainer, "disconnect");

			}
		});

		return chatPanelContainer;
	}
}