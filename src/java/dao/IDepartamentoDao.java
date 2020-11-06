package dao;

import dto.DepartamentoDto;
import java.util.List;

public interface IDepartamentoDao {

    public List<DepartamentoDto> listarDepartamentos();

    public DepartamentoDto buscarPorId(Integer id);

    public DepartamentoDto buscarPorCodigo(String codigo);

    public DepartamentoDto guardar(DepartamentoDto departamento);

}
