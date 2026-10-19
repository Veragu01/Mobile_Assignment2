package com.example.mobile_assignment2.task1.task1b;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_assignment2.task1.Exercise1b;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatController implements Callback<List<CatBreed>> {
    final String BASE_URL = "https://api.thecatapi.com/";
    public CatBreeds catBreeds;

    ProgressBar pbLoadingCatBreeds;
    RecyclerView rvCatBreeds;
    Exercise1b exercise1b;
    List<CatBreed> CatBreedsList;

    public CatController(RecyclerView rvCatBreeds, ProgressBar pbLoadingCatBreeds, Exercise1b exercise1b) {
        this.rvCatBreeds = rvCatBreeds;
        this.pbLoadingCatBreeds = pbLoadingCatBreeds;
        this.exercise1b = exercise1b;
    }

    public void start() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CatAPI catAPI = retrofit.create(CatAPI.class);
        Call<List<CatBreed>> call = catAPI.getCatBreeds();
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<List<CatBreed>> call, Response<List<CatBreed>> response) {
        if (response.isSuccessful()) {
            catBreeds = new CatBreeds();
            catBreeds.setCatBreeds(response.body());
//            Log.d("CAT_API", "Getting Cat Breeds Successfully" + catBreeds.getData().size());
            if (catBreeds.data != null) {
                Log.d("CAT_API", catBreeds.getData().toString());
//                Toast.makeText(getApplicationContext(), catBreeds.getData().toString(), Toast.LENGTH_LONG).show();
//                CatBreedsList = catBreeds.getData();
//                rvCatBreeds.setLayoutManager(new LinearLayoutManager(exercise1b.getApplicationContext(), LinearLayoutManager.VERTICAL, false));
//                rvCatBreeds.setLayoutManager(new LinearLayoutManager(this));

//                CatBreedcvAdapter adapter = new CatBreedcvAdapter(CatBreedsList);
//                rvCatBreeds.setAdapter(new CatBreedcvAdapter(CatBreedsList));
                pbLoadingCatBreeds.setVisibility(View.GONE);
                rvCatBreeds.setVisibility(View.VISIBLE);
                exercise1b.setRecyclerView();



            } else {
                Log.d("CAT_API", "Error get Cat data");
            }
        }
    }

    @Override
    public void onFailure(Call<List<CatBreed>> call, Throwable t) {
        Log.d("CAT_API", "On Failure: Error get Cat Breeds");
    }
}
