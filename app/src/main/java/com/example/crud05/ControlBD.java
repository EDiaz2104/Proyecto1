package com.example.crud05;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.crud05.Modelos.Preferencial;
import com.example.crud05.Modelos.Usuario;

public class ControlBD{
    private static final String[]camposTipoPago = new String []{"idTipoPago","tipoPago"};
    private static final String[]camposDetallepedido = new String []{"idDetallePedido","idTipoPago","idProducto","cantidad","EstadoPedido"};
    private static final String[]camposProducto = new String []{"idProducto","NombreProducto","idLocal","idProveedor"};
    private static final String[]camposHorario = new String []{"idhorario","idlocal","dia","apertura","cierre"};
    private static final String[]camposLocal = new String []{"idlocal", "idencargadolocal", "idubicacion", "nomlocal"};
    private static final String[]camposUbicacion = new String []{"idubicacion","descripcionubicacion"};
    private static final String[]camposEncargadoLocal = new String []{"idencargadolocal", "idusuario", "nomencargado", "apelencargado","telencargado"};
    private static final String[]camposDetalleProducto = new String []{"idDetalleProduc", "idProducto", "cantidadProducto", "precioProducto"};
    private static final String[]camposPedido = new String [] {"idPedido","idLocal","idCombo","idUsuario", "idDetallePedido","FechaPedido"};
    private static final String[]camposPedidoAsignado = new String [] {"idPedidoAsignado","idPedido","idRepartidor"};
    private static final String[]camposRepartidor = new String [] {"idRepartidor","idLocal","NombreRepartidor","CarnetRepartidor"};

    private static final String[] campos_usuario = new String[] {
            "idusuario",
            "nombreUsuario",
            "apelUsuario",
            "telUsuario",
            "direccionUsuario",
            "estadoUsuario",
            "emailUsuario",
            "claveUsuario"
    };
    private static final String[] campos_preferencial = new String[] {
            "idPreferencial",
            "idUsuario",
            "idLocal"
    };

    private static final String[]camposCategoria = new String [] {"idCategoria","idProducto","NombreCategoria","DescripcionCategoria"};


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
            db.execSQL("CREATE TABLE categoriaproducto(idCategoria INTEGER NOT NULL PRIMARY KEY,idProducto INTEGER,NombreCategoria VARCHAR(30), DescripcionCategoria VARCHAR(30));");
            db.execSQL("CREATE TABLE pedidoasignado(idPedidoAsignado INTEGER NOT NULL PRIMARY KEY,idPedido INTEGER,idRepartidor INTEGER);");
            db.execSQL("CREATE TABLE pedido(idPedido INTEGER NOT NULL PRIMARY KEY,idLocal INTEGER,idCombo INTEGER, idUsuario INTEGER,idDetallePedido INTEGER,FechaPedido VARCHAR(30));");

            db.execSQL("CREATE TABLE ubicacion(idubicacion INTEGER NOT NULL PRIMARY KEY, descripcionubicacion VARCHAR(50));");
            db.execSQL("CREATE TABLE encargadolocal(idencargadolocal INTEGER NOT NULL PRIMARY KEY, idusuario INTEGER NOT NULL, nomencargado VARCHAR(50), apelencargado VARCHAR(50), telencargado VARCHAR(10));");
            db.execSQL("CREATE TABLE local(idlocal INTEGER NOT NULL PRIMARY KEY, idencargadolocal INTEGER NOT NULL, idubicacion INTEGER NOT NULL, nomlocal VARCHAR(50));");
            db.execSQL("CREATE TABLE horario(idhorario INTEGER NOT NULL PRIMARY KEY, idlocal INTEGER NOT NULL, dia VARCHAR(10), apertura VARCHAR(10),cierre VARCHAR(10));");
            db.execSQL("CREATE TABLE detalleproducto(idDetalleProduc INTEGER NOT NULL PRIMARY KEY, idProducto INTEGER NOT NULL, cantidadProducto INTEGER NOT NULL, precioProducto VARCHAR(8));");

