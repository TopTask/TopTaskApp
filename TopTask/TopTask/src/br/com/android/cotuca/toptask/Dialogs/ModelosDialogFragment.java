package br.com.android.cotuca.toptask.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.Activitys.CadastroProjetoActivity;

public class ModelosDialogFragment extends DialogFragment implements
		DialogInterface.OnClickListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		// setar as configuracoes do dialog
		builder.setTitle("Escolha um modelo de projeto");
		builder.setItems(R.array.nomes_modelos, this);

		return builder.create();

	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// which qual o modelo escolhido
		// 0 -- Scrum
		// 1 -- Livre

		if (which == 0) {
			// chamar a intent que mexe com o modelo scrum
			Toast.makeText(getActivity(), "Modelo de Scrum escolhido", Toast.LENGTH_SHORT).show();

			
		} else {
			if (which == 1) {
				// a principio chamarei a MSimplesActivity, mas depois devemos
				// chamar uma activity pra criar o projeto
				// e depois chamar a MSimplesActivity
				
				Toast.makeText(getActivity(), "Modelo de Livre escolhido", Toast.LENGTH_SHORT).show();
			}
		}
		
		Intent iCadastro = new Intent(getActivity(),
				CadastroProjetoActivity.class);
		startActivity(iCadastro);

	}

}
