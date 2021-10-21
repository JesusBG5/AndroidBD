package com.example.conexion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Consulta extends AppCompatActivity {


    LinearLayout tabla;

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
}