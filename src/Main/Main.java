package Main;

import Calculator.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void printMode(int mode) {
        if (mode == 10) {
            System.out.println("\n<< Decimal Calculator >>\n");
        } else if (mode == 2) {
            System.out.println("\n<< Binary Calculator >>\n");
        } else if (mode == 16) {
            System.out.println("\n<< Hexadecimal Calculator >>\n");
        } else {
            System.out.println("\n<< Octal Calculator >>\n");
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String expr, remain = "none";
        int mode = 10, num = 0;

        printMode(mode);

        while (true) {
            System.out.print("\nInput Expression >> ");

            expr = sc.nextLine();
            if (expr.length() == 0) {
                continue;
            }

            if (expr.equals("e")) {
                System.out.println("End Calculation");
                System.exit(0);
            } else if (expr.equals("c")) {
                while (true) {
                    System.out.print("Enter Number What You Want to Calculate(2: Binary, 8: Octal, 10: Decimal, 16: Hexadecimal) >> ");
                    try {
                        num = sc.nextInt();
                        if (num == 2 || num == 8 || num == 10 || num == 16) {
                            mode = num;
                            System.out.println("\nChange Complete");
                            break;
                        } else {
                            System.out.println("Wrong number");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Input number");
                    }
                    sc.nextLine();
                }

                printMode(mode);

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
                        try {
                            num = Integer.parseInt(arr[1]);
                            if (num > 50) {
                                System.out.println("That number doesn't exist in record");
                            } else {
                                num = Record.loadRecord(num);
                                if (num == 1) {
                                    System.out.println("That number doesn't exist in record");
                                } else if (num == 2) {
                                    System.out.println("File not exist");
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Input Number");
                        }
                    }
                }
                continue;
            } else if (expr.charAt(0) == 'd') {
                String[] arr = expr.split(" ");
                if (arr.length != 2) {
                    System.out.println("Input Number");
                } else {
                    try {
                        num = Integer.parseInt(arr[1]);
                        if (num > 50) {
                            System.out.println("That number doesn't exist in record");
                        } else {
                            num = Record.deleteRecord(num);
                            if (num == 1) {
                                System.out.println("That number doesn't exist in record");
                            } else if (num == 2) {
                                System.out.println("File not exist");
                            } else {
                                System.out.println("Delete Complete");
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input Number");
                    }
                }
                continue;
            } else if (expr.charAt(0) == 'h' && expr.length() == 1) {
                System.out.println("This is a calculator using the postfix calculation method.");
                System.out.println("There are four types of calculations available: Binary, Octal, Decimal, and Hexadecimal.");
                System.out.println("And also, the last 30 expressions recorded in 'record.txt' file.");

                System.out.println("\nCommand");
                System.out.println("c: Change mode(2: Binary, 8: Octal, 10: Decimal, 16: Hexadecimal)");
                System.out.println("r: Output all expressions");
                System.out.println("r (number): Output the corresponding number expression only");
                System.out.println("d (number): Delete the corresponding number expression from record");
                System.out.println("h: Output information about this Calculator");
                System.out.println("e: Exit Program");
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
