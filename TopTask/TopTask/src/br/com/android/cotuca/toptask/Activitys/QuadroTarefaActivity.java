package br.com.android.cotuca.toptask.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.DAO.TarefaDAO;

public class QuadroTarefaActivity extends Activity implements OnMenuItemClickListener{

	private Bundle dadosRecebidos;
	private int idMover;
	
	@Override
	protected void onCreate(Bundle estado) {
		// TODO Auto-generated method stub
		super.onCreate(estado);
		setContentView(R.layout.activity_quadro_tarefas);
		dadosRecebidos = getIntent().getExtras();
	}
	
	public void showPopup(View v){
		PopupMenu pop = new PopupMenu(this, v);
		MenuInflater inflater = pop.getMenuInflater();
		inflater.inflate(R.menu.mover_tarefa, pop.getMenu());

		Log.i("Efeito do tarefas", String.valueOf(v.getId()));
		pop.show();
	}
	
	@Override
	public boolean onMenuItemClick(MenuItem item) {
		TarefaDAO tarefas = TarefaDAO.getInstance(this);
		switch(item.getItemId()){
			case R.id.mover_fazer:

			case R.id.mover_fazendo:

			case R.id.mover_feito:

		}
		
		this.recreate();
		return false;
	}
	
}
