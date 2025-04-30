package com.example.ppe;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CoiffeurAdapter extends RecyclerView.Adapter<CoiffeurAdapter.CoiffeurViewHolder> {
    private List<Coiffeur> coiffeurs;
    private int selectedPosition = -1;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Coiffeur coiffeur);
    }

    public CoiffeurAdapter(List<Coiffeur> coiffeurs) {
        this.coiffeurs = coiffeurs;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
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
        holder.bind(coiffeur);
        holder.itemView.setBackgroundColor(position == selectedPosition ? Color.LTGRAY : Color.TRANSPARENT);

        holder.itemView.setOnClickListener(v -> {
            int previousSelectedPosition = selectedPosition;
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(previousSelectedPosition);
            notifyItemChanged(selectedPosition);
            if (listener != null && selectedPosition != RecyclerView.NO_POSITION) {
                listener.onItemClick(coiffeurs.get(selectedPosition));
            }
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

        public CoiffeurViewHolder(@NonNull View itemView) {
            super(itemView);
            nomPrenom = itemView.findViewById(R.id.tv_nom_prenom);
        }

        public void bind(Coiffeur coiffeur) {
            nomPrenom.setText(coiffeur.getPrenom() + " " + coiffeur.getNom());
        }
    }
}