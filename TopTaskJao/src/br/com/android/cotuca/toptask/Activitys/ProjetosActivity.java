package br.com.android.cotuca.toptask.Activitys;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.Dialogs.ModelosDialogFragment;

public class ProjetosActivity extends Activity {

	public static final String TAG_DIALOG = "Escolha Modelo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setTitle("Projetos");
		setContentView(R.layout.activity_projetos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the acstion bar if it is present.
		getMenuInflater().inflate(R.menu.projetos, menu);
		return true;
	}

	public void onClickNovo(View v) {
		// ao clicar no botao de criar novo projeto
		// abrir dialog com opções de Simples ou Scrum
		
		chamarDialog();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// trata o botao de crair novo projeto e de sair

		switch (item.getItemId()) {
		case R.id.action_add_projeto:
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

}
