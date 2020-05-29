import java.util.*;

public class Ten extends Calculator {
    String remain;

    public Ten(String str, String remain) {
        super(str);
        this.remain = remain;
    }

    public int checkError() {
        int i = 0, s, e, cnt = 0;
        char ch;
        char[] expr = getExpr();

        while (true) {
            ch = categorize(expr[i]);

            if (i == 0) {
                if (ch == 'o' || ch == 'n') {
                    ;
                } else if (ch == 's') {
                    i++;
                    while (true) {
                        ch = categorize(expr[i]);
                        if (ch == 's') {
                            ;
                        } else if (ch == 'o' || ch == 'n') {
                            i = 0;
                            ch = categorize(expr[i]);
                            break;
                        } else if (ch == 'e') {
                            if (remain.equals("none")) {
                                return 1;
                            } else {
                                putTokens(remain.toCharArray(), 0, remain.toCharArray().length - 1);
                                i = 0;
                                ch = categorize(expr[i]);
                                break;
                            }
                        } else {
                            return 1;
                        }
                        i++;
                    }
                } else if (ch == 'e') {
                    if (remain.equals("none")) {
                        return 1;
                    } else {
                        putTokens(remain.toCharArray(), 0, remain.toCharArray().length - 1);
                    }
                } else {
                    return 1;
                }
            }

            if (ch == 'n') {
                s = i;
                e = i;
                i++;
                while (true) {
                    ch = categorize(expr[i]);
                    if (ch == 't') {
                        putTokens(expr, s, e);
                        if (cnt == 0) {
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
                                    putTokens(expr, s, e);
                                    return 0;
                                } else if (ch == 'c' || ch == 'e' || ch == 't') {
                                    putTokens(expr, s, e);
                                    break;
                                } else if (ch == 's') {
                                    i++;
                                    while (true) {
                                        ch = categorize(expr[i]);
                                        if (ch == 's') {
                                            ;
                                        } else if (ch == 'n' || ch == 'o' || ch == 'r') {
                                            return 1;
                                        } else if (ch == 'c' || ch == 'e') {
                                            putTokens(expr, s, e);
                                            break;
                                        } else if (ch == 't') {
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
                            if (ch == 's') {
                                ;
                            } else if (ch == 'n' || ch == 'o' || ch == 'r' || ch == 'p') {
                                return 1;
                            } else if (ch == 'c' || ch == 'e') {
                                putTokens(expr, s, e);
                                break;
                            } else if (ch == 't') {
                                putTokens(expr, s, e);
                                return 0;
                            }
                            i++;
                        }
                        i = e;
                        break;
                    } else if (ch == 'n') {
                        e++;
                    } else if (ch == 'e' || ch == 'c') {
                        putTokens(expr, s, e);
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
                        putTokens(expr, s, e);
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
                        putTokens(expr, s, e);
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
                        putTokens(expr, s, e);
                        if (cnt == 0) {
                            return 0;
                        } else {
                            return 1;
                        }
                    } else if (ch == 'e' || ch == 'c') {
                        putTokens(expr, s, e);
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
        } else if (c >= 48 && c <= 57) {
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

    public void putTokens(char[] expr, int s, int e) {
        Vector<Element> tokens = getTokens();
        Element el = null;

        char ch = categorize(expr[s]);

        if (ch == 'o') {
            el = new Open();
        } else if (ch == 'c') {
            el = new Close();
        } else if (ch == 'n') {
            e++;
            char[] arr = new char[e - s];
            for (int i = s; i < e; i++) {
                arr[i - s] = expr[i];
            }

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
        setTokens(tokens);
    }

    public void printPostfix() {
        Vector<Element> tokens = getPostfix();

        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).getType() == 'n') {
                Operate op = (Operate) tokens.get(i);
                System.out.print(op.getValue() + " ");
            } else {
                System.out.print(tokens.get(i).getType() + " ");
            }
        }
        System.out.println();
    }

    public void makePostfix() {
        Vector<Element> tokens = getTokens();
        Vector<Element> postfix = new Vector<Element>();
        Vector<Element> stack = new Vector<Element>();
        Element t, s;

        for (int i = 0; i < tokens.size(); i++) {
            t = tokens.get(i);
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

        setPostfix(postfix);
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
        Vector<Element> postfix = getPostfix();
        Vector<Element> stack = new Vector<Element>();

        Element el;
        Operand n, m, ans;
        Operator op;

        for (int i = 0; i < postfix.size(); i++) {
            el = postfix.get(i);
            if (el.getType() == 'n') {
                stack.add(el);
            } else {
                n = (Operand) stack.remove(stack.size() - 1);
                m = (Operand) stack.remove(stack.size() - 1);
                op = (Operator) el;

                op.calculate(n.getValue(), m.getValue());
                ans = new Operand(op.getValue());

                stack.add(ans);
            }
        }

        el = stack.get(0);
        ans = (Operand) el;

        setValue(ans.getValue());
    }
}