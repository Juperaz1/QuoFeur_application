package com.example.ppe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SalonAdapter extends RecyclerView.Adapter<SalonAdapter.SalonViewHolder> {

    private List<Salon> salonList;

    public SalonAdapter(List<Salon> salonList) {
        this.salonList = salonList;
    }

    @Override
    public SalonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_salon, parent, false);
        return new SalonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SalonViewHolder holder, int position) {
        Salon salon = salonList.get(position);
        holder.nameTextView.setText(salon.getName());
        holder.locationTextView.setText(salon.getLocation());
    }

    @Override
    public int getItemCount() {
        return salonList.size();
    }

    public static class SalonViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView locationTextView;

        public SalonViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.salonName);
            locationTextView = itemView.findViewById(R.id.salonLocation);
        }
    }
}
