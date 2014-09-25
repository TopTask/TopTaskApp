package br.com.android.cotuca.toptask.DAO;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.android.cotuca.toptask.BD.ContratoMembros;
import br.com.android.cotuca.toptask.BD.ContratoUsuarios;
import br.com.android.cotuca.toptask.BD.DBHelper;
import br.com.android.cotuca.toptask.Beans.Membro;
import br.com.android.cotuca.toptask.tags.Tags;

public class MembroDAO {

	private static MembroDAO instancia;
	private DBHelper dbHelper;
	private static SQLiteDatabase db;
	private static String[] colunas = new String[] {
			ContratoMembros.Colunas._ID,
			ContratoMembros.Colunas.ID_PROJETO,
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
	
	public static List<Membro> getMembros() {


		Cursor c = db.query(ContratoMembros.NOME_TABELA,colunas, null, null,
				null, null, ContratoUsuarios.Colunas.NOME);
		//ERRO PODE ESTAR AQUI

		List<Membro> membros = new ArrayList<Membro>();

		try {
			if (c.moveToFirst()) {
				do {
					Membro m = getCursor(c);
					membros.add(m);
				} while (c.moveToNext());
			}

		} finally {
			c.close();
		}

		return membros;
	}
	
	public static Membro getMembro(String _id){
		Cursor c = db.query(ContratoMembros.NOME_TABELA,colunas, ContratoMembros.Colunas._ID + " = ? ", new String[]{_id},
				null, null, ContratoUsuarios.Colunas.NOME);
		//PODE ESTAR DANDO ERRO AQUI
		Membro m = null;
		try {
		if (c.moveToFirst()){
			m = getCursor(c);
		}
		}finally {
			c.close();
		}
		return m;
	}
	
	
	private static Membro getCursor(Cursor c) {

		int _id = c.getInt(c.getColumnIndex(ContratoMembros.Colunas._ID));
		
		int idUsuario = c.getInt(c.getColumnIndex(ContratoMembros.Colunas.ID_USUARIO));
		
		int idProjeto = c.getInt(c.getColumnIndex(ContratoMembros.Colunas.ID_PROJETO));
		
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

	
	public void save(Membro membro) {
		ContentValues values = new ContentValues();
		values.put(ContratoMembros.Colunas._ID, membro.getId());
		values.put(ContratoMembros.Colunas.ID_USUARIO,membro.getIdUsuario());
		values.put(ContratoMembros.Colunas.ID_PROJETO, membro.getIdProjeto());
		
		int permissao = 0;
		switch (membro.getPermissao()){
		
		case PO:
			permissao = 0;
			break;
			
		case SM:
			permissao = 1;
			break;
			
		case PR:
			permissao = 2;
			
		}
		values.put(ContratoMembros.Colunas.PERMISSAO, permissao);

		
		Log.d(Tags.TOPTASK_BD, "Proximo passo cadastrar");
		
		long id = db.insert(ContratoMembros.NOME_TABELA, null, values);
		
		membro.setID((int) id);
	}
	
}
