package br.com.android.cotuca.toptask.Activitys;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.BD.ContratoUsuarios;
import br.com.android.cotuca.toptask.Fragments.FragmentTabGraficoBurnDown;
import br.com.android.cotuca.toptask.Fragments.FragmentTabGraficoProjeto;
import br.com.android.cotuca.toptask.Fragments.FragmentTabGraficoTarefa;
import br.com.android.cotuca.toptask.tags.Tags;

public class GraficosActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
		getActionBar().setDisplayHomeAsUpEnabled(true);
	    
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
	    
	    tab = actionBar.newTab().setText(R.string.tab_g_burn_down)
                .setTabListener(new TabListener<FragmentTabGraficoBurnDown>(
                        this, "Burn Down", FragmentTabGraficoBurnDown.class));
	    actionBar.addTab(tab);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 if (item.getItemId() == android.R.id.home) {
				Intent i = new Intent(getApplicationContext(),
						MSimplesActivity.class);
				Bundle dadosBundle = new Bundle();
//				dadosBundle.putInt(Tags.ID_PROJETO, idProjeto);
//				dadosBundle.putInt(Tags.ID_USUARIO, idDono);
				i.putExtras(dadosBundle);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(i);

				return true;

			}

			return super.onOptionsItemSelected(item);
	}
}