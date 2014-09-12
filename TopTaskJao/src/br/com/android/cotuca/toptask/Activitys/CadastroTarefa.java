package br.com.android.cotuca.toptask.Activitys;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.BD.ContratoTarefas;
import br.com.android.cotuca.toptask.Beans.Tarefa;
import br.com.android.cotuca.toptask.DAO.TarefaDAO;
import br.com.android.cotuca.toptask.Dialogs.DateDialog;
import br.com.android.cotuca.toptask.ManipsWeb.ManipTarefaTask;
import br.com.android.cotuca.toptask.Receivers.NotificacaoSimplesReceiver;
import br.com.android.cotuca.toptask.tags.Tags;

public class CadastroTarefa extends Activity implements OnItemSelectedListener,
		DateDialog.SetDateListener{

	private ArrayAdapter<Character> adapter;
	private TarefaDAO dao;

	private EditText edtNome;
	private EditText edtDescricao;
	private Spinner spinner;
	private EditText edtData ;
	private boolean ehAtu;
	private int idTarefa;
	
	//variaveis sobre o alarme
	private AlarmManager am;
	private PendingIntent pi;
	
	private int dia, mes,ano;

	@Override
	protected void onCreate(Bundle estado) {
		super.onCreate(estado);
		setContentView(R.layout.activity_cadastro_tarefa);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);

		spinner = (Spinner) findViewById(R.id.s_prioridade);

		adapter = new ArrayAdapter<Character>(this,
				android.R.layout.simple_spinner_item); 
		adapter.add('1');
		adapter.add('2');
		adapter.add('3');
		adapter.add('4');

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);

		dao = TarefaDAO.getInstance(this);

		edtNome = (EditText) findViewById(R.id.edt_nomeNovaTarefa);
		edtDescricao = (EditText) findViewById(R.id.edt_descricaoNovaTarefa);
		edtData = (EditText) findViewById(R.id.edt_data_tarefa);
		
		ehAtu = false;
		
		Bundle dados = getIntent().getExtras();
		
		if (dados != null) {
			
			ehAtu = true;
			String nome = dados.getString(ContratoTarefas.Colunas.NOME);
			String descricao = dados.getString(ContratoTarefas.Colunas.DESCRICAO);
			String data = dados.getString(ContratoTarefas.Colunas.DATA_ENTREGA);
			idTarefa = dados.getInt(ContratoTarefas.Colunas._ID);
			
			edtNome.setText(nome);
			edtDescricao.setText(descricao);
			edtData.setText(data);
			
			//colocar o spinner na posicao certa
			dados = null; // ==> n sei se isso dar� certo; Objetivo n hora que voltar para essa activity zerar os dados
		}
		
		//pega o manager para o alarme 
		am = (AlarmManager) getSystemService(ALARM_SERVICE);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro_tarefa, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

	public void onClickSetarData(View v) {
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

		if (id == R.id.action_criar_tarefa) {
		
			String nome = edtNome.getText().toString();
			String descricao = edtDescricao.getText().toString();
			String data = edtData.getText().toString();
			int prioridade = Integer.valueOf(spinner.getSelectedItem().toString());
			
			if(nome == null || nome.equals("") || descricao == null || descricao.equals("") || data == null || data.equals("")){
				Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
				return false;
				}
			
			if (!ehAtu) {
				Tarefa novaTarefa = new Tarefa(nome, descricao, 1, data, prioridade, 1,0, "");
				//TEREMOS Q CRIAR UMA CLASSE QUE SALVARA EM JSON O idUsuario e idProjeto
				//PRA N TER QUE FICAR PESQUISANDO QND FOR INSERIR/ALTERAR/ETC 
				dao.save(novaTarefa); 
				//Integer resposta = manip.executaAcoes(novaTarefa, ManipTarefaTask.ADD_TAREFA);
				} else {
				Log.d(Tags.TOPTASK_ACTIVITY, "ID tarefa:" + idTarefa);
				
				Tarefa tarefaAtu = dao.getTarefa(String.valueOf(idTarefa));
		
				tarefaAtu.setNome(nome);
				tarefaAtu.setDescricao(descricao);
				tarefaAtu.setDataEntrega(data);
				tarefaAtu.setPrioridade(prioridade);
				
				dao.update(tarefaAtu);
				
				//ManipTarefaTask manip = new ManipTarefaTask();
				//Integer resposta = manip.executaAcoes(tarefaAtu, ManipTarefaTask.ALTERAR_TAREFA);
			}
			//agenda o alarme para esse mesmo dia no horario atual
			agendarNotificacao(nome,descricao,ano,mes,dia);
			Toast.makeText(getApplicationContext(), "Tarefa criada com sucesso", Toast.LENGTH_SHORT).show();
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

	private void agendarNotificacao(String titulo, String descricao,int ano,int mes, int dia){
		
		Calendar c = Calendar.getInstance();
		
		
		//setar a data atual
		c.set(ano, mes, dia);
		
		
		Intent i = new Intent (getApplicationContext(),NotificacaoSimplesReceiver.class);
		
		Bundle dados = new Bundle();
		
		dados.putString("titulo", titulo);
		dados.putString("descricao",descricao);
		i.putExtras(dados);
		
		pi = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
		
		
		am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
		
		//sendBroadcast(i);
		
		
	}
}
