package dao;

import dto.MotivoSolicitudDto;
import java.util.List;

public interface IMotivoSolicitudDao {

    public List<MotivoSolicitudDto> listarPorTipoSolicitud(Integer fkTipoSolicitud);
    
    public MotivoSolicitudDto buscarPorId(Integer id);
    
    public MotivoSolicitudDto guardar(MotivoSolicitudDto motivo);
    
}
