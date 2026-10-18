package com.example.mobile_assignment2.task1.task1a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Task1a_FruitUpdate extends AppCompatActivity {

    EditText etUpdateName, etUpdateType, etUpdateQuantity;
    RatingBar rbUpdateRating;
    Button btnUpdateFruit, btnDeleteFruit, btnCancelUpdate;
    FirebaseFirestore db;
    Fruit fruitObject;
    String fruitID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task1a_fruit_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etUpdateName = findViewById(R.id.etUpdateFruitName);
        etUpdateType = findViewById(R.id.etUpdateFruitType);
        etUpdateQuantity = findViewById(R.id.etUpdateFruitQuantity);
        rbUpdateRating = findViewById(R.id.rbUpdateFruitRating);
        btnUpdateFruit = findViewById(R.id.btnUpdateFruit);
        btnDeleteFruit = findViewById(R.id.btnDeleteFruit);
        btnCancelUpdate = findViewById(R.id.btnCancelUpdate);
        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        fruitID = intent.getStringExtra("fruitID");


        db.collection("Fruit").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document: task.getResult()) {
                        if (document.getId().equals(fruitID)) {
                            fruitObject = document.toObject(Fruit.class);
                            fruitObject.setDocumentID(document.getId());
                            etUpdateName.setText(fruitObject.getName());
                            etUpdateType.setText(fruitObject.getType());
                            etUpdateQuantity.setText(fruitObject.getQuantity() + "");
                            rbUpdateRating.setRating(fruitObject.getRating());
                        }
                    }

                }
            }
        });



        btnUpdateFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fieldsFilled()) {
                    String name = etUpdateName.getText().toString().trim();
                    String type = etUpdateType.getText().toString().trim();
                    int quantity = Integer.parseInt(etUpdateQuantity.getText().toString().trim());
                    int rating = (int)rbUpdateRating.getRating();

                    DocumentReference docRef = db.collection("Fruit").document(fruitID);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                docRef.update("name", name);
                                docRef.update("type", type);
                                docRef.update("quantity", quantity);
                                docRef.update("rating", rating);
                                Toast.makeText(getApplicationContext(), "Success: Fruit Updated", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Exercise1a.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });

        btnDeleteFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("Fruit").document(fruitID).delete();
                Toast.makeText(getApplicationContext(), "Success: Fruit Deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Exercise1a.class);
                startActivity(intent);
                finish();
            }
        });


        btnCancelUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Exercise1a.class);
                startActivity(intent);
                finish();
            }
        });





    }

    public Boolean fieldsFilled() {
        String name = etUpdateName.getText().toString().trim();
        String type = etUpdateType.getText().toString().trim();
        String quantity = etUpdateQuantity.getText().toString().trim();
        int rating = (int)rbUpdateRating.getRating();

        if (name.isEmpty() || type.isEmpty() || quantity.isEmpty() || rating<1 || rating>5) {
            return false;
        } else {
            return true;
        }
    }
}