package com.example.crud05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetalleProductoCrudActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editIdDetalle, editIdProducto;
    EditText editCantidad, editPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto_crud);
        helper = new ControlBD(this);
        editIdDetalle = (EditText) findViewById(R.id.et_idDetalleProducto);
        editIdProducto = (EditText) findViewById(R.id.et_idProdDetalle);
        editCantidad = (EditText) findViewById(R.id.et_cantidDetalleProc);
        editPrecio = (EditText) findViewById(R.id.et_precioUniDetalle);
    }

    public void insertarDetalleP(View v) {
        if(!editIdDetalle.getText().toString().isEmpty() && !editIdProducto.getText().toString().isEmpty()
                && !editCantidad.getText().toString().isEmpty() && !editPrecio.getText().toString().isEmpty()){

            Integer idDetalle=Integer.valueOf(editIdDetalle.getText().toString());
            Integer idProducto=Integer.valueOf(editIdProducto.getText().toString());
            Integer cantidadProducto=Integer.valueOf(editCantidad.getText().toString());
            String precioProducto=editPrecio.getText().toString();

            String regInsertados;
            DetalleProducto detallePro=new DetalleProducto();

            detallePro.setIdDetalleProduc(idDetalle);
            detallePro.setIdProducto(idProducto);
            detallePro.setCantidadProducto(cantidadProducto);
            detallePro.setPrecioProducto(precioProducto);
            helper.abrir();
            regInsertados=helper.insertarDetalleP(detallePro);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Debe ingresar datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizarDetalleP(View v) {
        DetalleProducto detallePro=new DetalleProducto();
        if(!editIdDetalle.getText().toString().isEmpty() && !editIdProducto.getText().toString().isEmpty()
                && !editCantidad.getText().toString().isEmpty() && !editPrecio.getText().toString().isEmpty()) {

            detallePro.setIdDetalleProduc(Integer.valueOf(editIdDetalle.getText().toString()));
            detallePro.setIdProducto(Integer.valueOf(editIdProducto.getText().toString()));
            detallePro.setCantidadProducto(Integer.valueOf(editCantidad.getText().toString()));
            detallePro.setPrecioProducto(editPrecio.getText().toString());

            helper.abrir();
            String estado = helper.actualizarDetalleP(detallePro);
            helper.cerrar();

            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Debe llenar los campos obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    public void consultarDetalleP(View v) {
        if(!editIdDetalle.getText().toString().isEmpty()){
            helper.abrir();
            DetalleProducto detallePro = helper.consultarDetalleP(editIdDetalle.getText().toString());
            helper.cerrar();
            if(detallePro == null)
                Toast.makeText(this, "El Detalle del producto con ID: " + editIdDetalle.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
            else{
                editIdDetalle.setText(String.valueOf(detallePro.getIdDetalleProduc()));
                editIdProducto.setText(String.valueOf(detallePro.getIdProducto()));
                editCantidad.setText(String.valueOf(detallePro.getCantidadProducto()));
                editPrecio.setText(detallePro.getPrecioProducto());
            }
        }else {
            Toast.makeText(this, "Debe ingresar el ID del horario", Toast.LENGTH_LONG).show();
        }
    }

    public void eliminarDetalleP(View v){
        String regEliminadas;
        DetalleProducto detallePro=new DetalleProducto();
        if(!editIdDetalle.getText().toString().isEmpty()){
            detallePro.setIdDetalleProduc(Integer.valueOf(editIdDetalle.getText().toString()));
            helper.abrir();
            regEliminadas=helper.eliminarDetalleP(detallePro);
            helper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Debe ingresar el ID", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        editIdDetalle.setText("");
        editIdProducto.setText("");
        editCantidad.setText("");
        editPrecio.setText("");
    }

}