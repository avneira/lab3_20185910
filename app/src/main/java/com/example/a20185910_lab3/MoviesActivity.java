package com.example.a20185910_lab3;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a20185910_lab3.databinding.ActivityMoviesBinding;
import com.example.a20185910_lab3.dto.Movies;
import com.example.a20185910_lab3.services.MovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesActivity extends AppCompatActivity {

    private ActivityMoviesBinding binding;
    MovieService movieService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(MoviesActivity.this, "Bienvenido a la vista de pelis", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        binding = ActivityMoviesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        movieService = new Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService.class);

        movieService.getMovies().enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(@NonNull Call<Movies> call, @NonNull Response<Movies> response) {
                if (response.isSuccessful()) {
                    Movies movie1 = response.body();
                    binding.textView1.setText(movie1.getTitle());
                    binding.textView2.setText(movie1.getActors());
                    binding.textView3.setText(movie1.getDirector());
                    binding.textView4.setText(movie1.getGenre());
                    binding.textView5.setText(movie1.getReleased());
                    binding.textView6.setText(movie1.getPlot());
                    binding.textView7.setText(movie1.getWriters());
                }else{
                    Log.d("msg-test", "error en la respuesta del webservice");
                }
            }
            @Override
            public void onFailure(@NonNull Call<Movies> call, Throwable t) {
                t.printStackTrace();
            }
        });







    }
    public boolean tengoInternet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        Log.d("msg-internet", "Internet: " + tieneInternet);

        return tieneInternet;
    }
}