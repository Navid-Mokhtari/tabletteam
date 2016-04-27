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
import java.io.File;
import java.net.URISyntaxException;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserListener;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowOpeningEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FlashPlayer extends JDialog {

	/**
	 * Create the dialog.
	 */
	public FlashPlayer() {
		setBounds(MainPage.getWindowSize());
		setResizable(false);
		setUndecorated(true);

		final JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JWebBrowser webBrowser;
		try {
			webBrowser = createWebBrowser();
			contentPanel.add(webBrowser, BorderLayout.CENTER);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		add(contentPanel, BorderLayout.CENTER);
	}

	private JWebBrowser createWebBrowser() throws URISyntaxException {
		final JWebBrowser webBrowser = new JWebBrowser();
		final String url = new File(new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile(), "web/KOLS/KOLS___Pasient_og_p_r_rende.html").toURI().toString();
		//webBrowser.navigate(getClass().getResource("/KOLS/KOLS___Pasient_og_p_r_rende.html").toString());
		webBrowser.navigate(url);
		webBrowser.setBarsVisible(false);
		webBrowser.setMenuBarVisible(false);

		webBrowser.addWebBrowserListener(new WebBrowserListener() {

			@Override
			public void windowWillOpen(WebBrowserWindowWillOpenEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpening(WebBrowserWindowOpeningEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WebBrowserEvent arg0) {
				FlashPlayer.this.setVisible(false);
			}

			@Override
			public void titleChanged(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void statusChanged(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void locationChanging(WebBrowserNavigationEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void locationChanged(WebBrowserNavigationEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void locationChangeCanceled(WebBrowserNavigationEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void loadingProgressChanged(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void commandReceived(WebBrowserCommandEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		return webBrowser;
	}
}


