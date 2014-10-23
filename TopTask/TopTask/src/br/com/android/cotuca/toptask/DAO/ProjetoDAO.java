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
import br.com.android.cotuca.toptask.BD.ContratoUsuarios;
import br.com.android.cotuca.toptask.BD.DBHelper;
import br.com.android.cotuca.toptask.Beans.Projeto;
import br.com.android.cotuca.toptask.Beans.Tarefa;
import br.com.android.cotuca.toptask.tags.Tags;

public class ProjetoDAO {
	private static ProjetoDAO instancia;
	private DBHelper dbHelper;
	private SQLiteDatabase db;
	private String[] colunas = new String[] { 
			ContratoProjetos.Colunas._ID,
			ContratoProjetos.Colunas.NOME,
			ContratoProjetos.Colunas.DESCRICAO,
			ContratoProjetos.Colunas.DATA_ENTREGA,
			ContratoProjetos.Colunas.DONO,
			ContratoProjetos.Colunas.CONCLUIDA,
			ContratoProjetos.Colunas.FOTO,
			ContratoProjetos.Colunas.TOTAL_FEITO,
			ContratoProjetos.Colunas.TOTAL_LIMITE
			};
	
	public static ProjetoDAO getInstance(Context contexto) {
		if (instancia == null) {
			instancia = new ProjetoDAO(contexto.getApplicationContext());
		}
		return instancia;
	}

	private ProjetoDAO(Context contexto) {
		dbHelper = DBHelper.getInstance(contexto);
		db = dbHelper.getWritableDatabase();
	}


	public List<Projeto> getProjetos() {

		Cursor c = db.query(ContratoProjetos.NOME_TABELA,colunas, null, null,
				null, null, ContratoProjetos.Colunas.DATA_ENTREGA);

		List<Projeto> projetos = new ArrayList<Projeto>();

		try {
			if (c.moveToFirst()) {
				do {
					Projeto p = ProjetoDAO.getCursor(c);
					projetos.add(p);
				} while (c.moveToNext());
			}

		} finally {
			c.close();
		}

		return projetos;
	}
	
	public Projeto getProjeto (String _id) {
		Cursor c = db.query(ContratoProjetos.NOME_TABELA,colunas, ContratoProjetos.Colunas._ID + " = ? ", new String[]{_id},
				null, null, ContratoProjetos.Colunas.DATA_ENTREGA);
		
		Projeto p = null; 
		try {
			if (c.moveToFirst()) {
					p = ProjetoDAO.getCursor(c);
			}

		} finally {
			c.close();
		}

		return p;
		
	}
	
	public List<Projeto> getProjetosDoUsuario (int _id) {
		Cursor c = db.query(ContratoProjetos.NOME_TABELA,colunas, ContratoProjetos.Colunas.DONO + " = ? ", new String[]{String.valueOf(_id)},
				null, null, ContratoProjetos.Colunas.DATA_ENTREGA);
		

		List<Projeto> projetos = new ArrayList<Projeto>();

		try {
			if (c.moveToFirst()) {
				do {
					Projeto p = ProjetoDAO.getCursor(c);
					projetos.add(p);
				} while (c.moveToNext());
			}

		} finally {
			c.close();
		}

		return projetos;
	}
	

	public static Projeto getCursor(Cursor c) {

		String nome = c.getString(c.getColumnIndex(ContratoProjetos.Colunas.NOME));
		String descricao = c.getString(c.getColumnIndex(ContratoProjetos.Colunas.DESCRICAO));
		String data = c.getString(c.getColumnIndex(ContratoProjetos.Colunas.DATA_ENTREGA));
		int dono = c.getInt((c.getColumnIndex(ContratoProjetos.Colunas.DONO)));
		int _id = c.getInt(c.getColumnIndex(ContratoProjetos.Colunas._ID));
		int concluida = c.getInt(c.getColumnIndex(ContratoProjetos.Colunas.CONCLUIDA));
		String foto = c.getString(c.getColumnIndex(ContratoProjetos.Colunas.FOTO));
		int tempoFeito = c.getInt(c.getColumnIndex(ContratoProjetos.Colunas.TOTAL_FEITO));
		int tempoLimite = c.getInt(c.getColumnIndex(ContratoProjetos.Colunas.TOTAL_LIMITE));
		
		return new Projeto(_id,nome,descricao,data,dono,concluida,foto,tempoFeito,tempoLimite); 
		
	}

