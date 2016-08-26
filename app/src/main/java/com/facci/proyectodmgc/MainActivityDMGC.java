package com.facci.proyectodmgc;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityDMGC extends AppCompatActivity {
    DBHelper dbSQLITE;

    EditText txtNombre_dmgc,txtApellido_dmgc,txtRecintoElectoral_dmgc,txtID_dmgc,txtAnoNacimiento_dmgc;

    Button btningresar,btnmodifica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_dmgc);
        dbSQLITE = new DBHelper(this);
    }
    public void insertarClick(View v){

        txtNombre_dmgc = (EditText) findViewById(R.id.txtNombre_dmgc);
        txtApellido_dmgc = (EditText) findViewById(R.id.txtApellido_dmgc);
        txtRecintoElectoral_dmgc = (EditText) findViewById(R.id.txtRecintoElectoral_dmgc);
        txtAnoNacimiento_dmgc = (EditText) findViewById(R.id.txtAnoNacimiento_dmgc);

        boolean estaInsertado = dbSQLITE.insertar(txtNombre_dmgc.getText().toString(),txtApellido_dmgc.getText().toString(),txtRecintoElectoral_dmgc.getText().toString(),Integer.parseInt(txtAnoNacimiento_dmgc.getText().toString()));

        if(estaInsertado)
            Toast.makeText(MainActivityDMGC.this, "Datos han sido Ingresados", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivityDMGC.this,"Lo sentimos ha ocurrido un error",Toast.LENGTH_SHORT).show();

    }

    public void verTodosClick(View v){

        Cursor res = dbSQLITE.selectVerTodos();
        if(res.getCount() == 0){
            mostrarMensaje("Error","No se encontraron registros");
            return;
        }

        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext()){
            buffer.append("Id : "+res.getString(0)+"\n");
            buffer.append("Nombre : "+res.getString(1)+"\n");
            buffer.append("Apellido : "+res.getString(2)+"\n");
            buffer.append("Recinto Electoral : "+res.getString(3)+"\n");
            buffer.append("Año Nacimiento : "+res.getInt(4)+"\n\n");
        }

        mostrarMensaje("Registros",buffer.toString());
    }

    public void mostrarMensaje(String titulo, String Mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();

    }

    public void modificarRegistroClick(View v){
        txtNombre_dmgc = (EditText) findViewById(R.id.txtNombre_dmgc);
        txtApellido_dmgc = (EditText) findViewById(R.id.txtApellido_dmgc);
        txtRecintoElectoral_dmgc = (EditText) findViewById(R.id.txtRecintoElectoral_dmgc);
        txtAnoNacimiento_dmgc = (EditText) findViewById(R.id.txtAnoNacimiento_dmgc);
        txtID_dmgc = (EditText) findViewById(R.id.txtID_dmgc);

        boolean estaAcutalizado = dbSQLITE.modificarRegistro(txtID_dmgc.getText().toString(),txtNombre_dmgc.getText().toString(),txtApellido_dmgc.getText().toString(),txtRecintoElectoral_dmgc.getText().toString(),Integer.parseInt(txtAnoNacimiento_dmgc.getText().toString()));
        if (estaAcutalizado == true){
            Toast.makeText(MainActivityDMGC.this,"Registro Actualizado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityDMGC.this,"ERROR: Registro NO Actualizado",Toast.LENGTH_SHORT).show();
        }
    }


    public void eliminarRegistroClick(View v){

       txtID_dmgc = (EditText) findViewById(R.id.txtID_dmgc);
        Integer registrosEliminados = dbSQLITE.eliminarRegistro(txtID_dmgc.getText().toString());

        if(registrosEliminados > 0 ){
            Toast.makeText(MainActivityDMGC.this,"Registro(s) Eliminado(s)",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityDMGC.this,"ERROR: Registro no eliminado",Toast.LENGTH_SHORT).show();
        }

    }

}
