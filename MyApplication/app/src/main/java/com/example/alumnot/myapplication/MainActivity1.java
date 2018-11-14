package com.example.alumnot.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity1 extends AppCompatActivity {

    private EditText et1; //
    private EditText et2; //
    private TextView tv1; //
    private TextView tv2; //
    private TextView tv3; //
    private TextView tv4; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        et1 = (EditText)findViewById(R.id.num1);
        et2 = (EditText)findViewById(R.id.num2);
        tv1 = (TextView)findViewById(R.id.resultado);
        tv2 = (TextView)findViewById(R.id.restaR);
        tv3 = (TextView)findViewById(R.id.divR);
        tv4 = (TextView)findViewById(R.id.multiR);
    }

    //Este método realiza la suma
    public void Sumar(View view){
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();

        int num1 = Integer.parseInt(valor1);
        int num2 = Integer.parseInt(valor2);

        int suma = num1 + num2;

        String result = String.valueOf(suma);
        tv1.setText(result);

    }

    public void resta(View view) {
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();

        int num1 = Integer.parseInt(valor1);
        int num2 = Integer.parseInt(valor2);

        int resta = num1 - num2;

        String result = String.valueOf(resta);
        tv2.setText(result);
    }
    public void division(View view) {
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();

        int num1 = Integer.parseInt(valor1);
        int num2 = Integer.parseInt(valor2);

        int div = num1 / num2;

        String result = String.valueOf(div);
        tv3.setText(result);
    }
    public void multiplicacion(View view) {
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();

        int num1 = Integer.parseInt(valor1);
        int num2 = Integer.parseInt(valor2);

        int div = num1 * num2;

        String result = String.valueOf(div);
        tv4.setText(result);
    }
}

