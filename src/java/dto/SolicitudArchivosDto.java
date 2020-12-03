package dto;

import dao.postgres.ArchivoDao;
import java.io.Serializable;
import util.Column;
import util.Table;

@Table(name = "solicitud_archivos")
public class SolicitudArchivosDto implements Serializable {

    private Integer id;

    @Column(name = "fk_solicitud")
    private Integer fkSolicitud;

    @Column(name = "fk_archivo")
    private Integer fkArchivo;

    @Column(name = "es_respuesta")
    private Boolean esRespuesta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkSolicitud() {
        return fkSolicitud;
    }

    public void setFkSolicitud(Integer fkSolicitud) {
        this.fkSolicitud = fkSolicitud;
    }

    public Integer getFkArchivo() {
        return fkArchivo;
    }

    public void setFkArchivo(Integer fkArchivo) {
        this.fkArchivo = fkArchivo;
    }

    public Boolean getEsRespuesta() {
        return esRespuesta;
    }

    public void setEsRespuesta(Boolean esRespuesta) {
        this.esRespuesta = esRespuesta;
    }

    @Override
    public String toString() {
        return "SolicitudArchivosDto{" + "id=" + id + ", fkSolicitud=" + fkSolicitud + ", fkArchivo=" + fkArchivo + ", esRespuesta=" + esRespuesta + '}';
    }

    public ArchivoDto getArchivo() {
        return new ArchivoDao().buscarPorId(this.fkArchivo);
    }

}
