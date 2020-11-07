package util;

import service.postgres.Service;

public class Pruebas {

    public void test1() {

        Service service = new Service();
        
        System.out.println(service.serviceDepartamento().buscarPorId(8));
        
    }

    public static void main(String[] args) {
        Pruebas p = new Pruebas();

        p.test1();
    }
}
