package Element;

public class Open implements Element {
    private final double value = '\0';
    private final char type;

    public Open() {
        this.type = '(';
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
