package com.bernal.pdm_p03;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GeneradorClaves {

    private int tamClave;
    private String metodo;

    TextView finalMsg;

    BigInteger e,d,n,p,q,totient;

    private long segundos;
    private long milis;

    private DatabaseReference rootReference;

    public GeneradorClaves(String potencia, String metodo)
    {
        rootReference = FirebaseDatabase.getInstance().getReference();
        tamClave = Integer.parseInt(potencia);
        this.metodo = metodo;


    }

    public void calcular(TextView msg)
    {
        String resultado = null;

        finalMsg = msg;

        if(metodo.equals("Principal"))
        {
            resultado = mainThread();

            finalMsg.setText(resultado);
        }

        if(metodo.equals("Thread"))
        {
            thread();
        }

        if(metodo.equals("AsyncTask"))
        {
            new Clavetask().execute();
        }


    }

    public String mainThread() {
        String mensaje = "";
        long time= generaClavesRSA();

        segundos = time/1000;

        milis = time%1000;

        mensaje += "Clave Generada en "+segundos+":"+milis+" en main" ;

        subirDatosBD();

        return mensaje;
    }

    public void thread() {

        String mensaje = "";

        new Thread(new Runnable() {

            public void run() {
                final long time = generaClavesRSA();

                finalMsg.post(new Runnable(){
                    public void run(){

                        segundos = time/1000;

                        milis = time%1000;
                        finalMsg.setText("Clave Generada en "+segundos+":"+milis+" en thrad" );
                        subirDatosBD();
                    }
                });

            }
        }).start();

    }

    private class Clavetask extends AsyncTask<Void, Void, Long> {

        @Override
        protected Long doInBackground(Void... params) {
            Log.d("ThreadANR","Asyntask Creada y vuelve hebra principal");
            return generaClavesRSA();
        }


        protected void onPostExecute(Long time) {
            segundos = time/1000;

            milis = time%1000;
            finalMsg.setText("Clave Generada en "+segundos+":"+milis+" en asynctask" );
            subirDatosBD();
            //Log.d("ThreadANR","onPostExecute");

        }


    }

    public long generaClavesRSA() {

        long ini = System.currentTimeMillis();
        generaPrimos(); //Genera p y q
        generaClaves(); //Genera e y d
        long fin = System.currentTimeMillis();
        Log.d("ThreadANR","Clave Creada y Finalizada");

        return fin-ini;
    }

    public void generaPrimos()	{
        p = new BigInteger(tamClave/2, 10, new Random());

        do q = new BigInteger(tamClave/2, 10, new Random());
        while(q.compareTo(p)==0);
    }


    public void generaClaves(){
        // n = p * q
        n = p.multiply(q);
        // toltient = (p-1)*(q-1)
        totient = p.subtract(BigInteger.valueOf(1));
        totient = totient.multiply(q.subtract(BigInteger.valueOf(1)));
        // Elegimos un e coprimo de y menor que n
        do e = new BigInteger(tamClave, new Random());

        while((e.compareTo(totient) != -1) ||
                (e.gcd(totient).compareTo(BigInteger.valueOf(1)) != 0));
        // d = e^1 mod totient
        d = e.modInverse(totient);
    }

    private void subirDatosBD()
    {
        String tiempo = "" + segundos + ":" + milis;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaComoCadena = sdf.format(new Date());

        Map<String, Object> datosClave = new HashMap<>();
        datosClave.put("metodo", metodo);
        datosClave.put("tiempo", tiempo);
        datosClave.put("fecha", fechaComoCadena);

        rootReference.child("DatosGeneracion").push().setValue(datosClave);
    }
}
