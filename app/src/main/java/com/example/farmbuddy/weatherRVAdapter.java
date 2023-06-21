package com.example.farmbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmbuddy.bottom.weather;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class weatherRVAdapter extends RecyclerView.Adapter<weatherRVAdapter.ViewHolder> {
    private Context context;
    private ArrayList<weatherRVModel> weatherRVModelArrayList;

    public weatherRVAdapter(Context context, ArrayList<weatherRVModel> weatherRVModelArrayList) {
        this.context = context;
        this.weatherRVModelArrayList = weatherRVModelArrayList;
    }

    public weatherRVAdapter(weather weather, ArrayList<weatherRVModel> weatherRVModelArrayList) {
    }

    @NonNull
    @Override
    public weatherRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull weatherRVAdapter.ViewHolder holder, int position) {
        weatherRVModel model = weatherRVModelArrayList.get(position);
        holder.temp.setText(model.getTemperature()+"°c");
        Picasso.get().load("http".concat(model.getIcon())).into(holder.cond);
        holder.wind.setText(model.getWindSpeed()+"km/h");
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");
        try {
            Date t = input.parse(model.getTime());
            holder.time.setText(output.format(t));
        }catch (ParseException e){
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return weatherRVModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private  TextView wind, temp, time;
        private ImageView cond;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wind = itemView.findViewById(R.id.TVwind);
            temp = itemView.findViewById(R.id.TVtemp);
            time = itemView.findViewById(R.id.TVime);
            cond = itemView.findViewById(R.id.TVcon);
        }
    }
}
