import java.util.Scanner;

public class caesar {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter word: ");
        String word = sc.nextLine();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {

            char ch = word.charAt(i);

            if (ch >= 'a' && ch <= 'z') {

                ch = (char)(ch + 1);

                if (ch > 'z')
                    ch = 'a';
            }

            else if (ch >= 'A' && ch <= 'Z') {

                ch = (char)(ch + 1);

                if (ch > 'Z')
                    ch = 'A';
            }

            result.append(ch);
        }

        System.out.println("Encrypted: " + result);
    }
}