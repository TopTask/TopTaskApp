package br.com.android.cotuca.toptask.Activitys;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.Beans.Projeto;
import br.com.android.cotuca.toptask.Dialogs.ModelosDialogFragment;
import br.com.android.cotuca.toptask.Fragments.FragmentProjetos.ListenerClickProjeto;
import br.com.android.cotuca.toptask.Fragments.FragmentSemProjetos;
import br.com.android.cotuca.toptask.Fragments.FragmentSemProjetos.ListenerClickNovoProjeto;

public class ProjetosActivity extends Activity implements ListenerClickNovoProjeto, ListenerClickProjeto{

	public static final String TAG_DIALOG = "Escolha Modelo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setTitle("Projetos");
		setContentView(R.layout.activity_projetos);
		
//		Logica para verificar qual fragment colocar 
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		
		FragmentSemProjetos fsp = new FragmentSemProjetos();
		
		ft.add(R.id.container, fsp, "fsp");
		ft.commit();	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the acstion bar if it is present.
		getMenuInflater().inflate(R.menu.projetos, menu);
		return true;
	}
	
	@Override
	public void onClickNovo(View v) {
		// ao clicar no botao de criar novo projeto
		// abrir dialog com opções de Simples ou Scrum
		
		chamarDialog();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// trata o botao de crair novo projeto e de sair

		switch (item.getItemId()) {
		case R.id.action_accept_projeto:
			chamarDialog();
			return true;
			
		case R.id.action_sair_conta:
			Intent i = new Intent (getApplication(),EntradaActivity.class);
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
		
//		mas tenho que passar o id de quem esta logado
		Intent i = new Intent(getApplicationContext(),MSimplesActivity.class);
		startActivity(i);
	}
	
	

}
