package br.com.android.cotuca.toptask.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.DAO.TarefaDAO;
import br.com.android.cotuca.toptask.Graphs.GraficoPizzaTarefasView;
import br.com.android.cotuca.toptask.tags.Tags;

public class FragmentTabGraficoTarefa extends Fragment {
	
	private TarefaDAO dao;
	private Bundle dadosRecebidos;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_grafico_tarefa, container, false);

		ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.view_graph_tarefa); 
		dadosRecebidos = getActivity().getIntent().getExtras(); //idUsu e idProj
		
		dao = TarefaDAO.getInstance(getActivity().getApplicationContext());
		
		int idProjeto = dadosRecebidos.getInt(Tags.ID_PROJETO);
		int idUsuario = dadosRecebidos.getInt(Tags.ID_USUARIO);
		
		int concluidas = dao.getConcluidasDoMembroNoProjeto(idProjeto, idUsuario).size();
		int fazendo = dao.getFazendoDoMembroNoProjeto(idProjeto, idUsuario).size();
		int pendentes = dao.getNaoConcluidasDoMembroNoProjeto(idProjeto, idUsuario).size();
		
		if(dao.getConcluidasDoMembroNoProjeto(idProjeto, idUsuario).isEmpty())
			concluidas = 0;
		if(dao.getFazendoDoMembroNoProjeto(idProjeto, idUsuario).isEmpty())
			fazendo = 0;
		if(dao.getNaoConcluidasDoMembroNoProjeto(idProjeto, idUsuario).isEmpty())
			pendentes = 0;
		
<<<<<<< HEAD
		

=======
>>>>>>> 4b13e25f649a09878972622d750e735e2b73b84d
		Log.d("Qntidade de tarefas TAREFA", "Tarefas concluidas: "+concluidas);
		
		Log.d("Qntidade de tarefas TAREFA", "Tarefas andamento: "+fazendo);
		
		Log.d("Qntidade de tarefas TAREFA", "Tarefas pendentes: "+pendentes);
		
		GraficoPizzaTarefasView g = new GraficoPizzaTarefasView(getActivity().getApplicationContext(),concluidas,fazendo,pendentes);
		
		//GraficoPizzaTarefasView g = new GraficoPizzaTarefasView(getActivity().getApplicationContext(), 1,2,3);
<<<<<<< HEAD

=======
>>>>>>> 4b13e25f649a09878972622d750e735e2b73b84d
		viewGroup.addView(g);

		return view;

	}
}