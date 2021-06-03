package com.example.crud05.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsuarioAPI {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("nombreUsuario")
    private String nombreUsuario;

    @Expose
    @SerializedName("apelUsuario")
    private String apelUsuario;

    @Expose
    @SerializedName("telUsuario")
    private String telUsuario;

    @Expose
    @SerializedName("direccionUsuario")
    private String direccionUsuario;

    @Expose
    @SerializedName("estadoUsuario")
    private String estadoUsuario;

    @Expose
    @SerializedName("emailUsuario")
    private String emailUsuario;

    @Expose
    @SerializedName("claveUsuario")
    private String claveUsuario;

    public UsuarioAPI() {
    }

    public UsuarioAPI(String id, String nombreUsuario, String apelUsuario, String telUsuario, String direccionUsuario, String estadoUsuario, String emailUsuario, String claveUsuario) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.apelUsuario = apelUsuario;
        this.telUsuario = telUsuario;
        this.direccionUsuario = direccionUsuario;
        this.estadoUsuario = estadoUsuario;
        this.emailUsuario = emailUsuario;
        this.claveUsuario = claveUsuario;
    }

    public String getIdUsuario() {
        return id;
    }

    public void setIdUsuario(String id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApelUsuario() {
        return apelUsuario;
    }

    public void setApelUsuario(String apelUsuario) {
        this.apelUsuario = apelUsuario;
    }

    public String getTelUsuario() {
        return telUsuario;
    }

    public void setTelUsuario(String telUsuario) {
        this.telUsuario = telUsuario;
    }

    public String getDireccionUsuario() {
        return direccionUsuario;
    }

    public void setDireccionUsuario(String direccionUsuario) {
        this.direccionUsuario = direccionUsuario;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }
}
