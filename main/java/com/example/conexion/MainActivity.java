package com.example.conexion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText cajaN,cajaE,cajaC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            cajaN = this.findViewById(R.id.cajaNombre);
            cajaE = this.findViewById(R.id.cajaEdad);
            cajaC = this.findViewById(R.id.cajaCarrera);
        }catch(Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public void guardar(View view){
        try {
            AdminSQLiteOpenHelper bd = new AdminSQLiteOpenHelper(this, "alumno", null, 1);
            SQLiteDatabase obj = bd.getWritableDatabase();
            ContentValues fila = new ContentValues();
            fila.put("nombre", cajaN.getText().toString());
            fila.put("edad", Integer.valueOf(cajaE.getText().toString()));
            fila.put("carrera", cajaC.getText().toString());
            obj.insert("alumno", null, fila);
            bd.close();
            Toast.makeText(this,"Datos guardados ",Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
   //Firebase         Mysql -> Android    (Web services)
    public void consultar(View view){

        Intent obj2 = new Intent(this,Consulta.class);
        this.startActivity(obj2);
    }


}