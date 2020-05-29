import java.util.*;

public class main {
    public static void main(String[] args) {
        String str;
        Scanner sc = new Scanner(System.in);
        String remain = "none";

        while (true) {
            str = sc.nextLine();
            Ten c = new Ten(str, remain);
            if (c.checkError() == 0) {
                c.makePostfix();
                c.evaluation();
                c.printPostfix();
                remain = Double.toString(c.getValue());
                System.out.println(c.getValue());
            } else {
                System.out.println("error");
                remain = "none";
            }
        }
    }
}
