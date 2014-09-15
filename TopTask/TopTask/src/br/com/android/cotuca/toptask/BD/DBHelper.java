package br.com.android.cotuca.toptask.BD;

//import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

	private static DBHelper instancia;
	private static final String DB_NOME = "DB_Toptask";
	private static final int DB_VERSAO = 2;
	
	private static final String SQL_DROP_USUARIO = "DROP TABLE IF EXISTS " + ContratoUsuarios.NOME_TABELA;
	private static final String SQL_DROP_PROJETO = "DROP TABLE IF EXISTS " + ContratoProjetos.NOME_TABELA;
	private static final String SQL_DROP_TAREFA = "DROP TABLE IF EXISTS " + ContratoTarefas.NOME_TABELA;
	
	private static final String SQL_CREATE_USUARIO = String.format(
			"CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"%s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT)", 
			ContratoUsuarios.NOME_TABELA,
			ContratoUsuarios.Colunas._ID, 
			ContratoUsuarios.Colunas.NOME,
			ContratoUsuarios.Colunas.EMAIL,
			ContratoUsuarios.Colunas.SENHA, 
			ContratoUsuarios.Colunas.FOTO
			);

	private static final String SQL_CREATE_PROJETO = String.format(
			"CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"%s TEXT NOT NULL, %s TEXT, %s TEXT NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL, %s TEXT)", 
			ContratoProjetos.NOME_TABELA,
			ContratoProjetos.Colunas._ID, 
			ContratoProjetos.Colunas.NOME,
			ContratoProjetos.Colunas.DESCRICAO, 
			ContratoProjetos.Colunas.DATA_ENTREGA,
			ContratoProjetos.Colunas.DONO,
			ContratoProjetos.Colunas.CONCLUIDA,
			ContratoProjetos.Colunas.FOTO
			);
	
	private static final String SQL_CREATE_TAREFA = String.format(
			"CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"%s TEXT NOT NULL, %s TEXT, %s INTEGER NOT NULL, %s TEXT, %s INTEGER NOT NULL, %s INTEGER NOT NULL,%s INTEGER NOT NULL)", 
			ContratoTarefas.NOME_TABELA,
			ContratoTarefas.Colunas._ID, 
			ContratoTarefas.Colunas.NOME,
			ContratoTarefas.Colunas.DESCRICAO, 
			ContratoTarefas.Colunas.DONO,
			ContratoTarefas.Colunas.DATA_ENTREGA , 
			ContratoTarefas.Colunas.PRIORIDADE, 
			ContratoTarefas.Colunas.PROJETO,
			ContratoTarefas.Colunas.CONCLUIDA);
	
	private DBHelper(Context context){
		super(context, DB_NOME, null, DB_VERSAO);
		
	}
	
	public static DBHelper getInstance(Context contexto) {
		if (instancia == null) {
			instancia = new DBHelper(contexto);
		}

		return instancia;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_DROP_USUARIO);
		db.execSQL(SQL_DROP_TAREFA);
		db.execSQL(SQL_DROP_PROJETO);
		db.execSQL(SQL_CREATE_USUARIO);
		db.execSQL(SQL_CREATE_PROJETO);
		db.execSQL(SQL_CREATE_TAREFA);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onCreate(db);
	}
	
	//private void lerBanco(InputStream in) {
	//}

}
