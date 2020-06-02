package Main;

import java.io.*;

public class Record {
    private static final File record = new File(".\\src\\Main\\record.txt");
    private static final File save = new File(".\\src\\Main\\save.txt");

    public static void saveRecord(String expression, String remain, int mode) throws IOException {
        int num;
        String str;

        try {
            FileReader fin = new FileReader(record);
            BufferedReader br = new BufferedReader(fin);
            FileWriter fout;
            BufferedWriter bw;

            int last = 0;

            while (true) {
                num = Integer.parseInt(br.readLine());
                if (num == 0) {
                    break;
                } else {
                    br.readLine();
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
                        bw.write(br.readLine() + "\n");
                        bw.flush();
                    }
                }
                bw.write((last + 1) + "\n" + mode + "\n" + expression + "\n" + remain + "\n");
            } else {
                for (int i = 0; i < 4; i++) {
                    br.readLine();
                }

                for (int i = 0; i < 29; i++) {
                    bw.write((Integer.parseInt(br.readLine()) - 1) + "\n");
                    bw.flush();
                    bw.write(br.readLine() + "\n");
                    bw.flush();
                    bw.write(br.readLine() + "\n");
                    bw.flush();
                    bw.write(br.readLine() + "\n");
                    bw.flush();
                }
                bw.write(30 + "\n" + mode + "\n" + expression + "\n" + remain + "\n");
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
            bw.write(1 + "\n" + mode + "\n" + expression + "\n" + remain + "\n");
            bw.flush();
            bw.write("0");
            bw.flush();
            bw.close();
        }
    }

    public static void loadRecord() throws IOException {
        int num, mode;

        try {
            FileReader fin = new FileReader(record);
            BufferedReader br = new BufferedReader(fin);
            while (true) {
                num = Integer.parseInt(br.readLine());
                if (num == 0) {
                    break;
                } else {
                    mode = Integer.parseInt(br.readLine());
                    System.out.println(num + ": " + br.readLine() + "= " + br.readLine() + " (" + version(mode) + ")");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not exist");
        }
    }

    public static int loadRecord(int n) throws IOException {
        int num, mode;

        try {
            FileReader fin = new FileReader(record);
            BufferedReader br = new BufferedReader(fin);
            while (true) {
                num = Integer.parseInt(br.readLine());
                if (num == 0) {
                    return 1;
                } else {
                    if (num == n) {
                        mode = Integer.parseInt(br.readLine());
                        System.out.println(br.readLine() + "= " + br.readLine() + " (" + version(mode) + ")");
                        fin.close();
                        br.close();
                        return 0;
                    } else {
                        br.readLine();
                        br.readLine();
                        br.readLine();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            return 2;
        }
    }

    public static String version(int mode) {
        if (mode == 10) {
            return "Decimal";
        } else if (mode == 2) {
            return "Binary";
        } else if (mode == 16) {
            return "Hexadecimal";
        } else {
            return "Octal";
        }
    }

    public static int deleteRecord(int n) throws IOException {
        int num;
        String str;

        try {
            FileReader fin = new FileReader(record);
            BufferedReader br = new BufferedReader(fin);
            FileWriter fout = new FileWriter(save);
            BufferedWriter bw = new BufferedWriter(fout);
            while (true) {
                num = Integer.parseInt(br.readLine());
                if (num == 0) {
                    save.delete();
                    fin.close();
                    br.close();
                    fout.close();
                    bw.close();
                    return 1;
                } else {
                    if (num == n) {
                        br.readLine();
                        br.readLine();
                        br.readLine();

                        while(true) {
                            num = Integer.parseInt(br.readLine());
                            if (num == 0) {
                                bw.write(num + "\n");
                                bw.flush();
                                break;
                            }
                            bw.write((num - 1) + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n");
                            bw.flush();
                        }

                        fout.close();
                        fin.close();
                        bw.close();
                        br.close();

                        record.delete();

                        fin = new FileReader(save);
                        br = new BufferedReader(fin);
                        fout = new FileWriter(record);
                        bw = new BufferedWriter(fout);

                        while ((str = br.readLine()) != null) {
                            bw.write(str + "\n");
                            bw.flush();
                        }

                        fout.close();
                        fin.close();
                        bw.close();
                        br.close();

                        save.delete();

                        return 0;
                    } else {
                        bw.write(num + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n");
                        bw.flush();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            return 2;
        }
    }
}
