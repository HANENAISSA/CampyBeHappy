package com.example.campybehappy.Controller.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campybehappy.Model.Camp;
import com.example.campybehappy.R;

import java.util.List;

public class ListCampFavoriteAdapter extends RecyclerView.Adapter<ListCampFavoriteAdapter.FavCampViewHolder> {

    List<Camp> listeCamp;
    Context context;
    private OnCampListener mOnCampListener;

    public ListCampFavoriteAdapter(List<Camp> listeCamp, Context context, OnCampListener mOnCampListener ) {
        this.listeCamp = listeCamp;
        this.context = context;
        this.mOnCampListener = mOnCampListener;
    }

    @NonNull
    @Override
    public FavCampViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vue= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_fav_camp_item,parent,false);
        FavCampViewHolder PVH=new FavCampViewHolder(vue, mOnCampListener);
        return PVH;
    }

    @Override
    public void onBindViewHolder(@NonNull FavCampViewHolder holder, int position) {
        Camp camp =listeCamp.get(position);
        holder.nom.setText(camp.getLibelle());
        holder.quantite.setText(camp.getQuantité());
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Libelle: "+listeCamp.get(position).getLibelle()+ "\n quantite :" + listeCamp.get(position).getQuantité(), Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return listeCamp.size();
    }




    public static class FavCampViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nom;
        TextView quantite;
        OnCampListener onCampListener;

        public FavCampViewHolder(@NonNull View itemView, OnCampListener onCampListener) {
            super(itemView);
            nom=itemView.findViewById(R.id.lib);
            quantite=itemView.findViewById(R.id.sous_nom);

            this.onCampListener = onCampListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onCampListener.onNoteClick(getAdapterPosition());
        }
    }
    public interface OnCampListener
    {
        void onNoteClick(int position);
    }
}
