package com.example.anthony.sublime.Fragments; /**
 * Created by anthony on 15/03/18.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anthony.sublime.R;
import com.example.anthony.sublime.Registrar.user_gs;

import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<user_gs> list;
    Context context;

    public RecyclerViewAdapter(Context context, List<user_gs> list) {
        this.list = list;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_adapter, parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        user_gs user_gs = null;
        holder.name.setText(user_gs.getNome());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nome_user_post);
        }
    }
}