package br.com.android.cotuca.toptask.Activitys;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.BD.ContratoProjetos;
import br.com.android.cotuca.toptask.Beans.Projeto;
import br.com.android.cotuca.toptask.DAO.ProjetoDAO;
import br.com.android.cotuca.toptask.Dialogs.ModelosDialogFragment;
import br.com.android.cotuca.toptask.Fragments.FragmentProjetos.ListenerClickProjeto;
import br.com.android.cotuca.toptask.Fragments.FragmentMembros;
import br.com.android.cotuca.toptask.Fragments.FragmentProjetos;
import br.com.android.cotuca.toptask.Fragments.FragmentSemProjetos;
import br.com.android.cotuca.toptask.Fragments.FragmentTarefas;
import br.com.android.cotuca.toptask.Fragments.FragmentSemProjetos.ListenerClickNovoProjeto;
import br.com.android.cotuca.toptask.tags.Tags;

public class ProjetosActivity extends Activity implements
		ListenerClickNovoProjeto, ListenerClickProjeto, Callback {

	public static final String TAG_DIALOG = "Escolha Modelo";

	private Projeto projetoSelecionado;
	private boolean actionModeAtivado = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setTitle("Projetos");
		setContentView(R.layout.activity_projetos);

		selecionaFragmentAdequado();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the acstion bar if it is
		// present.
		getMenuInflater().inflate(R.menu.projetos, menu);
		return true;
	}

	@Override
	public void onClickNovo(View v) {
		// ao clicar no botao de criar novo projeto
		// abrir dialog com opções de Simples ou Scrum

		// chamarDialog();

		Intent iCadastro = new Intent(this,
				CadastroProjetoActivity.class);
		startActivity(iCadastro);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// trata o botao de crair novo projeto e de sair

		switch (item.getItemId()) {
		case R.id.action_accept_projeto:
			chamarDialog();
			return true;

		case R.id.action_sair_conta:
			Intent i = new Intent(getApplication(), EntradaActivity.class);
			startActivity(i);

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	public void chamarDialog() {
		FragmentManager fm = getFragmentManager();
		ModelosDialogFragment mdf = new ModelosDialogFragment();

		mdf.show(fm, TAG_DIALOG);

	}

	@Override
	public void onProjetoClick(Projeto projeto) {

		int idProjetoSelecionado = projeto.getId();

		Log.i(Tags.TOPTASK_ACTIVITY, "ID do projeto selecionado: "
				+ idProjetoSelecionado);

		Intent i = new Intent(getApplicationContext(), MSimplesActivity.class);
		Bundle dados = new Bundle();
		dados.putInt("ID_PROJETO", idProjetoSelecionado);

		i.putExtras(dados);
		startActivity(i);
	}

	@Override
	public void onLongoClick(Projeto projeto) {

		projetoSelecionado = projeto;
		startActionMode(this);

		actionModeAtivado = false;

	}

	@Override
	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		int idItem = item.getItemId();
		ProjetoDAO projetosDAO = ProjetoDAO.getInstance(this);

		if (idItem == R.id.action_entrar_projeto) {

			onProjetoClick(projetoSelecionado);
			projetoSelecionado = null;

			mode.finish();
			return true;

		} else if (idItem == R.id.action_excluir_projeto) {
			// deletar todas as tarefas com o id do projeto
			projetosDAO.delete(projetoSelecionado);
			projetoSelecionado = null;
			selecionaFragmentAdequado();
			mode.finish();

			return true;

		} else if (idItem == R.id.action_editar_projeto) {
			return true;
		}

		return false;
	}

	@Override
	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		if (!actionModeAtivado) {
			mode.getMenuInflater().inflate(R.menu.actionmode_projeto, menu);
		}
		return true;
	}

	@Override
	public void onDestroyActionMode(ActionMode mode) {
		actionModeAtivado = false;
	}

	@Override
	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		// TODO Auto-generated method stub
		return false;
	}

	private void selecionaFragmentAdequado() {
		ProjetoDAO projetos = ProjetoDAO.getInstance(this);
		List<Projeto> listProjetos = projetos.getProjetos();

		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();

		if (!listProjetos.isEmpty()) {
			FragmentProjetos fp = new FragmentProjetos();
			ft.replace(R.id.container, fp, "fp");
		} else {
			FragmentSemProjetos fsp = new FragmentSemProjetos();
			ft.replace(R.id.container, fsp, "fsp");
		}

		ft.commit();

	}

}
