package app;

import javax.swing.JFrame;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;

import databaseaccess.DBConnection;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingUtilities;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import javax.swing.JLabel;

import vitalsignals.Pulse;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.awt.GridLayout;

import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class History extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3737876809601416261L;
	JCalendar calendar;
	private JLabel oxygenValueLabel;
	private JLabel timeValueLabel;
	private JLabel pulseValueLabel;

	public History() {
		setBounds(0, 0, 1366, 768);
		setResizable(false);
		initialize();

	}

	private void initialize() {
		JPanel calendarPanel = new JPanel();
		calendarPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		JPanel valuesPanel = new JPanel();
		final JCalendar calendar = new JCalendar(Utilities.getCurrentLanguage());
		calendar.getYearChooser().getSpinner().setFont(new Font("Verdana", Font.PLAIN, 13));
		calendar.getMonthChooser().getComboBox().setFont(new Font("Tahoma", Font.PLAIN, 11));
		calendar.getMonthChooser().setPreferredSize(new Dimension(98, 40));
		calendar.setTodayButtonVisible(false);
		calendar.addPropertyChangeListener("calendar",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						updateLabels(calendar.getDate());

					}
				});
		setColourForEachDayWithValueInMonth(calendar);
		updateLabels(calendar.getDate());
		calendarPanel.setLayout(new GridLayout(0, 1, 0, 0));
		calendarPanel.add(calendar);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addComponent(calendarPanel, GroupLayout.DEFAULT_SIZE,
								674, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(valuesPanel, GroupLayout.PREFERRED_SIZE,
								679, GroupLayout.PREFERRED_SIZE).addGap(1)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addComponent(calendarPanel,
												GroupLayout.PREFERRED_SIZE,
												724, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(46, Short.MAX_VALUE))
						.addGroup(
								Alignment.TRAILING,
								groupLayout
										.createSequentialGroup()
										.addContainerGap(280, Short.MAX_VALUE)
										.addComponent(valuesPanel,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(280)));
		valuesPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel pulseLabel = new JLabel("Pulse:");
		pulseLabel.setFont(new Font("Verdana", Font.PLAIN, 50));
		pulseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		valuesPanel.add(pulseLabel);

		pulseValueLabel = new JLabel();
		pulseValueLabel.setFont(new Font("Verdana", Font.PLAIN, 50));
		pulseValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		valuesPanel.add(pulseValueLabel);

		JLabel oxygenLabel = new JLabel("Spo2:");
		oxygenLabel.setFont(new Font("Verdana", Font.PLAIN, 50));
		oxygenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		valuesPanel.add(oxygenLabel);

		oxygenValueLabel = new JLabel();
		oxygenValueLabel.setFont(new Font("Verdana", Font.PLAIN, 50));
		oxygenValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		valuesPanel.add(oxygenValueLabel);

		JLabel timeLabel = new JLabel("Time:");
		timeLabel.setFont(new Font("Verdana", Font.PLAIN, 50));
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		valuesPanel.add(timeLabel);

		timeValueLabel = new JLabel();
		timeValueLabel.setFont(new Font("Verdana", Font.PLAIN, 50));
		timeValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		valuesPanel.add(timeValueLabel);

		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 50));
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		valuesPanel.add(btnNewButton);
		getContentPane().setLayout(groupLayout);
	}

	private void updateLabels(Date date) {
		DBConnection connection = new DBConnection();
		final Pulse pulse = connection.getMeasurementsForDay(date);
		new Thread() {
			@Override
			public void run() {
				// callWebService();
				// do stuff
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						if (pulse != null) {
							if (pulse.getPulse() != null) {
								pulseValueLabel.setText(pulse.getPulse());
								oxygenValueLabel.setText(pulse.getOxigen());
								Date date = pulse.getDate();
								@SuppressWarnings("deprecation")
								int hours = date.getHours();
								@SuppressWarnings("deprecation")
								int minutes = date.getMinutes();
								timeValueLabel.setText(hours + ":" + minutes);
							}
						} else {
							pulseValueLabel.setText("");
							oxygenValueLabel.setText("");
							timeValueLabel.setText("");
						}
					}
				});
			}
		}.start();

	}

	private void setColourForEachDayWithValueInMonth(JCalendar calendar) {
		Color dayWithValue = Color.BLACK;
		JDayChooser dayChooser = calendar.getDayChooser();
		JPanel dayPanel = dayChooser.getDayPanel();
		Component components[] = dayPanel.getComponents();
		JButton button = (JButton) components[29];
		button.setBorder(new LineBorder(dayWithValue, 3));
		button = (JButton) components[32];
		button.setBorder(new LineBorder(dayWithValue, 3));
		button = (JButton) components[30];
		button.setBorder(new LineBorder(dayWithValue, 3));
	}
}
