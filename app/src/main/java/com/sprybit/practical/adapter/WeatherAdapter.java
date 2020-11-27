package com.sprybit.practical.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sprybit.practical.databinding.ItemWeatherBinding;
import com.sprybit.practical.model.Weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private List<Weather> weatherList = new ArrayList<>();
    private Context context;

    public WeatherAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemWeatherBinding binding = ItemWeatherBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {
        Weather weather = weatherList.get(position);

        holder.itemWeatherBinding.txtCity.setText(weather.getName());
        //temp
        float kelvin = weather.getMain().getTemp().floatValue();
        // Kelvin to Degree Celsius Conversion
        float celsius = kelvin - 273.15F;
        holder.itemWeatherBinding.txtTemp.setText("Cel:-" + celsius);
        holder.itemWeatherBinding.imgMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float lat = weather.getCoord().getLat().floatValue();
                float lon = weather.getCoord().getLon().floatValue();
                String city = weather.getName();
                String location = "geo:" + lat + "," + lon + "?q=" + city;
                Uri gmmIntentUri = Uri.parse(location);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                context.startActivity(mapIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemWeatherBinding itemWeatherBinding;

        public ViewHolder(@NonNull ItemWeatherBinding itemWeatherBinding) {
            super(itemWeatherBinding.getRoot());
            this.itemWeatherBinding = itemWeatherBinding;
        }
    }
//    float kelvin = Float.parseFloat(br.readLine());
//		// Kelvin to Degree Celsius Conversion
//		float celsius = kelvin - 273.15F;


    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
        notifyDataSetChanged();
    }
}
