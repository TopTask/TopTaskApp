package br.com.android.cotuca.toptask.Activitys;

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
import br.com.android.cotuca.toptask.Fragments.ListenerClickCadastroTarefa;
import br.com.android.cotuca.toptask.tags.Tags;

public class EditarTarefa extends Activity implements OnItemSelectedListener,
		DateDialog.SetDateListener, ListenerClickCadastroTarefa {

	private ArrayAdapter<Character> adapter;
	private TarefaDAO dao;

	private EditText edtNome;
	private EditText edtDescricao;
	private Spinner spinner;

	private EditText edtData;
	private String dataOriginal;

	private int idTarefa;
	private int idProjeto;
	private int idDono;

	private EditText edtTempoLimite;
	private EditText edtTempoFeito;

	@Override
	protected void onCreate(Bundle estado) {
		super.onCreate(estado);

		setContentView(R.layout.activity_editar_tarefa);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		dao = TarefaDAO.getInstance(this);

		Bundle dados = getIntent().getExtras();

		if (dados != null) {

			int acao = dados.getInt(Tags.B_ACAO);

			Log.d(Tags.B_ACAO, "Acao a ser realizada: " + acao);

			idProjeto = dados.getInt(Tags.ID_PROJETO);
			idDono = dados.getInt(ContratoTarefas.Colunas.DONO);
			String nome = dados.getString(ContratoTarefas.Colunas.NOME);
			String descricao = dados
					.getString(ContratoTarefas.Colunas.DESCRICAO);
			String data = dados.getString(ContratoTarefas.Colunas.DATA_ENTREGA);
			idTarefa = dados.getInt(Tags.ID_TAREFA);
			String tempoLimite = dados
					.getString(ContratoTarefas.Colunas.TEMPO_LIMITE);
			String tempoFeito = dados
					.getString(ContratoTarefas.Colunas.TEMPO_FEITO);

			edtNome = (EditText) findViewById(R.id.edt_nomeNovaTarefa);
			edtDescricao = (EditText) findViewById(R.id.edt_descricaoNovaTarefa);
			edtData = (EditText) findViewById(R.id.edt_data_tarefa);
			edtTempoFeito = (EditText) findViewById(R.id.edt_tempo_feito);
			edtTempoLimite = (EditText) findViewById(R.id.edt_tempo_limite);

			edtNome.setText(nome);
			edtDescricao.setText(descricao);
			edtData.setText(data);
			edtTempoFeito.setText(tempoFeito);
			edtTempoLimite.setText(tempoLimite);
		}
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

		dados = null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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

	@Override
	public void onSet(int ano, int mes, int dia) {

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
			int tempoLimite = Integer.parseInt(edtTempoLimite.getText()
					.toString());
			int tempoFeito = Integer.parseInt(edtTempoFeito.getText()
					.toString());
			String data = dataOriginal;
			int prioridade = Integer.valueOf(spinner.getSelectedItem()
					.toString());

			Log.d(Tags.TOPTASK_ACTIVITY, "ID tarefa:" + idTarefa);

			Tarefa tarefaAtu = dao.getTarefa(String.valueOf(idTarefa));

			tarefaAtu.setNome(nome);
			tarefaAtu.setDescricao(descricao);
			tarefaAtu.setDataEntrega(data);
			tarefaAtu.setTempoFeito(tempoFeito);
			tarefaAtu.setTempoLimite(tempoLimite);
			tarefaAtu.setPrioridade(prioridade);
			tarefaAtu.setIdProjeto(idProjeto);
			tarefaAtu.setDono(idDono);

			dao.update(tarefaAtu);


			Intent i = new Intent(this, MSimplesActivity.class);
			Bundle dadosBundle = new Bundle();

			dadosBundle.putInt(Tags.ID_PROJETO, idProjeto);
			dadosBundle.putInt(Tags.ID_USUARIO, idDono);
			i.putExtras(dadosBundle);

			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);

			return true;

		} else if (id == android.R.id.home) {
			Intent i = new Intent(getApplicationContext(),
					MSimplesActivity.class);
			Bundle dadosBundle = new Bundle();
			dadosBundle.putInt(Tags.ID_PROJETO, idProjeto);
			dadosBundle.putInt(Tags.ID_USUARIO, idDono);
			i.putExtras(dadosBundle);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);

			return true;

		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClickSetarData(View v) {
		FragmentManager fm = getFragmentManager();
		DateDialog dd = new DateDialog();
		dd.show(fm, "DateDialog");
	}

	@Override
	public void onClickSetarTempo(View v) {
		FragmentManager fm = getFragmentManager();

	}

}
