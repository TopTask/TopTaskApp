package br.com.android.cotuca.toptask.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.android.cotuca.toptask.R;

public class FragmentEditarTarefa extends Fragment {
	
	private ListenerClickCadastroTarefa listener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		if (!(activity instanceof ListenerClickCadastroTarefa)) {
			 throw new RuntimeException("A activity deve implementar a interface ListenerClickCadastroTarefa");
		}
		
		listener = (ListenerClickCadastroTarefa) activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_editar_tarefa, container, false);
	}
}
