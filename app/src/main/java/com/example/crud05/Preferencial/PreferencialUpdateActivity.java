package com.example.crud05.Preferencial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crud05.ControlBD;
import com.example.crud05.Modelos.Preferencial;
import com.example.crud05.Modelos.Usuario;
import com.example.crud05.R;
public class PreferencialUpdateActivity extends AppCompatActivity {

    EditText edit_idPreferencial;
    EditText edit_idUsuario;
    EditText edit_idLocal;
    ControlBD helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencial_update);
        helper = new ControlBD(this);
        edit_idPreferencial = (EditText) findViewById(R.id.edit_idPreferencial);
        edit_idUsuario = (EditText) findViewById(R.id.edit_idUsuario);
        edit_idLocal = (EditText) findViewById(R.id.edit_idLocal);
    }

    public void actualizarPreferencial(View v){

        Preferencial preferencial = new Preferencial();
        preferencial.setIdPreferencial(Integer.parseInt(edit_idPreferencial.getText().toString()));
        preferencial.setIdUsuario(Integer.parseInt(edit_idUsuario.getText().toString()));
        preferencial.setIdLocal(Integer.parseInt(edit_idLocal.getText().toString()));

        helper.abrir();
        String estado = helper.actualizar(preferencial);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        edit_idPreferencial.setText("");
        edit_idUsuario.setText("");
        edit_idLocal.setText("");
    }
}