package com.example.mobile_assignment2.task1.task1b;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface CatAPI {
    @Headers("x-api-key: live_L0mo9Oso5yzaXBj9m6QuicdN4MbTizUH9BBenSs8gkjPHqkKpjI4go1S1GxTANrD")
    @GET("v1/breeds")
    Call<List<CatBreed>> getCatBreeds();

}
