package com.example.a20185910_lab3;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a20185910_lab3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(tengoInternet()){
            Toast.makeText(MainActivity.this, "Tienes conexion a internet", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(MainActivity.this, "No tienes internet :/", Toast.LENGTH_SHORT).show();

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.searchMovie);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPeliculas(v);
            }
        });
    }

    public void irPeliculas (View view){
        Intent intent = new Intent(this, MoviesActivity.class);
        startActivity(intent);

    }
    public void irPrimos (View view){
        Intent intent = new Intent(this, PrimeNumbersActivity.class);
        startActivity(intent);

    }

    public boolean tengoInternet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        Log.d("msg-internet", "Internet: " + tieneInternet);

        return tieneInternet;
    }


}