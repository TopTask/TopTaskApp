package br.com.android.cotuca.toptask.Activitys;

import java.util.List;

import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.BD.ContratoProjetos;
import br.com.android.cotuca.toptask.Beans.Tarefa;
import br.com.android.cotuca.toptask.DAO.TarefaDAO;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;

public class QuadroTarefaActivity extends Activity{

	private Bundle dadosRecebidos;
	
	@Override
	protected void onCreate(Bundle estado) {
		// TODO Auto-generated method stub
		super.onCreate(estado);
		setContentView(R.layout.activity_quadro_tarefas);
		dadosRecebidos = getIntent().getExtras();
		
		selecionaFragmentAdequado(dadosRecebidos);
	}

	private void selecionaFragmentAdequado(Bundle dados) {
		TarefaDAO tarefas = TarefaDAO.getInstance(this);
		int idProjeto = dados.getInt(ContratoProjetos.Colunas._ID);
		
		List<Tarefa> tarefasProjeto = tarefas.getTarefasProjetos(idProjeto);
		
		if (tarefasProjeto.isEmpty()){ // Carregar o FragmentQuadroTarefasVazio
		    
		}else{
			
			
		}
	}
	
	public void showPopup(View v){
		PopupMenu pop = new PopupMenu(this, v);
		MenuInflater inflater = pop.getMenuInflater();
		inflater.inflate(R.menu.);
	}
	
}
