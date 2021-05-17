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
    private static final String[]camposHorario = new String []{"idhorario","idlocal","dia","apertura","cierre"};
    private static final String[]camposLocal = new String []{"idlocal", "idencargadolocal", "idubicacion", "nomlocal"};
    private static final String[]camposUbicacion = new String []{"idubicacion","descripcionubicacion"};
    private static final String[]camposEncargadoLocal = new String []{"idencargadolocal", "idusuario", "nomencargado", "apelencargado","telencargado"};
    private static final String[]camposPedido = new String [] {"idPedido","idLocal","idCombo","idUsuario", "idDetallePedido","FechaPedido"};
    private static final String[]camposPedidoAsignado = new String [] {"idPedidoAsignado","idPedido","idRepartidor"};
    private static final String[]camposRepartidor = new String [] {"idRepartidor","idLocal","NombreRepartidor","CarnetRepartidor"};

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

            db.execSQL("CREATE TABLE repartidor(idRepartidor INTEGER NOT NULL PRIMARY KEY,idLocal INTEGER,NombreRepartidor VARCHAR(30), CarnetRepartidor VARCHAR(10));");
            db.execSQL("CREATE TABLE pedidoasignado(idPedidoAsignado INTEGER NOT NULL PRIMARY KEY,idPedido INTEGER,idRepartidor INTEGER);");
            db.execSQL("CREATE TABLE pedido(idPedido INTEGER NOT NULL PRIMARY KEY,idLocal INTEGER,idCombo INTEGER, idUsuario INTEGER,idDetallePedido INTEGER,FechaPedido VARCHAR(30));");

            db.execSQL("CREATE TABLE ubicacion(idubicacion INTEGER NOT NULL PRIMARY KEY, descripcionubicacion VARCHAR(50));");
            db.execSQL("CREATE TABLE encargadolocal(idencargadolocal INTEGER NOT NULL PRIMARY KEY, idusuario INTEGER NOT NULL, nomencargado VARCHAR(50), apelencargado VARCHAR(50), telencargado VARCHAR(10));");
            db.execSQL("CREATE TABLE local(idlocal INTEGER NOT NULL PRIMARY KEY, idencargadolocal INTEGER NOT NULL, idubicacion INTEGER NOT NULL, nomlocal VARCHAR(50));");
            db.execSQL("CREATE TABLE horario(idhorario INTEGER NOT NULL PRIMARY KEY, idlocal INTEGER NOT NULL, dia VARCHAR(10), apertura VARCHAR(10),cierre VARCHAR(10));");

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

    ////////////////////////////////HORARIO//////////////////////////////////

    public String insertarHorario(Horario horario){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues registro = new ContentValues();
        registro.put("idhorario", horario.getIdhorario());
        registro.put("idlocal", horario.getIdlocal() );
        registro.put("dia", horario.getDia());
        registro.put("apertura", horario.getApertura());
        registro.put("cierre", horario.getCierre());
        contador=db.insert("horario", null, registro);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public Horario consultarHorario(String idhorario){
        String[] id = {idhorario};
        Cursor cursor = db.query("horario", camposHorario, "idhorario = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Horario horario = new Horario();
            horario.setIdhorario(cursor.getInt(0));
            horario.setIdlocal(cursor.getInt(1));
            horario.setDia(cursor.getString(2));
            horario.setApertura(cursor.getString(3));
            horario.setCierre(cursor.getString(4));
            return horario;
        }else{
            return null;
        }
    }

    public String actualizarHorario(Horario hora){
        Integer v_id = hora.getIdhorario();                                                        //Pequeño artifisio para convertir el int en string
        String[] id = {String.valueOf(v_id)};
        ContentValues cv = new ContentValues();
        cv.put("idlocal", hora.getIdlocal());
        cv.put("dia", hora.getDia());
        cv.put("apertura", hora.getApertura());
        cv.put("cierre", hora.getCierre());
        int registro = db.update("horario", cv, "idhorario = ?", id);            //el id solo acepta string

        if(registro == 1){
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con carnet " + hora.getIdhorario() + " no existe";
        }
    }

    public String eliminarHorario(Horario hora){
        String regAfectados="filas afectadas= ";
        int contador=0;
        //if (verificarIntegridad(hora,3)) {
        //    contador+=db.delete("nota", "carnet='"+hora.getIdhorario()+"'", null);
        //}
        contador+=db.delete("horario", "idhorario='"+hora.getIdhorario()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    ///////////////////////////////UBICACION///////////////////////////////

    public String insertarUbicacion(Ubicacion ubi){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues registro = new ContentValues();
        registro.put("idubicacion", ubi.getIdubicacion());
        registro.put("descripcionubicacion", ubi.getDescripcionubicacion());
        contador=db.insert("ubicacion", null, registro);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String actualizarUbicacion(Ubicacion ubi){
        Integer v_id = ubi.getIdubicacion();                                                        //Pequeño artifisio para convertir el int en string
        String[] id = {String.valueOf(v_id)};
        ContentValues cv = new ContentValues();
        cv.put("descripcionubicacion", ubi.getDescripcionubicacion());
        int registro = db.update("ubicacion", cv, "idubicacion = ?", id);         //el id solo acepta string

        if(registro == 1){
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con ID " + ubi.getIdubicacion() + " no existe";
        }
    }

    public Ubicacion consultarUbicacion(String idubicacion){
        String[] id = {idubicacion};
        Cursor cursor = db.query("ubicacion", camposUbicacion, "idubicacion = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Ubicacion ubi = new Ubicacion();
            ubi.setIdubicacion(cursor.getInt(0));
            ubi.setDescripcionubicacion(cursor.getString(1));
            return ubi;
        }else{
            return null;
        }
    }

    public String eliminarUbicacion(Ubicacion ubicacion){
        String regAfectados="filas afectadas= ";
        int contador=0;
        //if (verificarIntegridad(hora,3)) {
        //    contador+=db.delete("nota", "carnet='"+hora.getidubicacion()+"'", null);
        //}
        contador+=db.delete("ubicacion", "idubicacion='"+ubicacion.getIdubicacion()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    ///////////////////////////////ENCARGADO DE LOCAL///////////////////////////////

    public String insertarEncargado(EncargadoLocal encargado){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues registro = new ContentValues();
        registro.put("idencargadolocal", encargado.getIdencargadolocal());
        registro.put("idusuario", encargado.getIdusuario());
        registro.put("nomencargado", encargado.getNomencargado());
        registro.put("apelencargado", encargado.getApelencargado());
        registro.put("telencargado", encargado.getTelencargado());
        contador=db.insert("encargadolocal", null, registro);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String actualizarEncargado(EncargadoLocal encargado){
        Integer v_id = encargado.getIdencargadolocal();        //Pequeño artifisio para convertir el int en string
        String[] id = {String.valueOf(v_id)};
        ContentValues reg = new ContentValues();
        //reg.put("idencargadolocal", encargado.getIdencargadolocal());
        reg.put("idusuario", encargado.getIdusuario());
        reg.put("nomencargado", encargado.getNomencargado());
        reg.put("apelencargado", encargado.getApelencargado());
        reg.put("telencargado", encargado.getTelencargado());
        int registro = db.update("encargadolocal", reg, "idencargadolocal = ?", id); //el id solo acepta string

        if(registro == 1){
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con ID " + encargado.getIdencargadolocal() + " no existe";
        }
    }

    public EncargadoLocal consultarEncargado(String idencargado){
        String[] id = {idencargado};
        Cursor cursor = db.query("encargadolocal", camposEncargadoLocal, "idencargadolocal = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            EncargadoLocal encargado = new EncargadoLocal();
            encargado.setIdencargadolocal(cursor.getInt(0));
            encargado.setIdusuario(cursor.getInt(1));
            encargado.setNomencargado(cursor.getString(2));
            encargado.setApelencargado(cursor.getString(3));
            encargado.setTelencargado(cursor.getString(4));
            return encargado;
        }else{
            return null;
        }
    }

    public String eliminarEncargado(EncargadoLocal encargado){
        String regAfectados="filas afectadas= ";
        int contador=0;
        //if (verificarIntegridad(hora,3)) {
        //    contador+=db.delete("nota", "carnet='"+hora.getidubicacion()+"'", null);
        //}
        contador+=db.delete("encargadolocal", "idencargadolocal='"+encargado.getIdencargadolocal()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    /////////////////////////////////LOCAL///////////////////////////////////

    public String insertarLocal(Local local){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues registro = new ContentValues();
        registro.put("idlocal", local.getIdlocal());
        registro.put("idencargadolocal", local.getIdencargadolocal());
        registro.put("idubicacion", local.getIdubicacion());
        registro.put("nomlocal", local.getNomlocal());
        contador=db.insert("local", null, registro);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String actualizarLocal(Local local){
        Integer v_id = local.getIdlocal();        //Pequeño artifisio para convertir el int en string
        String[] id = {String.valueOf(v_id)};
        ContentValues registro = new ContentValues();
        registro.put("idencargadolocal", local.getIdencargadolocal());
        registro.put("idubicacion", local.getIdubicacion());
        registro.put("nomlocal", local.getNomlocal());
        int contador = db.update("local", registro, "idlocal = ?", id); //el id solo acepta string

        if(contador == 1){
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con ID " + local.getIdlocal() + " no existe";
        }
    }

    public Local consultarLocal(String idlocal){
        String[] id = {idlocal};
        Cursor cursor = db.query("local", camposLocal, "idlocal = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Local local = new Local();
            local.setIdlocal(cursor.getInt(0));
            local.setIdencargadolocal(cursor.getInt(1));
            local.setIdubicacion(cursor.getInt(2));
            local.setNomlocal(cursor.getString(3));
            return local;
        }else{
            return null;
        }
    }

    public String eliminarLocal(Local local){
        String regAfectados="filas afectadas= ";
        int contador=0;
        //if (verificarIntegridad(hora,3)) {
        //    contador+=db.delete("nota", "carnet='"+hora.getidubicacion()+"'", null);
        //}
        contador+=db.delete("local", "idlocal='"+local.getIdlocal()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String insertar(Pedido pedido){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues ped = new ContentValues();
        ped.put("idPedido", pedido.getIdPedido());
        ped.put("idLocal", pedido.getIdLocal());
        ped.put("idCombo", pedido.getIdCombo());
        ped.put("idUsuario", pedido.getIdUsuario());
        ped.put("idDetallePedido", pedido.getIdDetallePedido());
        ped.put("FechaPedido", String.valueOf(pedido.getFechaPedido()));
        contador=db.insert("pedido", null, ped);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public Pedido consultarPedido(String idPedido){

        String[] id = {idPedido};
        Cursor cursor = db.query("pedido", camposPedido, "idPedido = ?",
                id, null, null, null);
        if(cursor.moveToFirst()){
            Pedido pedido = new Pedido();
            pedido.setIdPedido(cursor.getInt(0));
            pedido.setIdLocal(cursor.getInt(1));
            pedido.setIdCombo(cursor.getInt(2));
            pedido.setIdUsuario(cursor.getInt(3));
            pedido.setIdDetallePedido(cursor.getInt(4));
            pedido.setFechaPedido(cursor.getString(5));
            return pedido;
        }else{
            return null;
        }

    }

    public String actualizar(Pedido pedido){
        if(pedido!=null){
            String[] id = {String.valueOf(pedido.getIdPedido())};
            ContentValues cv = new ContentValues();
            cv.put("idPedido", pedido.getIdPedido());
            cv.put("idLocal", pedido.getIdLocal());
            cv.put("idCombo", pedido.getIdCombo());
            cv.put("idUsuario", pedido.getIdUsuario());
            cv.put("idDetallePedido", pedido.getIdDetallePedido());
            cv.put("FechaPedido", String.valueOf(pedido.getFechaPedido()));
            db.update("pedido", cv, "idpedido = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con pedido " + pedido.getIdLocal() + " no existe";
        }
    }

    public String eliminar(Pedido pedido){
        String regAfectados="filas afectadas= ";
        int contador=0;
        if (pedido!=null) {
            contador+=db.delete("pedidoasignado", "idPedido='"+pedido.getIdPedido()+"'", null);
        }
        contador+=db.delete("pedido", "idPedido='"+pedido.getIdPedido()+"'", null);
        regAfectados+=contador;
        return regAfectados;

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