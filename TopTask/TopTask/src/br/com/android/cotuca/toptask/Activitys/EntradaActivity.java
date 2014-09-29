package br.com.android.cotuca.toptask.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import br.com.android.cotuca.toptask.R;

public class EntradaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entrada);
		getActionBar().hide();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.entrada, menu);
		return true;
	}
	
	public void clickEntrar(View v) {
		Intent iLogin = new Intent (this, LoginUsuarioActivity.class);
		startActivity(iLogin);
	}
	
	public void clickCadastrar(View v) {
		Intent iCadastro = new Intent (this, CadastroUsuarioActivity.class);
		startActivity(iCadastro);
	}

}
