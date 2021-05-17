package com.example.crud05;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UbicacionMenuActivity extends ListActivity {

    String[] menu={"Insertar y Actualizar Ubicacion","Consultar y Eliminar Ubicacion"};
    String[] activities={"UbicacionInserActuaActivity","UbicacionConsulElimiActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        String nombreValue=activities[position];
        
        try{
            Class<?> clase=Class.forName("com.example.crud05."+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}