package com.example.conexion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Consulta extends AppCompatActivity {


    LinearLayout tabla;
    Button botonEliminar [];
    String nombres [];
    int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
    try {
        AdminSQLiteOpenHelper bd = new AdminSQLiteOpenHelper(this,
                "alumno", null, 1);
        SQLiteDatabase obj = bd.getReadableDatabase();
        Cursor c = obj.rawQuery("SELECT * FROM alumno", null);
        tabla = this.findViewById(R.id.tabla);
        if (c != null) {
            c.moveToFirst();
            int filas = c.getCount();
            botonEliminar = new Button[filas];
            nombres = new String[filas];

            do {
                LayoutInflater inflater = LayoutInflater.from(this);
                int id = R.layout.layout_elemento3;
                RelativeLayout elemento = (RelativeLayout)
                        inflater.inflate(id, null, false);
                String nombre = c.getString(c.getColumnIndex("nombre"));
                int edad = c.getInt(c.getColumnIndex("edad"));
                String carrera = c.getString(c.getColumnIndex("carrera"));
                TextView textView = (TextView)
                        elemento.findViewById(R.id.contacto);
                Button boton = (Button) elemento.findViewById(R.id.boton);


                boton.setOnClickListener(miListener);


                botonEliminar[contador] = boton;
                nombres[contador]= nombre;
                contador++;
                textView.setText(nombre+ " " + edad + " " + carrera);
                tabla.addView(elemento);
            } while (c.moveToNext());
        }
        c.close();
        bd.close();
    }catch(Exception e){
        Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
    }

    }


    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener miListener = new View.OnClickListener() {
        public void onClick(View v) {
            int posicion = 0;
            for (int i = 0; i < contador; i++) {
                if(v==botonEliminar[i]){
                    posicion=i;
                    break;
                }
            }

            try {
                AdminSQLiteOpenHelper bd = new AdminSQLiteOpenHelper(Consulta.this, "alumno", null, 1);
                SQLiteDatabase obj = bd.getWritableDatabase();
                obj.execSQL("DELETE FROM alumno WHERE nombre='"+nombres[posicion]+"'");
                bd.close();
                Toast.makeText(Consulta.this,"Datos Eliminados ",Toast.LENGTH_LONG).show();
                actualizar();
            }catch(Exception e){
                Toast.makeText(Consulta.this,e.toString(),Toast.LENGTH_LONG).show();
            }
        }
    };

    public void actualizar(){
        Intent obj2 = new Intent(this,Consulta.class);
        this.startActivity(obj2);
        this.finish();
    }


}