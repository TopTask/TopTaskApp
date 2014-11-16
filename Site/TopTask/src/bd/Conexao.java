package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * "jdbc:sqlserver://"+SERVIDOR+":1433;" + "databaseName="+BANCO
 *  Exemplo de string connection
 * 
 */

public class Conexao {

	public static final String SERVIDOR   = "localhost"; // regulus
	public static final String USUARIO    = "root";
	public static final String SENHA      = "BD12161";
	public static final String BANCO      = "toptask";
	public static final String DRV        = "com.mysql.jdbc.Driver";
	public static final String PORTA      = "-1";
	private final String CABECALHO        = "jdbc:mysql://"; // "jdbc:sqlserver://"
	
	private Connection cnx;
	private Statement cmd;
	
	public Conexao(){
		
     		try {
				Class.forName(DRV);
			} catch (ClassNotFoundException e1) {
                e1.printStackTrace();
			}
			
			try
	        {
	            cnx = DriverManager.getConnection (CABECALHO+SERVIDOR+"/"+BANCO, USUARIO, SENHA);
	        }
	        catch (SQLException e)
	        {
	             e.printStackTrace();
	        }

	        try
	        {
	            cmd = cnx.createStatement
	                     (ResultSet.TYPE_SCROLL_INSENSITIVE,
	                      ResultSet.CONCUR_READ_ONLY);
	        }
	        catch (SQLException e)
	        {
	            System.err.println("Falha na cria��o do statement");
	        }
	}
	
	  public void execComando (String cmdSQL) throws Exception{
	        try
	        {
	            this.cmd.executeUpdate (cmdSQL);
	        }
	        catch (SQLException e)
	        {
	        	e.printStackTrace();
	        	throw new Exception(e);
	        }
	    }
	
	 public ResultSet execConsulta (String qrySQL) throws Exception
	    {
	        ResultSet resultado = null;

	        try
	        {
	            resultado = this.cmd.executeQuery (qrySQL);
	        }
	        catch (SQLException e)
	        {
	           e.printStackTrace();
	        }

	        return resultado;
	    }

	    public void fecharConexao () throws Exception
	    {
	        try
	        {
	            this.cmd.close ();
	            this.cmd = null;

	            this.cnx.close ();
	            this.cnx = null;
	        }
	        catch (SQLException e)
	        {
	            throw new Exception ("fechamento");
	        }
	    }
}
