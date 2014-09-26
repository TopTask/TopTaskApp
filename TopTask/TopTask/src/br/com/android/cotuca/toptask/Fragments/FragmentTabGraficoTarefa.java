package br.com.android.cotuca.toptask.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.Graphs.GraficoPizzaTarefasView;

public class FragmentTabGraficoTarefa extends Fragment {
	
	//private TarefaDAO dao;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_grafico_tarefa, container, false);

		ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.view_graph_tarefa); 
		
		//dao = TarefaDAO.getInstance(getActivity().getApplicationContext());
		
		//Bundle dados = getActivity().getIntent().getExtras();
		//int idProjeto = dados.getInt(ContratoProjetos.Colunas._ID);
		//int idUsuario = dados.getInt(ContratoUsuarios.Colunas._ID);
		
		//GraficoPizzaTarefasView g = new GraficoPizzaTarefasView(getActivity().getApplicationContext(), dao.getConcluidasDoMembroNoProjeto(idProjeto, idUsuario).size(),dao.getFazendoDoMembroNoProjeto(idProjeto, idUsuario).size(),dao.getNaoConcluidasDoMembroNoProjeto(idProjeto, idUsuario).size());
		
//		GraficoPizzaTarefasView g = new GraficoPizzaTarefasView(getActivity().getApplicationContext(), dao.getConcluidasDoMembroNoProjeto(idProjeto, idUsuario).size(),dao.getFazendoDoMembroNoProjeto(idProjeto, idUsuario).size(),dao.getNaoConcluidasDoMembroNoProjeto(idProjeto, idUsuario).size());
		GraficoPizzaTarefasView g = new GraficoPizzaTarefasView(getActivity().getApplicationContext(), 1,1,1);

		viewGroup.addView(g);

		return view;

	}
}