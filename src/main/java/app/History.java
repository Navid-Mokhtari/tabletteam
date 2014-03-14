package app;

import java.awt.Dimension;
import java.util.Locale;

import javax.swing.JFrame;

import com.toedter.calendar.JCalendar;

public class History extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3737876809601416261L;
	JCalendar calendar;

	public History() {
		initialize();

	}

	private void initialize() {
		setBounds(0, 0, 1366/2, 768/2);
		String currentLang = HealthProperties.getProperty("currentLanguage");
		Locale currentLocale = Locale.forLanguageTag(currentLang);
		calendar = new JCalendar(currentLocale);
		this.add(calendar);
	}

}
