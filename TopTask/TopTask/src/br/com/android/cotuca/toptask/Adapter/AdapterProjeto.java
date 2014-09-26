package br.com.android.cotuca.toptask.Adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.android.cotuca.toptask.R;
import br.com.android.cotuca.toptask.Beans.Projeto;

public class AdapterProjeto extends BaseAdapter{

	private List<Projeto> projetos = new ArrayList<Projeto>();
	private LayoutInflater inflater;
		
	public AdapterProjeto(Context contexto,List<Projeto> projetos) {
		inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		this.projetos = projetos;
	}
	
	@Override
	public int getCount() {
		return projetos.size();
	}

	@Override
	public Projeto getItem(int position) {
		return projetos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = convertView;
		ViewHolder holder = null;
		
		if (view == null) {
			view = inflater.inflate(R.layout.adapter_projeto,null);
			
			holder = new ViewHolder();
			holder.nomeProjeto = (TextView) view.findViewById(R.id.txt_nomeProjeto);
			holder.descrProjeto = (TextView) view.findViewById(R.id.txt_descricaoProjeto);
			//holder.foto = (ImageView) view.findViewById(R.id.img_projeto);
			holder.dataTermino = (TextView) view.findViewById(R.id.txt_dataTermino);
			
			view.setTag(holder);
		
		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		Projeto projeto = projetos.get(position);
		holder.nomeProjeto.setText(projeto.getNome());
		holder.dataTermino.setText(projeto.getDataEntrega());
		holder.descrProjeto.setText(projeto.getDescricao());
	
		//holder.imgDono.setImageBitmap(); colocar aqui a imagem de acordo com o icone do dono 
		
		return view;
		
	}
	
	private static class ViewHolder {
		TextView nomeProjeto;
		TextView descrProjeto;
		//ImageView foto;
		TextView dataTermino;
		
	}

}
