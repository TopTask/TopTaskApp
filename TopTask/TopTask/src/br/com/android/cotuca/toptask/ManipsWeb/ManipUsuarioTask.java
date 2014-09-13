package br.com.android.cotuca.toptask.ManipsWeb;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;
import br.com.android.cotuca.toptask.BD.ContratoUsuarios;
import br.com.android.cotuca.toptask.Beans.Usuario;

public class ManipUsuarioTask {

	private static final String PONTO_FINAL = "http:\\";
	private static final String NAMESPACE = "";
	
	// Metodos
	public static final String ADD_USU = "cadastrar";
	public static final String EXCLUIR_USU = "excluir";
	public static final String ALTERAR_USU = "alterar";
	public static final String CONSULTAR_USU = "consultar";
	public static final String LISTAR_USU = "listar";
	
	//resultados
	private Usuario[] respUsuarios = null;
	private Integer respAcoes = 1;

	
	public Integer executaAcoes(Usuario usu, String op) {
		
		ServiceUsuAcoes acao = new ServiceUsuAcoes();
		acao.execute(usu,op);
		
		return respAcoes;
	}
	
	public Usuario[] executaConsultas(String email,String op) {
		
		ServiceUsuConsultas consulta = new ServiceUsuConsultas();
		consulta.execute(email,op);
		
		return respUsuarios;
	}
	
	
	
	private class ServiceUsuAcoes extends AsyncTask<Object, Void, Integer> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Integer doInBackground(Object... params) {

			// operacao a ser realizada
			String op = (String) params[0];
			Usuario usu = (Usuario) params[1];

			chamarWebService(usu, op);

			return null;
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			respAcoes = result;
		}

		private Integer chamarWebService(Usuario usu, String op) {
			
			SoapObject soap = new SoapObject(NAMESPACE, op);
			if (op == ADD_USU) {

				// parametros
				soap.addProperty(ContratoUsuarios.Colunas.EMAIL, usu.getEmail());
				soap.addProperty(ContratoUsuarios.Colunas.NOME, usu.getNome());
				soap.addProperty(ContratoUsuarios.Colunas.SENHA, usu.getSenha());

			} else if (op == EXCLUIR_USU) {

				soap.addProperty(ContratoUsuarios.Colunas.EMAIL, usu.getEmail());

			} else if (op == ALTERAR_USU) {

				soap.addProperty(ContratoUsuarios.Colunas.EMAIL, usu.getEmail());
				soap.addProperty(ContratoUsuarios.Colunas.NOME, usu.getNome());
				soap.addProperty(ContratoUsuarios.Colunas.SENHA, usu.getSenha());
				// falta foto

			} else {
				// erro
			}

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(soap);

			HttpTransportSE transport = new HttpTransportSE(PONTO_FINAL);

			try {
				transport.call("", envelope);
				Object response = envelope.getResponse();
				return Integer.valueOf(response.toString());
			} catch (IOException e) {

				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}

			return 1;

		}

	}

	private class ServiceUsuConsultas extends AsyncTask<Object, Void, Usuario[]> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();
		}

		@Override
		protected Usuario[] doInBackground(Object... params) {

			// operacao a ser realizada
			String op = (String) params[0];
			String email  = (String) params[1];

			return chamarWebService(email, op);
		}

		@Override
		protected void onPostExecute(Usuario[] result) {
			super.onPostExecute(result);
			
			respUsuarios = result;
		}

		private Usuario[] chamarWebService(String email, String op) {
			SoapObject soap = new SoapObject(NAMESPACE, op);
			if (op == CONSULTAR_USU) {
				soap.addProperty(ContratoUsuarios.Colunas.EMAIL, email);
			} 

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(soap);

			HttpTransportSE transport = new HttpTransportSE(PONTO_FINAL);

			try {
				transport.call("", envelope);
				Object resposta = envelope.getResponse();
				
				return (Usuario[]) resposta;
			} catch (IOException e) {

				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}

			return null;

		}

	}

}
