package Element;

public class Close implements Element {
    private final double value = '\0';
    private final char type;

    public Close() {
        this.type = ')';
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