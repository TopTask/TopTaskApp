package br.com.android.cotuca.toptask.Activitys;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.Dialogs.DateDialog;

public class CadastroProjetoActivity extends Activity implements DateDialog.SetDateListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_projeto);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.cadastro_projeto, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		
		//Criacao do projeto
		if (id == R.id.action_accept) {
			Toast.makeText(getApplicationContext(),"Projeto Criado", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(getApplicationContext(),MSimplesActivity.class);
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	public void setarData(View v) {
		FragmentManager fm = getFragmentManager();
		DateDialog dd = new DateDialog();
		dd.show(fm, "DateDialog");
		
	}
	
	@Override
	public void onSet(int ano, int mes, int dia) {
			
			// seta a data no edt_data;
			EditText edt_data = (EditText) findViewById(R.id.edt_data);
						
			edt_data.setText(dia+"/"+mes+"/"+ano);
	
	}
	
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_cadastro_projeto, container, false);
			return rootView;
		}
	}

}
