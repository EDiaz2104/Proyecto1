package com.example.crud05.Preferencial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud05.ControlBD;
import com.example.crud05.Modelos.Preferencial;
import com.example.crud05.R;

public class PreferencialDeleteActivity extends AppCompatActivity {
    EditText edit_idPreferencial;
    ControlBD helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencial_delete);
        helper = new ControlBD(this);
        edit_idPreferencial = (EditText) findViewById(R.id.edit_idPreferencial);
    }

    public void eliminarPreferencial(View v){
        String regEliminadas;
        Preferencial preferencial = new Preferencial();
        preferencial.setIdPreferencial(Integer.parseInt(edit_idPreferencial.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminar(preferencial);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        edit_idPreferencial.setText("");
    }
}