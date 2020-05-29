import Calculator.*;
import java.util.*;

public class main {
    public static void main(String[] args) {
        String str, remain = "none";
        int mode = 10;
        Scanner sc = new Scanner(System.in);

        System.out.println("<< Decimal Calculator >>\n");

        while (true) {
            System.out.print("Input Expression >> ");

            str = sc.nextLine();
            if (str.equals("e")) {
                System.out.println("\nEnd Calculation");
                break;
            } else if (str.equals("c")) {
                while (true) {
                    System.out.print("\nEnter Number What You Want to Calculate(2: Binary, 8: Octal, 10: Decimal, 16: Hexadecimal) >> ");
                    int k = sc.nextInt();
                    if (k == 2 || k == 8 || k == 10 || k == 16) {
                        mode = k;
                        System.out.println("\nChange Complete");
                        break;
                    } else {
                        System.out.println("Wrong number");
                    }
                }
                if (mode == 10) {
                    System.out.println("\n<< Decimal Calculator >>\n");
                } else if (mode == 2) {
                    System.out.println("\n<< Binary Calculator >>\n");
                } else if (mode == 16) {
                    System.out.println("\n<< Hexadecimal Calculator >>\n");
                } else {
                    System.out.println("\n<< Octal Calculator >>\n");
                }
                remain = "none";
                sc.nextLine();
                continue;
            }

            if (mode == 10) {
                Decimal c = new Decimal(str, remain);
                if (c.checkError() == 0) {
                    c.makePostfix();
                    c.evaluation();
                    remain = c.getValue();
                    System.out.println("answer >> " + c.getValue());
                } else {
                    System.out.println("error");
                    remain = "none";
                }
            } else if (mode == 2) {
                Binary c = new Binary(str, remain);
                if (c.checkError() == 0) {
                    c.makePostfix();
                    c.evaluation();
                    remain = c.getValue();
                    System.out.println("answer >> " + c.getValue());
                } else {
                    System.out.println("error");
                    remain = "none";
                }
            } else if (mode == 16) {
                Hexadecimal c = new Hexadecimal(str, remain);
                if (c.checkError() == 0) {
                    c.makePostfix();
                    c.evaluation();
                    remain = c.getValue();
                    System.out.println("answer >> " + c.getValue());
                } else {
                    System.out.println("error");
                    remain = "none";
                }
            } else {
                Octal c = new Octal(str, remain);
                if (c.checkError() == 0) {
                    c.makePostfix();
                    c.evaluation();
                    remain = c.getValue();
                    System.out.println("answer >> " + c.getValue());
                } else {
                    System.out.println("error");
                    remain = "none";
                }
            }
        }
    }
}
