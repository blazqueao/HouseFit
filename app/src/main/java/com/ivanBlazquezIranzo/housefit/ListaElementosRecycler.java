package com.ivanBlazquezIranzo.housefit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListaElementosRecycler extends RecyclerView.Adapter<ListaElementosRecycler.ViewHolder> {
    private final List<Ejercicio> mData;
    private final LayoutInflater mInflater;
    private View.OnClickListener listener;

    public ListaElementosRecycler(List<Ejercicio> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = itemList;
    }

    @NonNull
    @Override
    public ListaElementosRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams")
        View view = mInflater.inflate(R.layout.lista_elementos_recycler, null);

        //noinspection Convert2Lambda
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!= null) {
                    listener.onClick(v);
                }
            }
        });

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListaElementosRecycler.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, musculo, dificultad;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreEjer);
            musculo = itemView.findViewById(R.id.musculoEjer);
            dificultad = itemView.findViewById(R.id.dificultadEjer);
        }

        @SuppressLint("SetTextI18n")
        void bindData(final Ejercicio item ) {
            nombre.setText(item.getNombre());
            musculo.setText(item.getMusculo());
            dificultad.setText("Dificultad: "+item.getDificultad()+"");
        }
    }

    public void setOnClickListener (View.OnClickListener listener) {
        this.listener = listener;
    }

}
