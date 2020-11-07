package dao.postgres;

import dao.ISolicitudArchivosDao;
import dto.SolicitudArchivosDto;
import java.util.List;

public class SolicitudArchivosDao extends RepositoryDao<SolicitudArchivosDto, Integer> implements ISolicitudArchivosDao {

    @Override
    public List<SolicitudArchivosDto> listarPorSolicitud(Integer fkSolicitud) {
        String query = String.format("select * from %s where fk_solicitud = ? ", getTableName());

        return listDtoByQuery(query, fkSolicitud);
    }

    @Override
    public List<SolicitudArchivosDto> listarPorSolicitudRespuesta(Integer fkSolicitud, Boolean esRespuesta) {
        String query = String.format("select * from %s where fk_solicitud = ? and es_respuesta = ?", getTableName());

        return listDtoByQuery(query, fkSolicitud, esRespuesta);
    }

    @Override
    public SolicitudArchivosDto buscarPorId(Integer id) {
        return getById(id);
    }

    @Override
    public SolicitudArchivosDto guardar(SolicitudArchivosDto solicitud) {
        return save(solicitud);
    }

}
