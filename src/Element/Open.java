package Element;

public class Open implements Element {
    private final char type;

    public Open() {
        this.type = '(';
    }

    @Override
    public char getType() {
        return type;
    }
}
