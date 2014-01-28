package app;

import java.awt.Frame;
import java.awt.Window;
import java.io.InputStream;

import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class Utilities {
	private static StreamConnectionNotifier service;
	static StreamConnection con;
	static InputStream is;

	public static void disposeDialog(JComponent component) {
		Frame[] frames = JOptionPane.getRootFrame().getFrames();
		for (Frame frame : frames) {
			System.out.println(frame.getName());
			Window[] windows = frame.getWindows();
			for (Window window : windows) {
				Window[] win = window.getOwnedWindows();
				for (Window w : win) {
					if (w != null) {
						System.out.println(w.getName());
						if (w.getName().contains("dialog")) {
							w.dispose();
						}
					}
				}
			}
		}
	}

	public static void setConnection(StreamConnectionNotifier service,
			StreamConnection con, InputStream is) {
		Utilities.service = service;
		Utilities.con = con;
		Utilities.is = is;
	}

	public static void closeConnection() {
		try {
			con.close();
			is.close();
			service.close();
		} catch (Exception e) {
			con = null;
			is = null;
			service = null;
			System.out.println("Input stream was unavalible");
		}
	}
}
