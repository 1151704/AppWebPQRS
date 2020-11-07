package dto;

import java.io.Serializable;
import java.util.Date;
import util.Column;
import util.Table;

@Table(name = "solicitud_archivos")
public class UsuarioDto implements Serializable {

    private Integer id;

    @Column(name = "fk_tipo_usuario")
    private Integer fkTipoUsuario;

    @Column(name = "fk_tipo_identificacion")
    private Integer fkTipoIdentificacion;

    @Column(name = "fk_municipio")
    private Integer fkMunicipio;

    @Column(name = "codigo_interno")
    private String codigoInterno;

    private String identificacion;

    @Column(name = "primer_nombre")
    private String primerNombre;

    @Column(name = "primer_apellido")
    private String primerApellido;

    @Column(name = "segundo_nombre")
    private String segundoNombre;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @Column(name = "telefono_fijo")
    private String telefonoFijo;

    private String celular;

    private String correo;

    private String direccion;

    private String barrio;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkTipoUsuario() {
        return fkTipoUsuario;
    }

    public void setFkTipoUsuario(Integer fkTipoUsuario) {
        this.fkTipoUsuario = fkTipoUsuario;
    }

    public Integer getFkTipoIdentificacion() {
        return fkTipoIdentificacion;
    }

    public void setFkTipoIdentificacion(Integer fkTipoIdentificacion) {
        this.fkTipoIdentificacion = fkTipoIdentificacion;
    }

    public Integer getFkMunicipio() {
        return fkMunicipio;
    }

    public void setFkMunicipio(Integer fkMunicipio) {
        this.fkMunicipio = fkMunicipio;
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

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "UsuarioDto{" + "id=" + id + ", fkTipoUsuario=" + fkTipoUsuario + ", fkTipoIdentificacion=" + fkTipoIdentificacion + ", fkMunicipio=" + fkMunicipio + ", codigoInterno=" + codigoInterno + ", identificacion=" + identificacion + ", primerNombre=" + primerNombre + ", primerApellido=" + primerApellido + ", segundoNombre=" + segundoNombre + ", segundoApellido=" + segundoApellido + ", telefonoFijo=" + telefonoFijo + ", celular=" + celular + ", correo=" + correo + ", direccion=" + direccion + ", barrio=" + barrio + ", fechaRegistro=" + fechaRegistro + '}';
    }

}
