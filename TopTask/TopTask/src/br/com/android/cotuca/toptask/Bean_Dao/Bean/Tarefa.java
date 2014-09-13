package Bean;

import java.util.Date;

public class Tarefa {

	private int       id;
	private String    nome;
	private String    descricao;
	private int       idMembro;
	private int       idProjeto;
        private int       prioridade;
	private Progresso progresso;
        private Date      data;
	public static enum Progresso{
		FAZER,FAZENDO,FEITA;
	}
	/**
	 * Caminho da foto da Tarefa.
	 */
	private String foto;
	
	public Tarefa(int id,String nome,String descricao, int idMembro, int idProjeto, Date data,int prioridade){
		this.id        = id;
		this.nome      = nome;
		this.descricao = descricao;
		this.idMembro  = idMembro;
		this.idProjeto = idProjeto;
                if (data == null)
                  this.data = new Date(0);
                else
                  this.data    = data;
                this.prioridade= prioridade;
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

	public int getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

        public Date getDate() {
                return data;
        }

        public void setDate(Date date) {
                this.data = date;
        }

        public int getPrioridade() {
               return prioridade;
        }

        public void setPrioridade(int prioridade) {
               this.prioridade = prioridade;
        }
        
        
        public Progresso getProgresso() {
		return progresso;
	}

	public void setProgresso(Progresso progresso) {
		this.progresso = progresso;
	}

}
