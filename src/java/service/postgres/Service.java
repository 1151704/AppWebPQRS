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
import dao.postgres.ArchivoDao;
import dao.postgres.DepartamentoDao;
import dao.postgres.FuncionarioDao;
import dao.postgres.MotivoSolicitudDao;
import dao.postgres.MunicipioDao;
import dao.postgres.SolicitudArchivosDao;
import dao.postgres.SolicitudDao;
import dao.postgres.TipoIdentificacionDao;
import dao.postgres.TipoSolicitudDao;
import java.io.Serializable;
import service.IService;
import util.Utilidades;

public class Service implements IService, Serializable {

    private Utilidades utilidades;

    public Service() {
        this.utilidades = new Utilidades();
    }

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
        return new ArchivoDao();
    }

    @Override
    public IFuncionarioDao serviceFuncionario() {
        return new FuncionarioDao();
    }

    @Override
    public IMotivoSolicitudDao serviceMotivoSolicitud() {
        return new MotivoSolicitudDao();
    }

    @Override
    public ISolicitudArchivosDao serviceSolicitudArchivos() {
        return new SolicitudArchivosDao();
    }

    @Override
    public ISolicitudDao serviceSolicitud() {
        return new SolicitudDao();
    }

    @Override
    public ITipoIdentificacionDao serviceTipoIdentificacion() {
        return new TipoIdentificacionDao();
    }

    @Override
    public ITipoSolicitudDao serviceTipoSolicitud() {
        return new TipoSolicitudDao();
    }

    @Override
    public ITipoUsuarioDao serviceTipoUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IUsuarioDao serviceUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Utilidades getUtilidades() {
        return utilidades;
    }

}
