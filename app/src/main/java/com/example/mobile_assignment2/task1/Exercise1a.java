package com.example.mobile_assignment2.task1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_assignment2.R;
import com.example.mobile_assignment2.task1.task1a.Task1aCVAdapter;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Exercise1a extends AppCompatActivity {

    RecyclerView rvFruit;


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

        db = FirebaseFirestore.getInstance();

        Task1aCVAdapter adapter = new Task1aCVAdapter(fruitList);
        rvFruit = findViewById(R.id.rvFruit);
        rvFruit.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rvFruit.setAdapter(adapter);





    }
}