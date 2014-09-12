package br.com.android.cotuca.toptask.BD;

public final class ContratoTarefas {
	
	public static final String NOME_TABELA = "TTTarefa";
	
	public static final class Colunas {
		public static final String _ID = "_id";
		public static final String NOME = "nome";
		public static final String DESCRICAO = "descr";
		public static final String DATA_ENTREGA = "entrega";
		public static final String DONO = "idUsuario";
		public static final String PRIORIDADE = "prioridade";
		public static final String PROJETO = "idProjeto";
		public static final String CONCLUIDA = "progresso";
		public static final String FOTO = "foto";
	}
}
