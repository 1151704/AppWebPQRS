package dto;

import dao.postgres.TipoSolicitudDao;
import java.io.Serializable;
import util.Column;
import util.Table;

@Table(name = "motivo_solicitud")
public class MotivoSolicitudDto implements Serializable {

    private Integer id;

    @Column(name = "fk_tipo_solicitud")
    private Integer fkTipoSolicitud;

    private String descripcion;

    @Column(name = "habilitar_entrada")
    private Boolean habilitarEntrada;

    private Boolean habilitado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkTipoSolicitud() {
        return fkTipoSolicitud;
    }

    public void setFkTipoSolicitud(Integer fkTipoSolicitud) {
        this.fkTipoSolicitud = fkTipoSolicitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getHabilitarEntrada() {
        return habilitarEntrada;
    }

    public void setHabilitarEntrada(Boolean habilitarEntrada) {
        this.habilitarEntrada = habilitarEntrada;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public TipoSolicitudDto getTipo() {
        return new TipoSolicitudDao().buscarPorId(this.fkTipoSolicitud);
    }

}
