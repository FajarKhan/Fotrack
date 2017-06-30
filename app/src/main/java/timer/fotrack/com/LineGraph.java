package timer.fotrack.com;

import android.content.Context;
import android.graphics.Color;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import static android.R.attr.value;
import static timer.fotrack.com.R.id.view2;

/**
 * Created by fajar on 03-Nov-16.
 */

public class LineGraph {
    private GraphicalView view;

    private TimeSeries dataset = new TimeSeries("Work Time");
    private TimeSeries dataset2 = new TimeSeries("Break Time");
    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();

    private XYSeriesRenderer renderer = new XYSeriesRenderer(); // This will be used to customize line 1
    private XYSeriesRenderer renderer2 = new XYSeriesRenderer();
    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); // Holds a collection of XYSeriesRenderer and customizes the graph

    public LineGraph()
    {
        // Add single dataset to multiple dataset
        mDataset.addSeries(dataset);

        // Customization time for line 1!
        renderer.setColor(Color.WHITE);
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setFillPoints(true);
        renderer.setLineWidth(1f);
        mDataset.addSeries(dataset2);

        renderer2.setColor(Color.parseColor("#32FFFFFF"));
        renderer2.setPointStyle(PointStyle.CIRCLE);
        renderer2.setFillPoints(true);
        renderer2.setLineWidth(3f);



        // Enable Zoom
        mRenderer.setZoomButtonsVisible(false);
        mRenderer.setXTitle("Days");
        mRenderer.setAxisTitleTextSize(40);
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.BLACK);
        mRenderer.setMarginsColor(Color.BLACK);
        mRenderer.setPointSize(10f);
        mRenderer.setShowLegend(true);
        mRenderer.setMargins(new int[] {100, 100, 100, 100});
        mRenderer.setLabelsTextSize(40);
        mRenderer.setLegendHeight(1);
        mRenderer.setLegendTextSize(40);
        mRenderer.setYTitle("Work Time(Min)");



        // Add single renderer to multiple renderer
        mRenderer.addSeriesRenderer(renderer);
        mRenderer.addSeriesRenderer(renderer2);
    }

    public GraphicalView getView(Context context)
    {
        view =  ChartFactory.getLineChartView(context, mDataset, mRenderer);
        return view;
    }

    public void addNewPoints(Point p,point2 p1)
    {
        dataset.add(p.getX(), p.getY());
        dataset2.add(p1.getX(), p1.getY());
    }
}
