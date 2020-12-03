package dao.postgres;

import dao.ISolicitudDao;
import dto.SolicitudDto;
import java.util.Date;
import java.util.List;

public class SolicitudDao extends RepositoryDao<SolicitudDto, Integer> implements ISolicitudDao {

    @Override
    public List<SolicitudDto> buscarPorFuncionario(Integer fkTipoIdentificacion) {
        String query = String.format("select * from %s where fk_tipo_identificacion = ? order by respondida, fecha_registro, id  ", getTableName());

        return listDtoByQuery(query, fkTipoIdentificacion);
    }

    @Override
    public List<SolicitudDto> buscarPorMotivo(Integer fkMotivoSolicitud) {
        String query = String.format("select * from %s where fk_motivo_solicitud = ? order by respondida, fecha_registro, id  ", getTableName());

        return listDtoByQuery(query, fkMotivoSolicitud);
    }

    @Override
    public List<SolicitudDto> buscarPorUsuario(Integer fkUsuario) {
        String query = String.format("select * from %s where fk_solicitud = ? order by respondida, fecha_registro, id  ", getTableName());

        return listDtoByQuery(query, fkUsuario);
    }

    @Override
    public List<SolicitudDto> listarTodas() {
        String query = String.format("select * from %s order by respondida, fecha_registro, id ", getTableName());

        return listDtoByQuery(query);
    }

    @Override
    public SolicitudDto guardar(SolicitudDto solicitud) {
        return save(solicitud);
    }

    @Override
    public SolicitudDto buscarPorId(Integer id) {
        return getById(id);
    }

    @Override
    public List<SolicitudDto> buscarPorRangoFecha(Date fechaInicial, Date fechaFinal) {
        String query = String.format("select * from %s where DATE(fecha_registro) between ? and ? order by respondida, fecha_registro, id ", getTableName());

        return listDtoByQuery(query, new java.sql.Date(fechaInicial.getTime()), new java.sql.Date(fechaFinal.getTime()));
    }

    @Override
    public List<SolicitudDto> buscarPorRangoFechaEstado(Date fechaInicial, Date fechaFinal, Boolean respondida) {
        String query = String.format("select * from %s where respondida = ? and DATE(fecha_registro) between ? and ? order by respondida, fecha_registro, id ", getTableName());

        return listDtoByQuery(query, respondida, new java.sql.Date(fechaInicial.getTime()), new java.sql.Date(fechaFinal.getTime()));
    }

    @Override
    public List<SolicitudDto> buscarFuncionarioPorRangoFecha(Integer funcionario, Date fechaInicial, Date fechaFinal) {
        String query = String.format("select * from %s where fk_funcionario = ? and DATE(fecha_registro) between ? and ? order by respondida, fecha_registro, id ", getTableName());

        return listDtoByQuery(query, funcionario, new java.sql.Date(fechaInicial.getTime()), new java.sql.Date(fechaFinal.getTime()));
    }

    @Override
    public List<SolicitudDto> buscarFuncionarioPorRangoFechaEstado(Integer funcionario, Date fechaInicial, Date fechaFinal, Boolean respondida) {
        String query = String.format("select * from %s where fk_funcionario = ? and respondida = ? and DATE(fecha_registro) between ? and ? order by respondida, fecha_registro, id ", getTableName());

        return listDtoByQuery(query, funcionario, respondida, new java.sql.Date(fechaInicial.getTime()), new java.sql.Date(fechaFinal.getTime()));
    }

}
