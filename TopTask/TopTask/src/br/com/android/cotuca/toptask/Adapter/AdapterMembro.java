package br.com.android.cotuca.toptask.Adapter;

import java.util.List;

import br.com.android.cotuca.toptask.Beans.Membro;
import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterMembro extends BaseAdapter{
	
	private List<Membro> membros;
	private LayoutInflater inflater;
	
	public AdapterMembro(Context contexto, List<Membro> listaMembro){
		inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		membros = listaMembro;
	}

	@Override
	public int getCount() {
		
		return this.membros.size();
	}

	@Override
	public Object getItem(int position) {
		
		return membros.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return membros.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			ViewHolder holder = null;
			
			if (view == null){
				view = this.inflater.inflate(R.layout.adapter_membro, null);
				
				holder = new ViewHolder();
				holder.id = (TextView) view.findViewById(R.id.txt_idMembro);
				holder.nome = (TextView) view.findViewById(R.id.txt_nomeMembro);
				holder.email = (TextView) view.findViewById(R.id.txt_emailMembro);
				holder.permissao = (TextView) view.findViewById(R.id.txt_emailMembro);
				
				view.setTag(holder);
				
			}
			else{
				holder = (ViewHolder) view.getTag();
			}
			
			Membro membro = membros.get(position);
//			UsuarioDAO
			List<Usuario> usuarios = UsuarioDao.getUsuarios(); 
			
			holder.id.setText(membro.getId());
			holder.nome.setText(membro.getIdUsuario()); 
			holder.email.setText(text);
			holder.permissao.setText(text);
		
				
		return view;
	}
	
	private static class ViewHolder {
		TextView id;
		TextView nome;
		TextView email;
		TextView permissao;
	}

}
