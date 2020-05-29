package Main;

import Calculator.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String expr, remain = "none";
        int mode = 10, num;
        Scanner sc = new Scanner(System.in);

        System.out.println("<< Decimal Calculator >>\n");

        while (true) {
            System.out.print("Input Expression >> ");

            expr = sc.nextLine();
            if (expr.equals("e")) {
                System.out.println("\nEnd Calculation");
                System.exit(0);
            } else if (expr.equals("c")) {
                while (true) {
                    System.out.print("\nEnter Number What You Want to Calculate(2: Binary, 8: Octal, 10: Decimal, 16: Hexadecimal) >> ");
                    num = sc.nextInt();
                    if (num == 2 || num == 8 || num == 10 || num == 16) {
                        mode = num;
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
            } else if (expr.charAt(0) == 'r') {
                String[] arr = expr.split(" ");
                if (arr.length == 1 && arr[0].equals("r")) {
                    Record.loadRecord();
                } else {
                    if (arr.length != 2 || !arr[0].equals("r")) {
                        System.out.println("error");
                    } else {
                        num = Integer.parseInt(arr[1]);
                        if (num > 50) {
                            System.out.println("That number doesn't exit in record");
                        } else {
                            if (Record.loadRecord(num) == 1) {
                                System.out.println("That number doesn't exit in record");
                            }
                        }
                    }
                }
                continue;
            }

            Calculator c;

            if (mode == 10) {
                c = new Decimal(expr, remain);
            } else if (mode == 2) {
                c = new Binary(expr, remain);
            } else if (mode == 16) {
                c = new Hexadecimal(expr, remain);
            } else {
                c = new Octal(expr, remain);
            }

            if (c.checkError() == 0) {
                c.makePostfix();
                c.evaluation();
                remain = c.getValue();
                System.out.println("answer >> " + c.getValue());

                String expression = c.getExpression();

                Record.saveRecord(expression, remain, mode);
            } else {
                System.out.println("error");
                remain = "none";
            }
        }
    }
}
