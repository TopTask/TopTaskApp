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
import br.com.android.cotuca.toptask.BD.ContratoUsuarios;
import br.com.android.cotuca.toptask.Beans.Usuario;
import br.com.android.cotuca.toptask.DAO.UsuarioDAO;

public class LoginUsuarioActivity extends Activity implements OnItemSelectedListener {

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
		Log.i("ENTROU NO CLICK LOGAR", "ENTROU NO CLICK LOGAR");
		if (id == R.id.action_entrar) {
			Log.i("ENTROU NO IF", "ENTROU NO IF");
			String email = edtEmail.getText().toString();
			String senha = edtSenha.getText().toString();
			
			if(email == null || email.equals("") || senha == null || senha.equals("")){
				Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
				return false;
				}
			Usuario u = dao.getUsuario(email);
			if(u.getEmail().equals("")||u.getEmail()==""){
				Toast.makeText(getApplicationContext(), "E-mail inexistente na base de dados", Toast.LENGTH_SHORT).show();
				return false;
			}
			if(u.getSenha().equals(senha)||u.getSenha()==senha){
				Toast.makeText(getApplicationContext(), "Logado com sucesso", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(this, ProjetosActivity.class);
				Bundle dadoIdUsuario = new Bundle();
				
				Log.d("ID DO USUARIO",u.getId()+"");
				
				dadoIdUsuario.putInt(ContratoUsuarios.Colunas._ID,u.getId());
				i.putExtras(dadoIdUsuario);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(i);
			
				return true;
			}
			else{
				Log.i("Senha incorreta", "Senha incorreta");
				Toast.makeText(getApplicationContext(), "Senha incorreta", Toast.LENGTH_SHORT).show();
				return false;
			}
			
		} else if (id == android.R.id.home) {
			Intent i = new Intent (getApplicationContext(), EntradaActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
			
			return true;
			
		}
		return super.onOptionsItemSelected(item);
	}
}
