import javax.swing.JTabbedPane;

public class Tabs extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2269612487959072782L;

	public Tabs() {
		QuestionnareTab questionnareTab = new QuestionnareTab();
		addTab("Questionnare", questionnareTab.getView());

		MeasurementTab measurementTab = new MeasurementTab();
		addTab("Measurements", measurementTab.getView());

	}

}
