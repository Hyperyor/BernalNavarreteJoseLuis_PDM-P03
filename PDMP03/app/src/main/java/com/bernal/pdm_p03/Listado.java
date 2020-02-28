package com.bernal.pdm_p03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    private DatabaseReference rootReference;

    private ListView listado;

    ArrayList<String> datosLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        listado = findViewById(R.id.listaView);

        rootReference = FirebaseDatabase.getInstance().getReference();
        //Log.e("Control:", "Entramos en listado");
        solicitarDatosFirebase();
    }

    private void solicitarDatosFirebase() {
        rootReference.child("DatosGeneracion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                datosLista = new ArrayList<String>();

                for(final DataSnapshot snapshot : dataSnapshot.getChildren()){

                    rootReference.child("DatosGeneracion").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            datosPojo data = snapshot.getValue(datosPojo.class);
                            String metodo = data.getMetodo();
                            String fecha = data.getFecha();
                            String tiempo = data.getTiempo();


                            String datos = metodo + " | " + fecha + " | " + " | " + tiempo;

                            datosLista.add(datos);

                            /*Log.e("Metodo:",""+metodo);
                            Log.e("Fecha:",""+fecha);
                            Log.e("tiempo:",""+tiempo);
                            Log.e("Datos:",""+snapshot.getValue());*/


                            mostrar();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.e("Control:", "Error de bd");
                        }
                    });



                }

                //mostrar();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void mostrar()
    {

        Log.e("Tamaño:",""+datosLista.size());
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datosLista);

        listado.setAdapter(itemsAdapter);
    }
}
