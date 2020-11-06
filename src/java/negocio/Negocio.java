package negocio;

import dao.IDepartamentoDao;
import dao.postgres.DepartamentoDao;

public class Negocio implements INegocio {

    @Override
    public IDepartamentoDao serviceDepartamento() {
        return new DepartamentoDao();
    }
    
}
