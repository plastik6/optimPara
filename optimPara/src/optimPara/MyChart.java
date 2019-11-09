package optimPara;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MyChart extends JFrame {
	private static final long serialVersionUID = 6294689542092367723L;

	public MyChart(ArrayList<Point> trace, int[] faiIndexes) {
		super("Trace GPS");

		// Create dataset
		XYDataset dataset = createDataset(trace, faiIndexes[0], faiIndexes[1], faiIndexes[2]);

		// Create chart
		JFreeChart chart = ChartFactory.createXYLineChart("Trace GPS", "Longitude", "Latitude", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		// Create an NumberAxis
		NumberAxis xAxis = new NumberAxis();
		xAxis.setTickUnit(new NumberTickUnit(2));
		xAxis.setAutoRangeIncludesZero(false);

		// Assign it to the chart
		XYPlot plot = (XYPlot) chart.getPlot();
//		plot.setDomainAxis(xAxis);

		NumberAxis latAxis = (NumberAxis) plot.getRangeAxis();
		latAxis.setAutoRangeIncludesZero(false);

		// Create Panel
		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}

	private XYDataset createDataset(ArrayList<Point> pointList, int p1Index, int p2Index, int p3Index) {
		XYSeriesCollection dataset = new XYSeriesCollection();

		// trace GPS
		XYSeries serieGPS = new XYSeries("trace GPS", false, true);

		for (Point point : pointList) {
			serieGPS.add(point.getLongitude(), point.getLatitude());

		}

		// triangle FAI
		XYSeries serieFAI = new XYSeries("triangle FAI", false, true);

		serieFAI.add(pointList.get(p1Index).getLongitude(), pointList.get(p1Index).getLatitude());
		serieFAI.add(pointList.get(p2Index).getLongitude(), pointList.get(p2Index).getLatitude());
		serieFAI.add(pointList.get(p3Index).getLongitude(), pointList.get(p3Index).getLatitude());
		serieFAI.add(pointList.get(p1Index).getLongitude(), pointList.get(p1Index).getLatitude());

		// Add series to dataset
		dataset.addSeries(serieGPS);
		dataset.addSeries(serieFAI);

		return dataset;
	}

}
