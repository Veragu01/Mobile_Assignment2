package com.example.mobile_assignment2.task1.task1b;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_assignment2.R;

public class CatBreedcvHolder extends RecyclerView.ViewHolder {
    CardView cvCat;
    TextView txtBreedName, txtLifespan, txtTemperament, txtOrigin, txtDescription;

    public CatBreedcvHolder(@NonNull View itemView) {
        super(itemView);

        txtBreedName = itemView.findViewById(R.id.txtBreedName);
        txtLifespan = itemView.findViewById(R.id.txtBreedLifespan);
        txtTemperament = itemView.findViewById(R.id.txtBreedTemperament);
        txtOrigin = itemView.findViewById(R.id.txtBreedOrigin);
        txtDescription = itemView.findViewById(R.id.txtBreedDescription);


    }
}
