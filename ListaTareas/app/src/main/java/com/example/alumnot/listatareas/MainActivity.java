package com.example.alumnot.listatareas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLDisplay;

import static com.example.alumnot.listatareas.MainActivity.Estado.HECHAS;
import static com.example.alumnot.listatareas.MainActivity.Estado.PENDIENTES;


public class MainActivity extends Activity implements View.OnClickListener{

    private ArrayList<String> tareasPendientes; // esto es un array para guardar los valores
    private ArrayList<String> tareasHechas;
    private ArrayList<String> tareasVisibles;
    private ArrayAdapter<String> adaptador;

    public enum Estado{//esto es para saber en que posicion donde esta
        HECHAS,
        PENDIENTES,

    }

    public Estado estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Button btAnadirTarea=findViewById(R.id.btAnadirTarea);
       btAnadirTarea.setOnClickListener(this);
       Button btPendientes=findViewById(R.id.btPendientes);
       btPendientes.setOnClickListener(this);
       Button btHechas=findViewById(R.id.btHechas);
       btHechas.setOnClickListener(this);


       tareasPendientes = new ArrayList<>();
       tareasHechas = new ArrayList<>();
       tareasVisibles =new ArrayList<>();
       adaptador = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, tareasVisibles);
        ListView lvTareas=findViewById(R.id.lvTarea);
        lvTareas.setAdapter(adaptador);

        registerForContextMenu(lvTareas); //menu constestual IMPORTANTISIMOOOOOOOOOOOO
        btPendientes.setEnabled(false); // desavilita el boton

        estado=PENDIENTES;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
            //esto infla el menu
        if (estado==PENDIENTES) {
            getMenuInflater().inflate(R.menu.menu_contestual, menu);
        }else{
            getMenuInflater().inflate(R.menu.menu_contestual2, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
       AdapterContextMenuInfo menuInfo=
                (AdapterContextMenuInfo) item.getMenuInfo();
        final int posicion=menuInfo.position;

        switch (item.getItemId()){
            case R.id.itemEliminar:
                // todo elimina la tarea
                AlertDialog.Builder builder=new AlertDialog.Builder(this);                                //esta
                builder.setTitle(R.string.eliminar_tarea).setMessage(R.string.estas_seguro)           //esta
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {               //uy esto te crea la ventana emergente
                            @Override
                            public void onClick(DialogInterface dialog, int which) {                          //esto te crea el no
                                dialog.dismiss();
                            }
                        });

                if (estado==PENDIENTES) {

                    builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {               //estp te crea el si
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tareasVisibles.remove(tareasPendientes.remove(posicion));
                            adaptador.notifyDataSetChanged();
                        }
                    });
                }else {
                    builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tareasVisibles.remove(tareasHechas.remove(posicion));
                            adaptador.notifyDataSetChanged();
                        }
                    });
                }
                builder.create().show();
                return true;
            case R.id.itemHecho:
                //todo pasar tarea para hacer
                String tarea= tareasPendientes.remove(posicion);
                tareasHechas.add(tarea);
                tareasVisibles.remove(tarea);
                adaptador.notifyDataSetChanged();
                return true;
            case R.id.itemVolver:
                tarea=tareasHechas.remove(posicion);
                tareasPendientes.add(tarea);
                tareasVisibles.remove(tarea);
                adaptador.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btAnadirTarea:
                //todo lo que hay que hacer //"todo" lo pone en azul ya que es algo especial que hay que hacer hay que empezar por todo tambien vale por fixmi
                EditText etTarea=findViewById(R.id.etTarea);
                String tarea = etTarea.getText().toString();
                tareasPendientes.add(tarea);
                tareasVisibles.add(tarea);
                adaptador.notifyDataSetChanged(); //coje los datos
                etTarea.setText(""); // borra la caja de textp
                break;
            case R.id.btPendientes:
                //todo lo que hay que hacer
                adaptador.clear();
                adaptador.addAll(tareasPendientes);
                adaptador.notifyDataSetChanged();
                findViewById(R.id.btPendientes).setEnabled(false);
                findViewById(R.id.btHechas).setEnabled(true);
                findViewById(R.id.btAnadirTarea).setEnabled(true);

                estado=PENDIENTES;
                break;
            case R.id.btHechas:
                //todo lo que hay que haccer
                adaptador.clear();
                adaptador.addAll(tareasHechas);
                adaptador.notifyDataSetChanged();
                findViewById(R.id.btPendientes).setEnabled(true);
                findViewById(R.id.btHechas).setEnabled(false);
                findViewById(R.id.btAnadirTarea).setEnabled(false);

                estado=HECHAS;
                break;


        }
    }
}
