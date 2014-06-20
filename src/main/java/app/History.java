package app;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.server.UID;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.jfree.ui.RefineryUtilities;

import vitalsignals.Pulse;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;

import databaseaccess.DBConnection;

public class History extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3737876809601416261L;
	JCalendar calendar;
	private JLabel oxygenValueLabel;
	private JLabel timeValueLabel;
	private JLabel pulseValueLabel;
	private final Color STANDART_COLOR=Color.BLACK;
	private final Color dayWithValue = Color.MAGENTA;
	public History() {
		setBounds(0, 0, 1366, 728);
		setResizable(false);
		setUndecorated(true);
		initialize();

	}

	private void initialize() {
		JPanel calendarPanel = new JPanel();
		calendarPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		JPanel valuesPanel = new JPanel();
		final JCalendar calendar = new JCalendar(Utilities.getCurrentLanguage());
		calendar.getYearChooser().getSpinner()
				.setFont(new Font("Verdana", Font.PLAIN, 13));
		calendar.getMonthChooser().getComboBox()
				.setFont(new Font("Tahoma", Font.PLAIN, 11));
		calendar.getMonthChooser().setPreferredSize(new Dimension(98, 40));
		Component[] components = calendar.getDayChooser().getDayPanel().getComponents();
//		for (Component component : components) {
//			JButton button = (JButton) component;
//			button.setBorder(new LineBorder(STANDART_COLOR, 1));
//		}
		calendar.setTodayButtonVisible(false);
		calendar.addPropertyChangeListener("calendar",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						updateLabels(calendar.getDate());
						setColourForEachDayWithValueInMonth(calendar);
					}
				});
		updateLabels(calendar.getDate());
		calendarPanel.setLayout(new GridLayout(0, 1, 0, 0));
		setColourForEachDayWithValueInMonth(calendar);
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

		JButton btnNewButton_1 = new JButton("Chart");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Chart chart = new Chart("Measurement time series");
				if (chart.getIsAvailable()) {
					chart.pack();
					RefineryUtilities.centerFrameOnScreen(chart);
					chart.setVisible(true);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 50));
		valuesPanel.add(btnNewButton_1);
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

	/*
	 * TODO найти первый день каждого месяца. написать запрос к БД, возврающий
	 * список всех дней, за текущий месяц, в которых есть значения. После этого
	 * отмечать их другим цветом Начинать думать о том, что делать с графиком
	 */
	private void setColourForEachDayWithValueInMonth(JCalendar calendar) {
		JDayChooser dayChooser = calendar.getDayChooser();
		JPanel dayPanel = dayChooser.getDayPanel();
		JMonthChooser monthChooser = calendar.getMonthChooser();
		int month = calendar.getMonthChooser().getMonth();
		int year = calendar.getYearChooser().getYear();
		int day = 1;
		DBConnection connection = new DBConnection();
		List<Integer> daysWithMeasurements = connection
				.getDaysWithMeasurements(monthChooser.getMonth());
		if (!daysWithMeasurements.isEmpty()) {
			int startingPosition = 7;// Because with 7 (from 0 -6 ) in
										// Components are empty cells
			Calendar cal = Calendar.getInstance();
			cal.set(year, month, day);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			Date date = cal.getTime();
			int firstDayOfMonth = date.getDay() - 1;
			System.out.println(firstDayOfMonth);
			Component components[] = dayPanel.getComponents();
			for (Component c : components) {
				JButton button = (JButton) c;
				button.setBorder(new LineBorder(STANDART_COLOR, 1));
			}
			for (int d : daysWithMeasurements) {
				JButton button = (JButton) components[d + startingPosition
						+ firstDayOfMonth-1];
				button.setBorder(new LineBorder(dayWithValue, 3));
			}
		}
	}
}
