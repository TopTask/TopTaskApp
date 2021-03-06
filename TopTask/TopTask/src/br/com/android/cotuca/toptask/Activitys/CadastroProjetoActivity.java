package br.com.android.cotuca.toptask.Activitys;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Toast;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.BD.ContratoProjetos;
import br.com.android.cotuca.toptask.Beans.Projeto;
import br.com.android.cotuca.toptask.DAO.BurnDownDAO;
import br.com.android.cotuca.toptask.DAO.ProjetoDAO;
import br.com.android.cotuca.toptask.DAO.TarefaDAO;
import br.com.android.cotuca.toptask.Dialogs.DateDialog;
import br.com.android.cotuca.toptask.tags.Tags;

public class CadastroProjetoActivity extends Activity implements
		OnItemSelectedListener, DateDialog.SetDateListener {

	private ProjetoDAO dao;
	private BurnDownDAO daoBurnDown;
	private TarefaDAO daoTarefa;

	private EditText edtNome;
	private EditText edtDescricao;
	private EditText edtData;
	private int idProjeto;
	private boolean ehAtu;
	private int dia, mes, ano;

	private String dataOriginal;

	private int idUsuario;
	
	private AlarmManager alarm;
	private PendingIntent pi;

	@Override
	protected void onCreate(Bundle estado) {
		super.onCreate(estado);
		setContentView(R.layout.fragment_cadastro_projeto);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		dao = ProjetoDAO.getInstance(this);
		daoBurnDown = BurnDownDAO.getInstance(this);
		daoTarefa = TarefaDAO.getInstance(this);

		edtNome = (EditText) findViewById(R.id.edt_nomeNovoProjeto);
		edtDescricao = (EditText) findViewById(R.id.edt_DescricaoNovoProjeto);
		edtData = (EditText) findViewById(R.id.edt_data);
		ehAtu = false;
		Bundle dados = getIntent().getExtras();

		alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

		int acao = dados.getInt("ACAO");
		if (acao == 1) {
			ehAtu = true;
			String nome = dados.getString(ContratoProjetos.Colunas.NOME);
			String descricao = dados
					.getString(ContratoProjetos.Colunas.DESCRICAO);
			String data = dados
					.getString(ContratoProjetos.Colunas.DATA_ENTREGA);
			idProjeto = dados.getInt(Tags.ID_PROJETO);
			idUsuario = dados.getInt(Tags.ID_USUARIO);

			edtNome.setText(nome);
			edtDescricao.setText(descricao);
			edtData.setText(data);

			dados = null;
		} else {
			if (acao == Tags.ACAO_CADASTRO) {
				idUsuario = dados.getInt(Tags.ID_USUARIO);
			}
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

		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		dataOriginal = (this.dia + "/" + this.mes + "/" + this.ano);
		mes += 1;
		edtData.setText(dia + "/" + mes + "/" + ano);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_accept) {
			String nome = edtNome.getText().toString();
			String descricao = edtDescricao.getText().toString();
			String data = dataOriginal;

			if (nome == null || nome.equals("") || data == null
					|| data.equals("")) {
				Toast.makeText(getApplicationContext(),
						"Preencha todos os campos", Toast.LENGTH_SHORT).show();
				return false;
			}

			if (!ehAtu) {
				Projeto novoProjeto = new Projeto(nome, descricao, data,
						idUsuario, ContratoProjetos.StatusTarefa.andamento, "");
				dao.save(novoProjeto);
				
				daoBurnDown.saveTeste(novoProjeto, 19, 11, 2014, 0, 100);
				daoBurnDown.saveTeste(novoProjeto, 20, 11, 2014, 50, 100);
				daoBurnDown.saveTeste(novoProjeto, 21, 11, 2014, 100, 200);
				daoBurnDown.saveTeste(novoProjeto, 22, 11, 2014, 150, 200);
				daoBurnDown.saveTeste(novoProjeto, 23, 11, 2014, 150, 250);
				daoBurnDown.saveTeste(novoProjeto, 24, 11, 2014, 150, 350);
				daoBurnDown.saveTeste(novoProjeto, 25, 11, 2014, 250, 350);
				
			//	agendamento de alarme para atualizacao das tabelas Burn Down para cria��o do gr�fico
//				Calendar calendar = Calendar.getInstance();
//				calendar.setTimeInMillis(System.currentTimeMillis());
//				calendar.set(Calendar.HOUR_OF_DAY, 17);
//				calendar.set(Calendar.MINUTE, 15);
//				calendar.set(Calendar.SECOND, 0);
//
//				Intent i = new Intent(getApplicationContext(), BurnDownReceiver.class);
//			
//				Bundle dados = new Bundle();
//				dados.putInt("id_projeto", novoProjeto.getId());
//				i.putExtras(dados);
//				pi = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
//						
//				Toast.makeText(getApplicationContext(), "Agendou alarme",Toast.LENGTH_SHORT).show();
//				
//				alarm.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),
//						AlarmManager.INTERVAL_DAY, pi);
				
			} else {
				Projeto projetoAtu = dao.getProjeto(String.valueOf(idProjeto));

				projetoAtu.setNome(nome);
				projetoAtu.setDescricao(descricao);
				projetoAtu.setDataEntrega(data);

				ehAtu = false;
				dao.update(projetoAtu);

			}
			Intent i = new Intent(this, ProjetosActivity.class);
			i.putExtra(Tags.ID_USUARIO, idUsuario);

			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			startActivity(i);

			return true;

		} else if (id == android.R.id.home) {

			Intent i = new Intent(this, ProjetosActivity.class);
			i.putExtra(Tags.ID_USUARIO, idUsuario);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			startActivity(i);

			return true;

		}

		return super.onOptionsItemSelected(item);
	}

	public void cancelaAlarmeAtualizaBurnDown() {
		alarm.cancel(pi);
	}

}
