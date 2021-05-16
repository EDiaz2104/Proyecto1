package com.example.crud05;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class ControlBD{
    private static final String[]camposTipoPago = new String []{"idTipoPago","tipoPago"};
    private static final String[]camposDetallepedido = new String []{"idDetallePedido","idTipoPago","idProducto","cantidad","EstadoPedido"};
    private static final String[] camposProducto = new String []{"idProducto","NombreProducto","idLocal","idProveedor"};
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    public ControlBD(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
}
private static class DatabaseHelper extends SQLiteOpenHelper {
    private static final String BASE_DATOS = "crud.s3db";
    private static final int VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, BASE_DATOS, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("CREATE TABLE tipopago(idTipoPago INTEGER NOT NULL PRIMARY KEY,tipoPago VARCHAR(30));");
            db.execSQL("CREATE TABLE producto(idProducto INTEGER NOT NULL PRIMARY KEY,NombreProducto VARCHAR(50),idLocal INTEGER,idProveedor INTEGER);");
            db.execSQL("CREATE TABLE detallepedido(idTipoPago INTEGER NOT NULL ,idProducto INTEGER NOT NULL ,idDetallePedido INTEGER ,cantidad INTEGER,EstadoPedido BOOLEAN ,PRIMARY KEY(idTipoPago,idProducto,idDetallePedido));");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
    }
}
    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;
    }
    public void cerrar(){
        DBHelper.close();
    }


    public String insertar(TipoPago tipopago){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues tipo = new ContentValues();
        tipo.put("idTipoPago", tipopago.getIdTipoPago());
        tipo.put("tipoPago", tipopago.getTipoPago());
        contador=db.insert("tipopago", null, tipo);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;

    }
    public String insertar(DetallePedido detallepedido){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        if(verificarIntegridad(detallepedido,1))
        {
            ContentValues detalle = new ContentValues();
            detalle.put("idDetallePedido", detallepedido.getIdDetallePedido());
            detalle.put("idTipoPago", detallepedido.getIdTipoPago());
            detalle.put("idProducto", detallepedido.getIdProducto());
            detalle.put("cantidad", detallepedido.getCantidad());
            detalle.put("EstadoPedido", detallepedido.isEstadoPedido());
            contador=db.insert("detallepedido", null, detalle);
        }
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;

        }
    public String insertar(Producto producto){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues product = new ContentValues();
        product.put("idProducto", producto.getIdProducto());
        product.put("NombreProducto", producto.getNombreProducto());
        product.put("idLocal", producto.getIdLocal());
        product.put("idProveedor", producto.getIdProveedor());
        contador=db.insert("producto", null, product);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
        }
    public String actualizar(TipoPago tipoPago){
        if(verificarIntegridad(tipoPago, 5)){
            String [] id = {String.valueOf(tipoPago.getIdTipoPago())};
            ContentValues cv = new ContentValues();
            cv.put("tipoPago", tipoPago.getTipoPago());
            db.update("tipopago", cv, "idTipoPago = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con idTipoPago " + tipoPago.getIdTipoPago() + " no existe";
        }
    }


    public String actualizar(Producto producto){

        if(verificarIntegridad(producto, 6)){
            String [] id = {String.valueOf(producto.getIdProducto())};
            ContentValues cv = new ContentValues();
            cv.put("NombreProducto", producto.getNombreProducto());
            cv.put("idLocal", producto.getIdLocal());
            cv.put("idProveedor", producto.getIdProveedor());
            db.update("producto", cv, "idProducto= ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con codigo de producto " + producto.getIdProducto() + " no existe";
        }
    }
    public String actualizar(DetallePedido detallePedido){

        if(verificarIntegridad(detallePedido, 2)){
            String [] id = {String.valueOf(detallePedido.getIdTipoPago()), String.valueOf(detallePedido.getIdProducto()), String.valueOf(detallePedido.getIdDetallePedido())};
            ContentValues cv = new ContentValues();
            cv.put("cantidad", detallePedido.getCantidad());
            cv.put("EstadoPedido", detallePedido.getIdDetallePedido());
            db.update("detallepedido", cv, "idProducto = ? AND idTipoPago = ? AND idDetallepedido = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro no Existe";
        }


        }
    public String eliminar(TipoPago tipopago){

        String regAfectados="filas afectadas= ";
        int contador=0;
        if (verificarIntegridad(tipopago,3)) {
            contador+=db.delete("detallepedido", "idTipoPago='"+tipopago.getIdTipoPago()+"'", null);
        }
        contador+=db.delete("tipopago", "idTipoPago='"+tipopago.getIdTipoPago()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String eliminar(DetallePedido detallePedido) {
        String regAfectados = "filas afectadas= ";
        int contador = 0;
        String where = "idTipoPedido='" + detallePedido.getIdTipoPago() + "'";
        where = where + " AND idProducto='" + detallePedido.getIdProducto() + "'";
        where = where + " AND idDetallePedido=" + detallePedido.getIdDetallePedido();
        contador += db.delete("DetallePedido", where, null);
        regAfectados += contador;

        return regAfectados;
    }
    public String eliminar(Producto producto){
        String regAfectados="filas afectadas= ";
        int contador=0;
        if (verificarIntegridad(producto,4)) {
            contador+=db.delete("detallepedido", "idProducto='"+producto.getIdProducto()+"'", null);
        }
        contador+=db.delete("producto", "idProducto='"+producto.getIdProducto()+"'", null);
        regAfectados+=contador;
        return regAfectados;

    }

    public TipoPago consultarTipoPago(String idTipoPago){

        String[] id = {idTipoPago};
        Cursor cursor = db.query("tipopago", camposTipoPago, "idTipoPago = ?",
                id, null, null, null);
        if(cursor.moveToFirst()){
            TipoPago tipopago = new TipoPago();
            tipopago.setIdTipoPago(cursor.getInt(0));
            tipopago.setTipoPago(cursor.getString(1));
            return tipopago;
        }else{
            return null;
        }
    }

    public Producto consultarProducto(String idProducto){
        String[] id = {idProducto};
        Cursor cursor = db.query("producto", camposProducto, "idProducto = ?",
                id, null, null, null);
        if(cursor.moveToFirst()){
            Producto producto = new Producto();
            producto.setIdProducto(cursor.getInt(0));
            producto.setNombreProducto(cursor.getString(1));
            return producto;
        }else{
            return null;
        }
    }
    public DetallePedido consultarDetallepedido(String idTipoPago,String idProducto, String idDetallepedido){
        String[] id = {idTipoPago,idProducto, idDetallepedido};
        Cursor cursor = db.query("DetallePedido", camposDetallepedido, "idTipoPago = ? AND idProducto = ? AND idDetallePedido = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            DetallePedido detalle = new DetallePedido();
            detalle.setIdTipoPago(cursor.getString(0));
            detalle.setIdProducto(cursor.getString(1));
            detalle.setIdDetallePedido(cursor.getString(2));
            detalle.setCantidad(cursor.getString(3));
            detalle.setEstadoPedido(cursor.getString(4));
            return detalle;
        }else{
            return null;
        }
        }
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
        switch(relacion){
        case 1:
        {
//verificar que al insertar nota exista carnet del alumno y el codigo de materia
        DetallePedido detallePedido = (DetallePedido) dato;
        String [] id1= {String.valueOf(detallePedido.getIdTipoPago())};
        String[] id2 = {String.valueOf(detallePedido.getIdProducto())};
            /* abrir(); */
        Cursor cursor1 = db.query("tipopago", null, "idTipoPago = ?", id1, null,
        null, null);
        Cursor cursor2 = db.query("producto", null, "idProducto = ?", id2,
        null, null, null);
        if(cursor1.moveToFirst() && cursor2.moveToFirst()){
//Se encontraron datos
        return true;
        }
        return false;
        }
        case 2:
        {
//verificar que al modificar nota exista carnet del alumno, el codigo de materia y el ciclo
        DetallePedido detalle1 = (DetallePedido) dato;
        String[] ids = {String.valueOf(detalle1.getIdTipoPago()), String.valueOf(detalle1.getIdProducto()),
                String.valueOf(detalle1.getIdDetallePedido())};
        abrir();
        Cursor c = db.query("detallepedido", null, "idTipoPago = ? AND idProducto = ? AND idDetallePedido = ?", ids, null, null, null);
        if(c.moveToFirst()){
//Se encontraron datos
        return true;
        }
        return false;
        }
        case 3:
        {
        TipoPago tipo = (TipoPago) dato;
        Cursor c=db.query(true, "detallepedido", new String[] {
                "idTipoPago" }, "idTipoPago='"+tipo.getIdTipoPago()+"'",null,
        null, null, null, null);
        if(c.moveToFirst())
        return true;
        else
        return false;
        }
        case 4:
        {
        Producto product = (Producto) dato;
        Cursor cmat=db.query(true, "detallepedido", new String[] {
        "idProducto" },
        "idProducto='"+product.getIdProducto()+"'",null, null, null, null, null);
        if(cmat.moveToFirst())
        return true;
        else
        return false;
        }
        case 5:
        {
//verificar que exista alumno
        TipoPago tipo2 = (TipoPago) dato;
        String[] id = {String.valueOf(tipo2.getIdTipoPago())};
        abrir();
        Cursor c2 = db.query("tipopago", null, "idTipoPago = ?", id, null, null,
        null);
        if(c2.moveToFirst()){
//Se encontro Alumno
        return true;
        }
        return false;
        }
        case 6:
        {
//verificar que exista Materia
        Producto pr2 = (Producto) dato;
        String[] idm = {String.valueOf(pr2.getIdProducto())};
        abrir();
        Cursor cm = db.query("producto", null, "idProducto = ?", idm, null, null, null);
        if(cm.moveToFirst()){
//Se encontro Materia
        return true;
        }
        return false;
        }
default:
        return false;
        }
    }

}