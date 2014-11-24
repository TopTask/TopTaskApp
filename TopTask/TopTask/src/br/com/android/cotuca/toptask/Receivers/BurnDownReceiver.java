package br.com.android.cotuca.toptask.Receivers;

import br.com.android.cotuca.toptask.Beans.Projeto;
import br.com.android.cotuca.toptask.DAO.BurnDownDAO;
import br.com.android.cotuca.toptask.DAO.ProjetoDAO;
import br.com.android.cotuca.toptask.DAO.TarefaDAO;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class BurnDownReceiver extends BroadcastReceiver {
	
	private BurnDownDAO daoBurnDown;
	private ProjetoDAO daoProjeto;
	private TarefaDAO daoTarefa;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Atualiza Burn Down foi chamado", Toast.LENGTH_LONG).show();
		Bundle dados = intent.getExtras();
		
		daoProjeto = new ProjetoDAO(context);
		daoBurnDown = new BurnDownDAO(context);
		daoTarefa = new TarefaDAO(context);
		
		
		Projeto projetoAtual = daoProjeto.getProjeto(dados.getString("id_projeto"));
		
		if(!daoTarefa.getTarefasProjeto(dados.getInt("id_projeto")).isEmpty()){
			projetoAtual.setTotalFeito(daoTarefa.getTotalFeito(dados.getInt("id_projeto")));
			projetoAtual.setTotalLimite(daoTarefa.getTotalLimite(dados.getInt("id_projeto")));
		
			daoProjeto.update(projetoAtual);
		
			daoBurnDown.save(projetoAtual);
		}
		
	}
}
