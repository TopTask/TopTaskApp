package br.com.android.cotuca.toptask.Graphs;

import java.util.ArrayList;
import java.util.List;

import org.afree.chart.AFreeChart;
import org.afree.chart.ChartFactory;
import org.afree.chart.axis.AxisLocation;
import org.afree.chart.axis.DateAxis;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.axis.ValueAxis;
import org.afree.chart.plot.CombinedDomainXYPlot;
import org.afree.chart.plot.CombinedRangeXYPlot;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.afree.data.time.Day;
import org.afree.data.time.TimeSeries;
import org.afree.data.time.TimeSeriesCollection;
import org.afree.data.xy.XYDataset;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;
import org.afree.graphics.geom.LineShape;
import org.afree.ui.RectangleInsets;

import br.com.android.cotuca.toptask.Beans.BurnDown;
import br.com.android.cotuca.toptask.DAO.BurnDownDAO;
import android.content.Context;
import android.graphics.Color;

public class GraficoBurnDownView extends DemoView {
	
	private int idProjeto;


	public GraficoBurnDownView(Context context, int idProjeto) {
		super(context);

		final AFreeChart chart = createChart(idProjeto);
		setChart(chart);
	}

	public static XYDataset createGraficoIdeal(int idProjeto) {

		TimeSeries s1 = new TimeSeries("");
		BurnDownDAO daoBurnDown;
		daoBurnDown = new BurnDownDAO(null);

		List<BurnDown> burnDowns = new ArrayList<BurnDown>();

		burnDowns = daoBurnDown.getBurnDownsDoProjeto(idProjeto);

			int primeiroDia = burnDowns.get(0).getDiaAtual();
			int primeiroMes = burnDowns.get(0).getMesAtual();
			int primeiroAno = burnDowns.get(0).getAnoAtual();

			int ultimoDia = burnDowns.get(burnDowns.size() - 1).getDiaAtual();
			int ultimoMes = burnDowns.get(burnDowns.size() - 1).getMesAtual();
			int ultimoAno = burnDowns.get(burnDowns.size() - 1).getAnoAtual();

			s1.add(new Day(primeiroDia, primeiroMes, primeiroAno), 10);
			s1.add(new Day(ultimoDia, ultimoMes, ultimoAno), 0);

			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(s1);

			return dataset;
	}

	public static XYDataset createGraficoReal(int idProjeto) {

		TimeSeries s1 = new TimeSeries("");
		BurnDownDAO daoBurnDown;
		daoBurnDown = new BurnDownDAO(null);

		List<BurnDown> burnDowns = new ArrayList<BurnDown>();

		burnDowns = daoBurnDown.getBurnDownsDoProjeto(idProjeto);

		for (int i = 0; i < burnDowns.size(); i++) {

			int feito = burnDowns.get(i).getFeito();
			int limite = burnDowns.get(i).getLimite();
			int porcentagem = 10*feito / limite;
			int real = 10 - porcentagem;

			int dia = burnDowns.get(i).getDiaAtual();
			int mes = burnDowns.get(i).getMesAtual();
			int ano = burnDowns.get(i).getAnoAtual();

			s1.add(new Day(dia, mes, ano), real);
		}

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(s1);

		return dataset;
	}

	private static AFreeChart createChart(int idProjeto) {

		// declaracao cores
		PaintType white = new SolidColor(Color.WHITE);
		PaintType gray = new SolidColor(Color.DKGRAY);

		XYDataset datasetReal = createGraficoReal(idProjeto);
		XYDataset datasetIdeal = createGraficoIdeal(idProjeto);

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

		XYPlot subplot2 = new XYPlot(datasetReal, null, rangeAxis2, renderer2);
		XYPlot subplot1 = new XYPlot(datasetIdeal, null, rangeAxis1, renderer1);

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
		plot.add(subplot2);
		plot.add(subplot1);
		

		AFreeChart chart = new AFreeChart(null, AFreeChart.DEFAULT_TITLE_FONT,
				plot, false);

		chart.setBackgroundPaintType(white);

		return chart;
	}
}