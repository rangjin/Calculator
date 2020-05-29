public interface Element {
    public abstract char getType();
}

class Open implements Element {
    private char type;

    Open() {
        this.type = '(';
    }

    @Override
    public char getType() {
        return type;
    }
}

class Close implements Element {
    private char type;

    Close() {
        this.type = ')';
    }

    @Override
    public char getType() {
        return type;
    }
}

interface Operate extends Element {
    public abstract double getValue();
}

class Operand implements Operate {
    private double value;
    private char type;

    Operand (double value) {
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

interface Operator extends Operate {
    public abstract void calculate(double a, double b);
}

class Plus implements Operator {
    private double value;
    private char type;

    Plus() {
        this.type = '+';
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
        this.value = a + b;
    }
}

class Minus implements Operator {
    private double value;
    private char type;

    Minus() {
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

class Time implements Operator {
    private double value;
    private char type;

    Time() {
        this.type = '*';
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
        this.value = a * b;
    }
}

class Div implements Operator {
    private double value;
    private char type;

    Div() {
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