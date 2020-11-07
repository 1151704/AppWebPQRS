package dto;

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
    
    private Boolean habilitar;

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

    public Boolean getHabilitar() {
        return habilitar;
    }

    public void setHabilitar(Boolean habilitar) {
        this.habilitar = habilitar;
    }

    @Override
    public String toString() {
        return "MotivoSolicitudDto{" + "id=" + id + ", fkTipoSolicitud=" + fkTipoSolicitud + ", descripcion=" + descripcion + ", habilitarEntrada=" + habilitarEntrada + ", habilitar=" + habilitar + '}';
    }

}
