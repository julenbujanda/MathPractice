package ga.julen.mathpractice;

public class Operacion {

    private int num1;
    private int num2;
    private int result;

    public Operacion(int level) {
        int number1 = generarNumero(level);
        int number2;
        if (level != 3) {
            number2 = generarNumero(level);
            result = number1 + number2;
        } else {
            do {
                number2 = generarNumero(level);
            } while (number2 >= number1);
            result = number1 - number2;
        }
    }

    private int generarNumero(int level) {
        switch (level) {
            case 1:
                return (int) (10 * Math.random());
            case 2:
                return (int) (100 * Math.random());
            case 3:
                return (int) (100 * Math.random());
            default:
                return 0;
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
