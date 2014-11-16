package br.com.android.cotuca.toptask.Fragments;

import org.afree.ui.Drawable;

import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.DAO.TarefaDAO;
import br.com.android.cotuca.toptask.Graphs.GraficoBurnDownView;
import br.com.android.cotuca.toptask.Graphs.GraficoPizzaTarefasView;
import br.com.android.cotuca.toptask.tags.Tags;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentTabGraficoBurnDown extends Fragment {
	
	private Bundle dadosRecebidos;
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_grafico_tarefa,
				container, false);


		ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.view_graph_burndown);
		
		dadosRecebidos = getActivity().getIntent().getExtras(); //idUsu e idProj
		
		int idProjeto = dadosRecebidos.getInt(Tags.ID_PROJETO);
		
		GraficoBurnDownView g = new GraficoBurnDownView(getActivity().getApplicationContext(), idProjeto);  
		
		viewGroup.addView(g);

		return view;
	}

}
