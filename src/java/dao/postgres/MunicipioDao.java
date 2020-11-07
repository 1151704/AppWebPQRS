package dao.postgres;

import dao.IMunicipioDao;
import dto.MunicipioDto;
import java.util.List;

public class MunicipioDao extends RepositoryDao<MunicipioDto, Integer> implements IMunicipioDao {

    @Override
    public List<MunicipioDto> buscarByIdDepartamento(Integer idDepartamento) {
        String query = String.format("select * from %s where fk_departamento = ? ", getTableName());
        return listDtoByQuery(query, idDepartamento);
    }

    @Override
    public MunicipioDto buscarById(Integer id) {
        return getById(id);
    }

}
