package Calculator;

import Element.*;
import java.util.*;

public class Hexadecimal extends Calculator {
    public Hexadecimal(String str, String remain) {
        super(str, remain);
    }

    @Override
    public int checkError() {
        if (super.checkError() == 1) {
            return 1;
        }

        int s = 0, e, i = 0;
        char[] expr = getExpr();

        while (true) {
            if (expr[i] == ' ') {
                e = i;
                putTokens(expr, s, e);
                s = i + 1;
            } else if (expr[i] == '\0') {
                break;
            }
            i++;
        }

        return 0;
    }

    @Override
    public void putTokens(char[] expr, int s, int e) {
        Vector<Element> tokens = getTokens();
        Element el = null;

        char ch = categorize(expr[s]);

        if (ch == 'o') {
            el = new Open();
        } else if (ch == 'c') {
            el = new Close();
        } else if (ch == 'n') {
            char[] arr = new char[e - s];
            System.arraycopy(expr, s, arr, 0, e - s);

            int i = Integer.parseInt(String.valueOf(arr), 16);
            el = new Operand(i);
        } else if (ch == 'e') {
            if (expr[s] == '+') {
                el = new Plus();
            } else if (expr[s] == '-') {
                el = new Minus();
            } else if (expr[s] == '*') {
                el = new Time();
            } else {
                el = new Div();
            }
        }

        tokens.add(el);
        setTokens(tokens);
    }

    @Override
    public void evaluation() {
        super.evaluation();

        String value = getValue();
        double num = Double.parseDouble(value);
        value = Integer.toHexString((int) num);
        setValue(value);
    }
}
