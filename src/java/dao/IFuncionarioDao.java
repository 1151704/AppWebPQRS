package dao;

import dto.FuncionarioDto;
import java.util.List;

public interface IFuncionarioDao {

    public List<FuncionarioDto> listarTodos();

    public List<FuncionarioDto> listarPorBusqueda(String busqueda);

    public FuncionarioDto buscarPorId(Integer id);

    public FuncionarioDto buscarPorCodigo(String codigo);

    public FuncionarioDto buscarPorIdentificacion(String identificacion);
    
    public FuncionarioDto buscarDisponible();
    
    public FuncionarioDto guardar(FuncionarioDto funcionario);
    
    public FuncionarioDto verificarAccesoCuenta(String username, String password);
    
}
