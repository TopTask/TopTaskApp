package Dao;

import Conexao.BD;

public abstract class IDao {

	protected BD bd;
	protected String Tabela;
	
	public abstract void   inserir  (Object o);
	public abstract void   alterar  (Object o);
	public abstract void   deletar  (Object o);
	public abstract Object pesquisar(Object key);	
	
}
