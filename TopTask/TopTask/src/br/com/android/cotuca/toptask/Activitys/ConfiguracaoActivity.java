package br.com.android.cotuca.toptask.Activitys;

import br.com.android.cotuca.toptask.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ConfiguracaoActivity extends PreferenceActivity{

	private Bundle contexto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.contexto = savedInstanceState;
		
		addPreferencesFromResource(R.layout.activity_configuracao);	
	}
	
}
