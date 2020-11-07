package dao;

import dto.ArchivoDto;

public interface IArchivoDao {

    public ArchivoDto guardar(ArchivoDto archivo);
    
    public ArchivoDto buscarPorId(Integer id);
    
    public boolean eliminarPorId(Integer id);
    
}
