package br.com.android.cotuca.toptask.Adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.Beans.Tarefa;
//import android.provider.SyncStateContract.Constants;
//import android.sax.StartElementListener;

public class AdapterTarefa extends BaseAdapter{

	private List<Tarefa> tarefas;
	private LayoutInflater inflater;
	
	public AdapterTarefa (Context contexto, List<Tarefa> tarefas) {
		inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		this.tarefas = tarefas;
	}
	
	@Override
	public int getCount() {
		return tarefas.size();
	}

	@Override
	public Tarefa getItem(int position) {
		
		return tarefas.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return tarefas.get(position).getID();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder = null;
		
		if (view == null) {
			view = inflater.inflate(R.layout.adapter_tarefa,null);
			
			holder = new ViewHolder();
			holder.nomeTarefa = (TextView) view.findViewById(R.id.txt_nomeTarefa);
			holder.discricaoTarefa = (TextView) view.findViewById(R.id.txt_descricaoTarefa);
			//holder.imgDono = (ImageView) view.findViewById(R.id.img_donoTarefa);
			holder.dataTermino = (TextView) view.findViewById(R.id.txt_dataTermino);
			
			view.setTag(holder);
		
		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		Tarefa tarefa = tarefas.get(position);
		holder.nomeTarefa.setText(tarefa.getNome());
		holder.dataTermino.setText(tarefa.getDataEntrega());
		holder.discricaoTarefa.setText(tarefa.getDescricao());
	
		//holder.imgDono.setImageBitmap(); colocar aqui a imagem de acordo com o icone do dono 
		
		return view;
	}
	
	private static class ViewHolder {
		TextView  nomeTarefa;
		TextView  discricaoTarefa;
		//ImageView imgDono;
		TextView  dataTermino;
	}

	
}
