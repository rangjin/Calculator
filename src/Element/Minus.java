package Element;

public class Minus implements Operator {
    private double value;
    private final char type;

    public Minus() {
        this.type = '-';
    }

    @Override
    public char getType() {
        return type;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void calculate(double a, double b) {
        this.value = a - b;
    }
}
