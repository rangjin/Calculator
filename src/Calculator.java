import java.util.*;

public class Calculator {
    private char[] expr;
    private Vector<Element> tokens = new Vector<Element>();
    private Vector<Element> postfix = new Vector<Element>();
    private double value;

    public Calculator(String str) {
        str = str + "\0";
        this.expr = str.toCharArray();
    }

    public char[] getExpr() {
        return this.expr;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public void setTokens(Vector<Element> tokens) {
        this.tokens = tokens;
    }

    public Vector<Element> getTokens() {
        return this.tokens;
    }

    public void setPostfix(Vector<Element> postfix) {
        this.postfix = postfix;
    }

    public Vector<Element> getPostfix() {
        return this.postfix;
    }
}
