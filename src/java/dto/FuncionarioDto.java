package dto;

import java.io.Serializable;
import java.util.Date;
import util.Column;
import util.Table;

@Table(name = "funcionario")
public class FuncionarioDto implements Serializable {

    private Integer id;

    @Column(name = "fk_tipo_identificacion")
    private Integer fkTipoIdentificacion;

    private String codigoInterno;

    private String identificacion;

    @Column(name = "nombreCompleto")
    private String nombreCompleto;

    private String cargo;

    private String celular;

    private String correo;

    private String contrasena;

    @Column(name = "es_administrador")
    private Boolean esAdministrador;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Column(name = "fecha_ultimo_ingreso")
    private Date fechaUltimoIngreso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkTipoIdentificacion() {
        return fkTipoIdentificacion;
    }

    public void setFkTipoIdentificacion(Integer fkTipoIdentificacion) {
        this.fkTipoIdentificacion = fkTipoIdentificacion;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEsAdministrador() {
        return esAdministrador;
    }

    public void setEsAdministrador(Boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getFechaUltimoIngreso() {
        return fechaUltimoIngreso;
    }

    public void setFechaUltimoIngreso(Date fechaUltimoIngreso) {
        this.fechaUltimoIngreso = fechaUltimoIngreso;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "id=" + id + ", fkTipoIdentificacion=" + fkTipoIdentificacion + ", codigoInterno=" + codigoInterno + ", identificacion=" + identificacion + ", nombreCompleto=" + nombreCompleto + ", cargo=" + cargo + ", celular=" + celular + ", correo=" + correo + ", contrasena=" + contrasena + ", esAdministrador=" + esAdministrador + ", fechaRegistro=" + fechaRegistro + ", fechaModificacion=" + fechaModificacion + ", fechaUltimoIngreso=" + fechaUltimoIngreso + '}';
    }

}
