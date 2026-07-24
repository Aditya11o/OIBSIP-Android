package com.aditya.stopwatch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.stopwatch.R;
import com.aditya.stopwatch.model.Lap;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class LapAdapter extends RecyclerView.Adapter<LapAdapter.LapViewHolder> {

    private final List<Lap> lapsList = new ArrayList<>();

    public void addLap(Lap lap) {
        lapsList.add(0, lap);
        notifyItemInserted(0);
    }

    public void clearLaps() {
        int size = lapsList.size();
        lapsList.clear();
        notifyItemRangeRemoved(0, size);
    }

    public ArrayList<Lap> getLapsList() {
        return new ArrayList<>(lapsList);
    }

    public void setLapsList(List<Lap> laps) {
        lapsList.clear();
        if (laps != null) {
            lapsList.addAll(laps);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lap, parent, false);
        return new LapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LapViewHolder holder, int position) {
        Lap lap = lapsList.get(position);
        Context context = holder.itemView.getContext();

        holder.tvLapNumber.setText(context.getString(R.string.lap_number_format, lap.getLapNumber()));
        holder.tvLapTime.setText(lap.getLapTimeFormatted());
        holder.tvTotalTime.setText(context.getString(R.string.total_label_format, lap.getTotalTimeFormatted()));
    }

    @Override
    public int getItemCount() {
        return lapsList.size();
    }

    static class LapViewHolder extends RecyclerView.ViewHolder {
        final MaterialTextView tvLapNumber;
        final MaterialTextView tvLapTime;
        final MaterialTextView tvTotalTime;

        LapViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLapNumber = itemView.findViewById(R.id.tv_lap_number);
            tvLapTime = itemView.findViewById(R.id.tv_lap_time);
            tvTotalTime = itemView.findViewById(R.id.tv_total_time);
        }
    }
}
