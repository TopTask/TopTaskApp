/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Bean.Tarefa;
import Conexao.BD;
//import java.sql.Date;
import java.util.Date;

/**
 *
 * @author u12161
 */
public class TarefasD extends IDao{

    public TarefasD(){
        this.bd     = new BD();
        this.Tabela = "TTTarefa";
    }
    
    @Override
    public void inserir(Object o) {
        if (o == null)
                throw new IllegalArgumentException("Objeto a ser incluido nulo.");
            
            if (!(o instanceof Tarefa))
                throw new IllegalArgumentException("Objeto não é do tipo aceito para inclusão[Requer Usuario]");
            
            Tarefa t = (Tarefa) o;
            /*
            
            id         INTEGER PRIMARY KEY,
  nome       VARCHAR(50)  NOT NULL,
  descr      VARCHAR(100) NOT NULL,
  entrega    DATE         NOT NULL,
  idUsuario  INTEGER      NOT nULL,
  idProjeto  INTEGER      NOT NULL,
  prioridade INTEGER      NOT NULL,
  progresso  INTEGER      NOT NULL,
  foto 
            
            */
            try { 
                String data = t.getDate().getYear() + "-" + t.getDate().getMonth() + "-" + t.getDate().getYear();
                String sql = "INSERT INTO "+ Tabela +" (nome,descricao,entrega,idUsuario,idProjeto,prioridade,progresso,foto) VALUES"
                        + " ('" + t.getNome() + "','" + t.getDescricao() + "','" + data + "'," + t.getIdMembro()+ ","
                        + t.getIdProjeto() + "," + t.getPrioridade() + ",'" + t.getProgresso() + "','" + t.getFoto() + "');";
                System.out.println(sql);
                bd.execComando(sql);
            } catch (Exception ex) {
                System.out.println("Erro ao executar o SQL para inclusão");
                ex.printStackTrace();
            }
    }

    @Override
    public void alterar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object pesquisar(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String args[]){
    }
    
}
