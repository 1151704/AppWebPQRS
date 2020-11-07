package dao.postgres;

import dao.ITipoUsuarioDao;
import dto.TipoUsuarioDto;
import java.util.List;

public class TipoUsuarioDao extends RepositoryDao<TipoUsuarioDto, Integer> implements ITipoUsuarioDao {

    @Override
    public TipoUsuarioDto guardar(TipoUsuarioDto tipoUsu) {
        return save(tipoUsu);
    }

    @Override
    public TipoUsuarioDto buscarPorId(Integer id) {
        return getById(id);
    }

    @Override
    public List<TipoUsuarioDto> listarTodos() {
        return listAll();
    }

}
