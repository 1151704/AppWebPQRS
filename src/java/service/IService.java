package service;

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

public interface IService {

    public IDepartamentoDao serviceDepartamento();

    public IMunicipioDao serviceMunicipio();

    public IArchivoDao serviceArchivo();

    public IFuncionarioDao serviceFuncionario();

    public IMotivoSolicitudDao serviceMotivoSolicitud();

    public ISolicitudArchivosDao serviceSolicitudArchivos();

    public ISolicitudDao serviceSolicitud();

    public ITipoIdentificacionDao serviceTipoIdentificacion();

    public ITipoSolicitudDao serviceTipoSolicitud();

    public ITipoUsuarioDao serviceTipoUsuario();

    public IUsuarioDao serviceUsuario();

}
