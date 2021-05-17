package com.example.crud05;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
    String[] menu={"Tabla TipoPago","Tabla DetallePedido","Tabla Producto","Tabla Local","Tabla Horario","Tabla Encargado local","Tabla Ubicacion"};
    String[] activities={"TipoPagoActivity","DetallePedidoActivity","ProductoActivity","LocalMenuActivity","HorarioMenuActivity","EncargadoLocalMenuActivity","UbicacionMenuActivity"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menu));

    }

    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);

        if(position!=7){   //SE TIENE QUE CAMBIAR EL NUMERO

            String nombreValue=activities[position];

            try{
                Class<?>
                        clase=Class.forName("com.example.crud05."+nombreValue);
                Intent inte = new Intent(this,clase);
                this.startActivity(inte);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }else{
            //CODIGO PARA LLENAR BASE DE DATOS
        }
    }
}
