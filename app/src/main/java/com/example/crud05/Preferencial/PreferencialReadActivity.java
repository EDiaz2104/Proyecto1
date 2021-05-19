package com.example.crud05.Preferencial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud05.ControlBD;
import com.example.crud05.Modelos.Preferencial;
import com.example.crud05.R;

public class PreferencialReadActivity extends AppCompatActivity {
    EditText edit_idPreferencial;
    EditText edit_idUsuario;
    EditText edit_idLocal;
    ControlBD helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencial_read);
        helper = new ControlBD(this);
        edit_idPreferencial = (EditText) findViewById(R.id.edit_idPreferencial);
        edit_idUsuario = (EditText) findViewById(R.id.edit_idUsuario);
        edit_idLocal = (EditText) findViewById(R.id.edit_idLocal);
    }
    public void consultarPreferencial(View v){
        helper.abrir();
        Preferencial preferencial = helper.consultarPrefererncial(edit_idPreferencial.getText().toString());
        helper.cerrar();
        if(preferencial == null) Toast.makeText(
                this,
                "Preferencial con id "+ edit_idPreferencial.getText().toString() + " no encontrado",
                Toast.LENGTH_LONG).show();
        else{
            edit_idLocal.setText(String.valueOf(preferencial.getIdLocal()));
            edit_idUsuario.setText(String.valueOf(preferencial.getIdUsuario()));
        }
    }
    public void limpiarTexto(View v){
        edit_idPreferencial.setText("");
        edit_idLocal.setText("");
        edit_idUsuario.setText("");
    }

}