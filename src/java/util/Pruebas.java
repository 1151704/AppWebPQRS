package util;

import dto.DepartamentoDto;
import negocio.Negocio;

public class Pruebas {

    public void test1() {

        Negocio service = new Negocio();
        
        DepartamentoDto d = new DepartamentoDto();
        d.setId(2);
        d.setCodigo("54");
        d.setNombre("Nawdwdr--");
        
        System.out.println(d);
        d = service.serviceDepartamento().guardar(d);
        System.out.println(d);
    }

    public static void main(String[] args) {
        Pruebas p = new Pruebas();

        p.test1();
    }
}
