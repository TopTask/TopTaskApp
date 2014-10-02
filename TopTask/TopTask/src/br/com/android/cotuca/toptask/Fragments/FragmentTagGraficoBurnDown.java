package br.com.android.cotuca.toptask.Fragments;

import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.Graphs.GraficoBurnDownView;
import br.com.android.cotuca.toptask.Graphs.GraficoPizzaTarefasView;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentTagGraficoBurnDown extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_grafico_tarefa,
				container, false);

		ViewGroup viewGroup = (ViewGroup) view
				.findViewById(R.id.view_graph_tarefa);

		// GraficoBurnDownView g = new
		// GraficoBurnDownView(1,2,50,50,umDrawablecomerro); //comerro

		// GraficoPizzaTarefasView g = new
		// GraficoPizzaTarefasView(getActivity().getApplicationContext(),
		// 1,2,3);

		// viewGroup.addView(g);

		return view;

	}
}
