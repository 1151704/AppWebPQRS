package service;

import dao.IDepartamentoDao;
import dao.IMunicipioDao;

public interface IService {
    
    public IDepartamentoDao serviceDepartamento();
    
    public IMunicipioDao serviceMunicipio();
    
}
