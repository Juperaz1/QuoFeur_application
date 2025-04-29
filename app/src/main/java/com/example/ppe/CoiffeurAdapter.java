package com.example.ppe;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CoiffeurAdapter extends RecyclerView.Adapter<CoiffeurAdapter.CoiffeurViewHolder> {
    private List<Coiffeur> coiffeurs;
    private int selectedPosition = -1;

    public CoiffeurAdapter(List<Coiffeur> coiffeurs) {
        this.coiffeurs = coiffeurs;
    }

    @NonNull
    @Override
    public CoiffeurViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_coiffeur, parent, false);
        return new CoiffeurViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoiffeurViewHolder holder, int position) {
        Coiffeur coiffeur = coiffeurs.get(position);
        holder.bind(coiffeur, position == selectedPosition);

        holder.itemView.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return coiffeurs.size();
    }

    public Coiffeur getSelectedCoiffeur() {
        if (selectedPosition != -1) {
            return coiffeurs.get(selectedPosition);
        }
        return null;
    }

    static class CoiffeurViewHolder extends RecyclerView.ViewHolder {
        private TextView nomPrenom;
        private View itemView;

        public CoiffeurViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            nomPrenom = itemView.findViewById(R.id.tv_nom_prenom);
        }

        public void bind(Coiffeur coiffeur, boolean isSelected) {
            nomPrenom.setText(coiffeur.getPrenom() + " " + coiffeur.getNom());

            itemView.setBackgroundColor(isSelected ?
                    Color.LTGRAY : Color.TRANSPARENT);
        }
    }
}