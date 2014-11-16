package br.com.android.cotuca.toptask.ManipsWeb;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.HttpsTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import br.com.android.cotuca.toptask.Beans.Projeto;
import android.os.AsyncTask;

public class ManipProjetoTask {
	private static final String PONTO_FINAL = "http://54.94.188.147/SpringTutorialService/CurrencyServiceWS.asmx";
	private static final String NAMESPACE = "http://zbra.com.br/springtutorial";

	//metodos
	static final String ADD_PROJETO = "inserir";
	static final String EXCLUIR_PROJETO = "excluir";
	static final String ALETRAR_PROJETO = "alterar";
	static final String CONSULTAR_PROJETO = "consultar";
	
	public Projeto executarAcao(Integer id){
		
		return null;
	}
	
	public Projeto consultarProjeto(Integer id) throws InterruptedException, ExecutionException{
		ChamarConsulta request = new ChamarConsulta();
		Projeto p = request.execute(id).get();
		return p;
	}
	
	
	private class ChamarConsulta extends AsyncTask<Object, Void, Projeto> {

		@Override
		protected Projeto doInBackground(Object... params) {
			
			int id = ((Integer)params[0]).intValue();
			try {
				Object result = chamaWSConsulta(id);
				if (result instanceof Projeto){
					return (Projeto)result;
				}
			} catch (IOException e) {
				e.printStackTrace();
				
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}			
			
			return null;
		}

		private Object chamaWSConsulta(int id) throws HttpResponseException, IOException, XmlPullParserException {
			SoapObject soap = new SoapObject(NAMESPACE, CONSULTAR_PROJETO);
			soap.addProperty("id", id);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
			envelope.addTemplate(soap);
			
			HttpTransportSE transporte = new HttpTransportSE(PONTO_FINAL);
			transporte.call("", envelope);
			
			return envelope.getResponse();
			
		}
		
	}
	
}

