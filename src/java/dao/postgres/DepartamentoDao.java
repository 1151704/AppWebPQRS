package dao.postgres;

import dao.IDepartamentoDao;
import dto.DepartamentoDto;
import java.util.List;

public class DepartamentoDao extends GenericDao<DepartamentoDto, Integer> implements IDepartamentoDao {

    private static final String NAME_TABLE = "departamento";
    private static final String NAME_COLUMN_ID = "id";
    
    public DepartamentoDao() {
        super(DepartamentoDto.class, NAME_TABLE, NAME_COLUMN_ID);
    }

    @Override
    public List<DepartamentoDto> listarDepartamentos() {
        return listAll();
    }

    @Override
    public DepartamentoDto buscarPorCodigo(String codigo) {
        String query = String.format("select * from %s where codigo = ? ", NAME_TABLE);
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

    @Override
    public DepartamentoDto guardar(DepartamentoDto departamento) {
        return save(departamento);
    }
    
    

}
