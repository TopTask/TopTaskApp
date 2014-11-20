package bean;

public class Usuario {

	// ========================================
	private int id;
	private String nome;
	private String email;
	private String senha;
	private String foto;

	
	// Vari�veis Retiradas
	 	// Descri��o
	// ========================================

	public Usuario() {
		this.id = -1;
		this.nome = "";
		this.email = "";
		this.senha = "";
		this.foto = "";
	}

	public Usuario(int id, String nome, String email, String senha, String foto) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.foto = foto;
	}

	public Usuario(String nome, String email, String senha, String foto) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}
