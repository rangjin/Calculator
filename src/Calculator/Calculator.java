package Calculator;

import Element.*;
import java.util.*;

public class Calculator {
    private char[] expr;
    private Vector<Element> tokens = new Vector<>();
    private Vector<Element> postfix = new Vector<>();
    private String value;
    private final String remain;
    private String expression = "";

    public Calculator(String str, String remain) {
        this.expr = (str + "\0").toCharArray();
        this.remain = remain;
    }

    public void setExpr() {
        this.expr = (expression + "\0").toCharArray();
    }

    public char[] getExpr() {
        return this.expr;
    }

    public void setTokens(Vector<Element> tokens) {
        this.tokens = tokens;
    }

    public Vector<Element> getTokens() {
        return this.tokens;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public String getExpression() {
        return expression;
    }

    public int checkError() {
        int i = 0, s, e, cnt = 0;
        char ch;
        char[] expr = getExpr();

        while (true) {
            ch = categorize(expr[i]);

            if (i == 0) {
                if (ch == 's') {
                    i++;
                    while (true) {
                        ch = categorize(expr[i]);
                        if (ch == 's') {
                            i++;
                        } else if (ch == 'o' || ch == 'n') {
                            i = 0;
                            ch = categorize(expr[i]);
                            break;
                        } else if (ch == 'e') {
                            if (remain.equals("none")) {
                                return 1;
                            } else {
                                concat(remain.toCharArray(), 0, remain.toCharArray().length - 1);
                                i = 0;
                                ch = categorize(expr[i]);
                                break;
                            }
                        } else {
                            return 1;
                        }
                    }
                } else if (ch == 'e') {
                    if (remain.equals("none")) {
                        return 1;
                    } else {
                        concat(remain.toCharArray(), 0, remain.toCharArray().length - 1);
                    }
                } else {
                    if (!(ch == 'o' || ch == 'n')) {
                        return 1;
                    }
                }
            }

            if (ch == 'n') {
                s = i;
                e = i;
                i++;
                while (true) {
                    ch = categorize(expr[i]);
                    if (ch == 't') {
                        concat(expr, s, e);
                        if (cnt == 0) {
                            setExpr();
                            return 0;
                        } else {
                            return 1;
                        }
                    } else if (ch == 'p') {
                        e++;
                        i++;
                        ch = categorize(expr[i]);
                        if (ch == 'n') {
                            e++;
                            i++;
                            while (true) {
                                ch = categorize(expr[i]);
                                if (ch == 'n') {
                                    e++;
                                } else if (ch == 't') {
                                    concat(expr, s, e);
                                    setExpr();
                                    return 0;
                                } else if (ch == 'c' || ch == 'e') {
                                    concat(expr, s, e);
                                    break;
                                } else if (ch == 's') {
                                    i++;
                                    while (true) {
                                        ch = categorize(expr[i]);
                                        if (ch == 'n' || ch == 'o' || ch == 'r') {
                                            return 1;
                                        } else if (ch == 'c' || ch == 'e') {
                                            concat(expr, s, e);
                                            break;
                                        } else if (ch == 't') {
                                            setExpr();
                                            return 0;
                                        }
                                        i++;
                                    }
                                    break;
                                } else {
                                    return 1;
                                }
                                i++;
                            }
                            i = e;
                            break;
                        } else {
                            return 1;
                        }
                    } else if (ch == 's') {
                        i++;
                        while (true) {
                            ch = categorize(expr[i]);
                            if (ch == 'n' || ch == 'o' || ch == 'r' || ch == 'p') {
                                return 1;
                            } else if (ch == 'c' || ch == 'e') {
                                concat(expr, s, e);
                                break;
                            } else if (ch == 't') {
                                concat(expr, s, e);
                                setExpr();
                                return 0;
                            }
                            i++;
                        }
                        i = e;
                        break;
                    } else if (ch == 'n') {
                        e++;
                    } else if (ch == 'e' || ch == 'c') {
                        concat(expr, s, e);
                        i = e;
                        break;
                    } else if (ch == 'o' || ch == 'r') {
                        return 1;
                    }
                    i++;
                }
            } else if (ch == 'e') {
                s = i;
                e = i;
                i++;
                while (true) {
                    ch = categorize(expr[i]);
                    if (ch == 'n' || ch == 'o') {
                        concat(expr, s, e);
                        i = e;
                        break;
                    } else if (ch == 'r' || ch == 'e' || ch == 'c' || ch == 't' || ch == 'p') {
                        return 1;
                    }
                    i++;
                }
            } else if (ch == 'o') {
                cnt++;
                s = i;
                e = i;
                i++;
                while (true) {
                    ch = categorize(expr[i]);
                    if (ch == 'n' || ch == 'o') {
                        concat(expr, s, e);
                        i = e;
                        break;
                    } else if (ch == 'e' || ch == 'c' || ch == 't' || ch == 'r' || ch == 'p') {
                        return 1;
                    }
                    i++;
                }
            } else if (ch == 'c') {
                cnt--;
                if (cnt < 0) {
                    return 1;
                }
                s = i;
                e = i;
                i++;
                while (true) {
                    ch = categorize(expr[i]);
                    if (ch == 't') {
                        concat(expr, s, e);
                        if (cnt == 0) {
                            setExpr();
                            return 0;
                        } else {
                            return 1;
                        }
                    } else if (ch == 'e' || ch == 'c') {
                        concat(expr, s, e);
                        i = e;
                        break;
                    } else if (ch == 'n' || ch == 'o' || ch == 'r' || ch == 'p') {
                        return 1;
                    }
                    i++;
                }
            }
            i++;
        }
    }

    public char categorize(char c) {
        if (c == 40) {
            return 'o'; // open
        } else if (c == 41) {
            return 'c'; // close
        } else if ((c >= 48 && c <= 57) || (c >= 65 && c <= 70) || (c >= 97 && c <= 102)) {
            return 'n'; // number
        } else if (c == 32) {
            return 's'; // space
        } else if (c == '\0') {
            return 't'; // zero
        } else if (c == 42 || c == 43 || c == 45 || c == 47) {
            return 'e'; // operator
        } else if (c == 46) {
            return 'p'; // point
        } else {
            return 'r'; // else
        }
    }

    public void concat(char[] expr, int s, int e) {
        e++;
        char[] arr = new char[e - s];
        System.arraycopy(expr, s, arr, 0, e - s);

        expression = expression + String.valueOf(arr) + " ";
    }

    public void putTokens(char[] expr, int s, int e) {
        Element el = null;

        char ch = categorize(expr[s]);

        if (ch == 'o') {
            el = new Open();
        } else if (ch == 'c') {
            el = new Close();
        } else if (ch == 'n') {
            char[] arr = new char[e - s];

            System.arraycopy(expr, s, arr, 0, e - s);

            String str = String.valueOf(arr);
            el = new Operand(Double.parseDouble(str));
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
    }

    public void makePostfix() {
        Vector<Element> stack = new Vector<>();
        Element t, s;

        for (Element token : tokens) {
            t = token;
            switch (check(t.getType())) {
                case 0:
                    stack.add(t);
                    break;
                case 1:
                    while (true) {
                        s = stack.get(stack.size() - 1);
                        if (check(s.getType()) == 0) {
                            stack.remove(stack.size() - 1);
                            break;
                        } else {
                            stack.remove(stack.size() - 1);
                            postfix.add(s);
                        }
                    }
                    break;
                case 2:
                case 3:
                    while (true) {
                        if (stack.size() == 0) {
                            break;
                        }
                        s = stack.get(stack.size() - 1);
                        if (check(s.getType()) == 0) {
                            break;
                        } else if (check(s.getType()) >= check(t.getType())) {
                            stack.remove(stack.size() - 1);
                            postfix.add(s);
                        } else {
                            break;
                        }
                    }
                    stack.add(t);
                    break;
                case 4:
                    postfix.add(t);
                    break;
            }
        }

        while (stack.size() != 0) {
            s = stack.remove(stack.size() - 1);
            postfix.add(s);
        }
    }

    public int check(char ch) {
        if (ch == '(') {
            return 0;
        } else if (ch == ')') {
            return 1;
        } else if (ch == '+' || ch == '-') {
            return 2;
        } else if (ch == '*' || ch == '/') {
            return 3;
        } else {
            return 4;
        }
    }

    public void evaluation() {
        Vector<Element> stack = new Vector<>();

        Element el;
        Operand n, m, ans;
        Operator op;

        for (Element element : postfix) {
            el = element;
            if (el.getType() == 'n') {
                stack.add(el);
            } else {
                n = (Operand) stack.remove(stack.size() - 1);
                m = (Operand) stack.remove(stack.size() - 1);
                op = (Operator) el;

                op.calculate(m.getValue(), n.getValue());
                ans = new Operand(op.getValue());

                stack.add(ans);
            }
        }

        el = stack.get(0);
        ans = (Operand) el;

        value = String.valueOf(ans.getValue());
    }
}
