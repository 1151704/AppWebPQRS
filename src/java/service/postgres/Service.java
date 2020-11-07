package service.postgres;

import dao.IArchivoDao;
import dao.IDepartamentoDao;
import dao.IFuncionarioDao;
import dao.IMotivoSolicitudDao;
import dao.IMunicipioDao;
import dao.ISolicitudArchivosDao;
import dao.ISolicitudDao;
import dao.ITipoIdentificacionDao;
import dao.ITipoSolicitudDao;
import dao.ITipoUsuarioDao;
import dao.IUsuarioDao;
import dao.postgres.DepartamentoDao;
import dao.postgres.MunicipioDao;
import service.IService;

public class Service implements IService {

    @Override
    public IDepartamentoDao serviceDepartamento() {
        return new DepartamentoDao();
    }

    @Override
    public IMunicipioDao serviceMunicipio() {
        return new MunicipioDao();
    }

    @Override
    public IArchivoDao serviceArchivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IFuncionarioDao serviceFuncionario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IMotivoSolicitudDao serviceMotivoSolicitud() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ISolicitudArchivosDao serviceSolicitudArchivos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ISolicitudDao serviceSolicitud() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ITipoIdentificacionDao serviceTipoIdentificacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ITipoSolicitudDao serviceTipoSolicitud() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ITipoUsuarioDao serviceTipoUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IUsuarioDao serviceUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
