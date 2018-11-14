package com.sfaci.mihorario;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static com.sfaci.mihorario.MainActivity.asignaturas;

public class NuevaClaseActivity extends Activity implements View.OnClickListener {
  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_clase);

        Button btAnadir = findViewById(R.id.btAnadir);
        btAnadir.setOnClickListener(this);
        Button btCancelar = findViewById(R.id.btCancelar);
        btCancelar.setOnClickListener(this);

        String[] dias = {"Lunes", "Martes", "Miércoles",
                "Jueves", "Viernes"};
        ArrayAdapter<String> diasAdapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dias);

        Spinner spDia = findViewById(R.id.spDia);
        spDia.setAdapter(diasAdapter);

    }
        public void onClick(View v) {

            Intent intent = null;

            switch (v.getId()) {
                case R.id.btAnadir:
                    EditText etAsignatura=findViewById(R.id.etAsignatura);
                    Spinner spDia=findViewById(R.id.spDia);
                    EditText etHora=findViewById(R.id.etHora);

                    String nombre=etAsignatura.getText().toString();
                    String dia=(String) spDia.getSelectedItem();
                    String hora=etHora.getText().toString();

                    Asignatura asignatura=new Asignatura(nombre,dia,hora);
                    asignaturas.add(asignatura);

                    etAsignatura.setText("");
                    etHora.setText("");
                    spDia.setSelection(0);

                    Toast.makeText(this,R.string.mensaje_añadir_asignatura,Toast.LENGTH_LONG).show();

                    break;
                case R.id.btCancelar:
                    onBackPressed(); // pulsar atras
                    break;

                default:
                    break;
            }
        }
}
