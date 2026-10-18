package com.example.mobile_assignment2.task1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_assignment2.R;
import com.example.mobile_assignment2.task1.task1a.Fruit;
import com.example.mobile_assignment2.task1.task1a.Task1aCVAdapter;
import com.example.mobile_assignment2.task1.task1a.Task1a_AddNewFruit;
import com.example.mobile_assignment2.task1.task1a.Task1a_FruitUpdate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Exercise1a extends AppCompatActivity {

    RecyclerView rvFruit;
    ProgressBar pbLoadingFruitRV;
    Button btnAddFruit;

    FirebaseFirestore db;
    List<Fruit> fruitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise1a);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvFruit = findViewById(R.id.rvFruit);
        pbLoadingFruitRV = findViewById(R.id.pbLoadingFruitRV);
        btnAddFruit = findViewById(R.id.btnAddNewFruit);

        fruitList = new ArrayList<Fruit>();
        Task1aCVAdapter adapter = new Task1aCVAdapter(fruitList);
        db = FirebaseFirestore.getInstance();

        db.collection("Fruit").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Fruit fruit = document.toObject(Fruit.class);
                        fruit.setDocumentID(document.getId());
                        fruitList.add(fruit);
                    }
                    adapter.notifyDataSetChanged();
                    pbLoadingFruitRV.setVisibility(View.GONE);
                    rvFruit.setVisibility(View.VISIBLE);
                }
            }
        });

        rvFruit.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rvFruit.setAdapter(adapter);


        btnAddFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Task1a_AddNewFruit.class);

            }
        });

    }

    public void updateFruit(String fruitID) {
        Intent intent = new Intent(getApplicationContext(), Task1a_FruitUpdate.class);
        intent.putExtra("fruitID", fruitID);
        startActivity(intent);
    }
}