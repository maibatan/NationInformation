package com.example.nationinformation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nationinformation.R;
import com.example.nationinformation.model.Country;
import com.squareup.picasso.Picasso;
import java.util.List;


public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    Context context;
    List<Country> countries;

    public CountryAdapter(Context context, List<Country> countries) {
        this.context = context;
        this.countries = countries;
    }


    public static final class CountryViewHolder extends RecyclerView.ViewHolder{

        TextView country_name;
        TextView population;
        TextView area;
        ImageView ensign;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            ensign=itemView.findViewById(R.id.ensign);
            country_name =itemView.findViewById(R.id.country_name);
            population=itemView.findViewById(R.id.population);
            area=itemView.findViewById(R.id.area);

        }
    }

    @NonNull
    @Override
    public CountryAdapter.CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item,parent,false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country nation= countries.get(position);
        Picasso.with(context).load("https://img.geonames.org/flags/x/"+nation.getCountryCode().toLowerCase()+".gif").into(holder.ensign);
        holder.country_name.setText(nation.getName());
        holder.population.setText(nation.getPopulation());
        holder.area.setText(nation.getAreaInSqKm());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}
