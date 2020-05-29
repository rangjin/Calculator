package Calculator;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String expr, remain = "none", str;
        int mode = 10;
        Scanner sc = new Scanner(System.in);
        File record = new File(".\\record.txt");
        File save = new File(".\\save.txt");

        System.out.println("<< Decimal Calculator >>\n");

        while (true) {
            System.out.print("Input Expression >> ");

            expr = sc.nextLine();
            if (expr.equals("e")) {
                System.out.println("\nEnd Calculation");
                break;
            } else if (expr.equals("c")) {
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
            } else if (expr.equals("r")) {
                try {
                    FileReader fin = new FileReader(record);
                    BufferedReader br = new BufferedReader(fin);
                    int num;
                    String expression, result;
                    while (true) {
                        num = Integer.parseInt(br.readLine());
                        if (num == 0) {
                            break;
                        } else {
                            expression = br.readLine();
                            result = br.readLine();
                            System.out.println(num + ": " + expression + "= " + result);
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("File not exist");
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

                try {
                    FileReader fin = new FileReader(record);
                    BufferedReader br = new BufferedReader(fin);
                    FileWriter fout;
                    BufferedWriter bw;

                    int num, last = 0;

                    while (true) {
                        num = Integer.parseInt(br.readLine());
                        if (num == 0) {
                            break;
                        } else {
                            br.readLine();
                            br.readLine();
                            last = num;
                        }
                    }

                    fin = new FileReader(record);
                    br = new BufferedReader(fin);
                    fout = new FileWriter(save);
                    bw = new BufferedWriter(fout);

                    if (last < 30) {
                        while (true) {
                            num = Integer.parseInt(br.readLine());
                            if (num == 0) {
                                break;
                            } else {
                                bw.write(num + "\n");
                                bw.flush();
                                bw.write(br.readLine() + "\n");
                                bw.flush();
                                bw.write(br.readLine() + "\n");
                                bw.flush();
                            }
                        }
                        bw.write((last + 1) + "\n" + expression + "\n" + remain + "\n");
                    } else {
                        br.readLine();
                        br.readLine();
                        br.readLine();
                        for (int i = 0; i < 29; i++) {
                            bw.write((Integer.parseInt(br.readLine()) - 1) + "\n");
                            bw.flush();
                            bw.write(br.readLine() + "\n");
                            bw.flush();
                            bw.write(br.readLine() + "\n");
                            bw.flush();
                        }
                        bw.write(30 + "\n" + expression + "\n" + remain + "\n");

                    }

                    bw.flush();
                    bw.close();

                    record.delete();

                    fout = new FileWriter(record);
                    bw = new BufferedWriter(fout);
                    fin = new FileReader(save);
                    br = new BufferedReader(fin);

                    while ((str = br.readLine()) != null) {
                        bw.write(str + "\n");
                        bw.flush();
                    }

                    bw.write("0");
                    bw.flush();

                    fout.close();
                    fin.close();
                    bw.close();
                    br.close();

                    save.delete();
                } catch (FileNotFoundException e) {
                    FileWriter fout = new FileWriter(record);
                    BufferedWriter bw = new BufferedWriter(fout);
                    bw.write(1 + "\n" + expression + "\n" + remain + "\n");
                    bw.flush();
                    bw.write("0");
                    bw.flush();
                    bw.close();
                }
            } else {
                System.out.println("error");
                remain = "none";
            }
        }
    }
}
