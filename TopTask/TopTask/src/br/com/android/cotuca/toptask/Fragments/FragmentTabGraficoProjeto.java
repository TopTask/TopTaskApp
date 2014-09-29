package br.com.android.cotuca.toptask.Fragments;

import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.Graphs.GraficoPizzaTarefasView;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentTabGraficoProjeto extends Fragment {
	  
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_grafico_projeto, container, false);

		ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.view_graph_projeto); 
		
		GraficoPizzaTarefasView g = new GraficoPizzaTarefasView(getActivity().getApplicationContext(), 1,5,3);
		
		viewGroup.addView(g);

		return view;

	}
}
