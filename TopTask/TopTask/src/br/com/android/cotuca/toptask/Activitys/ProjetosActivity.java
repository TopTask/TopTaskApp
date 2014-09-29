package br.com.android.cotuca.toptask.Activitys;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
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
import br.com.android.cotuca.toptask.Beans.Tarefa;
import br.com.android.cotuca.toptask.DAO.ProjetoDAO;
import br.com.android.cotuca.toptask.DAO.TarefaDAO;
import br.com.android.cotuca.toptask.Dialogs.ModelosDialogFragment;
import br.com.android.cotuca.toptask.Fragments.FragmentProjetos;
import br.com.android.cotuca.toptask.Fragments.FragmentProjetos.ListenerClickProjeto;
import br.com.android.cotuca.toptask.Fragments.FragmentSemProjetos;
import br.com.android.cotuca.toptask.Fragments.FragmentSemProjetos.ListenerClickNovoProjeto;
import br.com.android.cotuca.toptask.tags.Tags;

public class ProjetosActivity extends Activity implements
		ListenerClickNovoProjeto, ListenerClickProjeto, Callback {

	public static final String TAG_DIALOG = "Escolha Modelo";

	private Projeto projetoSelecionado;
	private boolean actionModeAtivado = false;
	private int idUsuario;

	private Bundle dadosRecebidos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setTitle("Projetos");
		setContentView(R.layout.activity_projetos);
		
		Bundle dados = getIntent().getExtras();
		dadosRecebidos = dados;
		
		selecionaFragmentAdequado(dadosRecebidos);

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
		dadosRecebidos.putInt("ACAO", 0);
		iCadastro.putExtras(dadosRecebidos);
		startActivity(iCadastro);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// trata o botao de crair novo projeto e de sair

		switch (item.getItemId()) {
		case R.id.action_accept_projeto:
			Intent iCadastro = new Intent(this,
					CadastroProjetoActivity.class);
			dadosRecebidos.putInt("ACAO",0);
			iCadastro.putExtras(dadosRecebidos);
			
			startActivity(iCadastro);
			return true;

		case R.id.action_sair_conta:
			Intent i = new Intent(getApplication(), EntradaActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
		
		Intent i = new Intent(this, MSimplesActivity.class);
		Bundle dados = new Bundle();
		
		dados.putInt(Tags.ID_PROJETO, idProjetoSelecionado);
		dados.putInt(Tags.ID_USUARIO,idUsuario);
		
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
		TarefaDAO tarefaDAO = TarefaDAO.getInstance(this);

		if (idItem == R.id.action_entrar_projeto) {

			onProjetoClick(projetoSelecionado);
			projetoSelecionado = null;

			mode.finish();
			return true;

		} else if (idItem == R.id.action_excluir_projeto) {
			List<Tarefa> tarefasProjeto =tarefaDAO.getTarefasProjeto(projetoSelecionado.getId());
		
			if(!tarefasProjeto.isEmpty()){
			
				for(int i=0;i<tarefasProjeto.size();i++){  
					Log.d("TAREFAS PROJETO - n� " +i, tarefasProjeto.get(i).getNome());
					tarefaDAO.delete(tarefasProjeto.get(i));    
				} 
			}
			
			projetosDAO.delete(projetoSelecionado);
			projetoSelecionado = null;
			selecionaFragmentAdequado(dadosRecebidos);
			mode.finish();

			return true;

		} else if (idItem == R.id.action_editar_projeto) {
			Intent iEditar = new Intent(this, CadastroProjetoActivity.class);
			
			dadosRecebidos.putInt("ACAO", 1);
			dadosRecebidos.putString(ContratoProjetos.Colunas.NOME,projetoSelecionado.getNome());
			dadosRecebidos.putString(ContratoProjetos.Colunas.DESCRICAO,projetoSelecionado.getDescricao());
			dadosRecebidos.putString(ContratoProjetos.Colunas.DATA_ENTREGA,projetoSelecionado.getDataEntrega());
			dadosRecebidos.putInt(Tags.ID_PROJETO,projetoSelecionado.getId());
			dadosRecebidos.putInt(Tags.ID_USUARIO,idUsuario);
			
			iEditar.putExtras(dadosRecebidos);
			projetoSelecionado = null;
			mode.finish();
			
			startActivity(iEditar);

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

	private void selecionaFragmentAdequado(Bundle dados) {
		ProjetoDAO projetos = ProjetoDAO.getInstance(this);
		idUsuario = dados.getInt(Tags.ID_USUARIO);
		
		List<Projeto> listProjetos = projetos.getProjetosDoUsuario(idUsuario);

		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();

		if (!listProjetos.isEmpty()) {
			FragmentProjetos fp = new FragmentProjetos();
			fp.setArguments(dados);
			ft.replace(R.id.container, fp, "fp");
		} else {
			FragmentSemProjetos fsp = new FragmentSemProjetos();
			ft.replace(R.id.container, fsp, "fsp");
		}

		ft.commit();
	}
	
	@Override
	public void onBackPressed() {
		//nao deve voltar para pagina de entrada
	}
}
