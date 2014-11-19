package br.com.android.cotuca.toptask.Beans;

public class BurnDown {
	
	private int id;
	private int idProjeto;
	private int diaAtual;
	private int mesAtual;
	private int anoAtual;
	private int limite; 
	private int feito; 
	
	public BurnDown(int idProjeto, int feito, int limite, int dia, int mes, int ano) {
		super();
		this.setIdProjeto(idProjeto);
		this.setFeito(feito);
		this.setLimite(limite);
		this.setDiaAtual(dia);
		this.setMesAtual(mes);
		this.setAnoAtual(ano);
	}
	
	//construtor para edição e retorno
	public BurnDown(int id, int idProjeto, int feito, int limite, int dia, int mes, int ano) {
		super();
		this.setId(id);
		this.setIdProjeto(idProjeto);
		this.setFeito(feito);
		this.setLimite(limite);
		this.setDiaAtual(dia);
		this.setMesAtual(mes);
		this.setAnoAtual(ano);
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

	public int getDiaAtual() {
		return diaAtual;
	}

	public void setDiaAtual(int diaAtual) {
		this.diaAtual = diaAtual;
	}

	public int getMesAtual() {
		return mesAtual;
	}

	public void setMesAtual(int mesAtual) {
		this.mesAtual = mesAtual;
	}

	public int getAnoAtual() {
		return anoAtual;
	}

	public void setAnoAtual(int anoAtual) {
		this.anoAtual = anoAtual;
	}
	
}
