package com.example.crud05.Modelos;

public class Preferencial {
    private Integer idPreferencial;
    private Integer idUsuario;
    private Integer idLocal;

    public Preferencial() {
    }

    public Preferencial(Integer idPreferencial, Integer idUsuario, Integer idLocal) {
        this.idPreferencial = idPreferencial;
        this.idUsuario = idUsuario;
        this.idLocal = idLocal;
    }

    public Integer getIdPreferencial() {
        return idPreferencial;
    }

    public void setIdPreferencial(Integer idPreferencial) {
        this.idPreferencial = idPreferencial;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }
}