	public void save(Projeto projeto) {
		ContentValues values = new ContentValues();
		values.put(ContratoProjetos.Colunas.NOME, projeto.getNome());
		values.put(ContratoProjetos.Colunas.DESCRICAO, projeto.getDescricao());
		values.put(ContratoProjetos.Colunas.DATA_ENTREGA,projeto.getDataEntrega());
		values.put(ContratoProjetos.Colunas.DONO, projeto.getDono());
		values.put(ContratoProjetos.Colunas.CONCLUIDA, projeto.getConcluida());
		values.put(ContratoProjetos.Colunas.FOTO, projeto.getFoto());
		values.put(ContratoProjetos.Colunas.TOTAL_FEITO, projeto.getTotalFeito());
		values.put(ContratoProjetos.Colunas.TOTAL_LIMITE, projeto.getTotalLimite());
		
		Log.d(Tags.TOPTASK_BD, "Proximo passo cadastrar");

		
		long id = db.insert(ContratoProjetos.NOME_TABELA, null, values);
		
		projeto.setId((int) id);
	}

	public void update(Projeto projeto) {
		ContentValues values = new ContentValues();
		values.put(ContratoProjetos.Colunas.NOME, projeto.getNome());
		values.put(ContratoProjetos.Colunas.DESCRICAO, projeto.getDescricao());
		values.put(ContratoProjetos.Colunas.DATA_ENTREGA,projeto.getDataEntrega());
		values.put(ContratoProjetos.Colunas.DONO, projeto.getDono());
		values.put(ContratoProjetos.Colunas.CONCLUIDA, projeto.getConcluida());
		values.put(ContratoProjetos.Colunas.FOTO, projeto.getFoto());
		values.put(ContratoProjetos.Colunas.TOTAL_FEITO, projeto.getTotalFeito());
		values.put(ContratoProjetos.Colunas.TOTAL_LIMITE, projeto.getTotalLimite());
		
		db.update(ContratoProjetos.NOME_TABELA, values,
				ContratoProjetos.Colunas._ID + " = ? ",
				new String[] { String.valueOf(projeto.getId()) });
		
		saveBurnDownProjeto(projeto);
	}
	
	public void saveBurnDownProjeto(Projeto projeto){
		Calendar cal = Calendar.getInstance();
		
		ContentValues values = new ContentValues();
		values.put(ContratoBurnDownProjeto.Colunas.DATA_ATUAL, cal.getTime().toString());
		values.put(ContratoBurnDownProjeto.Colunas.ID_PROJETO, projeto.getId());
		values.put(ContratoBurnDownProjeto.Colunas.TEMPO_FEITO, projeto.getTotalFeito());
		values.put(ContratoBurnDownProjeto.Colunas.TEMPO_LIMITE, projeto.getTotalLimite());
		
		db.insert(ContratoBurnDownProjeto.NOME_TABELA, null, values);
	}

	public void delete(Projeto projeto) {
	
		db.delete(ContratoProjetos.NOME_TABELA, ContratoProjetos.Colunas._ID
				+ " = ?", new String[] { String.valueOf(projeto.getId()) });
	}
	
	//public void concluirProjeto (Projeto projeto) {
	//	int id = projeto.getId(); 
		
	//	ContentValues values = new ContentValues();
	//	values.put(ContratoProjetos.Colunas.CONCLUIDA, 1);
		
	//	db.update(ContratoProjetos.NOME_TABELA, values, ContratoProjetos.Colunas._ID + " = ? ", new String[] {String.valueOf(id)});
		
	//}

}