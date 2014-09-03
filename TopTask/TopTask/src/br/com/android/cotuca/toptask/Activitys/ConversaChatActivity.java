package br.com.android.cotuca.toptask.Activitys;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.com.android.cotuca.toptask.R;

public class ConversaChatActivity extends Activity {

	private Socket cliente;
	private PrintWriter pw;
	private EditText txtMsg;
	private Button btnEnvio;
	private String mensagem;

	@Override
	protected void onCreate(Bundle instanciaSalva) {
		super.onCreate(instanciaSalva);
		setContentView(R.layout.activity_chat_conversa);

		txtMsg = (EditText) findViewById(R.id.edt_msg);
		btnEnvio = (Button) findViewById(R.id.btn_enviar);

		/*btnEnvio.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				mensagem = txtMsg.getText().toString();
				txtMsg.setText("");
				Enviar e = new Enviar();
				e.execute();
			}
		});*/
	}

	public void onClickEnviar (View v) {
		mensagem = txtMsg.getText().toString();
		txtMsg.setText("");
		Enviar e = new Enviar();
		e.execute();
	}
	
	private class Enviar extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				cliente = new Socket("IP BRUNIN", 1111);
				pw = new PrintWriter(cliente.getOutputStream(), true);
				pw.write(mensagem);
				pw.flush();
				pw.close();
				cliente.close();

			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

	}
}