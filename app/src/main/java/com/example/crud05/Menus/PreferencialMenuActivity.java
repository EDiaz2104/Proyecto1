package com.example.crud05.Menus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.crud05.R;

public class PreferencialMenuActivity extends ListActivity {
    String[] menu={"Insertar Registro", "Actualizar Registro","Consultar Registro","Eliminar Registro"};
    String[] activities={"Preferencial.PreferencialCreateActivity","Preferencial.PreferencialUpdateActivity","Preferencial.PreferencialReadActivity", "Preferencial.PreferencialDeleteActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
        ListView listView = getListView();
        //listView.setBackgroundColor(Color.rgb(64, 0, 128));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String nombreValue = activities[position];
        //l.getChildAt(position).setBackgroundColor(Color.rgb(255, 128, 0));
        try {
            Class<?> clase = Class.forName("com.example.crud05." + nombreValue);
            Intent inte = new Intent(this, clase);
            this.startActivity(inte);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}