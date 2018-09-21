package ga.julen.mathpractice;

import java.util.concurrent.ThreadLocalRandom;

public class Operacion {

    private int num1,num2,result;

    /**
     * Constructor de una nueva operación, la inicializa según el nievl
     * @param level Nivel actual de la aplicación
     */
    public Operacion(int level) {
        num1 = generarNumero(level);
        num2 = generarNumero(level);
        switch (level) {
            case 3:
                result = num1 - num2;
                break;
            default:
                result = num1 + num2;
                break;
        }

    }

    /**
     * @param level Nivel actual de la aplicación
     * @return Genera un número aleatorio según el nivel
     */
    private int generarNumero(int level) {
        switch (level) {
            case 1:
                return ThreadLocalRandom.current().nextInt(1, 10);
            default:
                return ThreadLocalRandom.current().nextInt(10, 100);
        }
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getResult() {
        return result;
    }

}
