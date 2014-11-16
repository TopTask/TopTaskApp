package br.com.android.cotuca.toptask.Receivers;

import br.com.android.cotuca.toptask.Beans.Projeto;
import br.com.android.cotuca.toptask.DAO.BurnDownDAO;
import br.com.android.cotuca.toptask.DAO.ProjetoDAO;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class BurnDownReceiver extends BroadcastReceiver {
	
	private BurnDownDAO daoBurnDown;
	private ProjetoDAO daoProjeto;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Atualiza Burn Down foi chamado", Toast.LENGTH_LONG).show();
		Bundle dados = intent.getExtras();
		
		Projeto projetoAtual = daoProjeto.getProjeto(dados.getString("id_projeto"));
		
		daoBurnDown.save(projetoAtual);
		
	}
}
