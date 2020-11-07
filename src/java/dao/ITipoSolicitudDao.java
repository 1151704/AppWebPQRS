package dao;

import dto.TipoSolicitudDto;
import java.util.List;

public interface ITipoSolicitudDao {

    public TipoSolicitudDto guardar(TipoSolicitudDto tipoSol);
    
    public TipoSolicitudDto buscarPorId(Integer id);
    
    public List<TipoSolicitudDto> listarTodos();
    
}
