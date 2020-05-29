package Calculator;

public class Decimal extends Calculator {
    public Decimal(String str, String remain) {
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
            } else if ((expr[i] >= 65 && expr[i] <= 70) || (expr[i] >= 97 && expr[i] <= 102)) {
                return 1;
            }
            i++;
        }

        return 0;
    }
}