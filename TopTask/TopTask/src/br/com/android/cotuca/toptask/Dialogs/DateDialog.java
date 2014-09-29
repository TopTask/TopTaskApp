package br.com.android.cotuca.toptask.Dialogs;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	private SetDateListener listener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		if (!(activity instanceof SetDateListener)) {
			Log.e("DateDialog", "N�o implementa SetDateListener");
			throw new RuntimeException("Activity não implementa a interface SetDateListener");
		} else {
			listener = (SetDateListener) activity;
		}
		
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Calendar c = Calendar.getInstance();
		
		int ano = c.get(Calendar.YEAR);
		int mes = c.get(Calendar.MONTH);
		int dia = c.get(Calendar.DAY_OF_MONTH);
		return new DatePickerDialog(getActivity(), this,ano,mes,dia);
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		listener.onSet(year, monthOfYear, dayOfMonth);
	}
	
	
	 public interface SetDateListener {
		public void onSet(int ano,int mes, int dia);
	}
}
