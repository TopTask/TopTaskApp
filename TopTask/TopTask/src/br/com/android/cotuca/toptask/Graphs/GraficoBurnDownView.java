package br.com.android.cotuca.toptask.Graphs;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import org.afree.chart.AFreeChart;
import org.afree.chart.annotations.XYTitleAnnotation;
import org.afree.chart.axis.AxisLocation;
import org.afree.chart.axis.DateAxis;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.axis.NumberTickUnit;
import org.afree.chart.axis.TickUnitSource;
import org.afree.chart.axis.TickUnits;
import org.afree.chart.axis.ValueAxis;
import org.afree.chart.plot.CombinedDomainXYPlot;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.xy.CandlestickRenderer;
import org.afree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.afree.chart.title.LegendTitle;
import org.afree.data.time.Day;
import org.afree.data.time.TimeSeries;
import org.afree.data.time.TimeSeriesCollection;
import org.afree.data.xy.DefaultHighLowDataset;
import org.afree.data.xy.OHLCDataset;
import org.afree.data.xy.XYDataset;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.LineShape;
import org.afree.ui.RectangleAnchor;
import org.afree.ui.RectangleEdge;
import org.afree.ui.RectangleInsets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

public class GraficoBurnDownView extends DemoView {

    public GraficoBurnDownView(Context context) {
        super(context);

        final AFreeChart chart = createChart();
        setChart(chart);
    }

    private static AFreeChart createChart() {

        // declare colors
        PaintType black = new SolidColor(Color.BLACK);
        PaintType blue = new SolidColor(Color.BLUE);
        PaintType red = new SolidColor(Color.RED); 
        PaintType white = new SolidColor(Color.WHITE);
        PaintType gray = new SolidColor(Color.DKGRAY);

        XYDataset dataset2 = createDataset2();
        NumberAxis rangeAxis2 = new NumberAxis();
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis2.setAxisLinePaintType(gray);
        rangeAxis2.setAxisLineStroke(1);
        rangeAxis2.setTickMarkPaintType(gray);
        rangeAxis2.setTickMarkStroke(1);
        rangeAxis2.setTickMarkOutsideLength(2);
        rangeAxis2.setLabelPaintType(gray);
        rangeAxis2.setTickLabelPaintType(gray);
        rangeAxis2.setTickLabelInsets(new RectangleInsets(10, 0, 10, 0));
        rangeAxis2.setLimitAble(true);
        rangeAxis2.setLimitRange(0, 100);
        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        renderer2.setBaseShapesVisible(false);
        renderer2.setLegendLine(new LineShape());

        XYPlot subplot2 = new XYPlot(dataset2, null, rangeAxis2,renderer2);
        subplot2.setDomainGridlinesVisible(true);
        subplot2.setBackgroundPaintType(white);
        subplot2.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        subplot2.setOutlineVisible(true);
        subplot2.setOutlinePaintType(gray);
        subplot2.setOutlineStroke(2.0f);

        // setting domain axis
        ValueAxis timeAxis = new DateAxis("");
        timeAxis.setAxisLinePaintType(gray);
        timeAxis.setAxisLineStroke(1);

        timeAxis.setTickMarkPaintType(gray);
        timeAxis.setTickMarkStroke(1);
        timeAxis.setTickMarkOutsideLength(2);

        timeAxis.setLabelPaintType(gray);
        timeAxis.setTickLabelPaintType(gray);

        CombinedDomainXYPlot plot = new CombinedDomainXYPlot(timeAxis);
        plot.setBackgroundPaintType(white);
        plot.add(subplot2, 1);

        AFreeChart chart = new AFreeChart(null,
                AFreeChart.DEFAULT_TITLE_FONT,
                plot,
                false);

        // setting chart
        chart.setBackgroundPaintType(white);

        return chart;
    }

    /**
     * Creates a dataset.
     * @return A dataset.
     */
    public static XYDataset createDataset2() {

        TimeSeries s1 = new TimeSeries("");
        
        s1.add(new Day(20, 9, 2014), 10);
        s1.add(new Day(22, 9, 2014), 8);
        s1.add(new Day(23, 9, 2014), 6);
        s1.add(new Day(24, 9, 2014), 4);
        s1.add(new Day(26, 9, 2014), 6);
        s1.add(new Day(28, 9, 2014), 8);
        s1.add(new Day(29, 9, 2014), 8);
        s1.add(new Day(30, 9, 2014), 7);
        s1.add(new Day(1, 10, 2014), 4);
        s1.add(new Day(2, 10, 2014), 3);
        s1.add(new Day(3, 10, 2014), 6);
        s1.add(new Day(4, 10, 2014), 7);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);

        return dataset;
    }
}