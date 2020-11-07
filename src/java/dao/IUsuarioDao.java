package dao;

import dto.UsuarioDto;
import java.util.List;

public interface IUsuarioDao {

    public UsuarioDto guardar(UsuarioDto usuario);

    public UsuarioDto buscarPorId(Integer id);

    public UsuarioDto buscarPorIdentificacion(String identificacion);

    public List<UsuarioDto> buscarPorTipoUsuario(Integer fkTipoUsuario);

    public List<UsuarioDto> listarPorBusqueda(String busqueda);

}
