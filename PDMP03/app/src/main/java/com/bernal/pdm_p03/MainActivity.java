package com.bernal.pdm_p03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cambiarACalculadora(View v)
    {
        Intent intentCalculadora = new Intent(MainActivity.this, ActividadCalculos.class);

        startActivity(intentCalculadora);
    }

    public void cambiarAListado(View v)
    {
        Intent intentList = new Intent(MainActivity.this, Listado.class);

        startActivity(intentList);
    }

    public void cambiarAGaleria(View v)
    {
        Intent intentGaleria = new Intent(MainActivity.this, Galeria.class);

        startActivity(intentGaleria);
    }


    @Override protected void onStart() {
        super.onStart();
        Log.d("Ciclo de vida", "Estamos en el OnStart");
    }

    @Override protected void onResume() {
        super.onResume();
        Log.d("Ciclo de vida", "Estamos en el OnResume");
    }

    @Override protected void onPause() {
        Log.d("Ciclo de vida", "Estamos en el OnPause");
        super.onPause();
    }

    @Override protected void onStop() {
        Log.d("Ciclo de vida", "Estamos en el OnStop");
        super.onStop();
    }

    @Override protected void onRestart() {
        super.onRestart();
        Log.d("Ciclo de vida", "Estamos en el OnRestart");
    }

    @Override protected void onDestroy() {
        Log.d("Ciclo de vida", "Estamos en el OnDestroy");
        super.onDestroy();
    }
}
