package br.com.android.cotuca.toptask.Activitys;

import br.com.android.cotuca.toptask.R;
import android.app.Activity;
import android.os.Bundle;

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
		
	}
	
}
