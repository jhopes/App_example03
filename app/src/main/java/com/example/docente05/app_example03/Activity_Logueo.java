package com.example.docente05.app_example03;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;


public class Activity_Logueo extends ActionBarActivity {
    Sqlite cx;
    EditText edt1, edt2;
    Cursor lista_usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__logueo);
        cx = new Sqlite(this, "BDventa", null, 2);
        edt1 = (EditText) findViewById(R.id.editText1);
        edt2 = (EditText) findViewById(R.id.editText2);
        /*edt1.setText("Frutas");
        edt2.setText("1");
        this.insert_cat();*/
    }

    public void valida(View view) {
        lista_usuarios = cx.getReadableDatabase().rawQuery("SELECT campo1, campo2 FROM usuario ", null);
        boolean flag = false;
        if (lista_usuarios.moveToFirst()) {
            do {
                if (lista_usuarios.getString(0).toString().equals(edt1.getText().toString())) {
                    if (lista_usuarios.getString(1).toString().equals(edt2.getText().toString())) {
                        flag=true;
                        break;
                    }
                    else{
                        flag=false;
                    }
                }else{
                    flag=false;
                }
            } while (lista_usuarios.moveToNext());

        }

        if(flag) {
            Intent i = new Intent(Activity_Logueo.this, MainActivity.class);
            this.finish();
            startActivity(i);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Logueo.this);
            builder.setMessage("Usuario incorrecto").show();
        }

    }
    public void insert_us() {
        if (!edt1.getText().toString().equals("") && !edt2.getText().toString().equals("")) {

            cx.getWritableDatabase().execSQL("INSERT INTO usuario(campo1, campo2) " +
                    " VALUES ('" + edt1.getText().toString() + "', '" + edt2.getText().toString() + "')");


        }
    }
    public void insert_cat() {
        if(!edt1.getText().toString().equals("") && !edt2.getText().toString().equals("")) {
            if (cx.getWritableDatabase() != null) {
                cx.getWritableDatabase().execSQL("INSERT INTO categoria(descripcion, estado) " +
                        " VALUES ('" + edt1.getText().toString() + "', '" + edt2.getText().toString() + "')");

                Toast.makeText(getApplicationContext(), "Insertado", Toast.LENGTH_SHORT).show();
            }
        }else{

            Toast.makeText(getApplicationContext(), "Cuadros de texto vacios", Toast.LENGTH_SHORT).show();
        }

    }
}
