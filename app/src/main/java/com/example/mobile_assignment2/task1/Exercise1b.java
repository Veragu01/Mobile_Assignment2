package com.example.mobile_assignment2.task1;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_assignment2.R;
import com.example.mobile_assignment2.task1.task1b.CatBreed;
import com.example.mobile_assignment2.task1.task1b.CatBreedcvAdapter;
import com.example.mobile_assignment2.task1.task1b.CatBreeds;
import com.example.mobile_assignment2.task1.task1b.CatController;

import java.util.ArrayList;
import java.util.List;

public class Exercise1b extends AppCompatActivity {
    CatController api;
    CatBreeds catBreeds;
    Button btnGetCatBreeds;
    ProgressBar pbGettingCatBreeds;
    List<CatBreed> CatBreedsList;
    RecyclerView rvCatBreeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise1b);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnGetCatBreeds = findViewById(R.id.btnGetCatBreeds);
//        btnDisplayCatBreeds = findViewById(R.id.btnDisplayCatBreeds);
        pbGettingCatBreeds = findViewById(R.id.pbGettingCatBreeds);
        rvCatBreeds = findViewById(R.id.rvCatBreeds);


        btnGetCatBreeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbGettingCatBreeds.setVisibility(View.VISIBLE);
                api = new CatController(rvCatBreeds, pbGettingCatBreeds, Exercise1b.this);
                api.start();
                btnGetCatBreeds.setActivated(false);
            }
        });
    }

    public void setRecyclerView() {
        if (api.catBreeds != null) {
            Log.d("TEST", "api.breeds != null");
            catBreeds = api.catBreeds;
            CatBreedsList = catBreeds.getData();
            Log.d("TEST2", CatBreedsList.toString());
            rvCatBreeds.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            CatBreedcvAdapter adapter = new CatBreedcvAdapter(CatBreedsList);
            rvCatBreeds.setAdapter(adapter);
            rvCatBreeds.setVisibility(View.VISIBLE);
        } else {
            Log.d("TEST", "api.breeds == null");
            setRecyclerView();
        }
    }
}