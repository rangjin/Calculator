package Element;

public class Div implements Operator {
    private double value;
    private final char type;

    public Div() {
        this.type = '/';
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
        this.value = a / b;
    }
}
