package br.com.android.cotuca.toptask.Beans;



/**
 * 
 * @author jvgengo
 * Bean de tarefa
 *
 */

public class Tarefa {
	
//========================================
	private String nome;
	private String descricao;
	private String DataEntrega;
	private int dono;
	private int prioridade;
	private int idProjeto;
	private int id;
	private int concluida;
	private int tempoLimite;
	private int tempoFeito;
//========================================
	
	//criação
	public Tarefa(String nome, String descricao, int dono,String dataEntrega, int tempoLimite,
			int prioridade, int idProjeto, int concluida) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.DataEntrega = dataEntrega;
		this.tempoLimite = tempoLimite;
		this.dono = dono;
		this.prioridade = prioridade;
		this.idProjeto = idProjeto;
		this.concluida = concluida;
		this.tempoFeito = 0;
	}
	
	//edição
	public Tarefa(int id,String nome, String descricao, int dono,String dataEntrega, int tempoLimite, int tempoFeito,
			int prioridade, int idProjeto, int conluida) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.DataEntrega = dataEntrega;
		this.tempoLimite = tempoLimite;
		this.tempoFeito = tempoFeito;
		this.dono = dono;
		this.prioridade = prioridade;
		this.idProjeto = idProjeto;
		this.concluida = conluida;
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
	public String getDataEntrega() {
		return DataEntrega;
	}
	public void setDataEntrega(String dataEntrega) {
		DataEntrega = dataEntrega;
	}
	public int getDono() {
		return dono;
	}
	public void setDono(int dono) {
		this.dono = dono;
	}
	public int getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	
	public int getConcluida() {
		return concluida;
	}
	public void setConcluida(int c) {
		this.concluida = c;
	}
	
	public int getIdProjeto() {
		return idProjeto;
	}
	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}
	public int getTempoLimite() {
		return tempoLimite;
	}
	public void setTempoLimite(int tempoLimite) {
		this.tempoLimite = tempoLimite;
	}
	public int getTempoFeito() {
		return tempoFeito;
	}
	public void setTempoFeito(int tempoFeito) {
		this.tempoFeito = tempoFeito;
	}
}
