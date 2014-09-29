package br.com.android.cotuca.toptask.Graphs;

import org.afree.chart.AFreeChart;
import org.afree.chart.ChartColor;
import org.afree.chart.ChartFactory;
import org.afree.chart.plot.PiePlot;
import org.afree.data.general.DefaultPieDataset;
import org.afree.data.general.PieDataset;
import org.afree.graphics.SolidColor;

import android.content.Context;

public class GraficoPizzaTarefasView extends DemoView{
    static final String TC = "Tarefas concluidas";
    static final String TA = "Tarefas em andamento";
    static final String TP = "Tarefas pendentes";

    public GraficoPizzaTarefasView(Context context, int tarefasConcluidas, int tarefasAndamentos, int tarefasPendentes){
        super(context);
        
        final PieDataset dataset = createDataset(tarefasConcluidas, tarefasAndamentos, tarefasPendentes);
        final AFreeChart chart = createChart(dataset);
        
        setChart(chart);
    }
    
    private static PieDataset createDataset(int tarefasConcluidas, int tarefasAndamentos, int tarefasPendentes) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue(TC, tarefasConcluidas);
        dataset.setValue(TA, tarefasAndamentos);
        dataset.setValue(TP, tarefasPendentes);
        return dataset;
    }
    
    private static AFreeChart createChart(PieDataset dataset) {

        AFreeChart chart = ChartFactory.createPieChart(null, // chart title
                dataset, // data
                true, // legenda
                false,
                false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelShadowPaint(new SolidColor(ChartColor.BLACK));
        plot.setLabelGenerator(null);
        plot.setExplodePercent(TC,0.04);
        plot.setExplodePercent(TA,0.04);
        plot.setExplodePercent(TP,0.04);
        //plot.setLabelFont(new Font("SansSerif", Typeface.NORMAL, 15));
        plot.setNoDataMessage("Tarefas nao encontradas");
        plot.setCircular(true);
        //plot.setLabelGap(0.02);
        plot.setSectionPaintType(TC, new SolidColor(ChartColor.LIGHT_GREEN));
        plot.setSectionPaintType(TA, new SolidColor(ChartColor.LIGHT_YELLOW));
        plot.setSectionPaintType(TP, new SolidColor(ChartColor.LIGHT_RED));
        return chart;

    }

}
