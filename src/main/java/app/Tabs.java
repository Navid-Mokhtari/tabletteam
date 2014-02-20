package app;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JTabbedPane;

public class Tabs extends JTabbedPane {

	String currentLang = HealthProperties.getProperty("currentLanguage");
	Locale currentLocale = Locale.forLanguageTag(currentLang);
	ResourceBundle currentLanguage = ResourceBundle.getBundle("language",
			currentLocale);

	private static final long serialVersionUID = 2269612487959072782L;

	public Tabs() throws IOException {
		super(JTabbedPane.TOP);
		setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		
		QuestionnareTab questionnareTab = new QuestionnareTab();
		addTab(currentLanguage.getString("Questionnaire"),
				questionnareTab.getView());

		MeasurementTab measurementTab = new MeasurementTab();
		addTab(currentLanguage.getString("Measurements"),
				measurementTab.getView());
		
		CatQuestionnaire catQuestionnaire = new CatQuestionnaire();
		addTab(currentLanguage.getString("CatQuestionnaire"),
				catQuestionnaire.getView());
		
//		ChatPanel chatPanel = new ChatPanel();
//		addTab("Chat", chatPanel.getView());

	}

}
