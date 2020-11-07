package dao.postgres;

import dao.IMotivoSolicitudDao;
import dto.MotivoSolicitudDto;
import java.util.List;

public class MotivoSolicitudDao extends RepositoryDao<MotivoSolicitudDto, Integer> implements IMotivoSolicitudDao {

    @Override
    public List<MotivoSolicitudDto> listarPorTipoSolicitud(Integer fkTipoSolicitud) {

        String query = String.format("select * from %s where fk_tipo_solicitud = ? ", getTableName());

        return listDtoByQuery(query, fkTipoSolicitud);
    }

    @Override
    public MotivoSolicitudDto buscarPorId(Integer id) {
        return getById(id);
    }

    @Override
    public MotivoSolicitudDto guardar(MotivoSolicitudDto motivo) {
        return save(motivo);
    }

}
