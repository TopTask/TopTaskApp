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
import br.com.android.cotuca.toptask.Receivers.NotificacaoSimplesReceiver;
import br.com.android.cotuca.toptask.tags.Tags;

public class CadastroTarefa extends Activity implements OnItemSelectedListener,
		DateDialog.SetDateListener {

	private ArrayAdapter<Character> adapter;
	private TarefaDAO dao;

	private EditText edtNome;
	private EditText edtDescricao;
	private Spinner spinner;
	private EditText edtData;
	private boolean ehAtu;
	private int idTarefa;

	// variaveis sobre o alarme
	private AlarmManager am;
	private PendingIntent pi;

	private int dia, mes, ano;
	private String dataOriginal;
	private int idProjeto;
	private int idDono;

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
			
			int acao = dados.getInt("ACAO");
			
			if (acao == 0) { //Adicao
				idProjeto = dados.getInt(Tags.ID_PROJETO);
				idDono = dados.getInt(ContratoTarefas.Colunas.DONO);
				
				Log.i(ContratoTarefas.Colunas.PROJETO, "Id projeto na pagina de cadastro: " + idProjeto);

			} else if (acao == 1){ //Alteracao
				ehAtu = true;
				idProjeto = dados.getInt(Tags.ID_PROJETO);
				idDono = dados.getInt(ContratoTarefas.Colunas.DONO);
				String nome = dados.getString(ContratoTarefas.Colunas.NOME);
				String descricao = dados.getString(ContratoTarefas.Colunas.DESCRICAO);
				String data = dados.getString(ContratoTarefas.Colunas.DATA_ENTREGA);
				idTarefa = dados.getInt(Tags.ID_TAREFA);

				edtNome.setText(nome);
				edtDescricao.setText(descricao);
				edtData.setText(data);
			}

			dados = null; 
		}

		// pega o manager para o alarme
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
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		dataOriginal = (dia + "/" + mes + "/" + ano);
		mes += 1;
		edtData.setText(dia + "/" + mes + "/" + ano);
		

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (R.id.action_criar_tarefa == id) {
			String nome = edtNome.getText().toString();
			String descricao = edtDescricao.getText().toString();
			String data = dataOriginal;
			int prioridade = Integer.valueOf(spinner.getSelectedItem().toString());
			
			if (nome == null || nome.equals("") || data == null || data.equals("")) {
				Toast.makeText(getApplicationContext(),"Preencha todos os campos", Toast.LENGTH_SHORT).show();
				return false;
			}

			if (!ehAtu) {
				dao.save(new Tarefa(nome, descricao, idDono, data, prioridade,idProjeto, 0)); //pendente == 0 
			} else {
				Log.d(Tags.TOPTASK_ACTIVITY, "ID tarefa:" + idTarefa);

				Tarefa tarefaAtu = dao.getTarefa(String.valueOf(idTarefa));

				tarefaAtu.setNome(nome);
				tarefaAtu.setDescricao(descricao);
				tarefaAtu.setDataEntrega(data);
				tarefaAtu.setPrioridade(prioridade);
				tarefaAtu.setIdProjeto(idProjeto);
				tarefaAtu.setDono(idDono);
				
				dao.update(tarefaAtu);

			}
			// agenda o alarme para esse mesmo dia no horario atual
//			agendarNotificacao(nome, descricao, ano, mes, dia);

			Intent i = new Intent(this, MSimplesActivity.class);
			Bundle dadosBundle = new Bundle();
			
			dadosBundle.putInt(Tags.ID_PROJETO, idProjeto);
			dadosBundle.putInt(Tags.ID_USUARIO, idDono);
			i.putExtras(dadosBundle);
			
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);

			return true;

		} else if (id == android.R.id.home) {
			Intent i = new Intent(getApplicationContext(),MSimplesActivity.class);
			Bundle dadosBundle = new Bundle();
			dadosBundle.putInt(Tags.ID_PROJETO, idProjeto);
			dadosBundle.putInt(Tags.ID_USUARIO, idDono);
			i.putExtras(dadosBundle);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);

			return true;

		}

		return super.onOptionsItemSelected(item);
	}

	private void agendarNotificacao(String titulo, String descricao, int ano,
			int mes, int dia) {

		Calendar c = Calendar.getInstance();

		// setar a data atual
		c.set(ano, mes, dia);

		Intent i = new Intent(this,NotificacaoSimplesReceiver.class);

		Bundle dados = new Bundle();

		dados.putString("titulo", titulo);
		dados.putString("descricao", descricao);
		dados.putInt(Tags.ID_PROJETO, idProjeto);
		
		i.putExtras(dados);

		pi = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);

		am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);

		// sendBroadcast(i);

	}
}
