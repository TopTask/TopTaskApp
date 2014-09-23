package br.com.android.cotuca.toptask.Fragments;

import br.com.android.cotuca.toptask.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentTabGraficoTarefa extends Fragment {
	  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		  return inflater.inflate(R.layout.fragment_grafico_tarefa, container, false);
	  }
}
