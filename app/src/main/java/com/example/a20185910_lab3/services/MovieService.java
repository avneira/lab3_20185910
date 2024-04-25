package com.example.a20185910_lab3.services;

import com.example.a20185910_lab3.dto.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

   @GET("/?apikey=bf81d461&i=tt3896198")
   Call<Movies> getMovies();

  //  @GET("/")
    //Call<Movies> getMovies(@Query("idPelicula") String i,
                         //  @Query("apikey") String apikey);

}
