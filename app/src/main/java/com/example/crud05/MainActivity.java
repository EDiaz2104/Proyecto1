package com.example.crud05;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

String[] menu={"Tabla DetallePedido","Tabla Producto","Tabla Local","Tabla Encargado local","Tabla Pedido","Tabla Repartidor","Tabla Pedido Asignado","Tabla Detalle Productos","Tabla Preferencial","Tabla Usuario","Tabla Categoria","Tabla Ubicacion","Tabla Horario","Tabla TipoPagoAPI","Tabla UsuarioAPI","Tabla Proveedor","LLenar Base de datos"}; //No borrar el llenar base
String[] activities={"DetallePedidoActivity","ProductoActivity","LocalMenuActivity","EncargadoLocalMenuActivity","PedidoMenuActivity","RepartidorMenuActivity","PedidoAsignadoMenuActivity","DetalleProductoCrudActivity","Menus.PreferencialMenuActivity","Menus.UsuarioMenuActivity","CategoriaRestActivity", "UbicacionRestActivity","TipopagoRestActivity","HorarioRestActivity","UsuarioRestActivity","ProveedorRestActivity"};
    ControlBD BDhelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menu));
        BDhelper=new ControlBD(this);
    }

    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);

        if(position!=activities.length){   //SE TIENE QUE CAMBIAR EL NUMERO

            String nombreValue=activities[position];

            try{
                Class<?> clase=Class.forName("com.example.crud05."+nombreValue);
                Intent inte = new Intent(this,clase);
                this.startActivity(inte);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }else{
            //CODIGO PARA LLENAR BASE DE DATOS
            Toast.makeText(this, "Ups!! falta esta funcion", Toast.LENGTH_SHORT).show();
           /* BDhelper.abrir();
            String tost=BDhelper.llenarBD();
            BDhelper.cerrar();
            Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();*/
        }
    }
}
