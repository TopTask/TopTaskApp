package br.com.android.cotuca.toptask.BD;

public final class ContratoTarefas {
	
	public static final String NOME_TABELA = "TTTarefa";
	
	public static final class Colunas {
		public static final String _ID = "_id";
		public static final String NOME = "nome";
		public static final String DESCRICAO = "descricao";
		public static final String DATA_ENTREGA = "dataEntrega";
		public static final String DONO = "dono";
		public static final String PRIORIDADE = "prioridade";
		public static final String PROJETO = "idProjeto";
		public static final String CONCLUIDA = "progresso";
		
	}
}
