package br.com.android.cotuca.toptask.Fragments;

import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.Adapter.AdapterTarefa;
import br.com.android.cotuca.toptask.DAO.TarefaDAO;
import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class FragmentQuadroTarefaFeito extends ListFragment{

	private Bundle dadosRecebidos;
	private AdapterTarefa adapter;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	
	@Override
	public void onCreate(Bundle dados) {
		super.onCreate(dados);
		this.dadosRecebidos = dados;
		
		Intent intentDadosRecebidos = getActivity().getIntent();
		Bundle dadosRecebidos = intentDadosRecebidos.getExtras();
		int idProjeto = dadosRecebidos.getInt("ID_PROJETO");
		
		TarefaDAO tarefas = TarefaDAO.getInstance(getActivity()); 
		
		adapter = new AdapterTarefa(getActivity(), tarefas.getConcluidasDoProjeto(idProjeto));
		setListAdapter(adapter);
	}
	
	// Método que coloca o menu lateral das tarefas.
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.tarefas, menu); // Menu lateral
		super.onCreateOptionsMenu(menu, inflater);
		
	}
}
