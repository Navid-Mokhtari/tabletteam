package app;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import junit.awtui.ProgressBar;
import sun.awt.image.OffScreenImage;
import bluetooth.PulseConnectionOld;
import bluetooth.SpirometryConnection;

public class ProgressMonitor extends JPanel implements ActionListener,
		PropertyChangeListener {
	private JProgressBar progressBar;
	private JComponent component;
	private Task task;
	private Object currentConnection;

	class Task extends SwingWorker<Void, Void> {
		@Override
		public Void doInBackground() {
			int progress = 0;
			setProgress(0);
			if (currentConnection instanceof SpirometryConnection) {
				((SpirometryConnection) currentConnection).measure();
			}
			if (currentConnection instanceof PulseConnectionOld) {

			}
			return null;
		}

		@Override
		public void done() {
			Toolkit.getDefaultToolkit().beep();
			// startButton.setEnabled(true);
			setCursor(null);
		}
	}

	public ProgressMonitor(JComponent component, Object connection) {
		this.component = component;
		if (connection instanceof SpirometryConnection) {
			currentConnection = (SpirometryConnection) connection;
		}
		if (connection instanceof PulseConnectionOld) {
			currentConnection = (PulseConnectionOld) connection;
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// Instances of javax.swing.SwingWorker are not reusuable, so
		// we create new instances as needed.
		task = new Task();
		task.addPropertyChangeListener(this);
		task.execute();

	}

}
