package br.com.android.cotuca.toptask.Activitys;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.Beans.Usuario;
import br.com.android.cotuca.toptask.ManipsWeb.ManipUsuarioTask;

public class CadastroUsuarioActivity extends Activity {
	
	
	private EditText edtNomeUsuario;
	private EditText edtEmailUsuario;
	private EditText edtSenhaUsuario;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_usuario);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		edtNomeUsuario = (EditText) findViewById(R.id.edt_nomeNovoUsuario);
		edtEmailUsuario = (EditText) findViewById(R.id.edt_emailNovoProjeto);
		edtSenhaUsuario = (EditText) findViewById(R.id.edt_senhaNovoUsuario);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.cadastro_usuario, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		
		int id = item.getItemId();
		if (id == R.id.action_criar_usuario) {

			Usuario usu = new Usuario(edtNomeUsuario.getText().toString(),
										edtEmailUsuario.getText().toString(),
											edtSenhaUsuario.getText().toString(),null);
			
			ManipUsuarioTask task= new ManipUsuarioTask();
			//Integer resposta = task.executaAcoes(usu, ManipUsuarioTask.ADD_USU);
			
			Toast.makeText(getApplicationContext(), "Conta criado com sucesso", Toast.LENGTH_SHORT).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static class PlaceholderFragment extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_cadastro_usuario, container, false);
			return rootView;
		}
	}

}
