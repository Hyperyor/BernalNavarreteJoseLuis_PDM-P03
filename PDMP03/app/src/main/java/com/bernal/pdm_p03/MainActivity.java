package com.bernal.pdm_p03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cambiarACalculadora(View v)
    {
        Intent intentCalculadora = new Intent(MainActivity.this, Calculadora.class);

        startActivity(intentCalculadora);
    }
}
