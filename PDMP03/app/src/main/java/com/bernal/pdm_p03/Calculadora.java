package com.bernal.pdm_p03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calculadora extends AppCompatActivity {

    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button b0;

    private Button bIgual;
    private Button bSuma;
    private Button bResta;
    private Button bMult;
    private Button bDiv;
    private Button bC;

    private TextView datos;



    private char operacionActual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        datos = findViewById(R.id.editText);

        b1 = findViewById(R.id.buttonOne);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datos.setText(datos.getText() + "1");
            }
        });


    }

    public void atras(View v)
    {
        Intent intentVolver = new Intent(Calculadora.this, MainActivity.class);
        startActivity(intentVolver);
    }


}
