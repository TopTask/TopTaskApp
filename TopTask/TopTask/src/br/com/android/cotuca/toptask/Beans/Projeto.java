package br.com.android.cotuca.toptask.Beans;

import android.media.Image;

public class Projeto {
	
	private int id;
	private int idUsuario; 
	private String nome;      
	private String email;   
	private String descr;     
	private Image foto;
	private String data;
	
	
	public Projeto(int id, int idUsuario, String nome, String email,
			String descr, String data) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.email = email;
		this.descr = descr;
		this.data = data;
	}
	
	public Projeto(int id, int idUsuario, String nome, String email,
			String descr, Image foto, String data) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.email = email;
		this.descr = descr;
		this.foto = foto;
		this.data = data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Image getFoto() {
		return foto;
	}
	public void setFoto(Image foto) {
		this.foto = foto;
	}  
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	

}
