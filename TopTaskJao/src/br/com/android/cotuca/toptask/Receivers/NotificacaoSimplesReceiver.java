package br.com.android.cotuca.toptask.Receivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import br.com.android.cotuca.toptask.Activitys.MSimplesActivity;
import br.com.android.cotuca.toptask.tags.Tags;

/**
 * 
 * @author jvgengo
 * Classe que mandara uma notificao de uma tarefa quando seu alerme for ativado
 *
 */
public class NotificacaoSimplesReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(Tags.TOPTASK_TAG,"ENTROU NO onReceive");
		
		// pega os dados que para enviar a notificao
		Bundle dados = intent.getExtras();
		String titulo = dados.getString("titulo");
		String descricao = dados.getString("descricao");
		
		//envia notificao
		notificar(context,titulo, descricao);
	}
	
	/**
	 * Envia a notificao
	 * 
	 * @param titulo nome da notificao
	 * @param descricao o que essa notificao significa
	 * @param context contexto da aplicacao
	 */
	public void notificar(Context context, String titulo, String descricao) {
		
		Log.d(Tags.TOPTASK_TAG,"ENTROU NO NOTIFICAR");
		//o que contera a notificao 
		Notification.Builder builder = new Notification.Builder(context);
		builder.setContentTitle("Sua tarefa termina hoje!");
		builder.setContentText(titulo);
		builder.setSmallIcon(android.R.drawable.ic_dialog_info);
		
		//preparando para enviar
		Intent i = new Intent(context, MSimplesActivity.class);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
		builder.setContentIntent(pi);
		builder.setAutoCancel(true);
		
		//cria a notificao
		Notification n = builder.build();
	
		//objeto para enviar a notificao
		NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
		
		//envia a notificao
		manager.notify(5128,n);
		Log.d(Tags.TOPTASK_TAG,"NOTIFICOU");

		//Toast.makeText(context, "Notificacao enviada", Toast.LENGTH_LONG).show();
		
	}

}
