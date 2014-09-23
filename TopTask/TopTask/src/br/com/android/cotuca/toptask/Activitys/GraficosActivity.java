package br.com.android.cotuca.toptask.Activitys;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.Fragments.FragmentTabGraficoProjeto;
import br.com.android.cotuca.toptask.Fragments.FragmentTabGraficoTarefa;

public class GraficosActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    ActionBar actionBar = getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	    Tab tab = actionBar.newTab().setText(R.string.tab_g_tarefa)
	                       .setTabListener(new TabListener<FragmentTabGraficoTarefa>(
	                               this, "Tarefa", FragmentTabGraficoTarefa.class));
	    actionBar.addTab(tab);

	    tab = actionBar.newTab().setText(R.string.tab_g_projeto)
	                   .setTabListener(new TabListener<FragmentTabGraficoProjeto>(
	                           this, "Projeto", FragmentTabGraficoProjeto.class));
	    actionBar.addTab(tab);
	}
}