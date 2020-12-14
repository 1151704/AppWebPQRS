package dao.postgres;

import dao.IFuncionarioDao;
import dto.FuncionarioDto;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao extends RepositoryDao<FuncionarioDto, Integer> implements IFuncionarioDao {

    @Override
    public List<FuncionarioDto> listarTodos() {
        return listAll();
    }

    @Override
    public List<FuncionarioDto> listarPorBusqueda(String busqueda) {

        String query = String.format("select * from %s where "
                + "coalesce(identificacion, '') || ' ' || "
                + "coalesce(codigo_interno, '') || ' ' || "
                + "coalesce(nombre_completo,'') ~ ? "
                + "order by nombre_completo", getTableName());

        return listDtoByQuery(query, busqueda);
    }

    @Override
    public FuncionarioDto buscarPorId(Integer id) {
        return getById(id);
    }

    @Override
    public FuncionarioDto buscarPorCodigo(String codigo) {
        String query = String.format("select * from %s codigo_interno = ? ", getTableName());

        List<FuncionarioDto> listado = listDtoByQuery(query, codigo);

        if (listado.isEmpty()) {
            return null;
        }
        return listado.get(0);
    }

    @Override
    public FuncionarioDto buscarPorIdentificacion(String identificacion) {
        String query = String.format("select * from %s where identificacion = ? ", getTableName());

        List<FuncionarioDto> listado = listDtoByQuery(query, identificacion);

        if (listado.isEmpty()) {
            return null;
        }
        return listado.get(0);
    }

    @Override
    public FuncionarioDto guardar(FuncionarioDto funcionario) {
        return save(funcionario);
    }

    @Override
    public FuncionarioDto buscarDisponible() {

        String query = String.format("select f.* from funcionario f "
                + "left outer join solicitud s ON (s.fk_funcionario = f.id and DATE(f.fecha_registro) = current_date) "
                + "where f.es_administrador = false "
                + "group by f.id order by count(s.id), f.id limit 1 ", getTableName());

        List<FuncionarioDto> listado = listDtoByQuery(query);

        if (listado.isEmpty()) {
            return null;
        }
        return listado.get(0);
    }

    @Override
    public FuncionarioDto verificarAccesoCuenta(String username, String password) {

        String query = String.format("select * from %s "
                + "where identificacion = ? and contrasena = ? limit 1", getTableName());

        List<FuncionarioDto> listado = listDtoByQuery(query, username, password);

        if (listado.isEmpty()) {
            return null;
        }
        return listado.get(0);
    }

}
