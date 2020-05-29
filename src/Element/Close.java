package Element;

public class Close implements Element {
    private final char type;

    public Close() {
        this.type = ')';
    }

    @Override
    public char getType() {
        return type;
    }
}