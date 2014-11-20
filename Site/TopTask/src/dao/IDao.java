package dao;

import bd.Conexao;

public abstract class IDao {

	protected Conexao bd;
	protected String Tabela;
	
	public abstract String inserir  (Object o);
	public abstract void   alterar  (Object o);
	public abstract void   deletar  (Object o);
	public abstract Object pesquisar(Object key);	
	
}
