package br.com.android.cotuca.toptask.DAO;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.android.cotuca.toptask.BD.ContratoUsuarios;
import br.com.android.cotuca.toptask.BD.DBHelper;
import br.com.android.cotuca.toptask.Beans.Usuario;
import br.com.android.cotuca.toptask.tags.Tags;

public class UsuarioDAO {
	
	private static UsuarioDAO instancia;
	private DBHelper dbHelper;
	private SQLiteDatabase db;
	private String[] colunas = new String[] { 
			ContratoUsuarios.Colunas._ID,
			ContratoUsuarios.Colunas.NOME,
			ContratoUsuarios.Colunas.EMAIL,
			ContratoUsuarios.Colunas.SENHA,
			ContratoUsuarios.Colunas.FOTO
			};
	
	public static UsuarioDAO getInstance(Context contexto) {
		if (instancia == null) {
			instancia = new UsuarioDAO(contexto.getApplicationContext());
		}
		return instancia;
	}

	private UsuarioDAO(Context contexto) {
		dbHelper = DBHelper.getInstance(contexto);
		db = dbHelper.getWritableDatabase();
	}


	public List<Usuario> getUsuarios() {

		Cursor c = db.query(ContratoUsuarios.NOME_TABELA,colunas, null, null,
				null, null, null);

		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			if (c.moveToFirst()) {
				do {
					Usuario u = UsuarioDAO.getCursor(c);
					usuarios.add(u);
				} while (c.moveToNext());
			}

		} finally {
			c.close();
		}

		return usuarios;
	}
	
	public Usuario getUsuario (String email) {
		Cursor c = db.query(ContratoUsuarios.NOME_TABELA,colunas, ContratoUsuarios.Colunas.EMAIL + " = ? ", new String[]{email},
				null, null, null);
		
		Usuario u = null; 
		try {
			if (c.moveToFirst()) {
					u = UsuarioDAO.getCursor(c);
			}
			else
				u = new Usuario("", "", "", "");

		} finally {
			c.close();
		}

		return u;
		
	}

	public static Usuario getCursor(Cursor c) {
		int _id = c.getInt(c.getColumnIndex(ContratoUsuarios.Colunas._ID));
		String nome = c.getString(c.getColumnIndex(ContratoUsuarios.Colunas.NOME));
		String email = c.getString(c.getColumnIndex(ContratoUsuarios.Colunas.EMAIL));
		String senha = c.getString(c.getColumnIndex(ContratoUsuarios.Colunas.SENHA));
		String foto = c.getString(c.getColumnIndex(ContratoUsuarios.Colunas.FOTO));
		
		return new Usuario(_id,nome,email,senha,foto); 
		
	}

	public void save(Usuario usuario) {
		ContentValues values = new ContentValues();
		values.put(ContratoUsuarios.Colunas.NOME, usuario.getNome());
		values.put(ContratoUsuarios.Colunas.EMAIL, usuario.getEmail());
		values.put(ContratoUsuarios.Colunas.SENHA, usuario.getSenha());
		values.put(ContratoUsuarios.Colunas.FOTO, usuario.getFoto());
		
		Log.d(Tags.TOPTASK_BD, "Proximo passo: cadastrar usuário");
		
		long id = db.insert(ContratoUsuarios.NOME_TABELA, null, values);
		
		usuario.setId((int) id);
	}

	public void update(Usuario usuario) {
		ContentValues values = new ContentValues();
		values.put(ContratoUsuarios.Colunas.NOME, usuario.getNome());
		values.put(ContratoUsuarios.Colunas.EMAIL, usuario.getEmail());
		values.put(ContratoUsuarios.Colunas.SENHA, usuario.getSenha());
		values.put(ContratoUsuarios.Colunas.FOTO, usuario.getFoto());
		
		db.update(ContratoUsuarios.NOME_TABELA, values,
				ContratoUsuarios.Colunas._ID + " = ? ",
				new String[] { String.valueOf(usuario.getId()) });
	}

	public void delete(Usuario usuario) {
	
		db.delete(ContratoUsuarios.NOME_TABELA, ContratoUsuarios.Colunas._ID
				+ " = ?", new String[] { String.valueOf(usuario.getId()) });
	}
}
