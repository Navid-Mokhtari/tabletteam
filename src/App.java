import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



public class App extends JFrame implements ActionListener, DocumentListener {
	private static final long serialVersionUID = 5430513214412853815L;
	private int bigWeight = 1000;

	private JPanel panel;

	public App() {
		super("eHealth monitoring");
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth()) - 100;
		int ySize = ((int) tk.getScreenSize().getHeight()) - 100;
		this.setSize(xSize, ySize);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		panel = new JPanel(new GridBagLayout());
		GridBagConstraints constrains = new GridBagConstraints();
		constrains.insets = new Insets(5, 5, 5, 5);
		bigWeight = xSize - 200;

		getContentPane().add(add(panel), BorderLayout.PAGE_START);
	}

	public static void main(String[] args) {
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
