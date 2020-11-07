package dao.postgres;

import dao.IArchivoDao;
import dto.ArchivoDto;

public class ArchivoDao extends RepositoryDao<ArchivoDto, Integer> implements IArchivoDao {

    @Override
    public ArchivoDto guardar(ArchivoDto archivo) {
        return save(archivo);
    }

    @Override
    public ArchivoDto buscarPorId(Integer id) {
        return getById(id);
    }

    @Override
    public boolean eliminarPorId(Integer id) {
        return deleteById(id);
    }

}
