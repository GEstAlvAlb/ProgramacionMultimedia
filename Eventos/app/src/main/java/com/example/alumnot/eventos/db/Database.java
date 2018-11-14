package com.example.alumnot.eventos.db;

import static android.provider.BaseColumns._ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.audiofx.AudioEffect;
import android.text.format.DateUtils;
import android.widget.ArrayAdapter;

import com.example.alumnot.eventos.base.Evento;
import com.example.alumnot.eventos.util.Util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.alumnot.eventos.util.Constantes.AFORO;
import static com.example.alumnot.eventos.util.Constantes.DATABASE_NAME;
import static com.example.alumnot.eventos.util.Constantes.DESCRIPCION;
import static com.example.alumnot.eventos.util.Constantes.DIRECCION;
import static com.example.alumnot.eventos.util.Constantes.FECHA;
import static com.example.alumnot.eventos.util.Constantes.IMAGEN;
import static com.example.alumnot.eventos.util.Constantes.NOMBRE;
import static com.example.alumnot.eventos.util.Constantes.PRECIO;
import static com.example.alumnot.eventos.util.Constantes.TABLA_EVENTO;
import static com.example.alumnot.eventos.util.Constantes.VERSION;

public class Database extends SQLiteOpenHelper {



    public Database(Context context ) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLA_EVENTO+"("+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +NOMBRE+" TEXT, "+ DESCRIPCION + " TEXT, "+ DIRECCION+" TEXT, "
                +PRECIO +" REAL, " + FECHA + " TEXT, " + AFORO + " INT, "+ IMAGEN +" BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_EVENTO);
        onCreate(db);
    }
    public void nuevoEvento(Evento evento){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOMBRE,evento.getNombre());
        values.put(DESCRIPCION,evento.getDescripcion());
        values.put(DIRECCION,evento.getDireccion());
        values.put(PRECIO,evento.getPrecio());
        values.put(FECHA, Util.formatearFecha(evento.getFecha()));
        values.put(AFORO, evento.getAforo());
        //values.put(IMAGEN, Util.getBytes(evento.getImagen()));


        db.insertOrThrow(TABLA_EVENTO,null,values);
        db.close();
    }

    public  void eliminaEvento(Evento evento){
        SQLiteDatabase db=getWritableDatabase();
        String[] argumentos= {String.valueOf(evento.getId())};
        db.delete(TABLA_EVENTO,"id = ?",argumentos);
        db.close();

    }

    public  void modificarEvento(Evento evento){
        SQLiteDatabase db=getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(NOMBRE, evento.getNombre());
        values.put(DESCRIPCION, evento.getDescripcion());
        values.put(DIRECCION,evento.getDireccion());
        values.put(PRECIO,evento.getPrecio());
        values.put(FECHA,Util.formatearFecha(evento.getFecha()));
        values.put(AFORO, evento.getAforo());
        //values.put(IMAGEN,Util.getBytes(evento.getImagen()));

    }

    public List<Evento> getEventos(){
        final String[]SELECT={_ID, NOMBRE, DESCRIPCION, DIRECCION, PRECIO,
                FECHA, AFORO, IMAGEN};
        final String ORDER_BY="fecha";

        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor =db.query(TABLA_EVENTO,SELECT,null,null,null,null,
                ORDER_BY);

        ArrayList<Evento> listaEventos=new ArrayList<>();
        Evento evento=null;
        while (cursor.moveToNext()){
            evento=new Evento();
            evento.setId(cursor.getLong(0));
            evento.setNombre(cursor.getString(1));
            evento.setDescripcion(cursor.getString(2));
            evento.setDireccion(cursor.getString(3));
            evento.setPrecio(cursor.getFloat(4));
            try{
                evento.setFecha(Util.parsearFecha(cursor.getString(5)));

            }catch (ParseException pe){
                evento.setFecha(new Date(System.currentTimeMillis()));
            }
            evento.setAforo(cursor.getInt(6));
            //evento.setImagen(Util.getBitmap(cursor.getBlob()));

            listaEventos.add(evento);
        }
        cursor.close();
        db.close();

        return listaEventos;
    }

    public List<Evento> getEventos(String busqueda){
        return null;
    }


}

