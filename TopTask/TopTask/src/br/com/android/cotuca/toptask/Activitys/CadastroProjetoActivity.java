package br.com.android.cotuca.toptask.Activitys;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.BD.ContratoProjetos;
import br.com.android.cotuca.toptask.Beans.Projeto;
import br.com.android.cotuca.toptask.DAO.ProjetoDAO;
import br.com.android.cotuca.toptask.Dialogs.DateDialog;
import br.com.android.cotuca.toptask.tags.Tags;

public class CadastroProjetoActivity extends Activity implements OnItemSelectedListener,
DateDialog.SetDateListener {

	private ProjetoDAO dao;

	private EditText edtNome;
	private EditText edtDescricao;
	private EditText edtData ;
	private int idProjeto;
	private boolean ehAtu;
	private int dia, mes,ano;
	
	@Override
	protected void onCreate(Bundle estado) {
		super.onCreate(estado);
		setContentView(R.layout.fragment_cadastro_projeto);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		dao = ProjetoDAO.getInstance(this);

		edtNome = (EditText) findViewById(R.id.edt_nomeNovoProjeto);
		edtDescricao = (EditText) findViewById(R.id.edt_DescricaoNovoProjeto);
		edtData = (EditText) findViewById(R.id.edt_data);
		ehAtu = false;
		Bundle dados = getIntent().getExtras();

		if (dados != null) {
			ehAtu = true;
			String nome = dados.getString(ContratoProjetos.Colunas.NOME);
			String descricao = dados.getString(ContratoProjetos.Colunas.DESCRICAO);
			String data = dados.getString(ContratoProjetos.Colunas.DATA_ENTREGA);
			idProjeto = dados.getInt(ContratoProjetos.Colunas._ID);
			
			edtNome.setText(nome);
			edtDescricao.setText(descricao);
			edtData.setText(data);
			
			dados = null; // ==> n sei se isso dar� certo; Objetivo n hora que voltar para essa activity zerar os dados
		
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro_projeto, menu);
		return true;
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
	}
	
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}


	public void setarData(View v) {
		FragmentManager fm = getFragmentManager();
		DateDialog dd = new DateDialog();
		dd.show(fm, "DateDialog");
	}
	

	@Override
	public void onSet(int ano, int mes, int dia) {
				edtData.setText(dia+"/"+mes+"/"+ano);
				this.dia = dia;
				this.mes = mes;
				this.ano = ano;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		Log.i("ENTROU NO CLICK", "ENTROU NO CLICK");
		if (id == R.id.action_accept) {
			Log.i("ENTROU NO IF", "ENTROU NO IF");
			String nome = edtNome.getText().toString();
			String descricao = edtDescricao.getText().toString();
			String data = edtData.getText().toString();
			
			if(nome == null || nome.equals("") || descricao == null || descricao.equals("") || data == null || data.equals("")){
				Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
				return false;
				}
			
			if (!ehAtu) {
				Log.i("criarei um proj novo", "criarei um proj novo");
				Projeto novoProjeto = new Projeto(nome, descricao,data,1,1,"urlPic");
				
				//TEREMOS Q CRIAR UMA CLASSE QUE SALVARA EM JSON O idUsuario e idProjeto
				//PRA N TER QUE FICAR PESQUISANDO QND FOR INSERIR/ALTERAR/ETC 
				dao.save(novoProjeto); 
				//Integer resposta = manip.executaAcoes(novoProjeto, ManipProjetoTask.ADD_PROJETO);
				} else {
				Log.d(Tags.TOPTASK_ACTIVITY, "ID projeto:" + idProjeto);
				
				Projeto projetoAtu = dao.getProjeto(String.valueOf(idProjeto));
		
				projetoAtu.setNome(nome);
				projetoAtu.setDescricao(descricao);
				projetoAtu.setDataEntrega(data);
				
				dao.update(projetoAtu);
				
				//ManipTarefaTask manip = new ManipProjetoTask();
				//Integer resposta = manip.executaAcoes(projetoAtu, ManipTarefaTask.ALTERAR_PROJETO);
			}
			Toast.makeText(getApplicationContext(), "Projeto criado com sucesso", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(this, MSimplesActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			
			return true;
			
		} else if (id == android.R.id.home) {
			Intent i = new Intent (getApplicationContext(), MSimplesActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
			
			return true;
			
		}

		return super.onOptionsItemSelected(item);
	}
}
