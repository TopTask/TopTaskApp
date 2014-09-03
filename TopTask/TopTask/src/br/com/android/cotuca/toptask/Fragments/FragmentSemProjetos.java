package br.com.android.cotuca.toptask.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.android.cotuca.toptask.R;

public class FragmentSemProjetos extends Fragment {
	
	private ListenerClickNovoProjeto listener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		if (!(activity instanceof ListenerClickNovoProjeto)) {
			 throw new RuntimeException("A activity deve implementar a interface ListenerClickNovoProjeto");
		}
		
		listener = (ListenerClickNovoProjeto) activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_sem_projetos, container, false);
	}
	
	public interface ListenerClickNovoProjeto {
		public void onClickNovo(View v);
	}
}
