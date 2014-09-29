package br.com.android.cotuca.toptask.Beans;

/**
 * 
 * @author laistardelli
 * Bean projeto
 *
 */

public class Projeto {

	//========================================
	private int id;
	private String nome;
	private String dataEntrega;
	private String descricao;
	private int dono;
	private int concluida;
	private String foto;
	//========================================
	
	public Projeto(String nome, String descricao, String dataEntrega, int dono, int concluida, String foto) {
		super();
		this.nome = nome;
		this.dataEntrega = dataEntrega;
		this.descricao = descricao;
		this.dono = dono;
		this.concluida = concluida;
		this.foto = foto;
	}
	
	public Projeto(int id,String nome, String descricao, String dataEntrega, int dono, int concluida, String foto) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataEntrega = dataEntrega;
		this.descricao = descricao;
		this.dono = dono;
		this.concluida = concluida;
		this.foto = foto;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getDono() {
		return dono;
	}
	public void setDono(int dono) {
		this.dono = dono;
	}
	public int getConcluida() {
		return concluida;
	}
	public void setConcluida(int concluida) {
		this.concluida = concluida;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
}
