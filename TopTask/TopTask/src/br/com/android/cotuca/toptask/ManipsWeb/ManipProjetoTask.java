package br.com.android.cotuca.toptask.ManipsWeb;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;
import br.com.android.cotuca.toptask.BD.ContratoProjetos;
import br.com.android.cotuca.toptask.Beans.Projeto;

public class ManipProjetoTask {
	private static final String PONTO_FINAL = "http://54.94.188.147/SpringTutorialService/CurrencyServiceWS.asmx";
	private static final String NAMESPACE = "http://zbra.com.br/springtutorial";

	// metodos
	static final String ADD_PROJETO = "inserir";
	static final String EXCLUIR_PROJETO = "excluir";
	static final String ALTERAR_PROJETO = "alterar";
	static final String CONSULTAR_PROJETO = "consultar";

	public Projeto executarAcao(Integer id) {

		return null;
	}

	public Projeto consultarProjeto(Integer id) throws InterruptedException,
			ExecutionException {
		// Realizando chamada do WebService
		ChamarConsulta request = new ChamarConsulta();
		Projeto p = request.execute(id).get();
		return p;
	}

	private class ChamarConsulta extends AsyncTask<Object, Void, Projeto> {

		@Override
		protected Projeto doInBackground(Object... params) {
			// Para passar de parametro na chamada do WS
			int id = ((Integer) params[0]).intValue();

			try {
				Object result = chamaWSConsulta(id);
				// Verificando se a operacao foi sucedida, e retornou um
				// <Projeto>
				if (result instanceof Projeto) {
					return (Projeto) result;
				}

			} catch (IOException e) {
				e.printStackTrace();

			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}

			return null;
		}

		private Object chamaWSConsulta(int id) throws HttpResponseException,
				IOException, XmlPullParserException {
			
			SoapObject soap = new SoapObject(NAMESPACE, CONSULTAR_PROJETO);
			soap.addProperty("id", id);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapSerializationEnvelope.VER11);
			envelope.addTemplate(soap);

			HttpTransportSE transporte = new HttpTransportSE(PONTO_FINAL);
			transporte.call("", envelope);

			return envelope.getResponse();

		}

	}
	
//Adicionar, Alterar e Excluir. Se diferenciam na hora de chamar o nome
//do metodo
	public Integer adicionarProjeto(Projeto p) {
		ChamaWSAcoes request = new ChamaWSAcoes();
		request.execute(p, ADD_PROJETO);
		return null;
	}

	public Integer alterarProjeto(Projeto p) {
		ChamaWSAcoes request = new ChamaWSAcoes();
		request.execute(p, ALTERAR_PROJETO);
		return null;
	}

	public Integer excluirProjeto(Projeto p) {
		ChamaWSAcoes request = new ChamaWSAcoes();
		request.execute(p, EXCLUIR_PROJETO);
		return null;
	}

	
	private class ChamaWSAcoes extends AsyncTask<Object, Void, Integer> {

		@Override
		protected Integer doInBackground(Object... params) {
			Projeto p = (Projeto) params[0];
			String operacao = (String) params[1];

			chamarWebService(p, operacao);

			return null;
		}

	}

	@SuppressWarnings("finally")
	private Integer chamarWebService(Projeto projeto, String op) {
		SoapObject soap = new SoapObject(NAMESPACE, op);
		if (op == EXCLUIR_PROJETO) {
			soap.addProperty(ContratoProjetos.Colunas._ID, projeto.getId());
		} else {
			// adicionar ou alterar
			soap.addProperty(ContratoProjetos.Colunas.NOME, projeto.getNome());
			soap.addProperty(ContratoProjetos.Colunas.DESCRICAO, projeto.getDescricao());
			soap.addProperty(ContratoProjetos.Colunas.DATA_ENTREGA,	projeto.getDataEntrega());
			soap.addProperty(ContratoProjetos.Colunas.DONO, projeto.getDono());
			soap.addProperty(ContratoProjetos.Colunas.CONCLUIDA, projeto.getConcluida());
			soap.addProperty(ContratoProjetos.Colunas.FOTO, projeto.getFoto());

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);

			envelope.setOutputSoapObject(soap);

			try {
				HttpTransportSE transport = new HttpTransportSE(PONTO_FINAL);
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
