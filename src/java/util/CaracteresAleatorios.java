package util;

import java.util.Random;

public class CaracteresAleatorios {

    private static int randomNum() {
        Random r = new Random();
        int aleatorio = r.nextInt(9) + 1;
        return aleatorio;
    }

    private static char randomLetraMinus() {
        char a;

        Random r = new Random();
        double aleatorio = Math.floor(Math.random() * (122 - 97 + 1) + 97);

        a = (char) aleatorio;

        return a;
    }

    private static char randomLetraMayus() {
        char a;

        Random r = new Random();
        double aleatorio = Math.floor(Math.random() * (90 - 65 + 1) + 65);

        a = (char) aleatorio;

        return a;
    }

    public static String getCodigoAlfaNumerico(int tamano) {
        char letra;
        String codigo = "";
        int num, aleatorio;
        Random r = new Random();
        for (int i = 1; i <= tamano; i++) {
            aleatorio = r.nextInt(3) + 1;
            if (aleatorio == 1) {
                num = randomNum();
                codigo += String.valueOf(num);
            }
            if (aleatorio == 2) {
                letra = randomLetraMinus();
                codigo += String.valueOf(letra);
            }
            if (aleatorio == 3) {
                letra = randomLetraMayus();
                codigo += String.valueOf(letra);
            }
        }
        return codigo;
    }

    public static String getCodigoAlfabetico(int tamano) {
        char letra;
        String codigo = "";
        int aleatorio;
        Random r = new Random();
        for (int i = 1; i <= tamano; i++) {
            aleatorio = r.nextInt(2) + 1;
            if (aleatorio == 1) {
                letra = randomLetraMayus();
                codigo += String.valueOf(letra);
            }
            if (aleatorio == 2) {
                letra = randomLetraMinus();
                codigo += String.valueOf(letra);
            }
        }
        return codigo;
    }

    public static String getCodigoNumerico(int tamano) {
        String codigo = "";
        int num, aleatorio;
        Random r = new Random();
        for (int i = 1; i <= tamano; i++) {
            aleatorio = r.nextInt(1) + 1;
            if (aleatorio == 1) {
                num = randomNum();
                codigo += String.valueOf(num);
            }
        }
        return codigo;
    }

//    public static void main(String[] args) {
//        CaracteresAleatorios na = new CaracteresAleatorios();
//        System.out.println(na.getCodigoAlfaNumerico(16) + ", " + na.getCodigoAlfaNumerico(16).length());
//        System.out.println(na.getCodigoAlfabetico(16) + ", " + na.getCodigoAlfabetico(16).length());
//        System.out.println(na.getCodigoNumerico(16) + ", " + na.getCodigoNumerico(16).length());
//    }
}
