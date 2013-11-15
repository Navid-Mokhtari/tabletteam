package app;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JTabbedPane;

public class Tabs extends JTabbedPane {

	/**
	 * 
	 */
	Locale currentLocale = Locale.forLanguageTag("no");
	ResourceBundle currentLanguage = ResourceBundle.getBundle("language",
			currentLocale);

	private static final long serialVersionUID = 2269612487959072782L;

	public Tabs() throws IOException {
		super(JTabbedPane.TOP);
		setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		QuestionnareTab questionnareTab = new QuestionnareTab();
		addTab("Questionnare", questionnareTab.getView());

		MeasurementTab measurementTab = new MeasurementTab();
		 addTab(currentLanguage.getString("Measurements"),
		 measurementTab.getView());

	}

}
