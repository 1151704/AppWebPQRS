package dto;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import util.Column;
import util.Table;

@Table(name = "archivo")
public class ArchivoDto implements Serializable {

    private Integer id;

    @Column(name = "nombre_por_usuario")
    private String nombrePorUsuario;

    @Column(name = "nombre_en_servidor")
    private String nombreEnServidor;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrePorUsuario() {
        return nombrePorUsuario;
    }

    public void setNombrePorUsuario(String nombrePorUsuario) {
        this.nombrePorUsuario = nombrePorUsuario;
    }

    public String getNombreEnServidor() {
        return nombreEnServidor;
    }

    public void setNombreEnServidor(String nombreEnServidor) {
        this.nombreEnServidor = nombreEnServidor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "ArchivoDto{" + "id=" + id + ", nombrePorUsuario=" + nombrePorUsuario + ", nombreEnServidor=" + nombreEnServidor + ", fechaRegistro=" + fechaRegistro + '}';
    }

    public String getUrlArchivo() {
        ResourceBundle rb = ResourceBundle.getBundle("resources.parametros");
        return rb.getString("FTP_PUBLIC") + "/" + this.nombreEnServidor;
    }

}
