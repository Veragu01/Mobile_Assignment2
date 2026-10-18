package com.example.mobile_assignment2.task1.task1a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_assignment2.R;
import com.example.mobile_assignment2.task1.Exercise1a;

public class Task1aCVHolder extends RecyclerView.ViewHolder {
    CardView cvFruit;
    TextView txtFruitTypeAndName, txtFruitQuantity, txtFruitID;
    RatingBar rbFruitRating;
    public Task1aCVHolder(@NonNull View itemView) {
        super(itemView);
        cvFruit = itemView.findViewById(R.id.cvFruit);
        txtFruitTypeAndName = itemView.findViewById(R.id.txtFruitTypeName);
        txtFruitQuantity = itemView.findViewById(R.id.txtFruitQuantity);
        rbFruitRating = itemView.findViewById(R.id.rbFruitRating);
        txtFruitID = itemView.findViewById(R.id.txtFruitID);

        cvFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = itemView.getContext();
                String fruitID = txtFruitID.getText().toString().trim();
                Intent intent = new Intent(cvFruit.getContext(), Task1a_FruitUpdate.class);
                intent.putExtra("fruitID", fruitID);
                context.startActivity(intent);

                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }            }
        });
    }
}
