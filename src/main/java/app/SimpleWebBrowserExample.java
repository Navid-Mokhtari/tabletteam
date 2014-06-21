package app;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SimpleWebBrowserExample extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SimpleWebBrowserExample dialog = new SimpleWebBrowserExample();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SimpleWebBrowserExample() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			
			    //super(new BorderLayout());
			    JPanel webBrowserPanel = new JPanel(new BorderLayout());
			   // webBrowserPanel.setBorder(BorderFactory.createTitledBorder("TEST"));
			   
			    final JWebBrowser webBrowser = new JWebBrowser();
			    webBrowser.navigate("C:/KOLS/KOLS___Pasient_og_p_r_rende.html");
			    webBrowser.setBarsVisible(false);
			    webBrowser.setMenuBarVisible(false);
			    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
			      add(webBrowserPanel, BorderLayout.CENTER);
			    
			  }
		}
	}


