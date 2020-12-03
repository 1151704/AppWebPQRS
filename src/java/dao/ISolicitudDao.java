package dao;

import dto.SolicitudDto;
import java.util.Date;
import java.util.List;

public interface ISolicitudDao {

    public List<SolicitudDto> buscarPorFuncionario(Integer fkTipoIdentificacion);

    public List<SolicitudDto> buscarPorMotivo(Integer fkMotivoSolicitud);

    public List<SolicitudDto> buscarPorUsuario(Integer fkUsuario);

    public List<SolicitudDto> buscarPorRangoFecha(Date fechaInicial, Date fechaFinal);

    public List<SolicitudDto> buscarPorRangoFechaEstado(Date fechaInicial, Date fechaFinal, Boolean respondida);

    public List<SolicitudDto> buscarFuncionarioPorRangoFecha(Integer funcionario, Date fechaInicial, Date fechaFinal);

    public List<SolicitudDto> buscarFuncionarioPorRangoFechaEstado(Integer funcionario, Date fechaInicial, Date fechaFinal, Boolean respondida);

    public List<SolicitudDto> listarTodas();

    public SolicitudDto guardar(SolicitudDto solicitud);

    public SolicitudDto buscarPorId(Integer id);

}
