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
import dao.postgres.TipoUsuarioDao;
import dao.postgres.UsuarioDao;
import java.io.Serializable;
import service.IService;
import util.Utilidades;

public class Service implements IService, Serializable {

    private final Utilidades utilidades;

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
        return new TipoUsuarioDao();
    }

    @Override
    public IUsuarioDao serviceUsuario() {
        return new UsuarioDao();
    }

    public Utilidades getUtilidades() {
        return utilidades;
    }

}
