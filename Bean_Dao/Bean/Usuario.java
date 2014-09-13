package Bean;

public class Usuario {

    private int id;
    private String nome;
    private String senha;
    private String email;
    private String descricao;
    /**
     * Caminho da foto de perfil.
     */
    private String foto;

    public Usuario(int id, String nome, String senha, String email, String descricao, String foto) {
        this.id        = id;
        this.nome      = nome;
        this.senha     = senha;
        this.email     = email;
        this.descricao = descricao;
        this.foto      = foto;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}
