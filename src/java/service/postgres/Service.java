package service.postgres;

import dao.IDepartamentoDao;
import dao.IMunicipioDao;
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

}
