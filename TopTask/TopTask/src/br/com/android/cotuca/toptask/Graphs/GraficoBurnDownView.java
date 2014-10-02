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
        XYDataset dataset1 = createDataset1();
        
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
        
        NumberAxis rangeAxis1 = new NumberAxis();
        rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis1.setAxisLinePaintType(gray);
        rangeAxis1.setAxisLineStroke(1);
        rangeAxis1.setTickMarkPaintType(gray);
        rangeAxis1.setTickMarkStroke(1);
        rangeAxis1.setTickMarkOutsideLength(2);
        rangeAxis1.setLabelPaintType(gray);
        rangeAxis1.setTickLabelPaintType(gray);
        rangeAxis1.setTickLabelInsets(new RectangleInsets(10, 0, 10, 0));
        rangeAxis1.setLimitAble(true);
        rangeAxis1.setLimitRange(0, 100);
        
        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        renderer2.setBaseShapesVisible(false);
        renderer2.setLegendLine(new LineShape());
        
        XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
        renderer1.setBaseShapesVisible(false);
        renderer1.setLegendLine(new LineShape());

        XYPlot subplot2 = new XYPlot(dataset2, null, rangeAxis2,renderer2);
        XYPlot subplot1 = new XYPlot(dataset1, null, rangeAxis1,renderer1);
        
        subplot2.setDomainGridlinesVisible(true);
        subplot2.setBackgroundPaintType(white);
        subplot2.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        subplot2.setOutlineVisible(true);
        subplot2.setOutlinePaintType(gray);
        subplot2.setOutlineStroke(2.0f);
        
        subplot1.setDomainGridlinesVisible(true);
        subplot1.setBackgroundPaintType(white);
        subplot1.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        subplot1.setOutlineVisible(true);
        subplot1.setOutlinePaintType(gray);
        subplot1.setOutlineStroke(2.0f);

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
        plot.add(subplot1,1);

        AFreeChart chart = new AFreeChart(null,
                AFreeChart.DEFAULT_TITLE_FONT,
                plot,
                false);

        // setting chart
        chart.setBackgroundPaintType(white);

        return chart;
    }

    //gráfico ideal
    public static XYDataset createDataset1() {

        TimeSeries s1 = new TimeSeries("");
        
        s1.add(new Day(24, 9, 2014), 10);
        s1.add(new Day(25, 9, 2014), 9);
        s1.add(new Day(26, 9, 2014), 8);
        s1.add(new Day(27, 9, 2014), 7);
        s1.add(new Day(28, 9, 2014), 6);
        s1.add(new Day(29, 9, 2014), 5);
        s1.add(new Day(30, 9, 2014), 4);
        s1.add(new Day(1, 10, 2014), 3);
        s1.add(new Day(2, 10, 2014), 2);
        s1.add(new Day(3, 10, 2014), 1);
        s1.add(new Day(4, 10, 2014), 0);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);

        return dataset;
    }
    
    //gráfico real
    public static XYDataset createDataset2() {

        TimeSeries s1 = new TimeSeries("");
        
        s1.add(new Day(24, 9, 2014), 10);
        s1.add(new Day(25, 9, 2014), 8);
        s1.add(new Day(26, 9, 2014), 6);
        s1.add(new Day(27, 9, 2014), 6);
        s1.add(new Day(28, 9, 2014), 5);
        s1.add(new Day(29, 9, 2014), 5.5);
        s1.add(new Day(30, 9, 2014), 5);
        s1.add(new Day(1, 10, 2014), 4.5);
        s1.add(new Day(2, 10, 2014), 4);
        s1.add(new Day(3, 10, 2014), 2.5);
        s1.add(new Day(4, 10, 2014), 0);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);

        return dataset;
    }
}