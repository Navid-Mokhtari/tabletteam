package app;
import javax.swing.JTabbedPane;

public class Tabs extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2269612487959072782L;
	public Tabs() {
		super(JTabbedPane.TOP);
		setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		QuestionnareTab questionnareTab = new QuestionnareTab();
		addTab("Questionnare", questionnareTab.getView());

		MeasurementTab measurementTab = new MeasurementTab();
		addTab("Measurements", measurementTab.getView());
		
		ChatPanel chatPanel = new ChatPanel();
		addTab("Chat", chatPanel.getView());
				

	}

}
