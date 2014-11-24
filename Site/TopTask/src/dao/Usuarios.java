package dao;

import java.sql.ResultSet;

import bd.Conexao;
import bean.Usuario;

public class Usuarios extends IDao{

	public Usuarios(){
		bd     = new Conexao();
		Tabela = "TTUsuario";
	}

	@Override
	public String inserir(Object o) {
            if (o == null)
                throw new IllegalArgumentException("Objeto a ser incluido nulo.");
            
            if (!(o instanceof Usuario))
                throw new IllegalArgumentException("Objeto não é do tipo aceito para inclusão[Requer Usuario]");
            
            Usuario u = (Usuario) o;
            
            try { 
                String sql = "INSERT INTO "+ Tabela +" (nome,senha,email,descricao,foto) VALUES"
                        + " ('"+u.getNome()+"','"+u.getSenha()+"','"+u.getEmail()+"','"+u.getFoto()+"');";
                System.out.println(sql);
                bd.execComando(sql);
                return u.getEmail();
            } catch (Exception ex) {
                System.out.println("Erro ao executar o SQL para inclusão");
                ex.printStackTrace();
            }
            return "";
	}

	@Override
	public void alterar(Object o) {
            if (o == null)
                throw new IllegalArgumentException("Objeto a ser alterado nulo.");
            
            if (!(o instanceof Usuario))
                throw new IllegalArgumentException("Objeto não é do tipo aceito para alteracao[Requer Usuario]");
            
            Usuario u = (Usuario) o;
            
            try {
                bd.execComando("UPDATE " + Tabela + " SET nome='" + u.getNome() + "',senha='" + u.getSenha() + "',"
                             + "email='" + u.getEmail() + "', foto='" + u.getFoto() + "'"
                             + " WHERE id=" + u.getId());
            } catch (Exception ex) {
                System.out.println("Erro ao executar o SQL de alteração.");
                ex.printStackTrace();
            }
	}

	@Override
	public void deletar(Object o) {
            if (o == null)
                throw new IllegalArgumentException("Objeto a ser alterado nulo.");
                
            if (!(o instanceof Usuario))
                throw new IllegalArgumentException("Objeto não é do tipo aceito para alteracao[Requer Usuario]");
                
            Usuario u = null;
            try {
                u = (Usuario) o;
                
                String SQL = "DELETE FROM TTUsuario WHERE id=" + u.getId();
                
                System.out.println(SQL);
                
                bd.execComando(SQL);
                
            } catch (Exception ex) {
                System.out.println("Erro ao deletar o usuário " + u.getNome());
                ex.printStackTrace();
            }
	}

        @Override
        public Object pesquisar(Object key) {
            if (!(key instanceof Integer) || !(key instanceof String))
                throw new IllegalArgumentException("Chave de pesquisa inválida. [Requer Inteiro ou String]");
            
            try {
            	String SQL = "SELECT * FROM " + Tabela + " WHERE ";
            	if (key instanceof Integer)
                    SQL += "id=" + key;
            	else
            		SQL += "email='" + key + "'";
                
                System.out.println(SQL);
                
                ResultSet rs = bd.execConsulta(SQL); // Executa o comando para realizar a pesquisa
                
                if (rs.next()){
                   // RS COMECA NO INDICE 1 
                    
                   Usuario u = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                   return u;
                }
                    
                
            } catch (Exception ex) {
                System.out.println("Erro ao pesquisar usuário com chave = " + key);
                ex.printStackTrace();
            }
                 
            return null; // Não encontrou nenhum usuário com esta chave
        }


    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
        bd.fecharConexao();
    }
	
       
    public static void main(String args[]){
          // TESTE DAO
         
          //UsuariosD usu = new UsuariosD();
          
          //Usuario u = new Usuario(-1, "Bruno Mattoso Gonçalves", "burno", "bruninhomattoso@hotmail.com",
          //                       "Oieeeeeeee GENTEIIII", "null");
          
          //usu.inserir(u);
          
          //u = (Usuario)usu.pesquisar(1);
          
          //usu.deletar(u);
          
          //System.out.println(" U = " + u.getId());
          
          //u.setDescricao("Agora eu sou certinho");
          
          //usu.alterar(u);
    }
    
}