            db.execSQL("CREATE TABLE Usuario (" +
                    "idusuario INTEGER PRIMARY KEY ," +
                    "nombreUsuario TEXT," +
                    "apelUsuario TEXT," +
                    "telUsuario TEXT," +
                    "direccionUsuario TEXT," +
                    "estadoUsuario INTEGER," +
                    "emailUsuario TEXT," +
                    "claveUsuario TEXT" +
                    ")");
            db.execSQL("CREATE TABLE Preferencial (" +
                    "idPreferencial INTEGER PRIMARY KEY," +
                    "idUsuario INTEGER," +
                    "idLocal INTEGER" +
                    ")");

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
            detalle.put("EstadoPedido", detallepedido.getEstadoPedido());
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
            String [] id = {String.valueOf(Integer.valueOf(detallePedido.getIdTipoPago())), String.valueOf(detallePedido.getIdProducto()), String.valueOf(detallePedido.getIdDetallePedido())};
            ContentValues cv = new ContentValues();
            cv.put("cantidad", detallePedido.getCantidad());
            cv.put("EstadoPedido", detallePedido.getIdDetallePedido());
            db.update("detallepedido", cv, "idDetallepedido = ? AND idProducto = ? AND idTipoPago = ? ", id);
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
            Producto product = new Producto();
            product.setIdProducto(cursor.getInt(0));
            product.setNombreProducto(cursor.getString(1));
            product.setIdLocal(cursor.getInt(2));
            product.setIdProveedor(cursor.getInt(3));
            return product;
        }else{
            return null;
        }
    }
    public DetallePedido consultarDetallepedido(String idTipoPago,String idProducto, String idDetallepedido){
        String[] id = {idTipoPago,idProducto, idDetallepedido};
        Cursor cursor = db.query("detallepedido", camposDetallepedido, "idDetallePedido = ? AND idTipoPago = ? AND idProducto = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            DetallePedido detalle = new DetallePedido();
            detalle.setIdDetallePedido(cursor.getInt(0));
            detalle.setIdTipoPago(cursor.getInt(1));
            detalle.setIdProducto(cursor.getInt(2));
            detalle.setCantidad(cursor.getInt(3));
            detalle.setEstadoPedido(cursor.getInt(4));
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

    /////////////////////////////////DETALLE///////////////////////////////////

    public String insertarDetalleP(DetalleProducto detalle){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues registro = new ContentValues();
        registro.put("idDetalleProduc", detalle.getIdDetalleProduc());
        registro.put("idProducto", detalle.getIdProducto());
        registro.put("cantidadProducto", detalle.getCantidadProducto());
        registro.put("precioProducto", detalle.getPrecioProducto());
        contador=db.insert("detalleproducto", null, registro);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String actualizarDetalleP(DetalleProducto detalle){
        Integer v_id = detalle.getIdDetalleProduc();        //Pequeño artifisio para convertir el int en string
        String[] id = {String.valueOf(v_id)};
        ContentValues registro = new ContentValues();
        registro.put("idProducto", detalle.getIdProducto());
        registro.put("cantidadProducto", detalle.getCantidadProducto());
        registro.put("precioProducto", detalle.getPrecioProducto());
        int contador = db.update("detalleproducto", registro, "idDetalleProduc = ?", id); //el id solo acepta string

        if(contador == 1){
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con ID " + detalle.getIdDetalleProduc() + " no existe";
        }
    }

    public DetalleProducto consultarDetalleP(String idDetalleProduc){
        String[] id = {idDetalleProduc};
        Cursor cursor = db.query("detalleproducto", camposDetalleProducto, "idDetalleProduc = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            DetalleProducto detalle = new DetalleProducto();
            detalle.setIdDetalleProduc(cursor.getInt(0));
            detalle.setIdProducto(cursor.getInt(1));
            detalle.setCantidadProducto(cursor.getInt(2));
            detalle.setPrecioProducto(cursor.getString(3));
            return detalle;
        }else{
            return null;
        }
    }

    public String eliminarDetalleP(DetalleProducto detalle){
        String regAfectados="filas afectadas= ";
        int contador=0;
        //if (verificarIntegridad(hora,3)) {
        //    contador+=db.delete("nota", "carnet='"+hora.getidubicacion()+"'", null);
        //}
        contador+=db.delete("detalleproducto", "idDetalleProduc='"+detalle.getIdDetalleProduc()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    /////////////////////////////////FIN///////////////////////////////////

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
            return "Registro con pedido " + pedido.getIdPedido() + " no existe";
        }
    }

    public String eliminar(Pedido pedido){
        String regAfectados="filas afectadas= ";
        int contador=0;
        if (pedido!=null) {
            contador+=db.delete("pedido", "idPedido='"+pedido.getIdPedido()+"'", null);
        }
        contador+=db.delete("pedido", "idPedido='"+pedido.getIdPedido()+"'", null);
        regAfectados+=contador;
        return regAfectados;

    }

    public String insertar(Repartidor repartidor){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues ped = new ContentValues();
        ped.put("idRepartidor", repartidor.getIdRepartidor());
        ped.put("idLocal", repartidor.getIdLocal());
        ped.put("NombreRepartidor", repartidor.getNombreRepartidor());
        ped.put("CarnetRepartidor", repartidor.getCarnetRepartidor());
        contador=db.insert("repartidor", null, ped);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String actualizar(Repartidor repartidor){
        if(repartidor!=null){
            String[] id = {String.valueOf(repartidor.getIdRepartidor())};
            ContentValues cv = new ContentValues();
            cv.put("idRepartidor", repartidor.getIdRepartidor());
            cv.put("idLocal", repartidor.getIdLocal());
            cv.put("NombreRepartidor", repartidor.getNombreRepartidor());
            cv.put("CarnetRepartidor", repartidor.getCarnetRepartidor());
            db.update("repartidor", cv, "idRepartidor = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con Repartidor " + repartidor.getIdRepartidor() + " no existe";
        }
    }

    public Repartidor consultarRepartidor(String idRepartidor){

        String[] id = {idRepartidor};
        Cursor cursor = db.query("repartidor", camposRepartidor, "idRepartidor = ?",
                id, null, null, null);
        if(cursor.moveToFirst()){
            Repartidor repartidor= new Repartidor();
            repartidor.setIdRepartidor(cursor.getInt(0));
            repartidor.setIdLocal(cursor.getInt(1));
            repartidor.setNombreRepartidor(cursor.getString(2));
            repartidor.setCarnetRepartidor(cursor.getString(3));
            return repartidor;
        }else{
            return null;
        }

    }

    public String eliminar(Repartidor repartidor){
        String regAfectados="filas afectadas= ";
        int contador=0;
        if (repartidor!=null) {
            contador+=db.delete("repartidor", "idRepartidor='"+repartidor.getIdRepartidor()+"'", null);
        }
        contador+=db.delete("repartidor", "idRepartidor='"+repartidor.getIdRepartidor()+"'", null);
        regAfectados+=contador;
        return regAfectados;

    }

    public String insertar(Categoria categoria){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues ped = new ContentValues();
        ped.put("idCategoria", categoria.getIdCategoria());
        ped.put("idProducto", categoria.getIdProducto());
        ped.put("NombreCategoria", categoria.getNombreCategoria());
        ped.put("DescripcionCategoria", categoria.getDescripcionCategoria());
        contador=db.insert("categoriaproducto", null, ped);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String actualizar(Categoria categoria){
        if(categoria!=null){
            String[] id = {String.valueOf(categoria.getIdCategoria())};
            ContentValues cv = new ContentValues();
            cv.put("idCategoria", categoria.getIdCategoria());
            cv.put("idProducto", categoria.getIdProducto());
            cv.put("NombreCategoria", categoria.getNombreCategoria());
            cv.put("DescripcionCategoria", categoria.getDescripcionCategoria());
            db.update("categoriaproducto", cv, "idCategoria = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con Categoria " + categoria.getIdCategoria() + " no existe";
        }
    }

    public String eliminar(Categoria categoria){
        String regAfectados="filas afectadas= ";
        int contador=0;
        if (categoria!=null) {
            contador+=db.delete("categoriaproducto", "idCategoria='"+categoria.getIdCategoria()+"'", null);
        }
        contador+=db.delete("categoriaproducto", "idCategoria='"+categoria.getIdCategoria()+"'", null);
        regAfectados+=contador;
        return regAfectados;

    }
    public Categoria consultarCategoria(String idCategoria){

        String[] id = {idCategoria};
        Cursor cursor = db.query("categoriaproducto", camposCategoria, "idCategoria = ?",
                id, null, null, null);
        if(cursor.moveToFirst()){
            Categoria categoria= new Categoria();
            categoria.setIdCategoria(cursor.getInt(0));
            categoria.setIdProducto(cursor.getInt(1));
            categoria.setNombreCategoria(cursor.getString(2));
            categoria.setDescripcionCategoria(cursor.getString(3));
            return categoria;
        }else{
            return null;
        }

    }

    public String insertar(PedidoAsignado pedidoasignado){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues ped = new ContentValues();
        ped.put("idPedidoAsignado", pedidoasignado.getIdPedidoAsignado());
        ped.put("idPedido", pedidoasignado.getIdPedido());
        ped.put("idRepartidor", pedidoasignado.getIdRepartidor());
        contador=db.insert("pedidoasignado", null, ped);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    public String actualizar(PedidoAsignado pedidoasignado){
        if(pedidoasignado!=null){
            String[] id = {String.valueOf(pedidoasignado.getIdPedido())};
            ContentValues cv = new ContentValues();
            cv.put("idPedidoAsignado", pedidoasignado.getIdPedidoAsignado());
            cv.put("idPedido", pedidoasignado.getIdPedido());
            cv.put("idRepartidor", pedidoasignado.getIdRepartidor());
            db.update("pedidoasignado", cv, "idPedidoAsignado = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con Pedido Asignado " + pedidoasignado.getIdPedidoAsignado() + " no existe";
        }
    }

    public String eliminar(PedidoAsignado pedidoasignado){
        String regAfectados="filas afectadas= ";
        int contador=0;
        if (pedidoasignado!=null) {
            contador+=db.delete("pedidoasignado", "idPedidoAsignado='"+pedidoasignado.getIdPedidoAsignado()+"'", null);
        }
        contador+=db.delete("pedidoasignado", "idPedidoAsignado='"+pedidoasignado.getIdPedidoAsignado()+"'", null);
        regAfectados+=contador;
        return regAfectados;

    }

    public PedidoAsignado consultarPedidoAsignado(String idPedidoAsignado){

        String[] id = {idPedidoAsignado};
        Cursor cursor = db.query("pedidoasignado", camposPedidoAsignado, "idPedidoAsignado = ?",
                id, null, null, null);
        if(cursor.moveToFirst()){
            PedidoAsignado pedidoAsignado= new PedidoAsignado();
            pedidoAsignado.setIdPedidoAsignado(cursor.getInt(0));
            pedidoAsignado.setIdPedido(cursor.getInt(1));
            pedidoAsignado.setIdRepartidor(cursor.getInt(2));
            return pedidoAsignado;
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
    //Usuario y Preferencial
    public String insertar(Usuario usuario){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues usu = new ContentValues();
        usu.put("nombreUsuario", usuario.getNombreUsuario());
        usu.put("apelUsuario", usuario.getApelUsuario());
        usu.put("telUsuario", usuario.getTelUsuario());
        usu.put("direccionUsuario", usuario.getDireccionUsuario());
        usu.put("estadoUsuario", usuario.getEstadoUsuario());
        usu.put("emailUsuario", usuario.getEmailUsuario());
        usu.put("claveUsuario", usuario.getClaveUsuario());
        contador=db.insert("Usuario", null, usu);
        if(contador==-1 || contador==0){regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }else{
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String insertar(Preferencial preferencial){
        if (verificarIntegridad2(preferencial, 2)){
            String regInsertados="Registro Insertado Nº= ";
            long contador=0;
            ContentValues pre = new ContentValues();
            pre.put("idLocal", preferencial.getIdLocal());
            pre.put("idUsuario",preferencial.getIdUsuario());

            contador=db.insert("Preferencial", null, pre);
            if(contador==-1 || contador==0){regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
            }else{
                regInsertados=regInsertados+contador;
            }
            return regInsertados;
        }
        else{
            return"Error de integridad. Registro usuario o local no existe";
        }
    }

    //Actualizar
    public String actualizar(Usuario usuario){
        if (verificarIntegridad2(usuario,3)){
            String[] id = {String.valueOf(usuario.getIdUsuario())};
            ContentValues cv = new ContentValues();
            cv.put("nombreUsuario", usuario.getNombreUsuario());
            cv.put("apelUsuario", usuario.getApelUsuario());
            cv.put("telUsuario", usuario.getTelUsuario());
            cv.put("direccionUsuario", usuario.getDireccionUsuario());
            cv.put("estadoUsuario", usuario.getEstadoUsuario());
            cv.put("emailUsuario", usuario.getEmailUsuario());
            cv.put("claveUsuario", usuario.getClaveUsuario());
            db.update("Usuario", cv, "idUsuario = ?", id);
            return"Registro Actualizado Correctamente";
        }
        else{
            return"Registro de usuario con id "+usuario.getIdUsuario()+" no existe";
        }
    }

    public String actualizar(Preferencial preferencial){
        if (verificarIntegridad2(preferencial,5)){
            String[] id = {String.valueOf(preferencial.getIdPreferencial())};
            ContentValues cv = new ContentValues();
            cv.put("idUsuario", preferencial.getIdUsuario());
            cv.put("idLocal", preferencial.getIdLocal());
            db.update("Preferencial", cv, "idPreferencial = ?", id);
            return"Registro Actualizado Correctamente";
        }
        else{
            return"Registro de preferencial con id "+preferencial.getIdPreferencial()+" no existe";
        }
    }

    //Eliminar
    public String eliminar(Usuario usuario){
        String regAfectados="filas afectadas= ";
        if (verificarIntegridad2(usuario,3)){
            db.delete("Usuario", "idUsuario='"+usuario.getIdUsuario()+"'", null);
            return"Registro Borrado Correctamente";
        }
        else{
            return"Registro de usuario con id "+usuario.getIdUsuario()+" no existe";
        }
    }

    public String eliminar(Preferencial preferencial){
        String regAfectados="filas afectadas= ";
        if (verificarIntegridad2(preferencial,5)){
            db.delete("Preferencial", "idPreferencial='"+preferencial.getIdPreferencial()+"'", null);
            return"Registro Borrado Correctamente";
        }
        else{
            return"Registro de preferencial con id "+preferencial.getIdUsuario()+" no existe";
        }    }

    //Consultar
    public Usuario consultarUsuario(String idU){
        String[] id = {idU};
        Cursor cursor = db.query("Usuario", campos_usuario, "idUsuario = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(cursor.getInt(0));
            usuario.setNombreUsuario(cursor.getString(1));
            usuario.setApelUsuario(cursor.getString(2));
            usuario.setTelUsuario(cursor.getString(3));
            usuario.setDireccionUsuario(cursor.getString(4));
            usuario.setEstadoUsuario(cursor.getInt(5));
            usuario.setEmailUsuario(cursor.getString(6));
            usuario.setClaveUsuario(cursor.getString(7));
            return usuario;
        }else{
            return null;
        }
    }

    public Preferencial consultarPrefererncial(String idP){
        String[] idp = {idP};
        Cursor cursor = db.query("Preferencial", campos_preferencial, "idPreferencial = ?", idp, null, null, null);
        if(cursor.moveToFirst()){
            Preferencial preferencial = new Preferencial();
            preferencial.setIdPreferencial(cursor.getInt(0));
            preferencial.setIdUsuario(cursor.getInt(1));
            preferencial.setIdLocal(cursor.getInt(2));
            return preferencial;
        }else{
            return null;
        }
    }

    //Integridades
    private boolean verificarIntegridad2(Object dato, int relacion) throws SQLException{
        switch(relacion){
            //Preferencial insertar -> exista Usuario y Local
            case 2:
            {
                Preferencial pre = (Preferencial) dato;
                String[] id1 = {String.valueOf(pre.getIdUsuario())};
                String[] id2 = {String.valueOf(pre.getIdLocal())};
                Cursor cursor1 = db.query("Usuario", null, "idUsuario = ?", id1, null, null, null);
                Cursor cursor2 = db.query("Local", null, "idLocal = ?", id2, null, null, null);
                if(cursor1.moveToFirst() && cursor2.moveToFirst()){
                    return true;
                }
                else return false;
            }
            //verificar que exista Usuario
            case 3:
            {   Usuario usuario = (Usuario)dato;
                String[] id = {String.valueOf(usuario.getIdUsuario())};
                abrir();
                Cursor c = db.query("Usuario", null, "idUsuario = ?", id, null, null, null);
                if(c.moveToFirst()){
                    //Se encontro Usuario
                    return true;
                }
                else{
                    return false;
                }
            }
            //verificar que exista Preferencial
            case 5:
            {
                Preferencial preferencial = (Preferencial) dato;
                String[] id3 = {String.valueOf(preferencial.getIdPreferencial())};
                abrir();
                Cursor c3 = db.query("Preferencial", null, "idPreferencial = ?", id3, null, null, null);
                if (c3.moveToFirst()) {
                    //Se encontro Preferencial
                    return true;
                } else {
                    return false;
                }
            }
            default: return false;
        }
    }
    public String llenarBD(){
        final int[] VTidTipoPago = {1,2};
        final String[] VTtipoPago = {"Credito","Tarjeta"};
        final int[] VPidProducto = {1,2};
        final String[] VPNombreProducto = {"Carne","Lacteos"};
        final int[] VPidLocal = {1,2};
        final int[] VPProveedor = {1,2};
        final int[] VDidDetallePedido = {1,2,3,4};
        final int[] VDidTipoPago = {1,1,2,2};
        final int[] VDidProducto = {1,2,1,1};
        final int[] VDcantidad = {2,5,6,7};
        final int[] VDEstadoPedido = {0,1,1,0};

        abrir();
        db.execSQL("DELETE FROM tipopago");
        db.execSQL("DELETE FROM producto");
        db.execSQL("DELETE FROM detallepedido");
        TipoPago tipo = new TipoPago();
        for(int i=0;i<2;i++){
            tipo.setIdTipoPago(VTidTipoPago[i]);
            tipo.setTipoPago(VTtipoPago[i]);
            insertar(tipo);
        }
        Producto p = new Producto();
        for(int i=0;i<2;i++){
            p.setIdProducto(VPidProducto[i]);
            p.setNombreProducto(VPNombreProducto[i]);
            p.setIdLocal(VPidLocal[i]);
            p.setIdProveedor(VPProveedor[i]);
            insertar(p);
        }
        DetallePedido d = new DetallePedido();
        for(int i=0;i<4;i++){
            d.setIdDetallePedido(VDidDetallePedido[i]);
            d.setIdTipoPago(VDidTipoPago[i]);
            d.setIdProducto(VDidProducto[i]);
            d.setCantidad(VDcantidad[i]);
            d.setEstadoPedido(VDEstadoPedido[i]);
            insertar(d);
        }
        cerrar();
        return "Guardo Correctamente";
}

}