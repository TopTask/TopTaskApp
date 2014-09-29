package br.com.android.cotuca.toptask.Fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.ListView;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.Activitys.CadastroProjetoActivity;
import br.com.android.cotuca.toptask.Adapter.AdapterProjeto;
import br.com.android.cotuca.toptask.Beans.Projeto;
import br.com.android.cotuca.toptask.DAO.ProjetoDAO;
import br.com.android.cotuca.toptask.tags.Tags;

public class FragmentProjetos extends ListFragment implements
		OnMenuItemClickListener {

	private AdapterProjeto adapter;
	private ListenerClickProjeto listener;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if (!(activity instanceof ListenerClickProjeto)) {
			throw new RuntimeException(
					"A activity deve implementar a interface ListenerClickProjeto");
		}

		listener = (ListenerClickProjeto) activity;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		inflater.inflate(R.menu.projetos, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ProjetoDAO projetos = ProjetoDAO.getInstance(getActivity());
		
		Bundle dadosRecebidos = getActivity().getIntent().getExtras();
		
		int idUsuario = dadosRecebidos.getInt(Tags.ID_USUARIO);
		
		adapter = new AdapterProjeto(getActivity(), projetos.getProjetosDoUsuario(idUsuario));
		setListAdapter(adapter);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Projeto projeto = adapter.getItem(position);

//		listener.onProjetoClick(projeto);
		
		listener.onLongoClick(projeto);

		
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		int id = item.getItemId();

		// Arrumar aqui possivel erro
		if (id == R.id.action_accept_projeto) {
			Intent i = new Intent(getActivity(), CadastroProjetoActivity.class);
			startActivity(i);
		}

		return false;
	}



	public interface ListenerClickProjeto {
		public void onLongoClick(Projeto projeto);

		public void onProjetoClick(Projeto projeto);
	}


}
