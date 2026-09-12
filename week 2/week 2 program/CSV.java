import java.util.Scanner;

public class CSV {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter CSV line: ");
        String csv = sc.nextLine();

        String[] fields = csv.split(",");

        for (String field : fields) {
            System.out.println(field);
        }
    }
}