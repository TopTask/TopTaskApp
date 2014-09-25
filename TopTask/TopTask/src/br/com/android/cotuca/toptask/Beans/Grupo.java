package br.com.android.cotuca.toptask.Beans;

public class Grupo {

	private int id;
	private int idTarefa;
	private int idMembro;
	
	public Grupo(int id,int idTarefa,int idMembro){
		this.id       = id;
		this.idTarefa = idTarefa;
		this.idMembro = idMembro;
	}

	public int getId() {
		return id;
	}

	public int getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(int idTarefa) {
		this.idTarefa = idTarefa;
	}

	public int getIdMembro() {
		return idMembro;
	}

	public void setIdMembro(int idMembro) {
		this.idMembro = idMembro;
	}
	
}
