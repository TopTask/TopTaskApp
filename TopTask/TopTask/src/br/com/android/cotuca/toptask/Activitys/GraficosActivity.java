package br.com.android.cotuca.toptask.Activitys;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.BD.ContratoUsuarios;
import br.com.android.cotuca.toptask.Fragments.FragmentTabGraficoProjeto;
import br.com.android.cotuca.toptask.Fragments.FragmentTabGraficoTarefa;
import br.com.android.cotuca.toptask.tags.Tags;

public class GraficosActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    //Log.d(Tags.ID_USUARIO, getIntent().getExtras().getInt(ContratoUsuarios.Colunas._ID)+ " no graficosActivity");

	    ActionBar actionBar = getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	    Tab tab = actionBar.newTab().setText(R.string.tab_g_tarefa)
	                       .setTabListener(new TabListener<FragmentTabGraficoTarefa>(
	                               this, "Tarefas", FragmentTabGraficoTarefa.class));
	    actionBar.addTab(tab);

	    tab = actionBar.newTab().setText(R.string.tab_g_projeto)
	                   .setTabListener(new TabListener<FragmentTabGraficoProjeto>(
	                           this, "Andamento Projeto", FragmentTabGraficoProjeto.class));
	    actionBar.addTab(tab);
	}
}