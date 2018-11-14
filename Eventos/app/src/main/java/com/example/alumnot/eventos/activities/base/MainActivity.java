package com.example.alumnot.eventos.activities.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.alumnot.eventos.R;
import com.example.alumnot.eventos.adapters.EventoAdapter;
import com.example.alumnot.eventos.base.Evento;
import com.example.alumnot.eventos.db.Database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    private List<Evento>eventos;
    private EventoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Database db = new Database(this);
        eventos =db.getEventos();

        ListView lvEventos= findViewById(R.id.lvEventos);
        adapter=new EventoAdapter(this,R.layout.item_evento,eventos);
        lvEventos.setAdapter(adapter);
        registerForContextMenu(lvEventos);



    }

    protected void onResume() {

        super.onResume();
        eventos.clear();
        Database db =new Database(this);
        eventos.addAll(db.getEventos());
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int posicion=menuInfo.position;

        switch (item.getItemId()){
            case R.id.menu_modificar:

                Intent intent=new Intent(this,AltaEventos.class);
                intent.putExtra("accion","modificar");
                intent.putExtra("evento", eventos.get(posicion));
                startActivity(intent);

                return true;
            case R.id.menu_eliminar:
                Database db=new Database(this);
                db.eliminaEvento(eventos.get(posicion));
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar,menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contestual,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_alta_eventos:
                Intent intent=new Intent(this,AltaEventos.class);
                intent.putExtra("accion","nuevo");
                startActivity(intent);
            default:
                return false;
        }
    }


}
