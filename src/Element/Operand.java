package Element;

public class Operand implements Element {
    private final double value;
    private final char type;

    public Operand(double value) {
        this.value = value;
        this.type = 'n';
    }

    @Override
    public char getType() {
        return type;
    }

    @Override
    public double getValue() {
        return value;
    }
}
