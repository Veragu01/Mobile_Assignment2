package com.example.mobile_assignment2.task1.task1a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobile_assignment2.R;
import com.example.mobile_assignment2.task1.Exercise1a;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task1a_AddNewFruit extends AppCompatActivity {

    Button btnAddFruit, btnCancel;
    EditText txtFruitName, txtFruitType, txtFruitQuantity;
    RatingBar rbNewFruitRating;
    FirebaseFirestore db;
    List<Fruit> fruitList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task1a_add_new_fruit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtFruitName = findViewById(R.id.etNewFruitName);
        txtFruitType = findViewById(R.id.etNewFruitType);
        txtFruitQuantity = findViewById(R.id.etNewFruitQuantity);
        rbNewFruitRating = findViewById(R.id.rbNewFruitRating);
        btnAddFruit = findViewById(R.id.btnAddFruit);
        btnCancel = findViewById(R.id.btnCancelAddFruit);

        fruitList = new ArrayList<Fruit>();
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
                }
            }
        });

        btnAddFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFields()) {
                    Map<String, Object> newFruit = new HashMap<>();
                    newFruit.put("name", txtFruitName.getText().toString().trim());
                    newFruit.put("type",txtFruitType.getText().toString().trim());
                    newFruit.put("quantity", Integer.parseInt(txtFruitQuantity.getText().toString()));
                    newFruit.put("rating", rbNewFruitRating.getRating());
                    db.collection("Fruit").document().set(newFruit).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Success: New Fruit added", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Task1a_AddNewFruit.this, Exercise1a.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });






        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Exercise1a.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public Boolean checkFields() {
        if (txtFruitName.getText().toString().isEmpty() || txtFruitType.getText().toString().isEmpty() || txtFruitQuantity.getText().toString().isEmpty() || rbNewFruitRating.getRating()==0) {
            Toast.makeText(getApplicationContext(), "Error: Please fill all fields", Toast.LENGTH_LONG).show();
            return false;
        }
        if (rbNewFruitRating.getRating()>5) {
            Toast.makeText(getApplicationContext(), "Error: Invalid Rating", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}