package dao.postgres;

import dao.IDepartamentoDao;
import dto.DepartamentoDto;
import java.util.List;

public class DepartamentoDao extends RepositoryDao<DepartamentoDto, Integer> implements IDepartamentoDao {
    
    @Override
    public List<DepartamentoDto> listarDepartamentos() {
        return listAll();
    }

    @Override
    public DepartamentoDto buscarPorCodigo(String codigo) {
        String query = String.format("select * from %s where codigo = ? ", getTableName());
        List<DepartamentoDto> listado = listDtoByQuery(query, codigo);

        if (listado.isEmpty()) {
            return null;
        }
        return listado.get(0);
    }

    @Override
    public DepartamentoDto buscarPorId(Integer id) {
        return getById(id);
    }
    
    

}
