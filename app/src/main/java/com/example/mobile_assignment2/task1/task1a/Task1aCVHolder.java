package com.example.mobile_assignment2.task1.task1a;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_assignment2.R;

public class Task1aCVHolder extends RecyclerView.ViewHolder {
    CardView cvFruit;
    TextView txtFruitTypeAndName, txtFruitQuantity;
    RatingBar rbFruitRating;
    public Task1aCVHolder(@NonNull View itemView) {
        super(itemView);
        cvFruit = itemView.findViewById(R.id.cvFruit);
        txtFruitTypeAndName = itemView.findViewById(R.id.txtFruitTypeName);
        txtFruitQuantity = itemView.findViewById(R.id.txtFruitQuantity);
        rbFruitRating = itemView.findViewById(R.id.rbFruitRating);

        cvFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
