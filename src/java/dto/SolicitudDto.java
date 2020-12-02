package dto;

import dao.postgres.FuncionarioDao;
import dao.postgres.MotivoSolicitudDao;
import dao.postgres.UsuarioDao;
import java.io.Serializable;
import java.util.Date;
import service.postgres.Service;
import util.Column;
import util.ColumnTimestamp;
import util.Table;

@Table(name = "solicitud")
public class SolicitudDto implements Serializable {

    private Integer id;

    @Column(name = "fk_motivo_solicitud")
    private Integer fkMotivoSolicitud;

    @Column(name = "fk_funcionario")
    private Integer fkFuncionario;

    @Column(name = "fk_usuario")
    private Integer fkUsuario;

    @Column(name = "otro_motivo")
    private String otroMotivo;

    private String descripcion;

    private String respuesta;

    private Boolean respondida;

    @Column(name = "fecha_registro")
    @ColumnTimestamp
    private Date fechaRegistro;

    @Column(name = "fecha_respuesta")
    @ColumnTimestamp
    private Date fechaRespuesta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkMotivoSolicitud() {
        return fkMotivoSolicitud;
    }

    public void setFkMotivoSolicitud(Integer fkMotivoSolicitud) {
        this.fkMotivoSolicitud = fkMotivoSolicitud;
    }

    public Integer getFkFuncionario() {
        return fkFuncionario;
    }

    public void setFkFuncionario(Integer fkFuncionario) {
        this.fkFuncionario = fkFuncionario;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public String getOtroMotivo() {
        return otroMotivo;
    }

    public void setOtroMotivo(String otroMotivo) {
        this.otroMotivo = otroMotivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Boolean getRespondida() {
        return respondida;
    }

    public void setRespondida(Boolean respondida) {
        this.respondida = respondida;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    @Override
    public String toString() {
        return "SolicitudDto{" + "id=" + id + ", fkMotivoSolicitud=" + fkMotivoSolicitud + ", fkFuncionario=" + fkFuncionario + ", fkUsuario=" + fkUsuario + ", otroMotivo=" + otroMotivo + ", descripcion=" + descripcion + ", respuesta=" + respuesta + ", respondida=" + respondida + ", fechaRegistro=" + fechaRegistro + ", fechaRespuesta=" + fechaRespuesta + '}';
    }

    public UsuarioDto getUsuario() {
        return new UsuarioDao().buscarPorId(this.fkUsuario);
    }

    public MotivoSolicitudDto getMotivo() {
        return new MotivoSolicitudDao().buscarPorId(this.fkMotivoSolicitud);
    }
    
    public FuncionarioDto getFuncionario() {
        return new FuncionarioDao().buscarPorId(this.fkFuncionario);
    }

}
