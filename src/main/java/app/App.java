package app;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class App extends JFrame implements ActionListener, DocumentListener {
	private static final long serialVersionUID = 5430513214412853815L;

	private JPanel tabPanel;
	private ConferencePanel conferencePanel;
	private Tabs tabs;
	private ConfPanel confPanel;

	public App() throws IOException {
		super("eHealth monitoring");
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		this.setSize(xSize, ySize);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		tabPanel = new JPanel(new GridBagLayout());
		tabs = new Tabs();
		tabPanel.add(tabs);
		getContentPane().add(add(tabPanel), BorderLayout.WEST);
//		confPanel = new ConfPanel(new GridBagLayout());
//		getContentPane().add(add(confPanel), BorderLayout.CENTER);
		/*
		 * conferencePanel = new ConferencePanel(new GridBagLayout());
		 * getContentPane().add(add(conferencePanel), BorderLayout.CENTER);
		 */
	}

	public static void main(String[] args) throws IOException {
		HealthProperties healthProperties = new HealthProperties();
		healthProperties.loadProperties();
		App initializer = new App();
		initializer.setVisible(true);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
