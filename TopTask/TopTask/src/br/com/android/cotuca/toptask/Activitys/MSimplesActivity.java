/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.android.cotuca.toptask.Activitys;


/**
 * @author jvgengo
 */
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.BD.ContratoProjetos;
import br.com.android.cotuca.toptask.BD.ContratoTarefas;
//import android.widget.Toast;
import br.com.android.cotuca.toptask.Beans.Tarefa;
import br.com.android.cotuca.toptask.DAO.TarefaDAO;
import br.com.android.cotuca.toptask.Fragments.FragmentMembros;
import br.com.android.cotuca.toptask.Fragments.FragmentTarefas;
import br.com.android.cotuca.toptask.tags.Tags;

public class MSimplesActivity extends Activity implements
		FragmentTarefas.ListenerClickTarefa, Callback {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mPaginaTitulo;

	private boolean actionModeAtivado = false;
	private Tarefa tarefaSelecionada; // tarefa que sera setada quando alguma tarefa for clicada no fragment de tarefas
	
	private int idProjetoSelecionado = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		mTitle = mDrawerTitle = getTitle();
		mPaginaTitulo = getResources().getStringArray(R.array.menu_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// Sombra ao abrir o Drawer Menu
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mPaginaTitulo));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// Permitir ícone do aplicativo ActionBar a comportar-se como uma
		// ação para alternar navDrawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle une as as interações adequadas
		// Entre o menu e o ícone do aplicativo barra de ação
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // Cria chamada para
										// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // Cria chamada para
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		} else {
			
			idProjetoSelecionado = savedInstanceState.getInt(ContratoProjetos.NOME_TABELA + "+" + ContratoProjetos.Colunas._ID);
			Log.i(Tags.TOPTASK_ACTIVITY, "ID do projeto selecionado: "+ idProjetoSelecionado); 
			
			selectItem(0);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/* Chamado quando chamado invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// se o Drawer menu esta aberto ocultar os icones da actionBar

		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_add).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// A actionBar do menu deve abrir e fechar.
		// ActionBarDrawerToggle vai fazer isso.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		// acao dos icones do actionbar
		switch (item.getItemId()) {
		case R.id.action_add:
			Intent i = new Intent(this, CadastroTarefa.class);
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private void selectItem(int posicao) {

		// ======================================
		// Criacao dos Fragments de cadas item
		// ======================================
		FragmentManager fm = getFragmentManager();
		
		if (posicao == 0) {
			Fragment f_tarefas = new FragmentTarefas();
			
			Bundle dadosProjeto = new Bundle();
			dadosProjeto.putInt("ID_PROJETO", idProjetoSelecionado);
			f_tarefas.setArguments(dadosProjeto);
			Log.i(Tags.TOPTASK_ACTIVITY, "id sendo enviado: "+ idProjetoSelecionado);
			
			fm.beginTransaction().replace(R.id.content_frame, f_tarefas).commit();
		} else if (posicao == 5) {
			Fragment f_membros = new FragmentMembros();
			fm.beginTransaction().replace(R.id.content_frame,f_membros).commit();
		}
		mDrawerList.setItemChecked(posicao, true);
		setTitle(mPaginaTitulo[posicao]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * Ao usar o ActionBarDrawerToggle, você deve chamá-lo durante
	 * OnPostCreate () e onConfigurationChanged () ...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		int id = item.getItemId();
		TarefaDAO tarefas = TarefaDAO.getInstance(this);
		
		if (id == R.id.acction_concluir_tarefa) {
			tarefas.concluirTarefa(tarefaSelecionada);
			tarefaSelecionada = null;
			this.selectItem(0);
			mode.finish();
			return true;
			
		} else if (id == R.id.acction_editar_tarefa) {
			Intent iEditar = new Intent(this, CadastroTarefa.class);
			Bundle dados = new Bundle();
			dados.putString(ContratoTarefas.Colunas.NOME,tarefaSelecionada.getNome());
			dados.putString(ContratoTarefas.Colunas.DESCRICAO,tarefaSelecionada.getDescricao());
			dados.putString(ContratoTarefas.Colunas.DATA_ENTREGA,tarefaSelecionada.getDataEntrega());
			dados.putInt(ContratoTarefas.Colunas._ID,tarefaSelecionada.getID());
			
			iEditar.putExtras(dados);
			
			startActivity(iEditar);
			tarefaSelecionada = null;
			this.selectItem(0);
			mode.finish();
			return true;
			
		} else if (id == R.id.acction_excluir_tarefa) {

			Log.d(Tags.TOPTASK_ACTIVITY, "ID tarefa:" + tarefaSelecionada.getID());
			
			tarefas.delete(tarefaSelecionada);
			tarefaSelecionada = null;
			this.selectItem(0);
			
			mode.finish();
			return true;
		}

		return false;
	}

	@Override
	public boolean onCreateActionMode(ActionMode mode, Menu menu) {

		if (!actionModeAtivado) {
			mode.getMenuInflater().inflate(R.menu.actionmode_tarefa, menu);
		}
		return true;
	}

	@Override
	public void onDestroyActionMode(ActionMode mode) {
		actionModeAtivado = false;
	}

	@Override
	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		return false;
	}

	@Override
	public void onTaskClick(Tarefa tarefa) {
		Intent i = new Intent(getApplicationContext(), CadastroTarefa.class);
		startActivity(i);
	}

	@Override
	public void onTaskLongClick(Tarefa tarefa) {
		
		tarefaSelecionada = tarefa;
		
		Log.d(Tags.TOPTASK_ACTIVITY, "ID tarefa:" + tarefaSelecionada.getID());
		
		startActionMode(this);
		actionModeAtivado = true;
	}


}