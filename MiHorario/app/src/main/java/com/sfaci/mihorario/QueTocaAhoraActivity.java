package com.sfaci.mihorario;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.sfaci.mihorario.MainActivity.asignaturas;

public class QueTocaAhoraActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_toca_ahora);

        TextView tvMensaje=findViewById(R.id.tvLoQueToca);

        int diasHoy =new GregorianCalendar().get(Calendar.DAY_OF_WEEK);
        for (Asignatura asignatura : asignaturas){
            if (Util.diaOrdinal(asignatura.getDia()) == diasHoy){
                int horaAhora=new GregorianCalendar().get(Calendar.HOUR);
                   if(Integer.parseInt(asignatura.getHora()) == horaAhora){
                       tvMensaje.setText("Te toca ir a clase de "+ asignatura.getNombre());
                       return;
                }
            }

        }

        tvMensaje.setText("Tiempo libre");

    }


}
