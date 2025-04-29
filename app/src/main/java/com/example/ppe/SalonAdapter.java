package com.example.ppe;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SalonAdapter extends RecyclerView.Adapter<SalonAdapter.SalonViewHolder> {
    private List<Salon> salonList;
    private OnSalonClickListener listener;
    private int selectedPosition = -1;

    public interface OnSalonClickListener {
        void onSalonClick(int position);
    }

    public void setOnSalonClickListener(OnSalonClickListener listener) {
        this.listener = listener;
    }

    public SalonAdapter(List<Salon> salonList) {
        this.salonList = salonList;
    }

    @Override
    public SalonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_salon, parent, false);
        return new SalonViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(SalonViewHolder holder, int position) {
        Salon salon = salonList.get(position);
        holder.nameTextView.setText(salon.getName());
        holder.locationTextView.setText(salon.getLocation());

        holder.itemView.setBackgroundColor(position == selectedPosition ?
                Color.LTGRAY : Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return salonList.size();
    }

    public static class SalonViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView locationTextView;
        OnSalonClickListener listener;

        public SalonViewHolder(View itemView, OnSalonClickListener listener) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.salonName);
            locationTextView = itemView.findViewById(R.id.salonLocation);
            this.listener = listener;

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onSalonClick(position);


                    }
                }
            });
        }
    }
}