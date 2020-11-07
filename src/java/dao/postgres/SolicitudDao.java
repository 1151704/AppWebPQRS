package dao.postgres;

import dao.ISolicitudDao;
import dto.SolicitudDto;
import java.util.List;

public class SolicitudDao extends RepositoryDao<SolicitudDto, Integer> implements ISolicitudDao {

    @Override
    public List<SolicitudDto> buscarPorFuncionario(Integer fkTipoIdentificacion) {
        String query = String.format("select * from %s where fk_tipo_identificacion = ? ", getTableName());

        return listDtoByQuery(query, fkTipoIdentificacion);
    }

    @Override
    public List<SolicitudDto> buscarPorMotivo(Integer fkMotivoSolicitud) {
        String query = String.format("select * from %s where fk_motivo_solicitud = ? ", getTableName());

        return listDtoByQuery(query, fkMotivoSolicitud);
    }

    @Override
    public List<SolicitudDto> buscarPorUsuario(Integer fkUsuario) {
        String query = String.format("select * from %s where fk_solicitud = ? ", getTableName());

        return listDtoByQuery(query, fkUsuario);
    }

    @Override
    public List<SolicitudDto> listarTodas() {
        return listAll();
    }

    @Override
    public SolicitudDto guardar(SolicitudDto solicitud) {
        return save(solicitud);
    }

    @Override
    public SolicitudDto buscarPorId(Integer id) {
        return getById(id);
    }

}
