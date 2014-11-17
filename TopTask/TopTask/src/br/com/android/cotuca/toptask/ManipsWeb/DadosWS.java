package br.com.android.cotuca.toptask.ManipsWeb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import br.com.android.cotuca.toptask.Beans.Projeto;
import br.com.android.cotuca.toptask.Beans.Tarefa;
import br.com.android.cotuca.toptask.Beans.Usuario;

import android.os.AsyncTask;

public class DadosWS {
	// UsuarioWS
	public static final String END_POINT = "http://54.94.188.147:8080/TopTaskWS/services/";//completar
	public static final String NAMESPACE = "http://ws";
	public static final String ADICIONAR = "adicionar";
	public static final String EXCLUIR = "deletar";
	public static final String ALTERAR = "alterar";
	public static final String CONSULTAR = "consultar";

}
