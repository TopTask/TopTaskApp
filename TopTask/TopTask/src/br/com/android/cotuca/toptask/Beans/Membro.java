package br.com.android.cotuca.toptask.Beans;

public class Membro {

	private int id;
	private int idUsuario;
	private int idProjeto;
	private Permissao permissao;
	public static enum Permissao{
		PO,SM,PR
		// Product Owner
		// Scrum Master
		// Programador
	}
	
	public Membro(int id,int idUsuario, int idProjeto,Permissao p){
	    this.id        = id;
	    this.idUsuario = idUsuario;
	    this.idProjeto = idProjeto;
	    this.permissao = p;
	}

	public int getId() {
		return id;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
	
	public void setID(int _id){
		id = _id;
	}
	
	
	
}
