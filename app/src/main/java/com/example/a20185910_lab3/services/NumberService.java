package com.example.a20185910_lab3.services;

import com.example.a20185910_lab3.dto.Movies;
import com.example.a20185910_lab3.dto.Numbers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NumberService {
    @GET("/primeNumbers?len=999&order=1")
    Call<List<Numbers>> getNumbers();
}
