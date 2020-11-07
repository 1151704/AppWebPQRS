package dao.postgres;

import dao.IUsuarioDao;
import dto.UsuarioDto;
import java.util.List;

public class UsuarioDao extends RepositoryDao<UsuarioDto, Integer> implements IUsuarioDao {

    @Override
    public UsuarioDto guardar(UsuarioDto usuario) {
        return save(usuario);
    }

    @Override
    public UsuarioDto buscarPorId(Integer id) {
        return getById(id);
    }

    @Override
    public UsuarioDto buscarPorIdentificacion(String identificacion) {
        String query = String.format("select * from %s where identificacion = ? ", getTableName());

        List<UsuarioDto> listado = listDtoByQuery(query, identificacion);
        
        if (listado.isEmpty()) {
            return null;
        }
        return listado.get(0);
    }

    @Override
    public List<UsuarioDto> buscarPorTipoUsuario(Integer fkTipoUsuario) {
        String query = String.format("select * from %s where fk_tipo_usuario = ? ", getTableName());

        return listDtoByQuery(query, fkTipoUsuario);
    }

    @Override
    public List<UsuarioDto> listarPorBusqueda(String busqueda) {
        String query = String.format("select * from %s where "
                + "coalesce(identificacion, '') || ' ' || "
                + "coalesce(primer_nombre, '') || ' ' || "
                + "coalesce(primer_apellido, '') || ' ' || "
                + "coalesce(correo, '') || ' ' || "
                + "coalesce(codigo_interno,'') ~ ? "
                + "order by primer_nombre, primer_apellido", getTableName());

        return listDtoByQuery(query, busqueda);
    }

}
