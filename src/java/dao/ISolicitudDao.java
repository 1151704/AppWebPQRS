package dao;

import dto.SolicitudDto;
import java.util.List;

public interface ISolicitudDao {

    public List<SolicitudDto> buscarPorFuncionario(Integer fkTipoIdentificacion);

    public List<SolicitudDto> buscarPorMotivo(Integer fkMotivoSolicitud);

    public List<SolicitudDto> buscarPorUsuario(Integer fkUsuario);

    public List<SolicitudDto> listarTodas();
    
    public SolicitudDto guardar(SolicitudDto solicitud);
    
    public SolicitudDto buscarPorId(Integer id);
    
}
