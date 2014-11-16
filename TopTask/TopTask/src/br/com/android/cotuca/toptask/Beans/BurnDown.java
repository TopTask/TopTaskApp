package br.com.android.cotuca.toptask.Beans;

public class BurnDown {
	
	private int id;
	private int idProjeto;
	private String data;
	private int limite; 
	private int feito; 
	
	public BurnDown(int idProjeto, int feito, int limite, String data) {
		super();
		this.setIdProjeto(idProjeto);
		this.setFeito(feito);
		this.setLimite(limite);
		this.setData(data);
	}
	
	//construtor para edição e retorno
	public BurnDown(int id, int idProjeto, int feito, int limite, String data) {
		super();
		this.setId(id);
		this.setIdProjeto(idProjeto);
		this.setFeito(feito);
		this.setLimite(limite);
		this.setData(data);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public int getFeito() {
		return feito;
	}

	public void setFeito(int feito) {
		this.feito = feito;
	}
	
}
