package br.com.android.cotuca.toptask.Beans;

public class SubTarefa {

	private int       id;
	private String    nome;
	private String    descricao;
	private int       idMembro;
	private int       idTarefa;
	private Progresso progresso;
	public static enum Progresso{
		FAZER,   // 0
		FAZENDO, // 1
		FEITA;   // 2
	}
	/**
	 * Caminho da foto da Tarefa.
	 */
	
	public SubTarefa(int id,String nome,String descricao,int idMembro,int idTarefa){
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.idMembro =  idMembro;
		this.idTarefa = idTarefa;
		this.progresso = Progresso.FAZER;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getIdMembro() {
		return idMembro;
	}
	
	public void setIdMembro(int idMembro) {
		this.idMembro = idMembro;
	}
	
	public int getIdTarefa() {
		return idTarefa;
	}
	
	public void setIdTarefa(int idTarefa) {
		this.idTarefa = idTarefa;
	}
	
	public Progresso getProgresso() {
		return progresso;
	}
	
	public void setProgresso(Progresso progresso) {
		this.progresso = progresso;
	}
	
		
	
}
