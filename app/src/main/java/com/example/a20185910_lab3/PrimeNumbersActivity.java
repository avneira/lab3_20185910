package com.example.a20185910_lab3;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a20185910_lab3.databinding.ActivityPrimeNumbersBinding;
import com.example.a20185910_lab3.dto.Numbers;
import com.example.a20185910_lab3.services.NumberService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrimeNumbersActivity extends AppCompatActivity {

    private ActivityPrimeNumbersBinding binding;
    NumberService numberService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(PrimeNumbersActivity.this, "Bienvenido a los numeros primos", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        binding = ActivityPrimeNumbersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        numberService = new Retrofit.Builder()
                .baseUrl("https://prime-number-api.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NumberService.class);
        binding.button.setOnClickListener(v -> buscarNumPrimo());

    }
    public boolean tengoInternet1() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        Log.d("msg-internet", "Internet: " + tieneInternet);

        return tieneInternet;
    }

    public void buscarNumPrimo(){
        numberService.getNumbers().enqueue(new Callback<List<Numbers>>() {
            @Override
            public void onResponse( Call<List<Numbers>> call,  Response<List<Numbers>
                    > response) {
                if (response.isSuccessful()) {
                    List<Numbers> number1 = response.body();
                    int numeroInt = Integer.parseInt(binding.editTextText.getText().toString());
                    for(Numbers i: number1){
                        if(numeroInt== Integer.parseInt(i.getOrder())){
                            binding.textView.setText(i.getNumber());
                        }
                    }


                }else{
                    Log.d("msg-test", "error en la respuesta del webservice");
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<Numbers>> call, Throwable t) {
                t.printStackTrace();
            }
        });



    }
}