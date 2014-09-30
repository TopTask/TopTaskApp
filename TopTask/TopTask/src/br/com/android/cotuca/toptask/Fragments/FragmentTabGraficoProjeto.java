package br.com.android.cotuca.toptask.Fragments;

import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.DAO.TarefaDAO;
import br.com.android.cotuca.toptask.Graphs.GraficoPizzaTarefasView;
import br.com.android.cotuca.toptask.tags.Tags;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentTabGraficoProjeto extends Fragment {
	
	private TarefaDAO dao;
	private Bundle dadosRecebidos;
	  
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_grafico_projeto, container, false);

		ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.view_graph_projeto); 
		dadosRecebidos = getActivity().getIntent().getExtras(); //idUsu e idProj
		
		dao = TarefaDAO.getInstance(getActivity().getApplicationContext());
		
		int idProjeto = dadosRecebidos.getInt(Tags.ID_PROJETO);
		
		int concluidas = dao.getConcluidasDoProjeto(idProjeto).size();
		int fazendo = dao.getFazendoDoProjeto(idProjeto).size();
		int pendentes = dao.getNaoConcluidasDoProjeto(idProjeto).size();
		
		if(dao.getConcluidasDoProjeto(idProjeto).isEmpty())
			concluidas = 0;
		if(dao.getFazendoDoProjeto(idProjeto).isEmpty())
			fazendo = 0;
		if(dao.getNaoConcluidasDoProjeto(idProjeto).isEmpty())
			pendentes = 0;
		
		
		Log.d("Qntidade de tarefas PROJETO", "Tarefas concluidas: "+concluidas);
		
		Log.d("Qntidade de tarefas PROJETO", "Tarefas andamento: "+fazendo);
		
		Log.d("Qntidade de tarefas PROJETO", "Tarefas pendentes: "+pendentes);
		
		GraficoPizzaTarefasView g = new GraficoPizzaTarefasView(getActivity().getApplicationContext(),concluidas,fazendo,pendentes);
		
		//GraficoPizzaTarefasView g = new GraficoPizzaTarefasView(getActivity().getApplicationContext(), 1,2,3);

		viewGroup.addView(g);

		return view;

	}

}
