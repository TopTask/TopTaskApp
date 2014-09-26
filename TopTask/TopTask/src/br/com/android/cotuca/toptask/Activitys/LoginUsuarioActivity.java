package br.com.android.cotuca.toptask.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Toast;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.Beans.Usuario;
import br.com.android.cotuca.toptask.DAO.UsuarioDAO;
import br.com.android.cotuca.toptask.tags.Tags;

public class LoginUsuarioActivity extends Activity implements
		OnItemSelectedListener {

	private UsuarioDAO dao;

	private EditText edtEmail;
	private EditText edtSenha;

	@Override
	protected void onCreate(Bundle estado) {
		super.onCreate(estado);
		setContentView(R.layout.fragment_login_usuario);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		dao = UsuarioDAO.getInstance(this);

		edtEmail = (EditText) findViewById(R.id.edt_emailLogin);
		edtSenha = (EditText) findViewById(R.id.edt_senhaLogin);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.login_usuario, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_entrar) {
			String email = edtEmail.getText().toString();
			String senha = edtSenha.getText().toString();

			if (email == null || email.equals("") || senha == null
					|| senha.equals("")) {
				Toast.makeText(getApplicationContext(),
						"Preencha todos os campos", Toast.LENGTH_SHORT).show();
				return false;
			}

			Usuario u = dao.getUsuario(email);

			if (u.getEmail().equals("") || u.getEmail() == "") {
				Toast.makeText(getApplicationContext(),
						"E-mail nao cadastrado no TopTask", Toast.LENGTH_SHORT)
						.show();
				edtEmail.requestFocus();
				edtEmail.setText("");
				return false;
			}
			if (u.getSenha().equals(senha) || u.getSenha() == senha) {
				Intent i = new Intent(this, ProjetosActivity.class);
				Bundle dadoIdUsuario = new Bundle();

				dadoIdUsuario.putInt(Tags.ID_USUARIO, u.getId());

				i.putExtras(dadoIdUsuario);
				startActivity(i);

				return true;
			} else {
				Toast.makeText(getApplicationContext(), "Senha incorreta",
						Toast.LENGTH_SHORT).show();
				edtSenha.requestFocus();
				edtSenha.setText("");
				return false;
			}

		} else if (id == android.R.id.home) {
			Intent i = new Intent(getApplicationContext(),
					EntradaActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);

			return true;

		}
		return super.onOptionsItemSelected(item);
	}
}
