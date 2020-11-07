package dao;

import dto.SolicitudArchivosDto;
import java.util.List;

public interface ISolicitudArchivosDao {

    public List<SolicitudArchivosDto> listarPorSolicitud(Integer fkSolicitud);

    public List<SolicitudArchivosDto> listarPorSolicitudRespuesta(Integer fkSolicitud, Boolean esRespuesta);

    public SolicitudArchivosDto buscarPorId(Integer id);

    public SolicitudArchivosDto guardar(SolicitudArchivosDto solicitud);

}
