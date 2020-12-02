package dto;

import java.io.Serializable;

public class Mensaje implements Serializable {

    private String titulo;
    private String mensaje;
    private TipoMensaje tipo;

    public Mensaje(String titulo, String mensaje, TipoMensaje tipo) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public TipoMensaje getTipo() {
        return tipo;
    }

    public void setTipo(TipoMensaje tipo) {
        this.tipo = tipo;
    }

}
