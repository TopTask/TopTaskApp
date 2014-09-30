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

public class CadastroUsuarioActivity extends Activity implements OnItemSelectedListener {
	
	private UsuarioDAO dao;
	
	private EditText edtNome;
	private EditText edtEmail;
	private EditText edtSenha;
//	private int idUsuario;
	
	@Override
	protected void onCreate(Bundle estado) {
		super.onCreate(estado);
		setContentView(R.layout.fragment_cadastro_usuario);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		dao = UsuarioDAO.getInstance(this);
		edtNome = (EditText) findViewById(R.id.edt_nomeNovoUsuario);
		edtSenha = (EditText) findViewById(R.id.edt_senhaNovoUsuario);
		edtEmail = (EditText) findViewById(R.id.edt_emailNovoUsuario);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.cadastro_usuario, menu);
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
		if (id == R.id.action_criar_usuario) {
			Log.i("ENTROU NO IF", "ENTROU NO IF");
			
			String nome = edtNome.getText().toString();
			String email = edtEmail.getText().toString();
			String senha = edtSenha.getText().toString();
			
			if(validaCampos(nome, email, senha)){
				Log.i("criarei um usu novo", "criarei um usu novo");
				Usuario novoUsuario = new Usuario(nome, email,senha,"urlPic");
			
				dao.save(novoUsuario); 
			
			    //ManipUsuarioTask task= new ManipUsuarioTask();
			    //Integer resposta = task.executaAcoes(usu, ManipUsuarioTask.ADD_USU);
			
			
				Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(this, ProjetosActivity.class);
				Bundle dadoIdUsuario = new Bundle();
				
				Log.d("ID DO USUARIO",novoUsuario.getId()+"");
				
				dadoIdUsuario.putInt(Tags.ID_USUARIO,novoUsuario.getId());
				i.putExtras(dadoIdUsuario);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			
				return true;
			}
		} else if (id == android.R.id.home) {
			Intent i = new Intent (getApplicationContext(), EntradaActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
			
			return true;
			
		}

		return super.onOptionsItemSelected(item);
	}
	
	public boolean validaCampos(String nome, String email, String senha){
		String validacaoEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		if(nome == null || nome.equals("") || email == null || email.equals("") || senha == null || senha.equals("")){
			Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
			return false;
		}
		
        Boolean emailEhValido = email.matches(validacaoEmail);
        
        if (emailEhValido == false) {
        	Toast.makeText(getApplicationContext(), "E-mail inv�lido", Toast.LENGTH_SHORT).show();
        	edtEmail.requestFocus();
			edtEmail.setText("");
			return false;
        }
        
        if(senha.length()<6){
        	Toast.makeText(getApplicationContext(), "Digite uma senha com mais de 6 d�gitos", Toast.LENGTH_SHORT).show();
        	edtSenha.requestFocus();
        	edtSenha.setText("");
			return false;
        }
        
        Usuario u = dao.getUsuario(email);
        
        if(!u.getEmail().equals("")||u.getEmail()!=""){
			Toast.makeText(getApplicationContext(), "E-mail j� cadastrado no TopTask", Toast.LENGTH_SHORT).show();
			edtEmail.requestFocus();
			edtEmail.setText("");
			return false;
		}
        
        return true;
	}

}
