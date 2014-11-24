package br.com.android.cotuca.toptask.Activitys;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import br.com.android.cotuca.toptask.R;

public class EntradaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entrada);
		getActionBar().hide();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.entrada, menu);
		
		return true;
	}
	
	public void clickEntrar(View v) {
		Intent iLogin = new Intent (this, LoginUsuarioActivity.class);
		startActivity(iLogin);
	}
	
	public void clickCadastrar(View v) {
		Intent iCadastro = new Intent (this, CadastroUsuarioActivity.class);
		startActivity(iCadastro);
	}

}
