package br.com.android.cotuca.toptask.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BurnDownReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Atualiza Burn Down foi chamado", Toast.LENGTH_LONG).show();
		
	}
}
