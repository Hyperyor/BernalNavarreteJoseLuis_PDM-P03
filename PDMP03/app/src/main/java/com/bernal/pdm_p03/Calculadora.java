package com.bernal.pdm_p03;

public class Calculadora {
    private int valorUno;
    private int valorDos;
    private char operacion;

    private boolean primerNumeroInsertado;

    public Calculadora()
    {
        valorUno = -1;
        valorDos = 0;
        operacion = ' ';
        primerNumeroInsertado = false;
    }

    public int getValorUno() {
        return valorUno;
    }

    public void setValorUno(int valorUno) {
        this.valorUno = valorUno;
        primerNumeroInsertado = true;
    }

    public int getValorDos() {
        return valorDos;
    }

    public void setValorDos(int valorDos) {
        this.valorDos = valorDos;
    }

    public char getOperacion() {
        return operacion;
    }

    public void setOperacion(char operacion) {
        this.operacion = operacion;
    }

    public boolean isPrimerNumeroInsertado() {
        return primerNumeroInsertado;
    }

    public void setPrimerNumeroInsertado(boolean primerNumeroInsertado) {
        this.primerNumeroInsertado = primerNumeroInsertado;
    }

    public int calcular()
    {
        primerNumeroInsertado = false;
        switch(operacion)
        {
            case '+':

                return valorUno + valorDos;
            case '-':

                return valorUno - valorDos;
            case '*':

                return (valorUno * valorDos);

            case '/':

                if(valorDos != 0) {
                    return (valorUno / valorDos);
                }
                else {
                    return -1;
                }
            default:
                return -1;
        }
    }
}
