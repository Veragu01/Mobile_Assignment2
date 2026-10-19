package com.example.mobile_assignment2.task1.task1b;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_assignment2.R;

import java.util.List;

public class CatBreedcvAdapter extends RecyclerView.Adapter<CatBreedcvHolder>{

    List<CatBreed> catBreedsList;

    public CatBreedcvAdapter(List<CatBreed> catBreedsList) {
        this.catBreedsList=catBreedsList;
    }


    @NonNull
    @Override
    public CatBreedcvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_task1b_cat_card, parent, false);
        return new CatBreedcvHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull CatBreedcvHolder holder, int position) {
        Log.d("TEST", catBreedsList.toString()+"");
        CatBreed catBreed = catBreedsList.get(position);

        holder.txtBreedName.setText(catBreed.getName());
        holder.txtLifespan.setText(catBreed.getLife_span());
        holder.txtTemperament.setText(catBreed.getTemperament());
        holder.txtOrigin.setText(catBreed.getOrigin());
        holder.txtDescription.setText(catBreed.getDescription());
    }

    @Override
    public int getItemCount() {
//        return 5;
        return catBreedsList.size();
    }
}
