package com.example.crud05.Modelos;

public class UsuarioAPI {
    private String idUsuario;
    private String nombreUsuario;
    private String apelUsuario;
    private String telUsuario;
    private String direccionUsuario;
    private String estadoUsuario;
    private String emailUsuario;
    private String claveUsuario;

    public UsuarioAPI() {
    }

    public UsuarioAPI(String idUsuario, String nombreUsuario, String apelUsuario, String telUsuario, String direccionUsuario, String estadoUsuario, String emailUsuario, String claveUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apelUsuario = apelUsuario;
        this.telUsuario = telUsuario;
        this.direccionUsuario = direccionUsuario;
        this.estadoUsuario = estadoUsuario;
        this.emailUsuario = emailUsuario;
        this.claveUsuario = claveUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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
