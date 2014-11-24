package br.com.android.cotuca.toptask.DAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.android.cotuca.toptask.BD.ContratoBurnDownProjeto;
import br.com.android.cotuca.toptask.BD.DBHelper;
import br.com.android.cotuca.toptask.Beans.BurnDown;
import br.com.android.cotuca.toptask.Beans.Projeto;

public class BurnDownDAO {

	private static BurnDownDAO instancia;
	private DBHelper dbHelper;
	private SQLiteDatabase db;
	private String[] colunas = new String[] { 
			ContratoBurnDownProjeto.Colunas._ID,
			ContratoBurnDownProjeto.Colunas.ID_PROJETO,
			ContratoBurnDownProjeto.Colunas.TEMPO_FEITO,
			ContratoBurnDownProjeto.Colunas.TEMPO_LIMITE,
			ContratoBurnDownProjeto.Colunas.DIA_ATUAL,
			ContratoBurnDownProjeto.Colunas.MES_ATUAL,
			ContratoBurnDownProjeto.Colunas.ANO_ATUAL};

	public static BurnDownDAO getInstance(Context contexto) {
		if (instancia == null) {
			instancia = new BurnDownDAO(contexto.getApplicationContext());
		}
		return instancia;
	}

	public BurnDownDAO(Context contexto) {
		dbHelper = DBHelper.getInstance(contexto);
		db = dbHelper.getWritableDatabase();
	}
	
	public void save(Projeto projeto){
		Calendar cal = Calendar.getInstance();
		
		ContentValues values = new ContentValues();
		values.put(ContratoBurnDownProjeto.Colunas.DIA_ATUAL, cal.get(Calendar.DAY_OF_MONTH));
		values.put(ContratoBurnDownProjeto.Colunas.MES_ATUAL, cal.get(Calendar.MONTH));
		values.put(ContratoBurnDownProjeto.Colunas.ANO_ATUAL, cal.get(Calendar.YEAR));
		values.put(ContratoBurnDownProjeto.Colunas.ID_PROJETO, projeto.getId());
		values.put(ContratoBurnDownProjeto.Colunas.TEMPO_FEITO, projeto.getTotalFeito());
		values.put(ContratoBurnDownProjeto.Colunas.TEMPO_LIMITE, projeto.getTotalLimite());
		
		db.insert(ContratoBurnDownProjeto.NOME_TABELA, null, values);
	}
	
	public void saveTeste(Projeto projeto, int dia, int mes, int ano, int tempoFeito, int tempoLimite){
		
		ContentValues values = new ContentValues();
		values.put(ContratoBurnDownProjeto.Colunas.DIA_ATUAL, dia);
		values.put(ContratoBurnDownProjeto.Colunas.MES_ATUAL, mes);
		values.put(ContratoBurnDownProjeto.Colunas.ANO_ATUAL, ano);
		values.put(ContratoBurnDownProjeto.Colunas.ID_PROJETO, projeto.getId());
		values.put(ContratoBurnDownProjeto.Colunas.TEMPO_FEITO, tempoFeito);
		values.put(ContratoBurnDownProjeto.Colunas.TEMPO_LIMITE, tempoLimite);
		
		db.insert(ContratoBurnDownProjeto.NOME_TABELA, null, values);
	}
	
	public void delete(Projeto projeto) {
		db.delete(ContratoBurnDownProjeto.NOME_TABELA, ContratoBurnDownProjeto.Colunas.ID_PROJETO
				+ " = ?", new String[] { String.valueOf(projeto.getId()) });
	}
	
	public static BurnDown getCursor(Cursor c) {
		
		int dia = c.getInt(c.getColumnIndex(ContratoBurnDownProjeto.Colunas.DIA_ATUAL));
		int mes = c.getInt(c.getColumnIndex(ContratoBurnDownProjeto.Colunas.MES_ATUAL));
		int ano = c.getInt(c.getColumnIndex(ContratoBurnDownProjeto.Colunas.ANO_ATUAL));
		int _id = c.getInt(c.getColumnIndex(ContratoBurnDownProjeto.Colunas._ID));
		int tempoFeito = c.getInt(c.getColumnIndex(ContratoBurnDownProjeto.Colunas.TEMPO_FEITO));
		int tempoLimite = c.getInt(c.getColumnIndex(ContratoBurnDownProjeto.Colunas.TEMPO_LIMITE));
		int idProjeto = c.getInt(c.getColumnIndex(ContratoBurnDownProjeto.Colunas.ID_PROJETO));
		
		return new BurnDown(_id,idProjeto,tempoFeito,tempoLimite,dia,mes,ano);
		
	}
	
	public List<BurnDown> getBurnDownsDoProjeto(int idProjeto) {

		Cursor c = db.query(ContratoBurnDownProjeto.NOME_TABELA,colunas, 
				ContratoBurnDownProjeto.Colunas.ID_PROJETO + " = ? ", new String[] {String.valueOf(idProjeto)},
				null, null, ContratoBurnDownProjeto.Colunas._ID);

		List<BurnDown> burnDowns = new ArrayList<BurnDown>();
		
		try {
			if (c.moveToFirst()) {
				do {
					BurnDown bd = BurnDownDAO.getCursor(c);
					burnDowns.add(bd);
				} while (c.moveToNext());
			}

		} finally {
			c.close();
		}
		
		return burnDowns;
	}
	
}
