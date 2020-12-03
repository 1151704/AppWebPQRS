package dao;

import dto.TipoUsuarioDto;
import java.util.List;

public interface ITipoUsuarioDao {

    public TipoUsuarioDto guardar(TipoUsuarioDto tipoUsu);
    
    public TipoUsuarioDto buscarPorId(Integer id);
    
    public List<TipoUsuarioDto> listarTodos();
    
    public List<TipoUsuarioDto> listarActivos();
    
}
