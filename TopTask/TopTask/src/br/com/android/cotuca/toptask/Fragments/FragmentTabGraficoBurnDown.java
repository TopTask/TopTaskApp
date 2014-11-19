package br.com.android.cotuca.toptask.Fragments;

import java.util.ArrayList;
import java.util.List;

import org.afree.ui.Drawable;

import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.Beans.BurnDown;
import br.com.android.cotuca.toptask.DAO.BurnDownDAO;
import br.com.android.cotuca.toptask.DAO.TarefaDAO;
import br.com.android.cotuca.toptask.Graphs.GraficoBurnDownView;
import br.com.android.cotuca.toptask.Graphs.GraficoPizzaTarefasView;
import br.com.android.cotuca.toptask.tags.Tags;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FragmentTabGraficoBurnDown extends Fragment {
	
	private Bundle dadosRecebidos;
	private BurnDownDAO daoBurnDown;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		daoBurnDown = new BurnDownDAO(getActivity());
		
		View view = inflater.inflate(R.layout.fragment_grafico_burndown, container, false);

		ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.view_graph_burndown);
		
		dadosRecebidos = getActivity().getIntent().getExtras(); //idUsu e idProj
		
		int idProjeto = dadosRecebidos.getInt(Tags.ID_PROJETO);

		List<BurnDown> burnDowns = daoBurnDown.getBurnDownsDoProjeto(idProjeto);
		
		if(!burnDowns.isEmpty()){
			GraficoBurnDownView g = new GraficoBurnDownView(getActivity().getApplicationContext(), idProjeto);  
			
			viewGroup.addView(g);

			return view;
		}
		Toast.makeText(getActivity(),"Ainda não é possível gerar o BurnDown", Toast.LENGTH_SHORT).show();
		return view;
	}

}
