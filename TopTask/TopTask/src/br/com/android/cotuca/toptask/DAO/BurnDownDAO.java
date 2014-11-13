package br.com.android.cotuca.toptask.DAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.android.cotuca.toptask.BD.ContratoBurnDownProjeto;
import br.com.android.cotuca.toptask.BD.ContratoBurnDownTarefa;
import br.com.android.cotuca.toptask.BD.ContratoProjetos;
import br.com.android.cotuca.toptask.BD.ContratoTarefas;
import br.com.android.cotuca.toptask.BD.DBHelper;
import br.com.android.cotuca.toptask.Beans.BurnDown;
import br.com.android.cotuca.toptask.Beans.Projeto;
import br.com.android.cotuca.toptask.Beans.Tarefa;
import br.com.android.cotuca.toptask.tags.Tags;

public class BurnDownDAO {

	private static BurnDownDAO instancia;
	private DBHelper dbHelper;
	private SQLiteDatabase db;
	private String[] colunas = new String[] { 
			ContratoBurnDownProjeto.Colunas._ID,
			ContratoBurnDownProjeto.Colunas.ID_PROJETO,
			ContratoBurnDownProjeto.Colunas.TEMPO_FEITO,
			ContratoBurnDownProjeto.Colunas.TEMPO_LIMITE,
			ContratoBurnDownProjeto.Colunas.DATA_ATUAL};

	public static BurnDownDAO getInstance(Context contexto) {
		if (instancia == null) {
			instancia = new BurnDownDAO(contexto.getApplicationContext());
		}
		return instancia;
	}

	private BurnDownDAO(Context contexto) {
		dbHelper = DBHelper.getInstance(contexto);
		db = dbHelper.getWritableDatabase();
	}
	
	public void save(Projeto projeto){
		Calendar cal = Calendar.getInstance();
		
		ContentValues values = new ContentValues();
		values.put(ContratoBurnDownProjeto.Colunas.DATA_ATUAL, cal.getTime().toString());
		values.put(ContratoBurnDownProjeto.Colunas.ID_PROJETO, projeto.getId());
		values.put(ContratoBurnDownProjeto.Colunas.TEMPO_FEITO, projeto.getTotalFeito());
		values.put(ContratoBurnDownProjeto.Colunas.TEMPO_LIMITE, projeto.getTotalLimite());
		
		db.insert(ContratoBurnDownProjeto.NOME_TABELA, null, values);
	}
	
	public void delete(Projeto projeto) {
		db.delete(ContratoBurnDownProjeto.NOME_TABELA, ContratoBurnDownProjeto.Colunas.ID_PROJETO
				+ " = ?", new String[] { String.valueOf(projeto.getId()) });
	}
	
	public static BurnDown getCursor(Cursor c) {
		String data = c.getString(c.getColumnIndex(ContratoProjetos.Colunas.DATA_ENTREGA));
		int _id = c.getInt(c.getColumnIndex(ContratoProjetos.Colunas._ID));;
		int tempoFeito = c.getInt(c.getColumnIndex(ContratoProjetos.Colunas.TOTAL_FEITO));
		int tempoLimite = c.getInt(c.getColumnIndex(ContratoProjetos.Colunas.TOTAL_LIMITE));
		int idProjeto = c.getInt(c.getColumnIndex(ContratoBurnDownProjeto.Colunas.ID_PROJETO));
		
		return new BurnDown(_id,idProjeto,tempoFeito,tempoLimite,data);
		
	}
}
