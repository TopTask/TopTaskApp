package br.com.android.cotuca.toptask.ManipsWeb;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;
import br.com.android.cotuca.toptask.BD.ContratoTarefas;
import br.com.android.cotuca.toptask.Beans.Tarefa;

public class ManipTarefaTask {
	
	//resultados
	private Tarefa[] respTarefas = null;
	private Integer respAcoes = 1;

	
	public Integer executaAcoes(Tarefa t, String op) {
		
		ServiceTarefaAcoes acao = new ServiceTarefaAcoes();
		acao.execute(t,op);
		
		return respAcoes;
	}
	
	public Tarefa[] executaConsultas(Integer id,String op) {
		
		ServiceTarefaConsultas consulta = new ServiceTarefaConsultas();
		consulta.execute(id,op);
		
		return respTarefas;
	}
	
	private class ServiceTarefaAcoes extends AsyncTask<Object, Void, Integer> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Integer doInBackground(Object... params) {

			// operacao a ser realizada
			Tarefa tarefa = (Tarefa) params[0];
			String op = (String) params[1];
			
			return chamarWebService(tarefa, op);
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			respAcoes = result;
		}

		private Integer chamarWebService(Tarefa tarefa, String op) {
			SoapObject soap = new SoapObject(DadosWS.NAMESPACE, op);
			if (op == DadosWS.ADICIONAR) {
				// parametros
				soap.addProperty(ContratoTarefas.Colunas.NOME, tarefa.getNome());
				soap.addProperty(ContratoTarefas.Colunas.DESCRICAO, tarefa.getDescricao());
				soap.addProperty(ContratoTarefas.Colunas.DATA_ENTREGA, tarefa.getDataEntrega());
				soap.addProperty(ContratoTarefas.Colunas.DONO, tarefa.getDono());
				soap.addProperty(ContratoTarefas.Colunas.PRIORIDADE, tarefa.getPrioridade());
				soap.addProperty(ContratoTarefas.Colunas.PROJETO, tarefa.getIdProjeto());
				soap.addProperty(ContratoTarefas.Colunas.CONCLUIDA, tarefa.getConcluida());
				//soap.addProperty(ContratoTarefas.Colunas.FOTO, tarefa.getFoto());
				
			} else if (op == DadosWS.EXCLUIR) {

				soap.addProperty(ContratoTarefas.Colunas._ID, tarefa.getID());

			} else if (op == DadosWS.ALTERAR) {

				soap.addProperty(ContratoTarefas.Colunas.NOME, tarefa.getNome());
				soap.addProperty(ContratoTarefas.Colunas.DESCRICAO, tarefa.getDescricao());
				soap.addProperty(ContratoTarefas.Colunas.DATA_ENTREGA, tarefa.getDataEntrega());
				soap.addProperty(ContratoTarefas.Colunas.DONO, tarefa.getDono());
				soap.addProperty(ContratoTarefas.Colunas.PRIORIDADE, tarefa.getPrioridade());
				soap.addProperty(ContratoTarefas.Colunas.PROJETO, tarefa.getIdProjeto());
				soap.addProperty(ContratoTarefas.Colunas.CONCLUIDA, tarefa.getConcluida());
				//soap.addProperty(ContratoTarefas.Colunas.FOTO, tarefa.getFoto());

			} else {
				
			}

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(soap);

			try {
				HttpTransportSE transport = new HttpTransportSE(DadosWS.END_POINT);
				transport.call("" +op, envelope);
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

	private class ServiceTarefaConsultas extends AsyncTask<Object, Void, Tarefa[]> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();
		}

		@Override
		protected Tarefa[] doInBackground(Object... params) {

			// operacao a ser realizada
			Integer id  = (Integer) params[0];
			String op = (String) params[1];

			return chamarWebService(id, op);
		}

		@Override
		protected void onPostExecute(Tarefa[] result) {
			super.onPostExecute(result);
			
			respTarefas = result;
		}

		private Tarefa[] chamarWebService(Integer id, String op) {
			SoapObject soap = new SoapObject(DadosWS.NAMESPACE, op);
			if (op == DadosWS.CONSULTAR) {
				soap.addProperty(ContratoTarefas.Colunas._ID, id);
			} 

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(soap);

			HttpTransportSE transport = new HttpTransportSE(DadosWS.END_POINT);

			try {
				transport.call(DadosWS.NAMESPACE +"/" +op, envelope);
				Object resposta = envelope.getResponse();
				return (Tarefa[]) resposta;
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}

			return null;

		}

	}

}

