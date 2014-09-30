package br.com.android.cotuca.toptask.Fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.Activitys.CadastroTarefa;
import br.com.android.cotuca.toptask.Adapter.AdapterTarefa;
import br.com.android.cotuca.toptask.BD.ContratoTarefas;
import br.com.android.cotuca.toptask.Beans.Tarefa;
import br.com.android.cotuca.toptask.DAO.TarefaDAO;
import br.com.android.cotuca.toptask.tags.Tags;

public class FragmentTarefas extends ListFragment implements OnMenuItemClickListener, OnItemLongClickListener{

	private AdapterTarefa adapter;
	private ListenerClickTarefa listener;
	
	private int idProjeto;
	
	@Override
	public void onAttach(Activity activity) { 
		super.onAttach(activity);
		
		if (!(activity instanceof ListenerClickTarefa)) {
			 throw new RuntimeException("A activity deve implementar a interface ListenerClickTarefa");
		}
		
		listener = (ListenerClickTarefa) activity;
	}
	
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		
		inflater.inflate(R.menu.tarefas, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intentDadosRecebidos = getActivity().getIntent();
		Bundle dadosRecebidos = intentDadosRecebidos.getExtras();
		idProjeto = dadosRecebidos.getInt(Tags.ID_PROJETO);
		
		TarefaDAO tarefas = TarefaDAO.getInstance(getActivity()); 
		
		adapter = new AdapterTarefa(getActivity(), tarefas.getNaoConcluidasDoProjeto(idProjeto));
		setListAdapter(adapter);
		
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Tarefa tarefa = adapter.getItem(position);
		
		Log.d(Tags.TOPTASK_FRAGMENT, "ID tarefa:" + tarefa.getID());
		
		listener.onTaskLongClick(tarefa);
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,int position, long id) {

		Tarefa tarefa = adapter.getItem(position);
		
		listener.onTaskClick(tarefa);
		

		return true;
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		Toast.makeText(getActivity(), "Entrou no click", Toast.LENGTH_SHORT).show();
		int id = item.getItemId();
		
		
		if (id == R.id.action_add_nova_tarefa) {
			
			Intent i = new Intent(getActivity(),CadastroTarefa.class);
			
			Bundle dados = new Bundle();
			dados.putInt(ContratoTarefas.Colunas.PROJETO, idProjeto);
			dados.putInt("ACAO",0);
			
			i.putExtras(dados);
			startActivity(i);
		}
		
		return false;
	}
	
	
	public interface ListenerClickTarefa {
		public void onTaskClick(Tarefa tarefa);
		public void onTaskLongClick(Tarefa tarefa);
	}



}
