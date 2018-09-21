package ga.julen.mathpractice;

import java.util.concurrent.ThreadLocalRandom;

public class Operacion {

    private int num1;
    private int num2;
    private int result;

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

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
