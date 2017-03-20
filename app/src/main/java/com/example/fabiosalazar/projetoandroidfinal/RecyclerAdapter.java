package com.example.fabiosalazar.projetoandroidfinal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabio on 20/03/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    static List<DatabaseModel> dbList;
    static Context context;

    RecyclerAdapter(Context context, List<DatabaseModel> dbList ){
        this.dbList = new ArrayList<DatabaseModel>();
        this.context = context;
        this.dbList = dbList;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.usuario_item, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapter.ViewHolder holder, final int position) {
        holder.name.setText(dbList.get(position).getName());

        holder.edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context, EditarActivity.class);

                Bundle extras = new Bundle();
                extras.putInt("position", holder.getAdapterPosition());
                intent.putExtras(extras);

                context.startActivity(intent);
            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome;
                nome = dbList.get(position).getName();

                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                databaseHelper.Delete(nome);

                dbList.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageButton edit;
        public ImageButton delete;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            name = (TextView) itemLayoutView.findViewById(R.id.tvNome);
            edit = (ImageButton) itemLayoutView.findViewById(R.id.imgEdit);
            delete = (ImageButton) itemLayoutView.findViewById(R.id.imgDelete);

        }
    }
}
