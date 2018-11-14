package com.example.alumnot.holamundo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MensajeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);

        Intent intent=getIntent();
        String mensaje=intent.getStringExtra("mensaje"); // trae el mensjae a este main y se guarda en la variable mensaje

        TextView tvMensaje= findViewById(R.id.tvMensaje); //mete el texto en el text
        tvMensaje.setText(mensaje);
    }
}
