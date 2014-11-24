package br.com.android.cotuca.toptask.ManipsWeb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;
import br.com.android.cotuca.toptask.BD.ContratoUsuarios;
import br.com.android.cotuca.toptask.Beans.Usuario;

//UNIFICAR PARA TODOS
public class ManipUsuarioTask {

	static final String END_POINT = DadosWS.END_POINT + "ProjetoWS";

	@SuppressWarnings("unchecked")
	public ArrayList<Usuario> consultarProjeto(String aPesquisar) throws InterruptedException, ExecutionException {
		// Realizando chamada do WebService
		ChamarConsulta request = new ChamarConsulta();
		
		ArrayList<Usuario> p;
		p = (ArrayList<Usuario>) request.execute(aPesquisar).get();
		
		return p;
	}

	private class ChamarConsulta extends AsyncTask<Object, Void, ArrayList<?>> {

		@Override
		protected ArrayList<Usuario> doInBackground(Object... params) {
			// Para passar de parametro na chamada do WS
			// param[1] = parte do nome para pesquisar (email ou nome)
			String aPesquisar = (String) params[1];

			try {
				Object result = chamaWSConsulta(aPesquisar);
				return (ArrayList<Usuario>) result;

			} catch (IOException e) {
				e.printStackTrace();

			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}

			return null;
		}

		private Object chamaWSConsulta(String aPesquisar)
				throws HttpResponseException, IOException,
				XmlPullParserException {
			// presquisar no campo email e nome
			SoapObject soap = new SoapObject(DadosWS.NAMESPACE,
					DadosWS.CONSULTAR);
			soap.addProperty("stringPesquisa", aPesquisar);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapSerializationEnvelope.VER11);
			envelope.addTemplate(soap);

			HttpTransportSE transporte = new HttpTransportSE(END_POINT);
			// Acho que ta errado
			transporte.call(DadosWS.NAMESPACE + "/" + DadosWS.CONSULTAR,
					envelope);

			return envelope.getResponse();

		}
	}

	// Adicionar, Alterar e Excluir. Se diferenciam na hora de chamar o nome
	// do metodo
	public Integer adicionarProjeto(Usuario u) {
		ChamaWSAcoes request = new ChamaWSAcoes();
		request.execute(u, DadosWS.ADICIONAR);
		return null;
	}

	public Integer alterarProjeto(Usuario u) {
		ChamaWSAcoes request = new ChamaWSAcoes();
		request.execute(u, DadosWS.ALTERAR);
		return null;
	}

	public Integer excluirProjeto(Usuario u) {
		ChamaWSAcoes request = new ChamaWSAcoes();
		request.execute(u, DadosWS.EXCLUIR);
		return null;
	}

	private class ChamaWSAcoes extends AsyncTask<Object, Void, Integer> {

		@Override
		protected Integer doInBackground(Object... params) {
			Usuario u = (Usuario) params[0];
			String operacao = (String) params[1];
				
			//1 se sucedido, 0 se houve falha.
			return chamarWebService(u, operacao);
		}

	}

	@SuppressWarnings("finally")
	private Integer chamarWebService(Usuario usuario, String op) {
		SoapObject soap = new SoapObject(DadosWS.NAMESPACE, op);
		
		if (op == DadosWS.EXCLUIR) {
			soap.addProperty(ContratoUsuarios.Colunas._ID, usuario.getId());
		} else {
			// adicionar ou alterar
			soap.addProperty(ContratoUsuarios.Colunas.NOME,   usuario.getNome());
			soap.addProperty(ContratoUsuarios.Colunas.EMAIL, usuario.getEmail());
			soap.addProperty(ContratoUsuarios.Colunas.SENHA, usuario.getSenha());
			soap.addProperty(ContratoUsuarios.Colunas.FOTO,   usuario.getFoto());

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

			envelope.setOutputSoapObject(soap);

			try {
				HttpTransportSE transport = new HttpTransportSE(END_POINT);
				transport.call(op, envelope);
				Object response = envelope.getResponse();
				
				return Integer.valueOf(response.toString());

			} catch (IOException e) {
				e.printStackTrace();

			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} finally {
				return 0;
			}

		}
		return null;
	}
}
