package com.example.mobile_assignment2.task1.task1a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_assignment2.R;

import java.util.List;

public class Task1aCVAdapter  extends RecyclerView.Adapter<Task1aCVHolder> {

    private List<Fruit> fruitList;

    public Task1aCVAdapter(List<Fruit> fruitList) {
        this.fruitList=fruitList;
    }

    @NonNull
    @Override
    public Task1aCVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_fruit_card, parent, false);
        return new Task1aCVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Task1aCVHolder holder, int position) {
        Fruit fruit = fruitList.get(position);

        holder.txtFruitTypeAndName.setText(fruit.getName() + " - " + fruit.getType());
        holder.txtFruitQuantity.setText("Quantity: " + fruit.getQuantity());
        holder.rbFruitRating.setRating(fruit.getRating());
        holder.txtFruitID.setText(fruit.getDocumentID());
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }
}
