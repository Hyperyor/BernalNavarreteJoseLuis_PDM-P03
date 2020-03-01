package com.bernal.pdm_p03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ActividadCalculos extends AppCompatActivity {

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
    private TextView finalMsg=null;

    private Calculadora calculadora;

    private Spinner potencias;

    private Spinner metodos;

    private GeneradorClaves generador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        finalMsg = (TextView)findViewById(R.id.textView2);

        calculadora = new Calculadora();

        darDatosSpinner();

        datos = findViewById(R.id.editText);

        b0 = findViewById(R.id.buttonZero);
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datos.setText(datos.getText() + "0");
            }
        });

        b1 = findViewById(R.id.buttonOne);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datos.setText(datos.getText() + "1");
            }
        });

        b2 = findViewById(R.id.buttonTwo);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datos.setText(datos.getText() + "2");
            }
        });


        b3 = findViewById(R.id.buttonThree);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datos.setText(datos.getText() + "3");
            }
        });

        b4 = findViewById(R.id.buttonFour);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datos.setText(datos.getText() + "4");
            }
        });

        b5 = findViewById(R.id.buttonFive);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datos.setText(datos.getText() + "5");
            }
        });

        b6 = findViewById(R.id.buttonSix);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datos.setText(datos.getText() + "6");
            }
        });

        b7 = findViewById(R.id.buttonSeven);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datos.setText(datos.getText() + "7");
            }
        });

        b8 = findViewById(R.id.buttonEight);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datos.setText(datos.getText() + "8");
            }
        });

        b9 = findViewById(R.id.buttonNine);
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datos.setText(datos.getText() + "9");
            }
        });

        bSuma = findViewById(R.id.buttonAdd);
        bSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = colocarPrimerNumero('+');

                if(b) {
                    Toast avisoOperacion =
                            Toast.makeText(getApplicationContext(),
                                    "Estas sumando", Toast.LENGTH_SHORT);

                    avisoOperacion.show();
                }
            }
        });

        bResta = findViewById(R.id.buttonSubtract);
        bResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = colocarPrimerNumero('-');

                if(b) {
                    Toast avisoOperacion =
                            Toast.makeText(getApplicationContext(),
                                    "Estas restando", Toast.LENGTH_SHORT);

                    avisoOperacion.show();
                }
            }
        });

        bMult = findViewById(R.id.buttonMultiply);
        bMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = colocarPrimerNumero('*');

                if(b) {
                    Toast avisoOperacion =
                            Toast.makeText(getApplicationContext(),
                                    "Estas multiplicando", Toast.LENGTH_SHORT);

                    avisoOperacion.show();
                }

            }
        });

        bDiv = findViewById(R.id.buttonDivide);
        bDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = colocarPrimerNumero('/');

                if(b) {
                    Toast avisoOperacion =
                            Toast.makeText(getApplicationContext(),
                                    "Estas dividiendo", Toast.LENGTH_SHORT);

                    avisoOperacion.show();
                }

            }
        });

        bC = findViewById(R.id.buttonClear);
        bC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datos.setText("");
                calculadora = new Calculadora();
            }
        });

        bIgual = findViewById(R.id.buttonEqual);
        bIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!datos.getText().toString().equals(""))
                {
                    if(calculadora.isPrimerNumeroInsertado())
                    {
                        calculadora.setValorDos(Integer.parseInt(datos.getText().toString()));

                        int resultado = calculadora.calcular();

                        datos.setText(""+resultado);

                        calculadora = new Calculadora();
                    }
                }
            }
        });



    }

    private void darDatosSpinner()
    {
        final String[] datosPotencias =
                new String[]{"512","1024","2048","4096"};

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, datosPotencias);

        potencias = (Spinner)findViewById(R.id.spinnerPotencia);

        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        potencias.setAdapter(adaptador);

        final String[] datosMetodos =
                new String[]{"Principal","Thread","AsyncTask" };

        ArrayAdapter<String> adaptadorMetodos =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, datosMetodos);

        metodos = (Spinner)findViewById(R.id.spinnerMetodo);

        adaptadorMetodos.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        metodos.setAdapter(adaptadorMetodos);
    }

    private boolean colocarPrimerNumero(char operacion)
    {
        if(!calculadora.isPrimerNumeroInsertado())
        {
            if(!datos.getText().toString().equals(""))
            {
                calculadora.setValorUno(Integer.parseInt(datos.getText().toString()));
                calculadora.setOperacion(operacion);
                datos.setText("");

                return true;
            }
            else
            {
                return false;
            }

        }
        else
        {
            calculadora.setOperacion(operacion);
            return true;
        }

    }

    public void atras(View v)
    {
        Intent intentVolver = new Intent(ActividadCalculos.this, MainActivity.class);
        startActivity(intentVolver);
    }

    public void generarClave(View v)
    {
        generador = new GeneradorClaves(potencias.getSelectedItem().toString(), metodos.getSelectedItem().toString());

       generador.calcular(finalMsg);


        //finalMsg.setText(mensaje);
    }


}
