package dto;

import java.io.Serializable;
import util.Table;

@Table(name = "tipo_solicitud")
public class TipoSolicitudDto implements Serializable {

    private Integer id;
    
    private String descripcion;
    
    private Boolean habilitado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public String toString() {
        return "TipoSolicitudDto{" + "id=" + id + ", descripcion=" + descripcion + ", habilitado=" + habilitado + '}';
    }

}
