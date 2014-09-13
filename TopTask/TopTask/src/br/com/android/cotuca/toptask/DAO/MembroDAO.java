package br.com.android.cotuca.toptask.DAO;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.android.cotuca.toptask.BD.ContratoMembros;
import br.com.android.cotuca.toptask.BD.ContratoTarefas;
import br.com.android.cotuca.toptask.BD.ContratoUsuarios;
import br.com.android.cotuca.toptask.BD.DBHelper;
import br.com.android.cotuca.toptask.Beans.Membro;
import br.com.android.cotuca.toptask.Beans.Tarefa;

public class MembroDAO {

	private static MembroDAO instancia;
	private DBHelper dbHelper;
	private SQLiteDatabase db;
	private String[] colunas = new String[] {
			ContratoMembros.Colunas._ID,
			ContratoMembros.Colunas.ID_PROJETOS,
			ContratoMembros.Colunas.ID_USUARIO,
			ContratoMembros.Colunas.ID_USUARIO};
	
	public static MembroDAO getInstance(Context contexto){
		if (instancia == null){
			instancia  = new MembroDAO(contexto.getApplicationContext());
		}
		return instancia;
		
	}

	private MembroDAO(Context contexto){
		dbHelper = DBHelper.getInstance(contexto);
		db = dbHelper.getWritableDatabase();
		
	}
	
	public List<Membro> getMembros() {


		Cursor c = db.query(ContratoMembros.NOME_TABELA,colunas, null, null,
				null, null, ContratoUsuarios.Colunas.NOME);
		//ERRO PODE ESTAR AQUI

		List<Membro> membros = new ArrayList<Membro>();

		try {
			if (c.moveToFirst()) {
				do {
					Membro m = MembroDAO.getCursor(c);
					membros.add(m);
				} while (c.moveToNext());
			}

		} finally {
			c.close();
		}

		return membros;
	}
	
	public static Membro getMembro(int id){
		
	}
	
	public static Membro getCursor(Cursor c) {

		int _id = c.getInt(c.getColumnIndex(ContratoMembros.Colunas._ID));
		
		int idUsuario = c.getInt(c.getColumnIndex(ContratoMembros.Colunas.ID_USUARIO));
		
		int idProjeto = c.getInt(c.getColumnIndex(ContratoMembros.Colunas.ID_PROJETOS));
		
		Membro.Permissao permissao;
		int p = c.getInt(c.getColumnIndex(ContratoMembros.Colunas.PERMISSAO));
		switch (p) {
		case 0:
			permissao = Membro.Permissao.PO;
			break;
			
		case 1:
			permissao = Membro.Permissao.SM;
			break;

		default:
			permissao = Membro.Permissao.PR;
			break;
		}
		
		return new Membro(_id, idUsuario, idProjeto, permissao);
	}
	
}
