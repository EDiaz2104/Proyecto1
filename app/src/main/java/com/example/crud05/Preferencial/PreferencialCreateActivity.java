package com.example.crud05.Preferencial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud05.ControlBD;
import com.example.crud05.Modelos.Preferencial;
import com.example.crud05.R;

public class PreferencialCreateActivity extends AppCompatActivity {
    EditText edit_idUsuario;
    EditText edit_idLocal;
    ControlBD helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencial_create);
        helper = new ControlBD(this);
        edit_idUsuario = (EditText) findViewById(R.id.edit_idUsuario);
        edit_idLocal = (EditText) findViewById(R.id.edit_idLocal);
    }

    public void insertarPreferencial(View v){
        String regInsertados;
        Integer idUsuario = Integer.parseInt(edit_idUsuario.getText().toString());
        Integer idLocal = Integer.parseInt(edit_idLocal.getText().toString());
        Preferencial preferencial = new Preferencial();
        preferencial.setIdUsuario(idUsuario);
        preferencial.setIdLocal(idLocal);

        helper.abrir();
        regInsertados = helper.insertar(preferencial);
        helper.cerrar();

        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        edit_idLocal.setText("");
        edit_idUsuario.setText("");
    }
}