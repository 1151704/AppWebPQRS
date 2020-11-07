package dao;

import dto.TipoIdentificacionDto;
import java.util.List;

public interface ITipoIdentificacionDao {
    
    public TipoIdentificacionDto guardar(TipoIdentificacionDto tipoId);
    
    public TipoIdentificacionDto buscarPorId(Integer id);
    
    public List<TipoIdentificacionDto> listarTodos();

}
