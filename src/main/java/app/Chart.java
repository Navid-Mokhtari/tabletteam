package app;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import vitalsignals.Pulse;
import databaseaccess.DBConnection;

public class Chart extends JFrame {
	/**
	 * Creates a new demo.
	 * 
	 * @param title
	 *            the frame title.
	 */
	public Chart(final String title) {

		super(title);
		final XYDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1366, 728));
		setContentPane(chartPanel);

	}

	/**
	 * Creates a sample dataset.
	 * 
	 * @return a sample dataset.
	 */
	private XYDataset createDataset() {
		DBConnection connection = new DBConnection();
		List<Pulse> pulses = connection.getAllMeasurements();
		final TimeSeries series1 = new TimeSeries("Pulse");
		final TimeSeries series2 = new TimeSeries("Oxygen");
		for (Pulse p : pulses) {
			Integer pulseValue = Integer.valueOf(p.getPulse());
			Integer oxigenValue = Integer.valueOf(p.getOxigen());
			series1.add(new Day(p.getDate()), pulseValue);
			series2.add(new Day(p.getDate()), oxigenValue);		
		}
		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		return dataset;

	}

	/**
	 * Creates a chart.
	 * 
	 * @param dataset
	 *            the data for the chart.
	 * 
	 * @return a chart.
	 */
	private JFreeChart createChart(final XYDataset dataset) {

		// create the chart...
		final JFreeChart chart = ChartFactory.createTimeSeriesChart(
				"Measurements", // chart title
				"Day", // x axis label
				"Value", // y axis label
				dataset, // data
				true, // include legend
				true, // tooltips
				false // urls
				);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.setBackgroundPaint(Color.white);

		// final StandardLegend legend = (StandardLegend) chart.getLegend();
		// legend.setDisplaySeriesShapes(true);

		// get a reference to the plot for further customisation...
		final XYPlot plot = chart.getXYPlot();
		DateAxis dateaxis = (DateAxis) plot.getDomainAxis();
		dateaxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 3,
	            new SimpleDateFormat("dd-MMM")));
		plot.setBackgroundPaint(Color.lightGray);
		// plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//		renderer.setSeriesLinesVisible(1, false);
//		renderer.setSeriesShapesVisible(2, false);
		plot.setRenderer(renderer);

		// change the auto tick unit selection to integer units only...
//		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
//		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// OPTIONAL CUSTOMISATION COMPLETED.

		return chart;

	}
}
